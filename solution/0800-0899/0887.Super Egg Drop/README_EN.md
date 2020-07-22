# [887. Super Egg Drop](https://leetcode.com/problems/super-egg-drop)

[中文文档](/solution/0800-0899/0887.Super%20Egg%20Drop/README.md)

## Description
<p>You are given <code>K</code> eggs, and you have access to a building with <code>N</code> floors from <code>1</code> to <code>N</code>.&nbsp;</p>



<p>Each egg is identical in function, and if an egg breaks, you cannot drop it&nbsp;again.</p>



<p>You know that there exists a floor <code>F</code> with <code>0 &lt;= F &lt;= N</code> such that any egg dropped at a floor higher than <code>F</code> will break, and any egg dropped at or below floor <code>F</code> will not break.</p>



<p>Each <em>move</em>, you may take an egg (if you have an unbroken one) and drop it from any floor <code>X</code> (with&nbsp;<code>1 &lt;= X &lt;= N</code>).&nbsp;</p>



<p>Your goal is to know&nbsp;<strong>with certainty</strong>&nbsp;what the value of <code>F</code> is.</p>



<p>What is the minimum number of moves that you need to know with certainty&nbsp;what <code>F</code> is, regardless of the initial value of <code>F</code>?</p>



<p>&nbsp;</p>



<ol>

</ol>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>K = <span id="example-input-1-1">1</span>, N = <span id="example-input-1-2">2</span>

<strong>Output: </strong><span id="example-output-1">2</span>

<strong>Explanation: </strong>

Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.

Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.

If it didn&#39;t break, then we know with certainty F = 2.

Hence, we needed 2 moves in the worst case to know what F is with certainty.

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>K = <span id="example-input-2-1">2</span>, N = 6

<strong>Output: </strong><span id="example-output-2">3</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong>K = <span id="example-input-3-1">3</span>, N = <span id="example-input-3-2">14</span>

<strong>Output: </strong><span id="example-output-3">4</span>

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>1 &lt;= K &lt;= 100</code></li>

	<li><code>1 &lt;= N &lt;= 10000</code></li>

</ol>

</div>

</div>

</div>




## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->