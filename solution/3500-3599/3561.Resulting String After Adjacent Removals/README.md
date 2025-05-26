---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3561.Resulting%20String%20After%20Adjacent%20Removals/README.md
---

<!-- problem:start -->

# [3561. 移除相邻字符](https://leetcode.cn/problems/resulting-string-after-adjacent-removals)

[English Version](/solution/3500-3599/3561.Resulting%20String%20After%20Adjacent%20Removals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>你&nbsp;<strong>必须&nbsp;</strong>在字符串 <code>s</code> 中至少存在两个&nbsp;<strong>连续&nbsp;</strong>字符时，反复执行以下操作：</p>

<ul>
	<li>移除字符串中&nbsp;<strong>最左边&nbsp;</strong>的一对按照字母表&nbsp;<strong>连续&nbsp;</strong>的相邻字符（无论是按顺序还是逆序，例如 <code>'a'</code> 和 <code>'b'</code>，或 <code>'b'</code> 和 <code>'a'</code>）。</li>
	<li>将剩余字符向左移动以填补空隙。</li>
</ul>

<p>当无法再执行任何操作时，返回最终的字符串。</p>

<p><strong>注意：</strong>字母表是循环的，因此 <code>'a'</code> 和 <code>'z'</code> 也视为连续。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abc"</span></p>

<p><strong>输出:</strong> <span class="example-io">"c"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>从字符串中移除 <code>"ab"</code>，剩下 <code>"c"</code>。</li>
	<li>无法进行进一步操作。因此，所有可能移除操作后的最终字符串为 <code>"c"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "adcb"</span></p>

<p><strong>输出:</strong> <span class="example-io">""</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>从字符串中移除 <code>"dc"</code>，剩下 <code>"ab"</code>。</li>
	<li>从字符串中移除 <code>"ab"</code>，剩下 <code>""</code>。</li>
	<li>无法进行进一步操作。因此，所有可能移除操作后的最终字符串为 <code>""</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "zadb"</span></p>

<p><strong>输出:</strong> <span class="example-io">"db"</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>从字符串中移除 <code>"za"</code>，剩下 <code>"db"</code>。</li>
	<li>无法进行进一步操作。因此，所有可能移除操作后的最终字符串为 <code>"db"</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
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
