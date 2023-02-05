# [2555. 两个线段获得的最多奖品](https://leetcode.cn/problems/maximize-win-from-two-segments)

[English Version](/solution/2500-2599/2555.Maximize%20Win%20From%20Two%20Segments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 <strong>X轴</strong>&nbsp;上有一些奖品。给你一个整数数组&nbsp;<code>prizePositions</code>&nbsp;，它按照 <strong>非递减</strong>&nbsp;顺序排列，其中&nbsp;<code>prizePositions[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以选择两个端点为整数的线段。每个线段的长度都必须是 <code>k</code>&nbsp;。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。</p>

<ul>
	<li>比方说&nbsp;<code>k = 2</code>&nbsp;，你可以选择线段&nbsp;<code>[1, 3]</code> 和&nbsp;<code>[2, 4]</code>&nbsp;，你可以获得满足&nbsp;<code>1 &lt;= prizePositions[i] &lt;= 3</code> 或者&nbsp;<code>2 &lt;= prizePositions[i] &lt;= 4</code>&nbsp;的所有奖品 i 。</li>
</ul>

<p>请你返回在选择两个最优线段的前提下，可以获得的 <strong>最多</strong>&nbsp;奖品数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>prizePositions = [1,1,2,2,3,3,5], k = 2
<b>输出：</b>7
<b>解释：</b>这个例子中，你可以选择线段 [1, 3] 和 [3, 5] ，获得 7 个奖品。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>prizePositions = [1,2,3,4], k = 0
<b>输出：</b>2
<b>解释：</b>这个例子中，一个选择是选择线段 <code>[3, 3]</code> 和 <code>[4, 4] ，获得 2 个奖品。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prizePositions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prizePositions[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup> </code></li>
	<li><code>prizePositions</code>&nbsp;有序非递减。</li>
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
