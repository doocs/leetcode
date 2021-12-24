# [1125. Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team)

[中文文档](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README.md)

## Description

<p>In a project, you have a list of required skills <code>req_skills</code>, and a list of people. The <code>i<sup>th</sup></code> person <code>people[i]</code> contains a list of skills that the person has.</p>

<p>Consider a sufficient team: a set of people such that for every required skill in <code>req_skills</code>, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.</p>

<ul>
	<li>For example, <code>team = [0, 1, 3]</code> represents the people with skills <code>people[0]</code>, <code>people[1]</code>, and <code>people[3]</code>.</li>
</ul>

<p>Return <em>any sufficient team of the smallest possible size, represented by the index of each person</em>. You may return the answer in <strong>any order</strong>.</p>

<p>It is <strong>guaranteed</strong> an answer exists.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
<strong>Output:</strong> [0,2]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= req_skills.length &lt;= 16</code></li>
	<li><code>1 &lt;= req_skills[i].length &lt;= 16</code></li>
	<li><code>req_skills[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>req_skills</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= people.length &lt;= 60</code></li>
	<li><code>0 &lt;= people[i].length &lt;= 16</code></li>
	<li><code>1 &lt;= people[i][j].length &lt;= 16</code></li>
	<li><code>people[i][j]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>people[i]</code> are <strong>unique</strong>.</li>
	<li>Every skill in <code>people[i]</code> is a skill in <code>req_skills</code>.</li>
	<li>It is guaranteed a sufficient team exists.</li>
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
