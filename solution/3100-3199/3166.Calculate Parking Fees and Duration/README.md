---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3166.Calculate%20Parking%20Fees%20and%20Duration/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3166. 计算停车费与时长 🔒](https://leetcode.cn/problems/calculate-parking-fees-and-duration)

[English Version](/solution/3100-3199/3166.Calculate%20Parking%20Fees%20and%20Duration/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>ParkingTransactions</code></p>

<pre>
+--------------+-----------+
| Column Name  | Type      |
+--------------+-----------+
| lot_id       | int       |
| car_id       | int       |
| entry_time   | datetime  |
| exit_time    | datetime  |
| fee_paid     | decimal   |
+--------------+-----------+
(lot_id, car_id, entry_time) 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含停车场的 ID，车的 ID，入场和出场时间，以及停车时长的支付费用。
</pre>

<p>编写一个解决方案来找到 <strong>所有停车场</strong>&nbsp;中每辆车支付的 <strong>总停车费</strong>，以及 <strong>每</strong> 辆车支付的&nbsp;<strong>每小时平均费用</strong>（舍入到&nbsp;<code>2</code> 位小数）。同时，找到每辆车 <strong>总花费时间</strong> 最多的 <strong>停车场</strong>。</p>

<p>返回结果表以<em>&nbsp;</em><code>car_id</code><em><b>&nbsp;升序 </b>排序。</em></p>

<p><strong>注意：</strong>测试用例的生成方式使得单辆汽车不能同时位于多个停车场。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>ParkingTransactions 表：</p>

<pre class="example-io">
+--------+--------+---------------------+---------------------+----------+
| lot_id | car_id | entry_time          | exit_time           | fee_paid |
+--------+--------+---------------------+---------------------+----------+
| 1      | 1001   | 2023-06-01 08:00:00 | 2023-06-01 10:30:00 | 5.00     |
| 1      | 1001   | 2023-06-02 11:00:00 | 2023-06-02 12:45:00 | 3.00     |
| 2      | 1001   | 2023-06-01 10:45:00 | 2023-06-01 12:00:00 | 6.00     |
| 2      | 1002   | 2023-06-01 09:00:00 | 2023-06-01 11:30:00 | 4.00     |
| 3      | 1001   | 2023-06-03 07:00:00 | 2023-06-03 09:00:00 | 4.00     |
| 3      | 1002   | 2023-06-02 12:00:00 | 2023-06-02 14:00:00 | 2.00     |
+--------+--------+---------------------+---------------------+----------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+--------+----------------+----------------+---------------+
| car_id | total_fee_paid | avg_hourly_fee | most_time_lot |
+--------+----------------+----------------+---------------+
| 1001   | 18.00          | 2.40           | 1             |
| 1002   | 6.00           | 1.33           | 2             |
+--------+----------------+----------------+---------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于汽车 ID 1001：
	<ul>
		<li>从 2023-06-01 08:00:00 到 2023-06-01 10:30:00 在停车场&nbsp;1：2.5 小时，费用&nbsp;5.00</li>
		<li>从 2023-06-02 11:00:00 到 2023-06-02 12:45:00 在停车场 1：1.75 小时，费用 3.00</li>
		<li>从 2023-06-01 10:45:00 到 2023-06-01 12:00:00 在停车场 2：1.25 小时，费用 6.00</li>
		<li>从 2023-06-03 07:00:00 到 2023-06-03 09:00:00 在停车场 3：2 小时，费用 4.00</li>
	</ul>
	总共支付费用：18.00，总小时：7.5，每小时平均费用：2.40，停车场 1 总花费时间最长：4.25&nbsp;小时。</li>
	<li>对于汽车 ID 1002：
	<ul>
		<li>从 2023-06-01 09:00:00 到 2023-06-01 11:30:00 在停车场 2：2.5 小时，费用 4.00</li>
		<li>从 2023-06-02 12:00:00 到 2023-06-02 14:00:00 在停车场 3：2 小时，费用 2.00</li>
	</ul>
	总共支付费用：6.00，总小时：4.5，每小时平均费用：1.33，停车场 2 总花费时间最长：2.5 小时。</li>
</ul>

<p><b>注意：</b>&nbsp;输出表以 car_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组 + 连接

我们可以先按照 `car_id` 和 `lot_id` 进行分组，计算每辆车在每个停车场的停车时长，然后利用 `RANK()` 函数对每辆车在每个停车场的停车时长进行排名，找到每辆车在停车时长最长的停车场。

最后，我们可以根据 `car_id` 进行分组，计算每辆车的总停车费、每小时平均费用和停车时长最长的停车场。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            car_id,
            lot_id,
            SUM(TIMESTAMPDIFF(SECOND, entry_time, exit_time)) AS duration
        FROM ParkingTransactions
        GROUP BY 1, 2
    ),
    P AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY car_id
                ORDER BY duration DESC
            ) AS rk
        FROM T
    )
SELECT
    t1.car_id,
    SUM(fee_paid) AS total_fee_paid,
    ROUND(
        SUM(fee_paid) / (SUM(TIMESTAMPDIFF(SECOND, entry_time, exit_time)) / 3600),
        2
    ) AS avg_hourly_fee,
    t2.lot_id AS most_time_lot
FROM
    ParkingTransactions AS t1
    LEFT JOIN P AS t2 ON t1.car_id = t2.car_id AND t2.rk = 1
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
