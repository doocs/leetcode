---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4116. 下雨天安排伞](https://leetcode.cn/problems/assign-umbrella-on-rainy-days)

[English Version](/solution/4100-4199/4116.Assign%20Umbrella%20on%20Rainy%20Days/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的 <strong>二进制数组</strong> <code>rain</code>，其中 <code>rain[i] == 1</code> 表示第 <code>i</code> 天下雨，<code>rain[i] == 0</code> 表示第 <code>i</code> 天不下雨。</p>

<p>Alice 和 Bob 在这 <code>n</code> 天里共用 <strong>一把伞</strong>。在每一天 <code>i</code>：</p>

<ul>
	<li>对于 <code>0 &lt;= i &lt; n - 1</code>，如果 <code>rain[i] == rain[i + 1] == 1</code>，则 Alice 在第 <code>i</code> 天需要伞。</li>
	<li>如果 <code>rain[i] == 1</code>，则 Bob 需要伞。</li>
	<li>他们之中 <strong>至多一人</strong> 可以得到伞。</li>
	<li>如果只有一个人需要伞，则由这个人得到伞。</li>
	<li>如果 <strong>两人</strong> 都需要伞，则伞归目前为止得到伞次数 <strong>较少</strong> 的人。如果次数 <strong>相同</strong>，则伞归 Alice。</li>
	<li>如果两人都不需要伞，则没有人得到伞。</li>
</ul>

<p>一个人的计数只有在其得到伞的那一天才会 <strong>增加</strong>。初始时，两人的计数均为 0。</p>

<p>返回一个长度为 <code>n</code> 的字符串数组 <code>answer</code>，其中 <code>answer[i]</code> 为 <code>"Alice"</code>、<code>"Bob"</code> 或 <code>"None"</code>，表示第 <code>i</code> 天得到伞的人。</p>
<strong>二进制数组</strong> 是只包含 0 和 1 的数组。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">rain = [1,1,0,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">["Alice","Bob","None","Alice","Bob"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 0 天：<code>rain[0] == 1</code> 且 <code>rain[1] == 1</code>，所以两人都需要伞。计数相同，所以 Alice 得到伞。</li>
	<li>第 1 天：<code>rain[1] == 1</code>，所以 Bob 需要伞，但 <code>rain[2] == 0</code>，所以 Alice 不需要伞。因此 Bob 得到伞。</li>
	<li>第 2 天：没有下雨，所以没有人得到伞。</li>
	<li>第 3 天：<code>rain[3] == 1</code> 且 <code>rain[4] == 1</code>，所以两人都需要伞。计数均为 1，相同，所以 Alice 得到伞。</li>
	<li>第 4 天：最后一天，所以只有 Bob 需要伞，由他得到伞。</li>
	<li>因此，<code>answer = ["Alice", "Bob", "None", "Alice", "Bob"]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">rain = [0,1,0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">["None","Bob","None","Bob"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从未出现连续两天下雨的情况，所以 Alice 从不需要伞。</li>
	<li>Bob 在第 1 天和第 3 天得到伞，第 0 天和第 2 天没有人得到伞。</li>
	<li>因此，<code>answer = ["None", "Bob", "None", "Bob"]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == rain.length &lt;= 100</code></li>
	<li><code>rain[i]</code> 为 0 或 1。</li>
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
