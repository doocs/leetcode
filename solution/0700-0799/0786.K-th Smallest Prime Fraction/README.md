# [786. 第 K 个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction)

[English Version](/solution/0700-0799/0786.K-th%20Smallest%20Prime%20Fraction/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个按递增顺序排序的数组 <code>arr</code> 和一个整数 <code>k</code> 。数组 <code>arr</code> 由 <code>1</code> 和若干 <strong>素数</strong>  组成，且其中所有整数互不相同。</p>

<p>对于每对满足 <code>0 < i < j < arr.length</code> 的 <code>i</code> 和 <code>j</code> ，可以得到分数 <code>arr[i] / arr[j]</code> 。</p>

<p>那么第 <code>k</code> 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 <code>answer[0] == arr[i]</code> 且 <code>answer[1] == arr[j]</code> 。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,5], k = 3
<strong>输出：</strong>[2,5]
<strong>解释：</strong>已构造好的分数,排序后如下所示: 
1/5, 1/3, 2/5, 1/2, 3/5, 2/3
很明显第三个最小的分数是 2/5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,7], k = 1
<strong>输出：</strong>[1,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= arr.length <= 1000</code></li>
	<li><code>1 <= arr[i] <= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[0] == 1</code></li>
	<li><code>arr[i]</code> 是一个 <strong>素数</strong> ，<code>i > 0</code></li>
	<li><code>arr</code> 中的所有数字 <strong>互不相同</strong> ，且按 <strong>严格递增</strong> 排序</li>
	<li><code>1 <= k <= arr.length * (arr.length - 1) / 2</code></li>
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
