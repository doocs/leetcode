# [2222. 选择建筑的方案数](https://leetcode-cn.com/problems/number-of-ways-to-select-buildings)

[English Version](/solution/2200-2299/2222.Number%20of%20Ways%20to%20Select%20Buildings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二进制字符串&nbsp;<code>s</code>&nbsp;，它表示一条街沿途的建筑类型，其中：</p>

<ul>
	<li><code>s[i] = '0'</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;栋建筑是一栋办公楼，</li>
	<li><code>s[i] = '1'</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;栋建筑是一间餐厅。</li>
</ul>

<p>作为市政厅的官员，你需要随机<strong>&nbsp;选择</strong>&nbsp;3 栋建筑。然而，为了确保多样性，选出来的 3 栋建筑 <strong>相邻</strong>&nbsp;的两栋不能是同一类型。</p>

<ul>
	<li>比方说，给你&nbsp;<code>s = "0<em><strong>0</strong></em>1<em><strong>1</strong></em>0<em><strong>1</strong></em>"</code>&nbsp;，我们不能选择第&nbsp;<code>1</code>&nbsp;，<code>3</code>&nbsp;和&nbsp;<code>5</code>&nbsp;栋建筑，因为得到的子序列是&nbsp;<code>"0<em><strong>11</strong></em>"</code>&nbsp;，有相邻两栋建筑是同一类型，所以 <strong>不合</strong>&nbsp;题意。</li>
</ul>

<p>请你返回可以选择 3 栋建筑的 <strong>有效方案数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "001101"
<b>输出：</b>6
<b>解释：</b>
以下下标集合是合法的：
- [0,2,4] ，从 "<em><strong>0</strong></em>0<em><strong>1</strong></em>1<em><strong>0</strong></em>1" 得到 "010"
- [0,3,4] ，从 "<em><strong>0</strong></em>01<em><strong>10</strong></em>1" 得到 "010"
- [1,2,4] ，从 "0<em><strong>01</strong></em>1<em><strong>0</strong></em>1" 得到 "010"
- [1,3,4] ，从 "0<em><strong>0</strong></em>1<em><strong>10</strong></em>1" 得到 "010"
- [2,4,5] ，从 "00<em><strong>1</strong></em>1<em><strong>01</strong></em>" 得到 "101"
- [3,4,5] ，从 "001<em><strong>101</strong></em>" 得到 "101"
没有别的合法选择，所以总共有 6 种方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "11100"
<b>输出：</b>0
<b>解释：</b>没有任何符合题意的选择。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code>&nbsp;。</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
