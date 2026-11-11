---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4105. 重复字符串匹配 II](https://leetcode.cn/problems/repeated-string-match-ii)

[English Version](/solution/4100-4199/4105.Repeated%20String%20Match%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个由小写英文字母组成的字符串 <code>a</code> 和 <code>b</code>，以及两个整数 <code>x</code> 和 <code>y</code>。</p>

<p>如果将 <code>b</code> <strong>重复恰好</strong> <code>y</code> 次得到的字符串 (<code>b * y</code>) 是将 <code>a</code> <strong>重复恰好</strong> <code>x</code> 次得到的字符串 (<code>a * x</code>) 的&nbsp;<strong><span data-keyword="substring-nonempty">子串</span>&nbsp;</strong>，返回 <code>true</code>。否则，返回 <code>false</code>。</p>
<strong>子串&nbsp;</strong>是字符串内连续的&nbsp;<strong>非空&nbsp;</strong>字符序列。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = "ab", b = "abab", x = 2, y = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a * x = "ab" * 2 = "abab"</code></li>
	<li><code>b * y = "abab" * 1 = "abab"</code></li>
	<li>它们完全相同，因此 <code>b * y</code> 是 <code>a * x</code> 的子串。所以，答案是 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = "abc", b = "bc", x = 2, y = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a * x = "abc" * 2 = "abcabc"</code></li>
	<li><code>b * y = "bc" * 3 = "bcbcbc"</code></li>
	<li>答案是 <code>false</code>，因为 <code>b * y = "bcbcbc"</code> 不是 <code>a * x = "abcabc"</code> 的子串。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = "abcd", b = "cdab", x = 2, y = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>a * x = "abcd" * 2 = "abcdabcd"</code></li>
	<li><code>b * y = "cdab"</code> 出现在 <code>a * x = "abcdabcd"</code> 的下标 <code>[2..5]</code> 处。因此，答案是 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length &lt;= 100</code></li>
	<li><code>1 &lt;= b.length &lt;= 100</code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= y &lt;= 10<sup>9</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
