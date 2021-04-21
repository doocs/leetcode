# [825. Friends Of Appropriate Ages](https://leetcode.com/problems/friends-of-appropriate-ages)

[中文文档](/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README.md)

## Description

<p>Some people will make friend requests. The&nbsp;list of their ages is given and&nbsp;<code>ages[i]</code>&nbsp;is the age of the&nbsp;ith person.&nbsp;</p>

<p>Person A will NOT friend request person B (B != A) if any of the following conditions are true:</p>

<ul>
	<li><code>age[B]&nbsp;&lt;= 0.5 * age[A]&nbsp;+ 7</code></li>
	<li><code>age[B]&nbsp;&gt; age[A]</code></li>
	<li><code>age[B]&nbsp;&gt; 100 &amp;&amp;&nbsp;age[A]&nbsp;&lt; 100</code></li>
</ul>

<p>Otherwise, A will friend request B.</p>

<p>Note that if&nbsp;A requests B, B does not necessarily request A.&nbsp; Also, people will not friend request themselves.</p>

<p>How many total friend requests are made?</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>[16,16]
<strong>Output: </strong>2
<strong>Explanation: </strong>2 people friend request each other.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>[16,17,18]
<strong>Output: </strong>2
<strong>Explanation: </strong>Friend requests are made 17 -&gt; 16, 18 -&gt; 17.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>[20,30,100,110,120]
<strong>Output: </strong>3
<strong>Explanation: </strong>Friend requests are made 110 -&gt; 100, 120 -&gt; 110, 120 -&gt; 100.
</pre>

<p>&nbsp;</p>

<p>Notes:</p>

<ul>
	<li><code>1 &lt;= ages.length&nbsp;&lt;= 20000</code>.</li>
	<li><code>1 &lt;= ages[i] &lt;= 120</code>.</li>
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
