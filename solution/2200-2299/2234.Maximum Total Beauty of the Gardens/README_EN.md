# [2234. Maximum Total Beauty of the Gardens](https://leetcode.com/problems/maximum-total-beauty-of-the-gardens)

[中文文档](/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README.md)

## Description

<p>Alice is a caretaker of <code>n</code> gardens and she wants to plant flowers to maximize the total beauty of all her gardens.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>flowers</code> of size <code>n</code>, where <code>flowers[i]</code> is the number of flowers already planted in the <code>i<sup>th</sup></code> garden. Flowers that are already planted <strong>cannot</strong> be removed. You are then given another integer <code>newFlowers</code>, which is the <strong>maximum</strong> number of flowers that Alice can additionally plant. You are also given the integers <code>target</code>, <code>full</code>, and <code>partial</code>.</p>

<p>A garden is considered <strong>complete</strong> if it has <strong>at least</strong> <code>target</code> flowers. The <strong>total beauty</strong> of the gardens is then determined as the <strong>sum</strong> of the following:</p>

<ul>
	<li>The number of <strong>complete</strong> gardens multiplied by <code>full</code>.</li>
	<li>The <strong>minimum</strong> number of flowers in any of the <strong>incomplete</strong> gardens multiplied by <code>partial</code>. If there are no incomplete gardens, then this value will be <code>0</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> total beauty that Alice can obtain after planting at most </em><code>newFlowers</code><em> flowers.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
<strong>Output:</strong> 14
<strong>Explanation:</strong> Alice can plant
- 2 flowers in the 0<sup>th</sup> garden
- 3 flowers in the 1<sup>st</sup> garden
- 1 flower in the 2<sup>nd</sup> garden
- 1 flower in the 3<sup>rd</sup> garden
The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
There is 1 garden that is complete.
The minimum number of flowers in the incomplete gardens is 2.
Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
No other way of planting flowers can obtain a total beauty higher than 14.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
<strong>Output:</strong> 30
<strong>Explanation:</strong> Alice can plant
- 3 flowers in the 0<sup>th</sup> garden
- 0 flowers in the 1<sup>st</sup> garden
- 0 flowers in the 2<sup>nd</sup> garden
- 2 flowers in the 3<sup>rd</sup> garden
The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
There are 3 gardens that are complete.
The minimum number of flowers in the incomplete gardens is 4.
Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
No other way of planting flowers can obtain a total beauty higher than 30.
Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= flowers[i], target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= newFlowers &lt;= 10<sup>10</sup></code></li>
	<li><code>1 &lt;= full, partial &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
