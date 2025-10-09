---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README.md
rating: 1739
source: 第 166 场双周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3694. 删除子字符串后不同的终点](https://leetcode.cn/problems/distinct-points-reachable-after-substring-removal)

[English Version](/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由字符 <code>'U'</code>、<code>'D'</code>、<code>'L'</code> 和 <code>'R'</code> 组成的字符串 <code>s</code>，表示在无限的二维笛卡尔网格上的移动。</p>

<ul>
	<li><code>'U'</code>: 从 <code>(x, y)</code> 移动到 <code>(x, y + 1)</code>。</li>
	<li><code>'D'</code>: 从 <code>(x, y)</code> 移动到 <code>(x, y - 1)</code>。</li>
	<li><code>'L'</code>: 从 <code>(x, y)</code> 移动到 <code>(x - 1, y)</code>。</li>
	<li><code>'R'</code>: 从 <code>(x, y)</code> 移动到 <code>(x + 1, y)</code>。</li>
</ul>

<p>你还得到了一个正整数 <code>k</code>。</p>

<p>你 <strong>必须</strong>&nbsp;选择并移除 <strong>恰好一个</strong> 长度为 <code>k</code> 的连续子字符串 <code>s</code>。然后，从坐标 <code>(0, 0)</code> 开始，按顺序执行剩余的移动。</p>

<p>返回可到达的 <strong>不同</strong>&nbsp;最终坐标的数量。</p>

<p>&nbsp;</p>

<p><strong><strong class="example">示例 1:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "LUL", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p>移除长度为 1 的子字符串后，<code>s</code> 可以是 <code>"UL"</code>、<code>"LL"</code> 或 <code>"LU"</code>。执行这些移动后，最终坐标将分别是 <code>(-1, 1)</code>、<code>(-2, 0)</code> 和 <code>(-1, 1)</code>。有两个不同的点 <code>(-1, 1)</code> 和 <code>(-2, 0)</code>，因此答案是 2。</p>
</div>

<p><strong><strong class="example">示例 2:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "UDLR", k = 4</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>移除长度为 4 的子字符串后，<code>s</code> 只能是空字符串。最终坐标将是 <code>(0, 0)</code>。只有一个不同的点 <code>(0, 0)</code>，因此答案是 1。</p>
</div>

<p><strong><strong class="example">示例 3:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "UU", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>移除长度为 1 的子字符串后，<code>s</code> 变为 <code>"U"</code>，它总是以 <code>(0, 1)</code> 结束，因此只有一个不同的最终坐标。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 只包含 <code>'U'</code>、<code>'D'</code>、<code>'L'</code> 和 <code>'R'</code>。</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
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
