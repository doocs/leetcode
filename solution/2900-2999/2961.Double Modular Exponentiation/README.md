# [2961. 双模幂运算](https://leetcode.cn/problems/double-modular-exponentiation)

[English Version](/solution/2900-2999/2961.Double%20Modular%20Exponentiation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0 </strong>开始的二维数组 <code>variables</code> ，其中 <code>variables[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i,</sub> m<sub>i</sub>]</code>，以及一个整数 <code>target</code> 。</p>

<p>如果满足以下公式，则下标 <code>i</code> 是 <strong>好下标</strong>：</p>

<ul>
	<li><code>0 &lt;= i &lt; variables.length</code></li>
	<li><code>((a<sub>i</sub><sup>b<sub>i</sub></sup> % 10)<sup>c<sub>i</sub></sup>) % m<sub>i</sub> == target</code></li>
</ul>

<p>返回一个由<strong> 好下标 </strong>组成的数组，<strong>顺序不限</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
<strong>输出：</strong>[0,2]
<strong>解释：</strong>对于 variables 数组中的每个下标 i ：
1) 对于下标 0 ，variables[0] = [2,3,3,10] ，(2<sup>3</sup> % 10)<sup>3</sup> % 10 = 2 。
2) 对于下标 1 ，variables[1] = [3,3,3,1] ，(3<sup>3</sup> % 10)<sup>3</sup> % 1 = 0 。
3) 对于下标 2 ，variables[2] = [6,1,1,4] ，(6<sup>1</sup> % 10)<sup>1</sup> % 4 = 2 。
因此，返回 [0,2] 作为答案。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>variables = [[39,3,1000,1000]], target = 17
<strong>输出：</strong>[]
<strong>解释：</strong>对于 variables 数组中的每个下标 i ：
1) 对于下标 0 ，variables[0] = [39,3,1000,1000] ，(39<sup>3</sup> % 10)<sup>1000</sup> % 1000 = 1 。
因此，返回 [] 作为答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= variables.length &lt;= 100</code></li>
	<li><code>variables[i] == [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub>]</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub> &lt;= 10<sup>3</sup></code></li>
	<li><code><font face="monospace">0 &lt;= target &lt;= 10<sup>3</sup></font></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
