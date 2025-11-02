---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3735.Lexicographically%20Smallest%20String%20After%20Reverse%20II/README.md
---

<!-- problem:start -->

# [3735. 反转后字典序最小的字符串 II 🔒](https://leetcode.cn/problems/lexicographically-smallest-string-after-reverse-ii)

[English Version](/solution/3700-3799/3735.Lexicographically%20Smallest%20String%20After%20Reverse%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的、长度为 <code>n</code> 的字符串 <code>s</code>。</p>

<p>你 必须执行 <strong>恰好&nbsp;</strong>一次操作：选择一个整数 <code>k</code>，满足 <code>1 &lt;= k &lt;= n</code>，然后执行以下两个选项之一：</p>

<ul>
	<li>反转 <code>s</code> 的 <strong>前</strong>&nbsp;<code>k</code> 个字符，或</li>
	<li>反转 <code>s</code> 的&nbsp;<strong>后</strong>&nbsp;<code>k</code> 个字符。</li>
</ul>

<p>返回在 <strong>恰好</strong>&nbsp;执行一次此类操作后可以获得的 <strong>字典序最小&nbsp;</strong>的字符串。</p>

<p>如果字符串 <code>a</code> 和字符串 <code>b</code> 在第一个不同的位置上，<code>a</code> 中的字母在字母表中比 <code>b</code> 中对应的字母出现得更早，则称字符串 <code>a</code>&nbsp;<strong>字典序小于&nbsp;</strong>字符串 <code>b</code>。如果前 <code>min(a.length, b.length)</code> 个字符都相同，则较短的字符串字典序较小。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "dcab"</span></p>

<p><strong>输出:</strong> <span class="example-io">"acdb"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 3</code>，反转前 3 个字符。</li>
	<li>将 <code>"dca"</code> 反转为 <code>"acd"</code>，得到的字符串 <code>s = "acdb"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abba"</span></p>

<p><strong>输出:</strong> <span class="example-io">"aabb"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 3</code>，反转后 3 个字符。</li>
	<li>将 <code>"bba"</code> 反转为 <code>"abb"</code>，得到的字符串是 <code>"aabb"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "zxy"</span></p>

<p><strong>输出:</strong> <span class="example-io">"xzy"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择 <code>k = 2</code>，反转前 2 个字符。</li>
	<li>将 <code>"zx"</code> 反转为 <code>"xz"</code>，得到的字符串是 <code>"xzy"</code>，这是可获得的字典序最小的字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
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
