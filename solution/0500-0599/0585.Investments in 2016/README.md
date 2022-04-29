# [585. 2016 年的投资](https://leetcode.cn/problems/investments-in-2016)

[English Version](/solution/0500-0599/0585.Investments%20in%202016/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>写一个查询语句，将&nbsp;2016 年 (<strong>TIV_2016</strong>) 所有成功投资的金额加起来，保留 2 位小数。</p>

<p>对于一个投保人，他在 2016 年成功投资的条件是：</p>

<ol>
	<li>他在 2015 年的投保额&nbsp;(<strong>TIV_2015</strong>) 至少跟一个其他投保人在 2015 年的投保额相同。</li>
	<li>他所在的城市必须与其他投保人都不同（也就是说维度和经度不能跟其他任何一个投保人完全相同）。</li>
</ol>

<p><strong>输入格式:</strong><br>
表&nbsp;<strong><em>insurance</em></strong> 格式如下：</p>

<pre>| Column Name | Type          |
|-------------|---------------|
| PID         | INTEGER(11)   |
| TIV_2015    | NUMERIC(15,2) |
| TIV_2016    | NUMERIC(15,2) |
| LAT         | NUMERIC(5,2)  |
| LON         | NUMERIC(5,2)  |
</pre>

<p><strong>PID</strong>&nbsp;字段是投保人的投保编号，&nbsp;<strong>TIV_2015</strong> 是该投保人在2015年的总投保金额，&nbsp;<strong>TIV_2016</strong> 是该投保人在2016年的投保金额，&nbsp;<strong>LAT</strong> 是投保人所在城市的维度，&nbsp;<strong>LON</strong>&nbsp;是投保人所在城市的经度。</p>

<p><strong>样例输入</strong></p>

<pre>| PID | TIV_2015 | TIV_2016 | LAT | LON |
|-----|----------|----------|-----|-----|
| 1   | 10       | 5        | 10  | 10  |
| 2   | 20       | 20       | 20  | 20  |
| 3   | 10       | 30       | 20  | 20  |
| 4   | 10       | 40       | 40  | 40  |
</pre>

<p><strong>样例输出</strong></p>

<pre>| TIV_2016 |
|----------|
| 45.00    |
</pre>

<p><strong>解释</strong></p>

<pre>就如最后一个投保人，第一个投保人同时满足两个条件：
1. 他在 2015 年的投保金额 <strong>TIV_2015 </strong>为 &#39;10&#39; ，与第三个和第四个投保人在 2015 年的投保金额相同。
2. 他所在城市的经纬度是独一无二的。

第二个投保人两个条件都不满足。他在 2015 年的投资 <strong>TIV_2015 </strong>与其他任何投保人都不相同。
且他所在城市的经纬度与第三个投保人相同。基于同样的原因，第三个投保人投资失败。

所以返回的结果是第一个投保人和最后一个投保人的 <strong>TIV_2016 </strong>之和，结果是 45 。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
