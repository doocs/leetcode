---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4102. 统计有效子字符串 I](https://leetcode.cn/problems/count-valid-substrings-i)

[English Version](/solution/4100-4199/4102.Count%20Valid%20Substrings%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由字符 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 组成的字符串 <code>s</code>。</p>

<p>如果一个字符串不包含 <code>"abc"</code> 作为&nbsp;<strong><span data-keyword="substring-nonempty">子串</span>&nbsp;</strong>，那么它被称为&nbsp;<strong>有效的&nbsp;</strong>。</p>

<p>返回 <code>s</code> 中&nbsp;<strong>有效子串&nbsp;</strong>的数量。起始或结束下标不同的子串分别计数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abac"</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>以下子串不包含 <code>"abc"</code> 作为子串：</p>

<ul>
	<li><code>s[0..0] = "a"</code></li>
	<li><code>s[0..1] = "ab"</code></li>
	<li><code>s[0..2] = "aba"</code></li>
	<li><code>s[0..3] = "abac"</code></li>
	<li><code>s[1..1] = "b"</code></li>
	<li><code>s[1..2] = "ba"</code></li>
	<li><code>s[1..3] = "bac"</code></li>
	<li><code>s[2..2] = "a"</code></li>
	<li><code>s[2..3] = "ac"</code></li>
	<li><code>s[3..3] = "c"</code></li>
</ul>

<p>因此，答案是 10。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc"</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>以下子串不包含 <code>"abc"</code> 作为子串：</p>

<ul>
	<li><code>s[0..0] = "a"</code></li>
	<li><code>s[0..1] = "ab"</code></li>
	<li><code>s[1..1] = "b"</code></li>
	<li><code>s[1..2] = "bc"</code></li>
	<li><code>s[2..2] = "c"</code>​​​​​​​</li>
</ul>

<p>因此，答案是 5。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "ac"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code> 中的所有子串都不包含 <code>"abc"</code> 作为子串​​​​​​​。因此，答案是 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>s</code> 仅由字符 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 组成。</li>
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
