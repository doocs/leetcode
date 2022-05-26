# [1152. 用户网站访问行为分析](https://leetcode.cn/problems/analyze-user-website-visit-pattern)

[English Version](/solution/1100-1199/1152.Analyze%20User%20Website%20Visit%20Pattern/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串数组&nbsp;<code>username</code>&nbsp;和&nbsp;<code>website</code>&nbsp;和一个整数数组&nbsp;<code>timestamp</code>&nbsp;。给定的数组长度相同，其中元组&nbsp;<code>[username[i], website[i], timestamp[i]]</code>&nbsp;表示用户&nbsp;<code>username[i]</code>&nbsp;在时间&nbsp;<code>timestamp[i]</code>&nbsp;访问了网站&nbsp;<code>website[i]</code>&nbsp;。</p>

<p><strong>访问模式</strong> 是包含三个网站的列表(不一定是完全不同的)。</p>

<ul>
	<li>例如，<code>["home"， "away"， "love"]</code>， <code>["leetcode"， "love"， "leetcode"]</code>，和 <code>["luffy"， "luffy"， "luffy"]</code> 都是模式。</li>
</ul>

<p>一种&nbsp;<strong>访问</strong><strong>模式</strong> 的 <strong>得分</strong> 是访问该模式中所有网站的用户数量，这些网站在该模式中出现的顺序相同。</p>

<ul>
	<li>例如，如果模式是 <code>[“home”，“away”，“love”] </code>，那么分数就是用户数量 <code>x</code> , <code>x</code> 访问了 “<code>home”</code> ，然后访问了 <code>“away”</code> ，然后访问了 <code>“love” </code>。</li>
	<li>同样，如果模式是 <code>["leetcode"， "love"， "leetcode"]</code> ，那么分数就是用户数量&nbsp;<code>x</code>&nbsp;，使得 <code>x</code> 访问了<code>"leetcode"</code>，然后访问了 <code>"love"</code> ，之后又访问了 <code>"leetcode"</code> 。</li>
	<li>另外，如果模式是 <code>[“luffy”，“luffy”，“luffy”]</code>&nbsp;，那么分数就是用户数量 <code>x</code> ，这样 <code>x</code> 就可以在不同的时间戳上访问 <code>“luffy”</code> 三次。</li>
</ul>

<p>返回<em> <strong>得分</strong> 最大的 <strong>访问</strong><strong>模式</strong></em> 。如果有多个访问模式具有相同的最大分数，则返回字典序最小的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
<strong>输出：</strong>["home","about","career"]
<strong>解释：</strong>本例中的元组是:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
模式("home", "about", "career") has score 2 (joe and mary).
模式("home", "cart", "maps") 的得分为 1 (james).
模式 ("home", "cart", "home") 的得分为 1 (james).
模式 ("home", "maps", "home") 的得分为 1 (james).
模式 ("cart", "maps", "home") 的得分为 1 (james).
模式 ("home", "home", "home") 的得分为 0(没有用户访问过home 3次)。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
<strong>输出:</strong> ["a","b","a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= username.length &lt;= 50</code></li>
	<li><code>1 &lt;= username[i].length &lt;= 10</code></li>
	<li><code>timestamp.length == username.length</code></li>
	<li><code>1 &lt;= timestamp[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>website.length == username.length</code></li>
	<li><code>1 &lt;= website[i].length &lt;= 10</code></li>
	<li><code>username[i]</code> 和&nbsp;<code>website[i]</code>&nbsp;都只含小写字符</li>
	<li>它保证至少有一个用户访问了至少三个网站</li>
	<li>所有元组&nbsp;<code>[username[i]， timestamp[i]， website[i]</code>&nbsp;均<strong>&nbsp;不重复</strong></li>
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
