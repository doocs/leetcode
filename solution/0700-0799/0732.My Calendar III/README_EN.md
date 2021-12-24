# [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii)

[中文文档](/solution/0700-0799/0732.My%20Calendar%20III/README.md)

## Description

<p>A <code>k</code>-booking happens when <code>k</code> events have some non-empty intersection (i.e., there is some time that is common to all <code>k</code> events.)</p>

<p>You are given some events <code>[start, end)</code>, after each given event, return an integer <code>k</code> representing the maximum <code>k</code>-booking between all the previous events.</p>

<p>Implement the <code>MyCalendarThree</code> class:</p>

<ul>
	<li><code>MyCalendarThree()</code> Initializes the object.</li>
	<li><code>int book(int start, int end)</code> Returns an integer <code>k</code> representing the largest integer such that there exists a <code>k</code>-booking in the calendar.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyCalendarThree&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
<strong>Output</strong>
[null, 1, 1, 2, 3, 3, 3]

<strong>Explanation</strong>
MyCalendarThree myCalendarThree = new MyCalendarThree();
myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
myCalendarThree.book(5, 10); // return 3
myCalendarThree.book(25, 55); // return 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>400</code> calls will be made to <code>book</code>.</li>
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
