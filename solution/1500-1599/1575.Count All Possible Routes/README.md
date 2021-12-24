# [1575. 统计所有可行路径](https://leetcode-cn.com/problems/count-all-possible-routes)

[English Version](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>互不相同</strong>&nbsp;的整数数组，其中&nbsp;<code>locations[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个城市的位置。同时给你&nbsp;<code>start</code>，<code>finish</code>&nbsp;和&nbsp;<code>fuel</code>&nbsp;分别表示出发城市、目的地城市和你初始拥有的汽油总量</p>

<p>每一步中，如果你在城市 <code>i</code>&nbsp;，你可以选择任意一个城市 <code>j</code>&nbsp;，满足 &nbsp;<code>j != i</code>&nbsp;且&nbsp;<code>0 &lt;= j &lt; locations.length</code>&nbsp;，并移动到城市&nbsp;<code>j</code>&nbsp;。从城市&nbsp;<code>i</code>&nbsp;移动到&nbsp;<code>j</code>&nbsp;消耗的汽油量为&nbsp;<code>|locations[i] - locations[j]|</code>，<code>|x|</code>&nbsp;表示&nbsp;<code>x</code>&nbsp;的绝对值。</p>

<p>请注意，&nbsp;<code>fuel</code>&nbsp;任何时刻都&nbsp;<strong>不能</strong>&nbsp;为负，且你&nbsp;<strong>可以</strong>&nbsp;经过任意城市超过一次（包括&nbsp;<code>start</code>&nbsp;和&nbsp;<code>finish</code>&nbsp;）。</p>

<p>请你返回从<em>&nbsp;</em><code>start</code>&nbsp;到&nbsp;<code>finish</code>&nbsp;所有可能路径的数目。</p>

<p>由于答案可能很大， 请将它对&nbsp;<code>10^9 + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>输出：</strong>4
<strong>解释：</strong>以下为所有可能路径，每一条都用了 5 单位的汽油：
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>locations = [4,3,1], start = 1, finish = 0, fuel = 6
<strong>输出：</strong>5
<strong>解释：</strong>以下为所有可能的路径：
1 -&gt; 0，使用汽油量为 fuel = 1
1 -&gt; 2 -&gt; 0，使用汽油量为 fuel = 5
1 -&gt; 2 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 5
1 -&gt; 0 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 3
1 -&gt; 0 -&gt; 1 -&gt; 0 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 5
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>locations = [5,2,1], start = 0, finish = 2, fuel = 3
<strong>输出：</strong>0
<strong>解释：</strong>没有办法只用 3 单位的汽油从 0 到达 2 。因为最短路径需要 4 单位的汽油。</pre>

<p><strong>示例 4 ：</strong></p>

<pre>
<strong>输入：</strong>locations = [2,1,5], start = 0, finish = 0, fuel = 3
<strong>输出：</strong>2
<strong>解释：</strong>总共有两条可行路径，0 和 0 -&gt; 1 -&gt; 0 。</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>locations = [1,2,3], start = 0, finish = 2, fuel = 40
<strong>输出：</strong>615088286
<strong>解释：</strong>路径总数为 2615088300 。将结果对 10^9 + 7 取余，得到 615088286 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= locations.length &lt;= 100</code></li>
	<li><code>1 &lt;= locations[i] &lt;= 10^9</code></li>
	<li>所有&nbsp;<code>locations</code>&nbsp;中的整数 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>0 &lt;= start, finish &lt;&nbsp;locations.length</code></li>
	<li><code>1 &lt;= fuel &lt;= 200</code></li>
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

### **...**

```

```

<!-- tabs:end -->
