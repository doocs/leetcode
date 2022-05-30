# [1868. 两个行程编码数组的积](https://leetcode.cn/problems/product-of-two-run-length-encoded-arrays)

[English Version](/solution/1800-1899/1868.Product%20of%20Two%20Run-Length%20Encoded%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><b>行程编码（</b><strong>Run-length encoding）</strong>是一种压缩算法，能让一个含有许多段<strong>连续重复</strong>数字的整数类型数组 <code>nums</code> 以一个（通常更小的）二维数组 <code>encoded</code> 表示。每个 <code>encoded[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> 表示 <code>nums</code> 中第 <code>i</code> 段重复数字，其中 <code>val<sub>i</sub></code> 是该段重复数字，重复了 <code>freq<sub>i</sub></code> 次。</p>

<ul>
	<li>例如， <code>nums = [1,1,1,2,2,2,2,2]</code> 可表示称行程编码数组 <code>encoded = [[1,3],[2,5]]</code> 。对此数组的另一种读法是“三个 <code>1</code> ，后面有五个 <code>2</code> ”。</li>
</ul>

<p>两个行程编码数组 <code>encoded1</code> 和 <code>encoded2</code> 的积可以按下列步骤计算：</p>

<ol>
	<li>将 <code>encoded1</code> 和 <code>encoded2</code> 分别<strong>扩展</strong>成完整数组 <code>nums1</code> 和 <code>nums2</code> 。</li>
	<li>创建一个新的数组 <code>prodNums</code> ，长度为 <code>nums1.length</code> 并设 <code>prodNums[i] = nums1[i] * nums2[i]</code> 。</li>
	<li>将 <code>prodNums</code> <strong>压缩</strong>成一个行程编码数组并返回之。</li>
</ol>

<p>给定两个<strong>行程编码</strong>数组 <code>encoded1</code> 和 <code>encoded2</code> ，分别表示完整数组 <code>nums1</code> 和 <code>nums2</code> 。<code>nums1</code> 和 <code>nums2</code> 的<strong>长度相同</strong>。 每一个 <code>encoded1[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> 表示 <code>nums1</code> 中的第 <code>i</code> 段，每一个 <code>encoded2[j] = [val<sub>j</sub>, freq<sub>j</sub>]</code> 表示 <code>nums2</code> 中的第 <code>j</code> 段。</p>

<p>返回<i> </i><code>encoded1</code> 和 <code>encoded2</code> 的<strong>乘积</strong>。</p>

<p><b>注：</b>行程编码数组需压缩成可能的<strong>最小</strong>长度。</p>

<p> </p>

<p><b>示例 1:</b></p>

<pre><strong>输入:</strong> encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
<strong>输出:</strong> [[6,6]]
<strong>解释n:</strong> encoded1 扩展为 [1,1,1,2,2,2] ，encoded2 扩展为 [6,6,6,3,3,3]。
prodNums = [6,6,6,6,6,6]，压缩成行程编码数组 [[6,6]]。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
<strong>输出:</strong> [[2,3],[6,1],[9,2]]
<strong>解释:</strong> encoded1 扩展为 [1,1,1,2,3,3] ，encoded2 扩展为 [2,2,2,3,3,3]。
prodNums = [2,2,2,6,9,9]，压缩成行程编码数组 [[2,3],[6,1],[9,2]]。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= encoded1.length, encoded2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>encoded1[i].length == 2</code></li>
	<li><code>encoded2[j].length == 2</code></li>
	<li>对于每一个 <code>encoded1[i]</code>， <code>1 &lt;= val<sub>i</sub>, freq<sub>i</sub> &lt;= 10<sup>4</sup></code>  </li>
	<li>对于每一个 <code>encoded2[j]</code>， <code>1 &lt;= val<sub>j</sub>, freq<sub>j</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>encoded1</code> 和 <code>encoded2</code> 表示的完整数组长度相同。</li>
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
