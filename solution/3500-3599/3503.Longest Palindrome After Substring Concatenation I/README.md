---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3503.Longest%20Palindrome%20After%20Substring%20Concatenation%20I/README.md
---

<!-- problem:start -->

# [3503. 子字符串连接后的最长回文串 I](https://leetcode.cn/problems/longest-palindrome-after-substring-concatenation-i)

[English Version](/solution/3500-3599/3503.Longest%20Palindrome%20After%20Substring%20Concatenation%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code>。</p>

<p>你可以从 <code>s</code> 中选择一个子串（可以为空）以及从 <code>t</code> 中选择一个子串（可以为空），然后将它们<strong> 按顺序 </strong>连接，得到一个新的字符串。</p>

<p>返回可以由上述方法构造出的<strong> 最长</strong> 回文串的长度。</p>

<p><strong>回文串</strong> 是指正着读和反着读都相同的字符串。</p>

<p><strong>子字符串 </strong>是指字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "a", t = "a"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>从 <code>s</code> 中选择 <code>"a"</code>，从 <code>t</code> 中选择 <code>"a"</code>，拼接得到 <code>"aa"</code>，这是一个长度为 2 的回文串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc", t = "def"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>由于两个字符串的所有字符都不同，最长的回文串只能是任意一个单独的字符，因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "b", t = "aaaa"</span></p>

<p><strong>输出：</strong> 4</p>

<p><strong>解释：</strong></p>

<p>可以选择 <code>"aaaa"</code> 作为回文串，其长度为 4。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcde", t = "ecdba"</span></p>

<p><strong>输出：</strong> 5</p>

<p><strong>解释：</strong></p>

<p>从 <code>s</code> 中选择 <code>"abc"</code>，从 <code>t</code> 中选择 <code>"ba"</code>，拼接得到 <code>"abcba"</code>，这是一个长度为 5 的回文串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 30</code></li>
	<li><code>s</code> 和 <code>t</code> 仅由小写英文字母组成。</li>
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
