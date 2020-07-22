# [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii)

## Description
<p>Implement a <code>MyCalendarThree</code> class to store your events. A new event can <b>always</b> be added.</p>



<p>Your class will have one method, <code>book(int start, int end)</code>. Formally, this represents a booking on the half open interval <code>[start, end)</code>, the range of real numbers <code>x</code> such that <code>start &lt;= x &lt; end</code>.</p>



<p>A <i>K-booking</i> happens when <b>K</b> events have some non-empty intersection (ie., there is some time that is common to all K events.)</p>



<p>For each call to the method <code>MyCalendar.book</code>, return an integer <code>K</code> representing the largest integer such that there exists a <code>K</code>-booking in the calendar.</p>

Your class will be called like this: <code>MyCalendarThree cal = new MyCalendarThree();</code> <code>MyCalendarThree.book(start, end)</code>



<p><b>Example 1:</b></p>



<pre>

MyCalendarThree();

MyCalendarThree.book(10, 20); // returns 1

MyCalendarThree.book(50, 60); // returns 1

MyCalendarThree.book(10, 40); // returns 2

MyCalendarThree.book(5, 15); // returns 3

MyCalendarThree.book(5, 10); // returns 3

MyCalendarThree.book(25, 55); // returns 3

<b>Explanation:</b> 

The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.

The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.

The remaining events cause the maximum K-booking to be only a 3-booking.

Note that the last event locally causes a 2-booking, but the answer is still 3 because

eg. [10, 20), [10, 40), and [5, 15) are still triple booked.

</pre>



<p>&nbsp;</p>



<p><b>Note:</b></p>



<ul>

	<li>The number of calls to <code>MyCalendarThree.book</code> per test case will be at most <code>400</code>.</li>

	<li>In calls to <code>MyCalendarThree.book(start, end)</code>, <code>start</code> and <code>end</code> are integers in the range <code>[0, 10^9]</code>.</li>

</ul>



<p>&nbsp;</p>


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