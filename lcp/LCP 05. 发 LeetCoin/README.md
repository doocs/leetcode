# [LCP 05. 发 LeetCoin](https://leetcode-cn.com/problems/coin-bonus)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣决定给一个刷题团队发<code>LeetCoin</code>作为奖励。同时，为了监控给大家发了多少<code>LeetCoin</code>，力扣有时候也会进行查询。</p>

<p>&nbsp;</p>

<p>该刷题团队的管理模式可以用一棵树表示：</p>

<ol>
	<li>团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）；</li>
	<li>不存在循环管理的情况，如A管理B，B管理C，C管理A。</li>
</ol>

<p>&nbsp;</p>

<p>力扣想进行的操作有以下三种：</p>

<ol>
	<li>给团队的一个成员（也可以是负责人）发一定数量的<code>LeetCoin</code>；</li>
	<li>给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，&hellip;&hellip;），发一定数量的<code>LeetCoin</code>；</li>
	<li>查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的<code>LeetCoin</code>之和。</li>
</ol>

<p>&nbsp;</p>

<p><strong>输入：</strong></p>

<ol>
	<li><code>N</code>表示团队成员的个数（编号为1～N，负责人为1）；</li>
	<li><code>leadership</code>是大小为<code>(N&nbsp;- 1) * 2</code>的二维数组，其中每个元素<code>[a, b]</code>代表<code>b</code>是<code>a</code>的下属；</li>
	<li><code>operations</code>是一个长度为<code>Q</code>的二维数组，代表以时间排序的操作，格式如下：
	<ol>
		<li><code>operations[i][0] = 1</code>: 代表第一种操作，<code>operations[i][1]</code>代表成员的编号，<code>operations[i][2]</code>代表<code>LeetCoin</code>的数量；</li>
		<li><code>operations[i][0] = 2</code>: 代表第二种操作，<code>operations[i][1]</code>代表成员的编号，<code>operations[i][2]</code>代表<code>LeetCoin</code>的数量；</li>
		<li><code>operations[i][0] = 3</code>: 代表第三种操作，<code>operations[i][1]</code>代表成员的编号；</li>
	</ol>
	</li>
</ol>

<p><strong>输出：</strong></p>

<p>返回一个数组，数组里是每次<strong>查询</strong>的返回值（发<code>LeetCoin</code>的操作不需要任何返回值）。由于发的<code>LeetCoin</code>很多，请把每次查询的结果模<code>1e9+7 (1000000007)</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>N = 6, leadership = [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]], operations = [[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]
<strong>输出：</strong>[650, 665]
<strong>解释：</strong>团队的管理关系见下图。
第一次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 0;
第二次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 15.
</pre>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2005.%20发%20LeetCoin/images/coin_example_1.jpg" style="height: 344px; width: 300px;"></p>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 50000</code></li>
	<li><code>1 &lt;= Q &lt;= 50000</code></li>
	<li><code>operations[i][0] != 3 时，1 &lt;= operations[i][2]&nbsp;&lt;= 5000</code></li>
</ol>

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
