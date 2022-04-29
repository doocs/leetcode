# [1714. 数组中特殊等间距元素的和](https://leetcode.cn/problems/sum-of-special-evenly-spaced-elements-in-array)

[English Version](/solution/1700-1799/1714.Sum%20Of%20Special%20Evenly-Spaced%20Elements%20In%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong>索引从 0 开始</strong>的整数类型数组 <code>nums</code> ，包含 <code>n</code> 个非负整数。</p>

<p>另外给定一个（包含查询指令的）数组 <code>queries</code> ，其中 <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>。 第 <code>i</code> 个查询指令的答案是 <code>nums[j]</code> 中满足该条件的所有元素的和： <code>x<sub>i</sub> &lt;= j &lt; n</code> 且 <code>(j - x<sub>i</sub>)</code> 能被 <code>y<sub>i</sub></code> 整除。</p>

<p>返回一个数组<em> </em><code>answer</code>，其中<em>  </em><code>answer.length == queries.length</code> 且 <code>answer[i]</code> 是第 <code>i</code> 个查询指令的答案对 <code>10<sup>9 </sup>+ 7</code> 取模。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
<strong>输出:</strong> [9,18,10]
<strong>解释:</strong> 每次查询的答案如下：
1) 符合查询条件的索引 j 有 0、 3 和 6。 nums[0] + nums[3] + nums[6] = 9
2) 符合查询条件的索引 j 有 5、 6 和 7。 nums[5] + nums[6] + nums[7] = 18
3) 符合查询条件的索引 j 有 4 和 6。 nums[4] + nums[6] = 10
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
<strong>输出:</strong> [303]
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 1.5 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= y<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
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
