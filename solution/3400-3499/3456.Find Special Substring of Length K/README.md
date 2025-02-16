---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README.md
---

<!-- problem:start -->

# [3456. 找出长度为 K 的特殊子字符串](https://leetcode.cn/problems/find-special-substring-of-length-k)

[English Version](/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>判断是否存在一个长度&nbsp;<strong>恰好&nbsp;</strong>为 <code>k</code> 的子字符串，该子字符串需要满足以下条件：</p>

<ol>
	<li>该子字符串&nbsp;<strong>只包含一个唯一字符</strong>（例如，<code>"aaa"</code> 或 <code>"bbb"</code>）。</li>
	<li>如果该子字符串的&nbsp;<strong>前面&nbsp;</strong>有字符，则该字符必须与子字符串中的字符不同。</li>
	<li>如果该子字符串的&nbsp;<strong>后面&nbsp;</strong>有字符，则该字符也必须与子字符串中的字符不同。</li>
</ol>

<p>如果存在这样的子串，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p><strong>子字符串&nbsp;</strong>是字符串中的连续、非空字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaabaaa", k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>子字符串 <code>s[4..6] == "aaa"</code> 满足条件：</p>

<ul>
	<li>长度为 3。</li>
	<li>所有字符相同。</li>
	<li>子串 <code>"aaa"</code> 前的字符是 <code>'b'</code>，与 <code>'a'</code> 不同。</li>
	<li>子串 <code>"aaa"</code> 后没有字符。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>不存在长度为 2 、仅由一个唯一字符组成且满足所有条件的子字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code></li>
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
