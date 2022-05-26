# [656. 金币路径](https://leetcode.cn/problems/coin-path)

[English Version](/solution/0600-0699/0656.Coin%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>A</code>（下标从 <code>1</code> 开始）包含 N 个整数：A<sub>1</sub>，A<sub>2</sub>，&hellip;&hellip;，A<sub>N</sub>&nbsp;和一个整数 <code>B</code>。你可以从数组 <code>A</code> 中的任何一个位置（下标为 <code>i</code>）跳到下标&nbsp;<code>i+1</code>，<code>i+2</code>，&hellip;&hellip;，<code>i+B</code>&nbsp;的任意一个可以跳到的位置上。如果你在下标为 <code>i</code> 的位置上，你需要支付 A<sub>i</sub> 个金币。如果 A<sub>i</sub> 是 -1，意味着下标为 <code>i</code> 的位置是不可以跳到的。</p>

<p>现在，你希望花费最少的金币从数组 <code>A</code> 的 <code>1</code> 位置跳到&nbsp;<code>N</code> 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。</p>

<p>如果有多种花费最少的方案，输出字典顺序最小的路径。</p>

<p>如果无法到达 N 位置，请返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong>样例 1 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 2
<strong>输出:</strong> [1,3,5]
</pre>

<p>&nbsp;</p>

<p><strong>样例 2 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 1
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>注释 :</strong></p>

<ol>
	<li>路径 Pa<sub>1</sub>，Pa<sub>2</sub>，&hellip;&hellip;，Pa<sub>n&nbsp;</sub>是字典序小于 Pb<sub>1</sub>，Pb<sub>2</sub>，&hellip;&hellip;，Pb<sub>m&nbsp;</sub>的，当且仅当第一个 Pa<sub>i</sub> 和 Pb<sub>i</sub> 不同的 <code>i</code> 满足 Pa<sub>i</sub> &lt; Pb<sub>i</sub>，如果不存在这样的 <code>i</code> 那么满足 <code>n</code> &lt; <code>m</code>。</li>
	<li>A<sub>1</sub> &gt;= 0。&nbsp;A<sub>2</sub>, ..., A<sub>N</sub>&nbsp;（如果存在）&nbsp;的范围是 [-1, 100]。</li>
	<li>A 数组的长度范围 [1, 1000].</li>
	<li>B 的范围&nbsp;[1, 100].</li>
</ol>

<p>&nbsp;</p>

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
