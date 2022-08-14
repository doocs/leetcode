# [1733. Minimum Number of People to Teach](https://leetcode.com/problems/minimum-number-of-people-to-teach)

[中文文档](/solution/1700-1799/1733.Minimum%20Number%20of%20People%20to%20Teach/README.md)

## Description

<p>On a social network consisting of <code>m</code> users and some friendships between users, two users can communicate with each other if they know a common language.</p>

<p>You are given an integer <code>n</code>, an array <code>languages</code>, and an array <code>friendships</code> where:</p>

<ul>
	<li>There are <code>n</code> languages numbered <code>1</code> through <code>n</code>,</li>
	<li><code>languages[i]</code> is the set of languages the <code>i<sup>​​​​​​th</sup></code>​​​​ user knows, and</li>
	<li><code>friendships[i] = [u<sub>​​​​​​i</sub>​​​, v<sub>​​​​​​i</sub>]</code> denotes a friendship between the users <code>u<sup>​​​​​</sup><sub>​​​​​​i</sub></code>​​​​​ and <code>v<sub>i</sub></code>.</li>
</ul>

<p>You can choose <strong>one</strong> language and teach it to some users so that all friends can communicate with each other. Return <i data-stringify-type="italic">the</i> <i><strong>minimum</strong> </i><i data-stringify-type="italic">number of users you need to teach.</i></p>
Note that friendships are not transitive, meaning if <code>x</code> is a friend of <code>y</code> and <code>y</code> is a friend of <code>z</code>, this doesn&#39;t guarantee that <code>x</code> is a friend of <code>z</code>.
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can either teach user 1 the second language or user 2 the first language.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Teach the third language to users 1 and 3, yielding two users to teach.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>languages.length == m</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>1 &lt;= languages[i].length &lt;= n</code></li>
	<li><code>1 &lt;= languages[i][j] &lt;= n</code></li>
	<li><code>1 &lt;= u<sub>​​​​​​i</sub> &lt; v<sub>​​​​​​i</sub> &lt;= languages.length</code></li>
	<li><code>1 &lt;= friendships.length &lt;= 500</code></li>
	<li>All tuples <code>(u<sub>​​​​​i, </sub>v<sub>​​​​​​i</sub>)</code> are unique</li>
	<li><code>languages[i]</code> contains only unique values</li>
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
