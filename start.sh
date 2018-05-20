#!/bin/sh
user=root
if jps |grep -q QuorumPeerMain;then
for zk in host101 host102 host103
do
ssh $user@$zk "source /etc/profile;/app/zookeeper/bin/zkServer.sh stop"
done
else
for zk in host101 host102 host103
do
ssh $user@$zk "source /etc/profile;/app/zookeeper/bin/zkServer.sh start"
done
fi
if jps |grep -q DataNode;then
/app/hadoop/sbin/stop-dfs.sh
else
/app/hadoop/sbin/start-dfs.sh
fi
