# [683. K Empty Slots](https://leetcode.com/problems/k-empty-slots)

[中文文档](/solution/0600-0699/0683.K%20Empty%20Slots/README.md)

## Description

<p>You have <code>N</code> bulbs in a row numbered from <code>1</code> to <code>N</code>. Initially, all the bulbs are turned off. We turn on exactly one bulb everyday until all bulbs are on after <code>N</code> days.</p>

<p>You are given an array <code>bulbs</code> of length <code>N</code> where <code>bulbs[i] = x</code> means that on the <code>(i+1)th</code> day, we will turn on the bulb at position <code>x</code> where <code>i</code> is <code>0-indexed</code> and <code>x</code> is <code>1-indexed.</code></p>

<p>Given an integer <code>K</code>, find out the <strong>minimum day number</strong> such that there exists two <strong>turned on</strong> bulbs that have <strong>exactly</strong> <code>K</code> bulbs between them that are <strong>all turned off</strong>.</p>

<p>If there isn't such day, return <code>-1</code>.</p>

<p> </p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> 
bulbs: [1,3,2]
K: 1
<b>Output:</b> 2
<b>Explanation:</b>
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.
</pre>

<p><b>Example 2:</b></p>

<pre>
<b>Input:</b> 
bulbs: [1,2,3]
K: 1
<b>Output:</b> -1
</pre>

<p> </p>

<p><b>Note:</b></p>

<ol>
	<li><code>1 <= N <= 20000</code></li>
	<li><code>1 <= bulbs[i] <= N</code></li>
	<li><code>bulbs</code> is a permutation of numbers from <code>1</code> to <code>N</code>.</li>
	<li><code>0 <= K <= 20000</code></li>
</ol>

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
