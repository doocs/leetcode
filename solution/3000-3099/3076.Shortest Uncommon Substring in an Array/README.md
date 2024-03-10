# [3076. 数组中的最短非公共子字符串](https://leetcode.cn/problems/shortest-uncommon-substring-in-an-array)

[English Version](/solution/3000-3099/3076.Shortest%20Uncommon%20Substring%20in%20an%20Array/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>arr</code>&nbsp;，数组中有 <code>n</code>&nbsp;个 <b>非空</b>&nbsp;字符串。</p>

<p>请你求出一个长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>answer</code>&nbsp;，满足：</p>

<ul>
	<li><code>answer[i]</code>&nbsp;是 <code>arr[i]</code>&nbsp;<strong>最短</strong>&nbsp;的子字符串，且它不是 <code>arr</code>&nbsp;中其他任何字符串的子字符串。如果有多个这样的子字符串存在，<code>answer[i]</code>&nbsp;应该是它们中字典序最小的一个。如果不存在这样的子字符串，<code>answer[i]</code>&nbsp;为空字符串。</li>
</ul>

<p>请你返回数组<em>&nbsp;</em><code>answer</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>arr = ["cab","ad","bad","c"]
<b>输出：</b>["ab","","ba",""]
<b>解释：</b>求解过程如下：
- 对于字符串 "cab" ，最短没有在其他字符串中出现过的子字符串是 "ca" 或者 "ab" ，我们选择字典序更小的子字符串，也就是 "ab" 。
- 对于字符串 "ad" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "bad" ，最短没有在其他字符串中出现过的子字符串是 "ba" 。
- 对于字符串 "c" ，不存在没有在其他字符串中出现过的子字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = ["abc","bcd","abcd"]
<b>输出：</b>["","","abcd"]
<b>解释：</b>求解过程如下：
- 对于字符串 "abc" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "bcd" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "abcd" ，最短没有在其他字符串中出现过的子字符串是 "abcd" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 20</code></li>
	<li><code>arr[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
