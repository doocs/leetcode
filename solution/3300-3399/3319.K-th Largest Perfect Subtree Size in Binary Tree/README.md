---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/README.md
---

<!-- problem:start -->

# [3319. 第 K 大的完美二叉子树的大小](https://leetcode.cn/problems/k-th-largest-perfect-subtree-size-in-binary-tree)

[English Version](/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>二叉树 </strong>的根节点 <code>root</code> 和一个整数<code>k</code>。</p>

<p>返回第 <code>k</code> 大的 <strong>完美二叉</strong><span data-keyword="subtree"><strong>子树</strong> </span>的大小，如果不存在则返回 <code>-1</code>。</p>

<p><strong>完美二叉树 </strong>是指所有叶子节点都在同一层级的树，且每个父节点恰有两个子节点。</p>

<p><strong>子树 </strong>是指树中的某一个节点及其所有后代形成的树。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/image.jpg" style="width: 300px; height: 175px;" /></p>

<p>完美二叉子树的根节点在图中以黑色突出显示。它们的大小按降序排列为 <code>[3, 3, 1, 1, 1, 1, 1, 1]</code>。<br />
第 <code>2</code> 大的完美二叉子树的大小是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [1,2,3,4,5,6,7], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/image1.jpg" style="width: 300px; height: 149px;" /></p>

<p>完美二叉子树的大小按降序排列为 <code>[7, 3, 3, 1, 1, 1, 1]</code>。最大的完美二叉子树的大小是 7。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">root = [1,2,3,null,4], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/image4.jpg" style="width: 150px; height: 130px;" /></p>

<p>完美二叉子树的大小按降序排列为 <code>[1, 1]</code>。完美二叉子树的数量少于 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数目在 <code>[1, 2000]</code> 范围内。</li>
	<li><code>1 &lt;= Node.val &lt;= 2000</code></li>
	<li><code>1 &lt;= k &lt;= 1024</code></li>
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
