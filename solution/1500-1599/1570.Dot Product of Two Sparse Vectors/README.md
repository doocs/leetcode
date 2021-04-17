# [1570. 两个稀疏向量的点积](https://leetcode-cn.com/problems/dot-product-of-two-sparse-vectors)

[English Version](/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个稀疏向量，计算它们的点积（数量积）。</p>

<p>实现类 <code>SparseVector</code>：</p>

<ul>
	<li><code>SparseVector(nums)</code> 以向量 <code>nums</code> 初始化对象。</li>
	<li><code>dotProduct(vec)</code> 计算此向量与 <code>vec</code> 的点积。</li>
</ul>

<p><strong>稀疏向量</strong> 是指绝大多数分量为 0 的向量。你需要 <strong>高效</strong> 地存储这个向量，并计算两个稀疏向量的点积。</p>

<p><strong>进阶：</strong>当其中只有一个向量是稀疏向量时，你该如何解决此问题？</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
<strong>输出：</strong>8
<strong>解释：</strong>v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
<strong>输出：</strong>0
<strong>解释：</strong>v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
<strong>输出：</strong>6
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 <= n <= 10^5</code></li>
	<li><code>0 <= nums1[i], nums2[i] <= 100</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
