---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/README.md
---

<!-- problem:start -->

# [3310. 移除可疑的方法](https://leetcode.cn/problems/remove-methods-from-project)

[English Version](/solution/3300-3399/3310.Remove%20Methods%20From%20Project/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在维护一个项目，该项目有 <code>n</code> 个方法，编号从 <code>0</code> 到 <code>n - 1</code>。</p>

<p>给你两个整数 <code>n</code> 和 <code>k</code>，以及一个二维整数数组 <code>invocations</code>，其中 <code>invocations[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示方法 <code>a<sub>i</sub></code> 调用了方法 <code>b<sub>i</sub></code>。</p>

<p>已知如果方法 <code>k</code> 存在一个已知的 bug。那么方法 <code>k</code> 以及它直接或间接调用的任何方法都被视为<strong> </strong><strong>可疑方法</strong> ，我们需要从项目中移除这些方法。</p>

<p><span class="text-only" data-eleid="13" style="white-space: pre;">只有当一组方法没有被这组之外的任何方法调用时，这组方法才能被移除。</span></p>

<p>返回一个数组，包含移除所有<strong> </strong><strong>可疑方法</strong> 后剩下的所有方法。你可以以任意顺序返回答案。如果无法移除<strong> 所有 </strong>可疑方法，则<strong> 不 </strong>移除任何方法。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[0,1,2,3]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph-2.png" style="width: 200px; height: 200px;" /></p>

<p>方法 2 和方法 1 是可疑方法，但它们分别直接被方法 3 和方法 0 调用。由于方法 3 和方法 0 不是可疑方法，我们无法移除任何方法，故返回所有方法。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[3,4]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph-3.png" style="width: 200px; height: 200px;" /></p>

<p>方法 0、方法 1 和方法 2 是可疑方法，且没有被任何其他方法直接调用。我们可以移除它们。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph.png" style="width: 200px; height: 200px;" /></p>

<p>所有方法都是可疑方法。我们可以移除它们。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
	<li><code>0 &lt;= invocations.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>invocations[i] == [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>invocations[i] != invocations[j]</code></li>
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
