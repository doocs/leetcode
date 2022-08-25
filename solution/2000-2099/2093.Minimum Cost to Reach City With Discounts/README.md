# [2093. 前往目标城市的最小费用](https://leetcode.cn/problems/minimum-cost-to-reach-city-with-discounts)

[English Version](/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一组公路连接&nbsp;<code>n</code>&nbsp;个城市，城市编号为从&nbsp;<code>0</code> 到&nbsp;<code>n - 1</code>&nbsp;。 输入包含一个二维数组&nbsp;<code>highways</code>&nbsp;，其中 <code>highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>]</code> 表示有一条连接城市&nbsp;<code>city1<sub>i</sub></code> 和&nbsp;<code>city2<sub>i</sub></code>&nbsp;的双向公路，允许汽车缴纳值为&nbsp;<code>toll<sub>i</sub></code>&nbsp;的费用从&nbsp;&nbsp;<code>city1<sub>i</sub></code>&nbsp;前往&nbsp;<code>city2<sub>i</sub></code>&nbsp;<strong>或</strong> 从&nbsp;&nbsp;<code>city2<sub>i</sub></code>&nbsp;前往&nbsp;<code>city1<sub>i</sub></code>&nbsp;。</p>

<p>另给你一个整数&nbsp;<code>discounts</code> 表示你最多可以使用折扣的次数。你可以使用一次折扣使通过第&nbsp;<code>i<sup>th</sup></code>&nbsp;条公路的费用降低至&nbsp;<code>toll<sub>i</sub> / 2</code>（<b>向下取整</b>）。&nbsp;最多只可使用&nbsp;<code>discounts</code> 次折扣，&nbsp;且<strong> 每条公路最多只可使用一次折扣</strong> 。</p>

<p>返回从城市<code>0</code><em>&nbsp;</em>前往城市<em>&nbsp;</em><code>n - 1</code>&nbsp;的<em><strong> </strong></em><strong>最小费用</strong><em><strong> 。</strong></em>如果不存在从城市<code>0</code><em>&nbsp;</em>前往城市<em>&nbsp;</em><code>n - 1</code>&nbsp;的路径，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222429-1.png" style="height: 250px; width: 404px;" /></p>

<pre>
<strong>输入：</strong>n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
<strong>输出：</strong>9
<strong>解释：</strong>
从 0 前往 1 ，需要费用为 4 。
从 1 前往 4 并使用一次折扣，需要费用为 11 / 2 = 5 。
从 0 前往 4 最小费用为 4 + 5 = 9 。
</pre>

<p><strong>示例 2：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222650-4.png" style="width: 284px; height: 250px;" /></p>

<pre>
<strong>输入：</strong>n = 4, highways = [[1,3,17],[1,2,7],[3,2,5],[0,1,6],[3,0,20]], discounts = 20
<strong>输出：</strong>8
<strong>解释：</strong>
从 0 前往 1 并使用一次折扣，需要费用为 6 / 2 = 3 。
从 1 前往 2 并使用一次折扣，需要费用为 7 / 2 = 3 。
从 2 前往 3 并使用一次折扣，需要费用为 5 / 2 = 2 。
从 0 前往 3 最小费用为 3 + 3 + 2 = 8 。
</pre>

<p><strong>示例 3：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222531-3.png" style="width: 275px; height: 250px;" /></p>

<pre>
<strong>输入：</strong>n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
<strong>输出：</strong>-1
<strong>解释：</strong>
不存在从 0 前往 3 的路径，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= highways.length &lt;= 1000</code></li>
	<li><code>highways[i].length == 3</code></li>
	<li><code>0 &lt;= city1<sub>i</sub>, city2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>city1<sub>i</sub> != city2<sub>i</sub></code></li>
	<li><code>0 &lt;= toll<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= discounts &lt;= 500</code></li>
	<li>任意两个城市之间最多只有一条公路相连</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
