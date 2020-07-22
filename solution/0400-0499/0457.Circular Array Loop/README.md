# [457. 环形数组循环](https://leetcode-cn.com/problems/circular-array-loop)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个含有正整数和负整数的<strong>环形</strong>数组&nbsp;<code>nums</code>。 如果某个索引中的数 <em>k</em>&nbsp;为正数，则向前移动 <em>k</em>&nbsp;个索引。相反，如果是负数 (<em>-k</em>)，则向后移动 <em>k</em>&nbsp;个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。</p>

<p>确定 <code>nums</code>&nbsp;中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 &gt; 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。<br>
&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[2,-1,1,2,2]
<strong>输出：</strong>true
<strong>解释：</strong>存在循环，按索引 0 -&gt; 2 -&gt; 3 -&gt; 0 。循环长度为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[-1,2]
<strong>输出：</strong>false
<strong>解释：</strong>按索引 1 -&gt; 1 -&gt; 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入：</strong>[-2,1,-1,-2,-2]
<strong>输出：</strong>false
<strong>解释：</strong>按索引 1 -&gt; 2 -&gt; 1 -&gt; ... 的运动无法构成循环，因为按索引 1 -&gt; 2 的运动是向前的运动，而按索引 2 -&gt; 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>-1000 &le;&nbsp;nums[i] &le;&nbsp;1000</li>
	<li>nums[i] &ne;&nbsp;0</li>
	<li>0 &le;&nbsp;nums.length &le; 5000</li>
</ol>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你能写出时间时间复杂度为 <strong>O(n)</strong>&nbsp;和额外空间复杂度为 <strong>O(1)</strong> 的算法吗？</p>



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