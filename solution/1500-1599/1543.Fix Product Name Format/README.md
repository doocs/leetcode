# [1543. 产品名称格式修复](https://leetcode-cn.com/problems/fix-product-name-format)

[English Version](/solution/1500-1599/1543.Fix%20Product%20Name%20Format/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Sales</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| sale_id      | int     |
| product_name | varchar |
| sale_date    | date    |
+--------------+---------+
sale_id 是该表主键
该表的每一行包含了产品的名称及其销售日期
</pre>

<p>因为在 2000 年该表是手工填写的，<code>product_name</code> 可能包含前后空格，而且包含大小写。</p>

<p>写一个 SQL 语句报告每个月的销售情况：</p>

<ul>
	<li><code>product_name</code> 是小写字母且不包含前后空格</li>
	<li><code>sale_date</code> 格式为 <code>('YYYY-MM')</code> </li>
	<li><code>total</code> 是产品在本月销售的次数</li>
</ul>

<p>返回结果以 <code>product_name</code> <strong>升序</strong> 排列，如果有排名相同，再以 <code>sale_date</code> <strong>升序 </strong>排列。</p>

<p>查询结果格式如下所示：</p>

<pre>
<code>Sales</code> 表：
+------------+------------------+--------------+
| sale_id    | product_name     | sale_date    |
+------------+------------------+--------------+
| 1          |      LCPHONE     | 2000-01-16   |
| 2          |    LCPhone       | 2000-01-17   |
| 3          |     LcPhOnE      | 2000-02-18   |
| 4          |      LCKeyCHAiN  | 2000-02-19   |
| 5          |   LCKeyChain     | 2000-02-28   |
| 6          | Matryoshka       | 2000-03-31   | 
+------------+------------------+--------------+

Result 表：
+--------------+--------------+----------+
| product_name | sale_date    | total    |
+--------------+--------------+----------+
| lcphone      | 2000-01      | 2        |
| lckeychain   | 2000-02      | 2        | 
| lcphone      | 2000-02      | 1        | 
| matryoshka   | 2000-03      | 1        | 
+--------------+--------------+----------+

1 月份，卖了 2 个 LcPhones，请注意产品名称是小写的，中间可能包含空格
2 月份，卖了 2 个 LCKeychains 和 1 个 LCPhone
3 月份，卖了 1 个 matryoshka
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
