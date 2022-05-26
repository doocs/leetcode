# [1735. 生成乘积数组的方案数](https://leetcode.cn/problems/count-ways-to-make-array-with-product)

[English Version](/solution/1700-1799/1735.Count%20Ways%20to%20Make%20Array%20With%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>queries</code> ，其中 <code>queries[i] = [n<sub>i</sub>, k<sub>i</sub>]</code> 。第 <code>i</code> 个查询 <code>queries[i]</code> 要求构造长度为 <code>n<sub>i</sub></code> 、每个元素都是正整数的数组，且满足所有元素的乘积为 <code>k<sub>i</sub></code><sub> </sub>，请你找出有多少种可行的方案。由于答案可能会很大，方案数需要对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 。</p>

<p>请你返回一个整数数组<em> </em><code>answer</code>，满足<em> </em><code>answer.length == queries.length</code> ，其中<em> </em><code>answer[i]</code>是第<em> </em><code>i</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>queries = [[2,6],[5,1],[73,660]]
<b>输出：</b>[4,1,50734910]
<b>解释：</b>每个查询之间彼此独立。
[2,6]：总共有 4 种方案得到长度为 2 且乘积为 6 的数组：[1,6]，[2,3]，[3,2]，[6,1]。
[5,1]：总共有 1 种方案得到长度为 5 且乘积为 1 的数组：[1,1,1,1,1]。
[73,660]：总共有 1050734917 种方案得到长度为 73 且乘积为 660 的数组。1050734917 对 10<sup>9</sup> + 7 取余得到 50734910 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<b>输入：</b>queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<b>输出：</b>[1,2,3,10,5]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= queries.length <= 10<sup>4</sup> </code></li>
	<li><code>1 <= n<sub>i</sub>, k<sub>i</sub> <= 10<sup>4</sup></code></li>
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
