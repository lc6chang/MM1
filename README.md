# 使用Java进行M/M/1队列模型仿真

## 输入参数

+ 到达率（人/秒）
+ 服务率（人/秒）
+ 顾客数目（人）
+ 队列最大长度（人）

## 输出数据

+ 到达率（人/秒）

  到达率=到达顾客总数/最后一位顾客到达时间

+ 服务率 （人/秒）

  服务率=服务顾客总数/服务总时间

+ 队列平均顾客数（人）

  平均顾客数=顾客总延迟时间/当前时间

+ 平均等待时间（秒）

  平均等待时间=顾客总延迟时间/顾客总数

+ 服务器利用率

  服务器利用率=服务器服务时间/当前时间

+ 关键节点信息

  关键节点信息输出至./log.txt

## 服务及排队策略

当顾客到达时，如果服务器空闲，则立即进行服务；如果服务器被占用且等待队列未到达队列最大长度，则进入等待队列等待；否则顾客立即离开。
