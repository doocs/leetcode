---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4043.Count%20Rotations%20With%20Exactly%20K%20Equal%20Adjacent%20Pairs/README.md
---

<!-- problem:start -->

# [4043. 恰好有 K 对相等相邻字符的循环移位数量](https://leetcode.cn/problems/count-rotations-with-exactly-k-equal-adjacent-pairs)

[English Version](/solution/4000-4099/4043.Count%20Rotations%20With%20Exactly%20K%20Equal%20Adjacent%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p><code>s</code> 的一次&nbsp;<strong>循环移位&nbsp;</strong>可以通过以下方式得到：选择 <code>s</code> 的一个长度在 0 到 <code>n - 1</code>（包含两端）之间的&nbsp;<strong>前缀</strong>&nbsp;，并将其移动到字符串末尾，同时保持所有字符的相对顺序不变。</p>

<p>对于 <code>s</code> 的<strong>&nbsp;每一种&nbsp;</strong>循环移位，定义其&nbsp;<strong>得分</strong>&nbsp;为满足以下条件的下标 <code>i</code> 的数量：<code>0 &lt;= i &lt; n - 1</code>，且位置 <code>i</code> 和 <code>i + 1</code> 处的字符相同。</p>

<p>返回得分等于 <code>k</code> 的循环移位数量。</p>

<p>字符串的<strong>&nbsp;前缀</strong>&nbsp;是指从字符串开头开始，并延伸到字符串中某个位置的子串。</p>

<p><strong>子串</strong>&nbsp;是字符串中一段连续的字符序列，可以为空。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aab", k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code> 的所有循环移位为：</p>

<ul>
	<li><code>"aab"</code>：位置 0 和 1 处的字符相同，因此 <code>score = 1</code>。</li>
	<li><code>"aba"</code>：不存在两个相邻且相同的字符，因此 <code>score = 0</code>。</li>
	<li><code>"baa"</code>：位置 1 和 2 处的字符相同，因此 <code>score = 1</code>。</li>
</ul>

<p>共有 2 种 <code>s</code> 的循环移位，其 <code>score</code> 等于 <code>k</code>，因此答案为 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abca", k = 0</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code> 的所有循环移位为：</p>

<ul>
	<li><code>"abca"</code>：不存在两个相邻且相同的字符，因此 <code>score = 0</code>。</li>
	<li><code>"bcaa"</code>：位置 2 和 3 处的字符相同，因此 <code>score = 1</code>。</li>
	<li><code>"caab"</code>：位置 1 和 2 处的字符相同，因此 <code>score = 1</code>。</li>
	<li><code>"aabc"</code>：位置 0 和 1 处的字符相同，因此 <code>score = 1</code>。</li>
</ul>

<p>只有 1 种 <code>s</code> 的循环移位，其 <code>score</code> 等于 <code>k</code>，因此答案为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
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
