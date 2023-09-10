# [1174. 即时食物配送 II](https://leetcode.cn/problems/immediate-food-delivery-ii)

[English Version](/solution/1100-1199/1174.Immediate%20Food%20Delivery%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>配送表: <code>Delivery</code></p>

<pre>
+-----------------------------+---------+
| Column Name                 | Type    |
+-----------------------------+---------+
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |
+-----------------------------+---------+
delivery_id 是该表中具有唯一值的列。
该表保存着顾客的食物配送信息，顾客在某个日期下了订单，并指定了一个期望的配送日期（和下单日期相同或者在那之后）。
</pre>

<p>&nbsp;</p>

<p>如果顾客期望的配送日期和下单日期相同，则该订单称为 「<strong>即时订单</strong>」，否则称为「<strong>计划订单</strong>」。</p>

<p>「<strong>首次订单</strong>」是顾客最早创建的订单。我们保证一个顾客只会有一个「首次订单」。</p>

<p>编写解决方案以获取即时订单在所有用户的首次订单中的比例。<strong>保留两位小数。</strong></p>

<p>结果示例如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Delivery 表：
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 2           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-12                  |
| 4           | 3           | 2019-08-24 | 2019-08-24                  |
| 5           | 3           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
| 7           | 4           | 2019-08-09 | 2019-08-09                  |
+-------------+-------------+------------+-----------------------------+
<strong>输出：</strong>
+----------------------+
| immediate_percentage |
+----------------------+
| 50.00                |
+----------------------+
<strong>解释：</strong>
1 号顾客的 1 号订单是首次订单，并且是计划订单。
2 号顾客的 2 号订单是首次订单，并且是即时订单。
3 号顾客的 5 号订单是首次订单，并且是计划订单。
4 号顾客的 7 号订单是首次订单，并且是即时订单。
因此，一半顾客的首次订单是即时的。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
select
    round(
        sum(
            if(
                t1.order_date = t1.customer_pref_delivery_date,
                1,
                0
            )
        ) / count(1) * 100,
        2
    ) as immediate_percentage
from
    Delivery t1
    right join (
        select
            customer_id,
            min(order_date) as order_date
        from
            Delivery
        group by
            customer_id
    ) t2 on t1.customer_id = t2.customer_id
    and t1.order_date = t2.order_date
```

<!-- tabs:end -->
