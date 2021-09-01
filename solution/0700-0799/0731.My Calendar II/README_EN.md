# [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii)

[中文文档](/solution/0700-0799/0731.My%20Calendar%20II/README.md)

## Description

<p>Implement a <code>MyCalendarTwo</code> class to store your events. A new event can be added if adding the event will not cause a <b>triple</b> booking.</p>



<p>Your class will have one method, <code>book(int start, int end)</code>. Formally, this represents a booking on the half open interval <code>[start, end)</code>, the range of real numbers <code>x</code> such that <code>start &lt;= x &lt; end</code>.</p>



<p>A <i>triple booking</i> happens when <b>three</b> events have some non-empty intersection (ie., there is some time that is common to all 3 events.)</p>



<p>For each call to the method <code>MyCalendar.book</code>, return <code>true</code> if the event can be added to the calendar successfully without causing a <b>triple</b> booking. Otherwise, return <code>false</code> and do not add the event to the calendar.</p>

Your class will be called like this: <code>MyCalendar cal = new MyCalendar();</code> <code>MyCalendar.book(start, end)</code>



<p><b>Example 1:</b></p>



<pre>

MyCalendar();

MyCalendar.book(10, 20); // returns true

MyCalendar.book(50, 60); // returns true

MyCalendar.book(10, 40); // returns true

MyCalendar.book(5, 15); // returns false

MyCalendar.book(5, 10); // returns true

MyCalendar.book(25, 55); // returns true

<b>Explanation:</b> 

The first two events can be booked.  The third event can be double booked.

The fourth event (5, 15) can&#39;t be booked, because it would result in a triple booking.

The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.

The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;

the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

</pre>



<p>&nbsp;</p>



<p><b>Note:</b></p>



<ul>
	<li>The number of calls to <code>MyCalendar.book</code> per test case will be at most <code>1000</code>.</li>
	<li>In calls to <code>MyCalendar.book(start, end)</code>, <code>start</code> and <code>end</code> are integers in the range <code>[0, 10^9]</code>.</li>
</ul>



<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class MyCalendarTwo {
    List<int[]> calendar;
    List<int[]> duplicationList;

    MyCalendarTwo() {
        calendar = new ArrayList<>();
        duplicationList = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] item : duplicationList) {
            if (item[0] < end && item[1] > start) {
                return false;
            }
        }
        for (int[] item : calendar) {
            if (item[0] < end && item[1] > start) {
                duplicationList.add(new int[]{Math.max(start, item[0]), Math.min(end, item[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
