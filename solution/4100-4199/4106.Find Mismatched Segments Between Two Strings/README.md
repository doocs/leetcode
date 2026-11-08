---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4106. 找出两个字符串的不匹配片段](https://leetcode.cn/problems/find-mismatched-segments-between-two-strings)

[English Version](/solution/4100-4199/4106.Find%20Mismatched%20Segments%20Between%20Two%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的字符串 <code>s1</code> 和 <code>s2</code>。</p>

<p><strong>不匹配片段</strong>&nbsp;是一个&nbsp;<strong>极大连续</strong>&nbsp;下标区间，其中每个下标处 <code>s1</code> 和 <code>s2</code> 的字符都不相同。</p>

<p>返回一个二维数组，其中包含每个不匹配片段对应的 <code>[sub1, sub2]</code>，<code>sub1</code> 和 <code>sub2</code> 分别是 <code>s1</code> 和 <code>s2</code> 中对应的&nbsp;<strong>子字符串</strong>。请按片段起始下标的&nbsp;<strong>递增</strong>&nbsp;顺序返回。如果没有不匹配片段，则返回一个空数组。</p>

<p><strong>子字符串&nbsp;</strong>是字符串中一个连续的&nbsp;<b>非空&nbsp;</b>字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "earth", s2 = "empty"</span></p>

<p><strong>输出：</strong> <span class="example-io">[["ar","mp"],["h","y"]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标 1 和 2 处的字符不同，形成不匹配片段 <code>["ar","mp"]</code>。</li>
	<li>下标 4 处的字符不同，形成不匹配片段 <code>["h","y"]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "action", s2 = "nation"</span></p>

<p><strong>输出：</strong> <span class="example-io">[["ac","na"]]</span></p>

<p><strong>解释：</strong></p>

<p>下标 0 和 1 处的字符不同，形成不匹配片段 <code>["ac","na"]</code>。两个字符串中其余位置的字符均相同。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "hot", s2 = "hot"</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>两个字符串完全相同，因此没有不匹配片段。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length == s2.length &lt;= 100</code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅由小写英文字母组成。</li>
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
