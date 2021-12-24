# [1560. Most Visited Sector in a Circular Track](https://leetcode.com/problems/most-visited-sector-in-a-circular-track)

[中文文档](/solution/1500-1599/1560.Most%20Visited%20Sector%20in%20%20a%20Circular%20Track/README.md)

## Description

<p>Given an integer <code>n</code> and an integer array <code>rounds</code>.&nbsp;We&nbsp;have a circular track which consists of <code>n</code> sectors labeled from <code>1</code> to <code>n</code>. A marathon will be held on this track, the marathon consists of <code>m</code> rounds. The <code>i<sup>th</sup></code>&nbsp;round starts at sector <code>rounds[i - 1]</code> and ends at sector <code>rounds[i]</code>. For example, round 1 starts at sector <code>rounds[0]</code> and ends at sector <code>rounds[1]</code></p>

<p>Return <em>an array of the most visited sectors</em> sorted in <strong>ascending</strong> order.</p>

<p>Notice that you&nbsp;circulate the track in ascending order of sector numbers in the counter-clockwise direction (See the first example).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1560.Most%20Visited%20Sector%20in%20%20a%20Circular%20Track/images/tmp.jpg" style="width: 433px; height: 341px;" />
<pre>
<strong>Input:</strong> n = 4, rounds = [1,3,1,2]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The marathon starts at sector 1. The order of the visited sectors is as follows:
1 --&gt; 2 --&gt; 3 (end of round 1) --&gt; 4 --&gt; 1 (end of round 2) --&gt; 2 (end of round 3 and the marathon)
We can see that both sectors 1 and 2 are visited twice and they are the most visited sectors. Sectors 3 and 4 are visited only once.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, rounds = [2,1,2,1,2,1,2,1,2]
<strong>Output:</strong> [2]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 7, rounds = [1,3,5,7]
<strong>Output:</strong> [1,2,3,4,5,6,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>rounds.length == m + 1</code></li>
	<li><code>1 &lt;= rounds[i] &lt;= n</code></li>
	<li><code>rounds[i] != rounds[i + 1]</code> for <code>0 &lt;= i &lt; m</code></li>
</ul>

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
