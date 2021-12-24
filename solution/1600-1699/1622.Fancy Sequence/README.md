# [1622. 奇妙序列](https://leetcode-cn.com/problems/fancy-sequence)

[English Version](/solution/1600-1699/1622.Fancy%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你实现三个 API <code>append</code>，<code>addAll</code> 和 <code>multAll</code> 来实现奇妙序列。</p>

<p>请实现 <code>Fancy</code> 类 ：</p>

<ul>
	<li><code>Fancy()</code> 初始化一个空序列对象。</li>
	<li><code>void append(val)</code> 将整数 <code>val</code> 添加在序列末尾。</li>
	<li><code>void addAll(inc)</code> 将所有序列中的现有数值都增加 <code>inc</code> 。</li>
	<li><code>void multAll(m)</code> 将序列中的所有现有数值都乘以整数 <code>m</code> 。</li>
	<li><code>int getIndex(idx)</code> 得到下标为 <code>idx</code> 处的数值（下标从 0 开始），并将结果对 <code>10<sup>9</sup> + 7</code> 取余。如果下标大于等于序列的长度，请返回 <code>-1</code> 。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
<strong>输出：</strong>
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

<strong>解释：</strong>
Fancy fancy = new Fancy();
fancy.append(2);   // 奇妙序列：[2]
fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
fancy.append(7);   // 奇妙序列：[5, 7]
fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
fancy.getIndex(0); // 返回 10
fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
fancy.append(10);  // 奇妙序列：[13, 17, 10]
fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
fancy.getIndex(0); // 返回 26
fancy.getIndex(1); // 返回 34
fancy.getIndex(2); // 返回 20
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= val, inc, m <= 100</code></li>
	<li><code>0 <= idx <= 10<sup>5</sup></code></li>
	<li>总共最多会有 <code>10<sup>5</sup></code> 次对 <code>append</code>，<code>addAll</code>，<code>multAll</code> 和 <code>getIndex</code> 的调用。</li>
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
