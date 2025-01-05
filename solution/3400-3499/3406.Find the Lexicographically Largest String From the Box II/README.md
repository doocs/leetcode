---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3406.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20II/README.md
---

<!-- problem:start -->

# [3406. 从盒子中找出字典序最大的字符串 II 🔒](https://leetcode.cn/problems/find-the-lexicographically-largest-string-from-the-box-ii)

[English Version](/solution/3400-3499/3406.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code> 和一个整数 <code>numFriends</code>。</p>

<p>Alice 正在为她的 <code>numFriends</code> 位朋友组织一个游戏。游戏分为多个回合，在每一回合中：</p>

<ul>
	<li><code>word</code> 被分割成 <code>numFriends</code> 个&nbsp;<strong>非空&nbsp;</strong>字符串，且该分割方式与之前的任意回合所采用的都 <strong>不完全相同&nbsp;</strong>。</li>
	<li>所有分割出的字符串都会被放入一个盒子中。</li>
</ul>

<p>在所有回合结束后，找出盒子中&nbsp;<strong>字典序最大的&nbsp;</strong>字符串。</p>

<p>字符串 <code>a</code> 的字典序&nbsp;<strong>小于&nbsp;</strong>字符串 <code>b</code> 的前提是：在两个字符串上第一处不同的位置上，<code>a</code> 的字母在字母表中的顺序早于 <code>b</code> 中对应的字母。<br />
如果前 <code>min(a.length, b.length)</code> 个字符都相同，那么较短的字符串字典序更小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> word = "dbca", numFriends = 2</p>

<p><strong>输出:</strong> "dbc"</p>

<p><strong>解释:</strong>&nbsp;</p>

<p>所有可能的分割方式为：</p>

<ul>
	<li><code>"d"</code> 和 <code>"bca"</code>。</li>
	<li><code>"db"</code> 和 <code>"ca"</code>。</li>
	<li><code>"dbc"</code> 和 <code>"a"</code>。</li>
</ul>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> word = "gggg", numFriends = 4</p>

<p><strong>输出:</strong> "g"</p>

<p><strong>解释:</strong>&nbsp;</p>

<p>唯一可能的分割方式为：<code>"g"</code>, <code>"g"</code>, <code>"g"</code>, 和 <code>"g"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2&nbsp;* 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= numFriends &lt;= word.length</code></li>
</ul>

<p>&nbsp;</p>

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
