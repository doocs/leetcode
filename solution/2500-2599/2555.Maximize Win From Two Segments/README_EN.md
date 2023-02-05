# [2555. Maximize Win From Two Segments](https://leetcode.com/problems/maximize-win-from-two-segments)

[中文文档](/solution/2500-2599/2555.Maximize%20Win%20From%20Two%20Segments/README.md)

## Description

<p>There are some prizes on the <strong>X-axis</strong>. You are given an integer array <code>prizePositions</code> that is <strong>sorted in non-decreasing order</strong>, where <code>prizePositions[i]</code> is the position of the <code>i<sup>th</sup></code> prize. There could be different prizes at the same position on the line. You are also given an integer <code>k</code>.</p>

<p>You are allowed to select two segments with integer endpoints. The length of each segment must be <code>k</code>. You will collect all prizes whose position falls within at least one of the two selected segments (including the endpoints of the segments). The two selected segments may intersect.</p>

<ul>
	<li>For example if <code>k = 2</code>, you can choose segments <code>[1, 3]</code> and <code>[2, 4]</code>, and you will win any prize <font face="monospace">i</font> that satisfies <code>1 &lt;= prizePositions[i] &lt;= 3</code> or <code>2 &lt;= prizePositions[i] &lt;= 4</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of prizes you can win if you choose the two segments optimally</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prizePositions = [1,1,2,2,3,3,5], k = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> In this example, you can win all 7 prizes by selecting two segments [1, 3] and [3, 5].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prizePositions = [1,2,3,4], k = 0
<strong>Output:</strong> 2
<strong>Explanation:</strong> For this example, <strong>one choice</strong> for the segments is <code>[3, 3]</code> and <code>[4, 4],</code> and you will be able to get <code>2</code> prizes. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prizePositions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prizePositions[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup> </code></li>
	<li><code>prizePositions</code> is sorted in non-decreasing order.</li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
