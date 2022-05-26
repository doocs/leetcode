# [2019. The Score of Students Solving Math Expression](https://leetcode.com/problems/the-score-of-students-solving-math-expression)

[中文文档](/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/README.md)

## Description

<p>You are given a string <code>s</code> that contains digits <code>0-9</code>, addition symbols <code>&#39;+&#39;</code>, and multiplication symbols <code>&#39;*&#39;</code> <strong>only</strong>, representing a <strong>valid</strong> math expression of <strong>single digit numbers</strong> (e.g., <code>3+5*2</code>). This expression was given to <code>n</code> elementary school students. The students were instructed to get the answer of the expression by following this <strong>order of operations</strong>:</p>

<ol>
	<li>Compute <strong>multiplication</strong>, reading from <strong>left to right</strong>; Then,</li>
	<li>Compute <strong>addition</strong>, reading from <strong>left to right</strong>.</li>
</ol>

<p>You are given an integer array <code>answers</code> of length <code>n</code>, which are the submitted answers of the students in no particular order. You are asked to grade the <code>answers</code>, by following these <strong>rules</strong>:</p>

<ul>
	<li>If an answer <strong>equals</strong> the correct answer of the expression, this student will be rewarded <code>5</code> points;</li>
	<li>Otherwise, if the answer <strong>could be interpreted</strong> as if the student applied the operators <strong>in the wrong order</strong> but had <strong>correct arithmetic</strong>, this student will be rewarded <code>2</code> points;</li>
	<li>Otherwise, this student will be rewarded <code>0</code> points.</li>
</ul>

<p>Return <em>the sum of the points of the students</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/images/student_solving_math.png" style="width: 678px; height: 109px;" />
<pre>
<strong>Input:</strong> s = &quot;7+3*1*2&quot;, answers = [20,13,42]
<strong>Output:</strong> 7
<strong>Explanation:</strong> As illustrated above, the correct answer of the expression is 13, therefore one student is rewarded 5 points: [20,<u><strong>13</strong></u>,42]
A student might have applied the operators in this wrong order: ((7+3)*1)*2 = 20. Therefore one student is rewarded 2 points: [<u><strong>20</strong></u>,13,42]
The points for the students are: [2,5,0]. The sum of the points is 2+5+0=7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3+5*2&quot;, answers = [13,0,10,13,13,16,16]
<strong>Output:</strong> 19
<strong>Explanation:</strong> The correct answer of the expression is 13, therefore three students are rewarded 5 points each: [<strong><u>13</u></strong>,0,10,<strong><u>13</u></strong>,<strong><u>13</u></strong>,16,16]
A student might have applied the operators in this wrong order: ((3+5)*2 = 16. Therefore two students are rewarded 2 points: [13,0,10,13,13,<strong><u>16</u></strong>,<strong><u>16</u></strong>]
The points for the students are: [5,0,0,5,5,2,2]. The sum of the points is 5+0+0+5+5+2+2=19.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;6+0*1&quot;, answers = [12,9,6,4,8,6]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The correct answer of the expression is 6.
If a student had incorrectly done (6+0)*1, the answer would also be 6.
By the rules of grading, the students will still be rewarded 5 points (as they got the correct answer), not 2 points.
The points for the students are: [0,0,5,0,0,5]. The sum of the points is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 31</code></li>
	<li><code>s</code> represents a valid expression that contains only digits <code>0-9</code>, <code>&#39;+&#39;</code>, and <code>&#39;*&#39;</code> only.</li>
	<li>All the integer operands in the expression are in the <strong>inclusive</strong> range <code>[0, 9]</code>.</li>
	<li><code>1 &lt;=</code> The count of all operators (<code>&#39;+&#39;</code> and <code>&#39;*&#39;</code>) in the math expression <code>&lt;= 15</code></li>
	<li>Test data are generated such that the correct answer of the expression is in the range of <code>[0, 1000]</code>.</li>
	<li><code>n == answers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= answers[i] &lt;= 1000</code></li>
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
