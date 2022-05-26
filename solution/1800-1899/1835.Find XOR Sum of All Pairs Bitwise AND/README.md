# [1835. 所有数对按位与结果的异或和](https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and)

[English Version](/solution/1800-1899/1835.Find%20XOR%20Sum%20of%20All%20Pairs%20Bitwise%20AND/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>列表的 <strong>异或和</strong>（<strong>XOR sum</strong>）指对所有元素进行按位 <code>XOR</code> 运算的结果。如果列表中仅有一个元素，那么其 <strong>异或和</strong> 就等于该元素。</p>

<ul>
	<li>例如，<code>[1,2,3,4]</code> 的 <strong>异或和</strong> 等于 <code>1 XOR 2 XOR 3 XOR 4 = 4</code> ，而 <code>[3]</code> 的 <strong>异或和</strong> 等于 <code>3</code> 。</li>
</ul>

<p>给你两个下标 <strong>从 0 开始</strong> 计数的数组 <code>arr1</code> 和 <code>arr2</code> ，两数组均由非负整数组成。</p>

<p>根据每个 <code>(i, j)</code> 数对，构造一个由 <code>arr1[i] AND arr2[j]</code>（按位 <code>AND</code> 运算）结果组成的列表。其中 <code>0 &lt;= i &lt; arr1.length</code> 且 <code>0 &lt;= j &lt; arr2.length</code> 。</p>

<p>返回上述列表的 <strong>异或和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,2,3], arr2 = [6,5]
<strong>输出：</strong>0
<strong>解释：</strong>列表 = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1] ，
异或和 = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr1 = [12], arr2 = [4]
<strong>输出：</strong>4
<strong>解释：</strong>列表 = [12 AND 4] = [4] ，异或和 = 4 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr1[i], arr2[j] &lt;= 10<sup>9</sup></code></li>
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
