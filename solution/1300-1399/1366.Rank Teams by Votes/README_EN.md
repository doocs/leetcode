# [1366. Rank Teams by Votes](https://leetcode.com/problems/rank-teams-by-votes)

[中文文档](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README.md)

## Description

<p>In a special ranking system,&nbsp;each voter gives a rank from highest to lowest to all teams participated in the competition.</p>

<p>The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.</p>

<p>Given an array of strings <code>votes</code> which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.</p>

<p>Return <em>a string of all teams</em> <strong>sorted</strong> by the ranking system.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ABC&quot;,&quot;ACB&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;ACB&quot;]
<strong>Output:</strong> &quot;ACB&quot;
<strong>Explanation:</strong> Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
Team B was ranked second by 2 voters and was ranked third by 3 voters.
Team C was ranked second by 3 voters and was ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team and team B is the third.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;WXYZ&quot;,&quot;XYZW&quot;]
<strong>Output:</strong> &quot;XWYZ&quot;
<strong>Explanation:</strong> X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position while W doesn&#39;t have any votes as second position. 
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;]
<strong>Output:</strong> &quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;
<strong>Explanation:</strong> Only one voter so his votes are used for the ranking.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;BCA&quot;,&quot;CAB&quot;,&quot;CBA&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;BAC&quot;]
<strong>Output:</strong> &quot;ABC&quot;
<strong>Explanation:</strong> 
Team A was ranked first by 2 voters, second by 2 voters and third by 2 voters.
Team B was ranked first by 2 voters, second by 2 voters and third by 2 voters.
Team C was ranked first by 2 voters, second by 2 voters and third by 2 voters.
There is a tie and we rank teams ascending by their IDs.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;M&quot;,&quot;M&quot;,&quot;M&quot;,&quot;M&quot;]
<strong>Output:</strong> &quot;M&quot;
<strong>Explanation:</strong> Only team M in the competition so it has the first rank.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= votes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= votes[i].length &lt;= 26</code></li>
	<li><code>votes[i].length ==&nbsp;votes[j].length</code> for&nbsp;<code>0 &lt;= i, j &lt; votes.length</code>.</li>
	<li><code>votes[i][j]</code> is an English <strong>upper-case</strong> letter.</li>
	<li>All characters of <code>votes[i]</code> are unique.</li>
	<li>All the characters&nbsp;that occur&nbsp;in <code>votes[0]</code> <strong>also&nbsp;occur</strong>&nbsp;in <code>votes[j]</code> where <code>1 &lt;= j &lt; votes.length</code>.</li>
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
