# [1125. Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team)

## Description
<p>In a project, you have a list of required skills <code>req_skills</code>,&nbsp;and a list of <code>people</code>.&nbsp; The i-th person <code>people[i]</code>&nbsp;contains a list of skills that person has.</p>

<p>Consider a <em>sufficient team</em>: a set of people such that for every required skill in <code>req_skills</code>, there is at least one person in the team who has that skill.&nbsp; We can represent these teams by the index of each person: for example, <code>team = [0, 1, 3]</code> represents the people with skills <code>people[0]</code>, <code>people[1]</code>, and <code>people[3]</code>.</p>

<p>Return <strong>any</strong>&nbsp;sufficient team of the smallest possible size, represented by the index of each person.</p>

<p>You may return the answer in any order.&nbsp; It is guaranteed an answer exists.</p>

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
	<li><code>1 &lt;= people.length &lt;= 60</code></li>
	<li><code>1 &lt;= people[i].length, req_skills[i].length, people[i][j].length&nbsp;&lt;= 16</code></li>
	<li>Elements of <code>req_skills</code> and <code>people[i]</code> are (respectively) distinct.</li>
	<li><code>req_skills[i][j], people[i][j][k]</code> are&nbsp;lowercase English letters.</li>
	<li>Every skill in <code>people[i]</code>&nbsp;is a skill in <code>req_skills</code>.</li>
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