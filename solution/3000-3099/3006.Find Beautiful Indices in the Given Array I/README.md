# [3006. 找出数组中的美丽下标 I](https://leetcode.cn/problems/find-beautiful-indices-in-the-given-array-i)

[English Version](/solution/3000-3099/3006.Find%20Beautiful%20Indices%20in%20the%20Given%20Array%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 、字符串 <code>a</code> 、字符串 <code>b</code> 和一个整数 <code>k</code> 。</p>

<p>如果下标 <code>i</code> 满足以下条件，则认为它是一个 <strong>美丽下标</strong>：</p>

<ul>
	<li><code>0 &lt;= i &lt;= s.length - a.length</code></li>
	<li><code>s[i..(i + a.length - 1)] == a</code></li>
	<li>存在下标 <code>j</code> 使得：
	<ul>
		<li><code>0 &lt;= j &lt;= s.length - b.length</code></li>
		<li><code>s[j..(j + b.length - 1)] == b</code></li>
		<li><code>|j - i| &lt;= k</code></li>
	</ul>
	</li>
</ul>

<p>以数组形式按<strong> 从小到大排序 </strong>返回美丽下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel", k = 15
<strong>输出：</strong>[16,33]
<strong>解释：</strong>存在 2 个美丽下标：[16,33]。
- 下标 16 是美丽下标，因为 s[16..17] == "my" ，且存在下标 4 ，满足 s[4..11] == "squirrel" 且 |16 - 4| &lt;= 15 。
- 下标 33 是美丽下标，因为 s[33..34] == "my" ，且存在下标 18 ，满足 s[18..25] == "squirrel" 且 |33 - 18| &lt;= 15 。
因此返回 [16,33] 作为结果。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", a = "a", b = "a", k = 4
<strong>输出：</strong>[0]
<strong>解释：</strong>存在 1 个美丽下标：[0]。
- 下标 0 是美丽下标，因为 s[0..0] == "a" ，且存在下标 0 ，满足 s[0..0] == "a" 且 |0 - 0| &lt;= 4 。
因此返回 [0] 作为结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= a.length, b.length &lt;= 10</code></li>
	<li><code>s</code>、<code>a</code>、和 <code>b</code> 只包含小写英文字母。</li>
</ul>

## 解法

<!-- end -->
