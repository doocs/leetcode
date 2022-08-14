# [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate)

[中文文档](/solution/0600-0699/0649.Dota2%20Senate/README.md)

## Description

<p>In the world of Dota2, there are two parties: the Radiant and the Dire.</p>

<p>The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise <strong>one</strong> of the two rights:</p>

<ul>
	<li><strong>Ban one senator&#39;s right:</strong> A senator can make another senator lose all his rights in this and all the following rounds.</li>
	<li><strong>Announce the victory:</strong> If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.</li>
</ul>

<p>Given a string <code>senate</code> representing each senator&#39;s party belonging. The character <code>&#39;R&#39;</code> and <code>&#39;D&#39;</code> represent the Radiant party and the Dire party. Then if there are <code>n</code> senators, the size of the given string will be <code>n</code>.</p>

<p>The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.</p>

<p>Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be <code>&quot;Radiant&quot;</code> or <code>&quot;Dire&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> senate = &quot;RD&quot;
<strong>Output:</strong> &quot;Radiant&quot;
<strong>Explanation:</strong> 
The first senator comes from Radiant and he can just ban the next senator&#39;s right in round 1. 
And the second senator can&#39;t exercise any rights anymore since his right has been banned. 
And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> senate = &quot;RDD&quot;
<strong>Output:</strong> &quot;Dire&quot;
<strong>Explanation:</strong> 
The first senator comes from Radiant and he can just ban the next senator&#39;s right in round 1. 
And the second senator can&#39;t exercise any rights anymore since his right has been banned. 
And the third senator comes from Dire and he can ban the first senator&#39;s right in round 1. 
And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == senate.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>senate[i]</code> is either <code>&#39;R&#39;</code> or <code>&#39;D&#39;</code>.</li>
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
