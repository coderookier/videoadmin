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

    final static Logger log = LoggerFactory.getLogger(ZKCurator.class);

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
                System.out.println("初始化成功");
                System.out.println("zookeeper服务器状态" + client.isStarted());
            }
        } catch (Exception e) {
            log.error("zookeeper客户端连接初始化错误");
            e.printStackTrace();
        }
    }
}
