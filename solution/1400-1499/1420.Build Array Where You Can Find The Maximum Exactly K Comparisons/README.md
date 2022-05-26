# [1420. 生成数组](https://leetcode.cn/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons)

[English Version](/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个整数 <code>n</code>、<code>m</code> 和 <code>k</code> 。下图描述的算法用于找出正整数数组中最大的元素。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/images/e.png" style="height: 372px; width: 424px;"></p>

<p>请你生成一个具有下述属性的数组 <code>arr</code> ：</p>

<ul>
	<li><code>arr</code> 中有 <code>n</code> 个整数。</li>
	<li><code>1 &lt;= arr[i] &lt;= m</code> 其中 <code>(0 &lt;= i &lt; n)</code> 。</li>
	<li>将上面提到的算法应用于 <code>arr</code> ，<code>search_cost</code> 的值等于 <code>k</code> 。</li>
</ul>

<p>返回上述条件下生成数组 <code>arr</code> 的 <strong>方法数</strong> ，由于答案可能会很大，所以 <strong>必须</strong> 对 <code>10^9 + 7</code> 取余。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2, m = 3, k = 1
<strong>输出：</strong>6
<strong>解释：</strong>可能的数组分别为 [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, m = 2, k = 3
<strong>输出：</strong>0
<strong>解释：</strong>没有数组可以满足上述条件
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 9, m = 1, k = 1
<strong>输出：</strong>1
<strong>解释：</strong>可能的数组只有 [1, 1, 1, 1, 1, 1, 1, 1, 1]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 50, m = 100, k = 25
<strong>输出：</strong>34549172
<strong>解释：</strong>不要忘了对 1000000007 取余
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 37, m = 17, k = 7
<strong>输出：</strong>418930126
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
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
