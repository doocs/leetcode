# [855. Exam Room](https://leetcode.com/problems/exam-room)

[中文文档](/solution/0800-0899/0855.Exam%20Room/README.md)

## Description

<p>In an exam room, there are <code>N</code> seats in a single row, numbered <code>0, 1, 2, ..., N-1</code>.</p>

<p>When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.&nbsp; If there are multiple such seats, they sit in the seat with the lowest number.&nbsp; (Also, if no one is in the room, then the student sits at seat number 0.)</p>

<p>Return a class <code>ExamRoom(int N)</code>&nbsp;that exposes two functions: <code>ExamRoom.seat()</code>&nbsp;returning an <code>int</code>&nbsp;representing what seat the student sat in, and <code>ExamRoom.leave(int p)</code>&nbsp;representing that the student in seat number <code>p</code>&nbsp;now leaves the room.&nbsp; It is guaranteed that any calls to <code>ExamRoom.leave(p)</code> have a student sitting in seat <code>p</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;ExamRoom&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;leave&quot;,&quot;seat&quot;]</span>, <span id="example-input-1-2">[[10],[],[],[],[],[4],[]]</span>

<strong>Output: </strong><span id="example-output-1">[null,0,9,4,2,null,5]</span>

<span><strong>Explanation</strong>:

ExamRoom(10) -&gt; null

seat() -&gt; 0, no one is in the room, then the student sits at seat number 0.

seat() -&gt; 9, the student sits at the last seat number 9.

seat() -&gt; 4, the student sits at the last seat number 4.

seat() -&gt; 2, the student sits at the last seat number 2.

leave(4) -&gt; null

seat() -&gt; 5, the student sits at the last seat number 5.</span>

</pre>

<p><span>​​​​​​​</span></p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
	<li><code>ExamRoom.seat()</code> and <code>ExamRoom.leave()</code> will be called at most <code>10^4</code> times across all test cases.</li>
	<li>Calls to <code>ExamRoom.leave(p)</code> are guaranteed to have a student currently sitting in seat number <code>p</code>.</li>
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
