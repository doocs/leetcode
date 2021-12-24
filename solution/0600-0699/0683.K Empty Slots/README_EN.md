# [683. K Empty Slots](https://leetcode.com/problems/k-empty-slots)

[中文文档](/solution/0600-0699/0683.K%20Empty%20Slots/README.md)

## Description

<p>You have <code>n</code> bulbs in a row numbered from <code>1</code> to <code>n</code>. Initially, all the bulbs are turned off. We turn on <strong>exactly one</strong> bulb every day until all bulbs are on after <code>n</code> days.</p>

<p>You are given an array <code>bulbs</code>&nbsp;of length <code>n</code>&nbsp;where <code>bulbs[i] = x</code> means that on the <code>(i+1)<sup>th</sup></code> day, we will turn on the bulb at position <code>x</code>&nbsp;where&nbsp;<code>i</code>&nbsp;is&nbsp;<strong>0-indexed</strong>&nbsp;and&nbsp;<code>x</code>&nbsp;is&nbsp;<strong>1-indexed.</strong></p>

<p>Given an integer <code>k</code>, return&nbsp;<em>the <strong>minimum day number</strong> such that there exists two <strong>turned on</strong> bulbs that have <strong>exactly</strong>&nbsp;<code>k</code> bulbs between them that are <strong>all turned off</strong>. If there isn&#39;t such day, return <code>-1</code>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> bulbs = [1,3,2], k = 1
<strong>Output:</strong> 2
<b>Explanation:</b>
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> bulbs = [1,2,3], k = 1
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == bulbs.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= bulbs[i] &lt;= n</code></li>
	<li><code>bulbs</code>&nbsp;is a permutation of numbers from&nbsp;<code>1</code>&nbsp;to&nbsp;<code>n</code>.</li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>4</sup></code></li>
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
