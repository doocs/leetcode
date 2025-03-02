---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README.md
---

<!-- problem:start -->

# [3472. 至多 K 次操作后的最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence-after-at-most-k-operations)

[English Version](/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此&nbsp;<code>'z'</code>&nbsp;的下一个字母是&nbsp;<code>'a'</code>）。例如，将 <code>'a'</code> 替换为下一个字母结果是 <code>'b'</code>，将 <code>'a'</code> 替换为上一个字母结果是 <code>'z'</code>；同样，将 <code>'z'</code> 替换为下一个字母结果是 <code>'a'</code>，替换为上一个字母结果是 <code>'y'</code>。</p>

<p>返回在进行&nbsp;<strong>最多</strong> <code>k</code> 次操作后，<code>s</code> 的&nbsp;<strong>最长回文子序列&nbsp;</strong>的长度。</p>

<p><strong>子序列&nbsp;</strong>是一个&nbsp;<strong>非空&nbsp;</strong>字符串，可以通过删除原字符串中的某些字符（或不删除任何字符）并保持剩余字符的相对顺序得到。</p>

<p><strong>回文&nbsp;</strong>是正着读和反着读都相同的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abced", k = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>将 <code>s[1]</code> 替换为下一个字母，得到 <code>"acced"</code>。</li>
	<li>将 <code>s[4]</code> 替换为上一个字母，得到 <code>"accec"</code>。</li>
</ul>

<p>子序列 <code>"ccc"</code> 形成一个长度为 3 的回文，这是最长的回文子序列。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "aaazzz", k = 4</span></p>

<p><strong>输出:</strong> 6</p>

<p><strong>解释:</strong></p>

<ul>
	<li>将 <code>s[0]</code> 替换为上一个字母，得到 <code>"zaazzz"</code>。</li>
	<li>将 <code>s[4]</code> 替换为下一个字母，得到 <code>"zaazaz"</code>。</li>
	<li>将 <code>s[3]</code> 替换为下一个字母，得到 <code>"zaaaaz"</code>。</li>
</ul>

<p>整个字符串形成一个长度为 6 的回文。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
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
