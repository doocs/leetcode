# [1998. 数组的最大公因数排序](https://leetcode-cn.com/problems/gcd-sort-of-an-array)

[English Version](/solution/1900-1999/1998.GCD%20Sort%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，你可以在 <code>nums</code> 上执行下述操作 <strong>任意次</strong> ：</p>

<ul>
	<li>如果 <code>gcd(nums[i], nums[j]) &gt; 1</code> ，交换 <code>nums[i]</code> 和 <code>nums[j]</code> 的位置。其中 <code>gcd(nums[i], nums[j])</code> 是&nbsp;<code>nums[i]</code> 和 <code>nums[j]</code> 的最大公因数。</li>
</ul>

<p>如果能使用上述交换方式将 <code>nums</code> 按 <strong>非递减顺序</strong> 排列，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,21,3]
<strong>输出：</strong>true
<strong>解释：</strong>可以执行下述操作完成对 [7,21,3] 的排序：
- 交换 7 和 21 因为 gcd(7,21) = 7 。nums = [<em><strong>21</strong></em>,<em><strong>7</strong></em>,3]
- 交换 21 和 3 因为 gcd(21,3) = 3 。nums = [<em><strong>3</strong></em>,7,<em><strong>21</strong></em>]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,2,6,2]
<strong>输出：</strong>false
<strong>解释：</strong>无法完成排序，因为 5 不能与其他元素交换。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [10,5,9,3,15]
<strong>输出：</strong>true
<strong>解释：</strong>
可以执行下述操作完成对 [10,5,9,3,15] 的排序：
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [<em><strong>15</strong></em>,5,9,3,<em><strong>10</strong></em>]
- 交换 15 和 3 因为 gcd(15,3) = 3 。nums = [<em><strong>3</strong></em>,5,9,<em><strong>15</strong></em>,10]
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [3,5,9,<em><strong>10</strong></em>,<em><strong>15</strong></em>]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
