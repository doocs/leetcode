---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4080. Design Bus Seat Booking System](https://leetcode.com/problems/design-bus-seat-booking-system)

[中文文档](/solution/4000-4099/4080.Design%20Bus%20Seat%20Booking%20System/README.md)

## Description

<!-- description:start -->

<p>You are asked to design a seat booking system for a bus.</p>

<p>The bus has <code>n</code> rows. Each row has four seats labeled <code>&quot;A&quot;</code>, <code>&quot;B&quot;</code>, <code>&quot;C&quot;</code>, and <code>&quot;D&quot;</code>.</p>

<p>Seats <code>&quot;A&quot;</code> and <code>&quot;D&quot;</code> are <strong>window</strong> seats; seats <code>&quot;B&quot;</code> and <code>&quot;C&quot;</code> are <strong>middle</strong> seats. In each row, seat <code>&quot;A&quot;</code> is <strong>adjacent</strong> to seat <code>&quot;B&quot;</code>, and seat <code>&quot;D&quot;</code> is <strong>adjacent</strong> to seat <code>&quot;C&quot;</code>.</p>

<p>Booking rules:</p>

<ul>
	<li>Booking a <strong>middle</strong> seat (<code>&quot;B&quot;</code> or <code>&quot;C&quot;</code>) always takes 1 second.</li>
	<li>Booking a <strong>window</strong> seat (<code>&quot;A&quot;</code> or <code>&quot;D&quot;</code>) takes 1 second if its <strong>adjacent middle</strong> seat is <strong>not</strong> booked, or 3 seconds (an extra delay of 2 seconds) if its <strong>adjacent middle</strong> seat is <strong>already</strong> booked.</li>
</ul>

<p>A seat is identified by a string such as <code>&quot;A1&quot;</code> or <code>&quot;D20&quot;</code> (one letter from <code>A</code> to <code>D</code> followed by its row number).</p>

<p>Implement the <code>BusBooking</code> class:</p>

<ul>
	<li><code>BusBooking(int n)</code>: Initializes the system with <code>n</code> <strong>empty</strong> rows.</li>
	<li><code>void toggle(string seat)</code>: Books <code>seat</code> if it is free, or frees it if it is already booked.</li>
	<li><code>int getTotalTime()</code>: Returns the total time (in seconds) taken to book all <strong>currently</strong> booked seats, where the seats are booked in the order their <code>toggle</code> calls were made. If no seats are currently booked, return 0.</li>
	<li><code>int getMinTime()</code>: Returns the <strong>smallest</strong> possible total time (in seconds) to book all <strong>currently</strong> booked seats, over every possible order of booking them. If no seats are currently booked, return 0.</li>
</ul>

<p><strong>Note</strong>: The time cost of a seat is fixed at the moment it is booked, based on the seats booked at that time. Booking or freeing other seats afterward does not change the cost of a seat that is already booked.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;BusBooking&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;]<br />
[[20], [&quot;B3&quot;], [&quot;A3&quot;], [], [], [&quot;C3&quot;], [&quot;D3&quot;], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 4, 2, null, null, 8, 4] </span></p>

<p><strong>Explanation</strong></p>
BusBooking busBooking = new BusBooking(20); // start with 20 empty rows<br />
busBooking.toggle(&quot;B3&quot;); // book <code>&quot;B3&quot;</code>, a middle seat, which costs 1 second. Total time is now 1.<br />
busBooking.toggle(&quot;A3&quot;); // book <code>&quot;A3&quot;</code>, a window seat. Its adjacent middle <code>&quot;B3&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>1 + 3 = 4</code>.<br />
busBooking.getTotalTime(); // return 4, since in toggle order <code>&quot;B3&quot;</code> costs 1 and <code>&quot;A3&quot;</code> costs 3.<br />
busBooking.getMinTime(); // return 2. Booking <code>&quot;A3&quot;</code> before <code>&quot;B3&quot;</code> avoids the delay, so 2 seats takes 2 second to book.<br />
busBooking.toggle(&quot;C3&quot;); // book <code>&quot;C3&quot;</code>, a middle seat, which costs 1 second. Total time is now <code>1 + 4 = 5</code>.<br />
busBooking.toggle(&quot;D3&quot;); // book <code>&quot;D3&quot;</code>, a window seat. Its adjacent middle <code>&quot;C3&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>5 + 3 = 8</code>.<br />
busBooking.getTotalTime(); // return 8, since in toggle order the seat costs are 1, 3, 1, 3.<br />
busBooking.getMinTime(); // return 4. With the best possible booking order, no delay is added, so the 4 booked seats take <code>4 * 1 = 4</code> seconds.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;BusBooking&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;]<br />
[[10], [&quot;C7&quot;], [&quot;D7&quot;], [], [&quot;D7&quot;], [&quot;C7&quot;], [&quot;D7&quot;], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 4, null, null, null, 1, 1] </span></p>

<p><strong>Explanation</strong></p>
BusBooking busBooking = new BusBooking(10); // start with 10 empty rows<br />
busBooking.toggle(&quot;C7&quot;); // book <code>&quot;C7&quot;</code>, a middle seat, which costs 1 second. Total time is now 1.<br />
busBooking.toggle(&quot;D7&quot;); // book <code>&quot;D7&quot;</code>, a window seat. Its adjacent middle <code>&quot;C7&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>1 + 3 = 4</code>.<br />
busBooking.getTotalTime(); // return 4, since in toggle order <code>&quot;C7&quot;</code> costs 1 and <code>&quot;D7&quot;</code> costs 3.<br />
busBooking.toggle(&quot;D7&quot;); // <code>&quot;D7&quot;</code> is already booked, so free it. This removes its cost of 3. Total time is now <code>4 - 3 = 1</code>.<br />
busBooking.toggle(&quot;C7&quot;); // <code>&quot;C7&quot;</code> is already booked, so free it. This removes its cost of 1. Total time is now <code>1 - 1 = 0</code>, and row 7 is empty.<br />
busBooking.toggle(&quot;D7&quot;); // book <code>&quot;D7&quot;</code> again, a window seat. Its adjacent middle <code>&quot;C7&quot;</code> is not booked this time, so it costs only 1 second. Total time is now <code>0 + 1 = 1</code>.<br />
busBooking.getTotalTime(); // return 1, since only <code>&quot;D7&quot;</code> is booked and it cost 1 this time.<br />
busBooking.getMinTime(); // return 1. With the best possible booking order, the 1 booked seat takes <code>1 * 1 = 1</code> second.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>seat[0]</code> is one of <code>&#39;A&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;D&#39;</code>.</li>
	<li>The remaining characters of <code>seat</code> form an integer <code>row</code> with <code>1 &lt;= row &lt;= n</code>.</li>
	<li>The total number of calls to <code>toggle</code>, <code>getTotalTime</code>, and <code>getMinTime</code> does not exceed <code>10<sup>5</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
