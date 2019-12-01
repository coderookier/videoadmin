package org.video.admin.common.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gutongxue
 * @date 2019/12/1 9:54
 * 操作zookeeper的工具类
 **/
public class ZKCurator {
    //zk客户端
    private CuratorFramework client = null;

    public ZKCurator(CuratorFramework client) {
        this.client = client;
    }

    final static Logger logger = LoggerFactory.getLogger(ZKCurator.class);

    public void init() {
        //判断在admin命名空间下是否有bgm节点 /admin/bgm
        client = client.usingNamespace("admin");
        try {
            /**
             * 对于zk来讲，有两种类型节点：
             * 持久节点：创建一个节点之后，这个节点永久存在，除非手动删除
             * 临时节点：创建一个节点之后，会话断开，会自动删除，同样可以手动删除
             */
            if (client.checkExists().forPath("/bgm") == null) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT) //节点类型：持久节点
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) //acl: 匿名权限
                        .forPath("/bgm");
                logger.info("初始化成功");
                logger.info("zookeeper服务器已经开启：{}", client.isStarted());
            }
        } catch (Exception e) {
            logger.error("zookeeper客户端连接初始化错误");
            e.printStackTrace();
        }
    }

    /**
     * 增加或者删除bgm，向zk-server创建子节点，供小程序后端监听
     * @param bgmId 背景音乐唯一主键ID
     * @param operObj 操作类型，添加或者删除bgm
     */
    public void sendBgmOperator(String bgmId, String operObj) {
        try {

            client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)		// 节点类型：持久节点
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)			// acl：匿名权限
                    .forPath("/bgm/" + bgmId, operObj.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
