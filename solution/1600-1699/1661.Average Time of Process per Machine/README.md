# [1661. 每台机器的进程平均运行时间](https://leetcode.cn/problems/average-time-of-process-per-machine)

[English Version](/solution/1600-1699/1661.Average%20Time%20of%20Process%20per%20Machine/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Activity</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| machine_id     | int     |
| process_id     | int     |
| activity_type  | enum    |
| timestamp      | float   |
+----------------+---------+
该表展示了一家工厂网站的用户活动.
(machine_id, process_id, activity_type) 是当前表的主键.
machine_id 是一台机器的ID号.
process_id 是运行在各机器上的进程ID号.
activity_type 是枚举类型 ('start', 'end').
timestamp 是浮点类型,代表当前时间(以秒为单位).
'start' 代表该进程在这台机器上的开始运行时间戳 , 'end' 代表该进程在这台机器上的终止运行时间戳.
同一台机器，同一个进程都有一对开始时间戳和结束时间戳，而且开始时间戳永远在结束时间戳前面.</pre>

<p>&nbsp;</p>

<p>现在有一个工厂网站由几台机器运行，每台机器上运行着相同数量的进程. 请写出一条SQL计算每台机器各自完成一个进程任务的平均耗时.</p>

<p>完成一个进程任务的时间指进程的<code>'end' 时间戳</code> 减去&nbsp;<code>'start' 时间戳</code>. 平均耗时通过计算每台机器上所有进程任务的总耗费时间除以机器上的总进程数量获得.</p>

<p>结果表必须包含<code>machine_id（机器ID）</code> 和对应的&nbsp;<strong>average time（平均耗时）</strong>&nbsp;别名&nbsp;<code>processing_time</code>, 且<strong>四舍五入保留3位小数.</strong></p>

<p>以 <strong>任意顺序</strong> 返回表。</p>

<p>具体参考例子如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Activity table:
+------------+------------+---------------+-----------+
| machine_id | process_id | activity_type | timestamp |
+------------+------------+---------------+-----------+
| 0          | 0          | start         | 0.712     |
| 0          | 0          | end           | 1.520     |
| 0          | 1          | start         | 3.140     |
| 0          | 1          | end           | 4.120     |
| 1          | 0          | start         | 0.550     |
| 1          | 0          | end           | 1.550     |
| 1          | 1          | start         | 0.430     |
| 1          | 1          | end           | 1.420     |
| 2          | 0          | start         | 4.100     |
| 2          | 0          | end           | 4.512     |
| 2          | 1          | start         | 2.500     |
| 2          | 1          | end           | 5.000     |
+------------+------------+---------------+-----------+
<strong>输出：</strong>
+------------+-----------------+
| machine_id | processing_time |
+------------+-----------------+
| 0          | 0.894           |
| 1          | 0.995           |
| 2          | 1.456           |
+------------+-----------------+
<strong>解释：</strong>
一共有3台机器,每台机器运行着两个进程.
机器 0 的平均耗时: ((1.520 - 0.712) + (4.120 - 3.140)) / 2 = 0.894
机器 1 的平均耗时: ((1.550 - 0.550) + (1.420 - 0.430)) / 2 = 0.995
机器 2 的平均耗时: ((4.512 - 4.100) + (5.000 - 2.500)) / 2 = 1.456</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
