# [2403. Minimum Time to Kill All Monsters](https://leetcode.cn/problems/minimum-time-to-kill-all-monsters)

[English Version](/solution/2400-2499/2403.Minimum%20Time%20to%20Kill%20All%20Monsters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given an integer array <code>power</code> where <code>power[i]</code> is the power of the <code>i<sup>th</sup></code> monster.</p>

<p>You start with <code>0</code> mana points, and each day you increase your mana points by <code>gain</code> where <code>gain</code> initially is equal to <code>1</code>.</p>

<p>Each day, after gaining <code>gain</code> mana, you can defeat a monster if your mana points are greater than or equal to the power of that monster. When you defeat a monster:</p>

<ul>
	<li>your mana points will be reset to <code>0</code>, and</li>
	<li>the value of <code>gain</code> increases by <code>1</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of days needed to defeat all the monsters.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> power = [3,1,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 2<sup>nd</sup> monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points.
- Day 3: Gain 2 mana points to get a total of 4 mana points. Spend all mana points to kill the 3<sup>rd</sup> monster.
- Day 4: Gain 3 mana points to get a total of 3 mana points. Spend all mana points to kill the 1<sup>st</sup> monster.
It can be proven that 4 is the minimum number of days needed. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> power = [1,1,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1<sup>st</sup> monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2<sup>nd</sup> monster.
- Day 3: Gain 3 mana points to get a total of 3 mana points.
- Day 4: Gain 3 mana points to get a total of 6 mana points. Spend all mana points to kill the 3<sup>rd</sup> monster.
It can be proven that 4 is the minimum number of days needed. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> power = [1,2,4,9]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal way to beat all the monsters is to:
- Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1st monster.
- Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2nd monster.
- Day 3: Gain 3 mana points to get a total of 3 mana points.
- Day 4: Gain 3 mana points to get a total of 6 mana points.
- Day 5: Gain 3 mana points to get a total of 9 mana points. Spend all mana points to kill the 4th monster.
- Day 6: Gain 4 mana points to get a total of 4 mana points. Spend all mana points to kill the 3rd monster.
It can be proven that 6 is the minimum number of days needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 17</code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
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
