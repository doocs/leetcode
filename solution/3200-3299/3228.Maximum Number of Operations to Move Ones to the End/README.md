---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README.md
---

<!-- problem:start -->

# [3228. 将 1 移动到末尾的最大操作次数](https://leetcode.cn/problems/maximum-number-of-operations-to-move-ones-to-the-end)

[English Version](/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <span data-keyword="binary-string">二进制字符串</span> <code>s</code>。</p>

<p>你可以对这个字符串执行 <strong>任意次</strong> 下述操作：</p>

<ul>
	<li>选择字符串中的任一下标 <code>i</code>（ <code>i + 1 &lt; s.length</code> ），该下标满足 <code>s[i] == '1'</code> 且 <code>s[i + 1] == '0'</code>。</li>
	<li>将字符 <code>s[i]</code> 向 <strong>右移 </strong>直到它到达字符串的末端或另一个 <code>'1'</code>。例如，对于 <code>s = "010010"</code>，如果我们选择 <code>i = 1</code>，结果字符串将会是 <code>s = "0<strong><u>001</u></strong>10"</code>。</li>
</ul>

<p>返回你能执行的<strong> 最大 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "1001101"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可以执行以下操作：</p>

<ul>
	<li>选择下标 <code>i = 0</code>。结果字符串为 <code>s = "<u><strong>001</strong></u>1101"</code>。</li>
	<li>选择下标 <code>i = 4</code>。结果字符串为 <code>s = "0011<u><strong>01</strong></u>1"</code>。</li>
	<li>选择下标 <code>i = 3</code>。结果字符串为 <code>s = "001<strong><u>01</u></strong>11"</code>。</li>
	<li>选择下标 <code>i = 2</code>。结果字符串为 <code>s = "00<strong><u>01</u></strong>111"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "00111"</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code>。</li>
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
