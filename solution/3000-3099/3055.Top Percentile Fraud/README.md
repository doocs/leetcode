# [3055. 最高欺诈百分位数](https://leetcode.cn/problems/top-percentile-fraud)

[English Version](/solution/3000-3099/3055.Top%20Percentile%20Fraud/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Fraud</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| policy_id   | int     |
| state       | varchar |
| fraud_score | int     |
+-------------+---------+
policy_id 是这张表中具有不同值的列。
这张表包含 policy id，state 和 fraud score。
</pre>

<p>Leetcode 保险公司开发了一个 ML 驱动的 <strong>预测模型</strong> 来检测欺诈索赔的 <strong>可能性</strong>。因此，他们分配了经验最丰富的理赔员来处理前 <code>5%</code> <strong>被标记</strong> 的索赔。</p>

<p>编写一个解决方案来找出 <strong>每个州</strong> 索赔的前 <code>5</code> <strong>百分位数</strong>。</p>

<p>返回结果表，以&nbsp;<code>state</code>&nbsp;<strong>升序&nbsp;</strong>排序，<code>fraud_score</code>&nbsp;<strong>降序</strong> 排序，<code>policy_id</code>&nbsp;<strong>升序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Fraud 表：
+-----------+------------+-------------+
| policy_id | state      | fraud_score | 
+-----------+------------+-------------+
| 1         | California | 0.92        | 
| 2         | California | 0.68        |   
| 3         | California | 0.17        | 
| 4         | New York   | 0.94        | 
| 5         | New York   | 0.81        | 
| 6         | New York   | 0.77        |  
| 7         | Texas      | 0.98        |  
| 8         | Texas      | 0.97        | 
| 9         | Texas      | 0.96        | 
| 10        | Florida    | 0.97        |  
| 11        | Florida    | 0.98        | 
| 12        | Florida    | 0.78        | 
| 13        | Florida    | 0.88        | 
| 14        | Florida    | 0.66        | 
+-----------+------------+-------------+
<strong>输出：</strong> 
+-----------+------------+-------------+
| policy_id | state      | fraud_score |
+-----------+------------+-------------+
| 1         | California | 0.92        | 
| 11        | Florida    | 0.98        | 
| 4         | New York   | 0.94        | 
| 7         | Texas      | 0.98        |  
+-----------+------------+-------------+
<strong>解释：</strong>
- 对于 California 州，只有 ID 为 1 的保单的欺诈分数为 0.92，属于该州的前 5%。
- 对于 Florida 州，只有 ID 为 11 的保单的欺诈分数为 0.98，属于该州的前 5%。
- 对于 New York 州，只有 ID 为 4 的保单的欺诈分数为 0.94，属于该州的前 5%。
- 对于 Texas 州，只有 ID 为 7 的保单的欺诈分数为 0.98，属于该州的前 5%。
输出表以&nbsp;<code>state</code>&nbsp;升序排序，<code>fraud_score</code>&nbsp;降序排序，<code>policy_id</code>&nbsp;升序排序。
</pre>

## 解法

### 方法一：使用窗口函数

我们可以使用 `RANK()` 窗口函数来计算每个州的欺诈分数的排名，然后筛选出排名为 1 的记录，并且按照题目要求排序。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY state
                ORDER BY fraud_score DESC
            ) AS rk
        FROM Fraud
    )
SELECT policy_id, state, fraud_score
FROM T
WHERE rk = 1
ORDER BY 2, 3 DESC, 1;
```

<!-- tabs:end -->

<!-- end -->
