# [2276. Count Integers in Intervals](https://leetcode.com/problems/count-integers-in-intervals)

[中文文档](/solution/2200-2299/2276.Count%20Integers%20in%20Intervals/README.md)

## Description

<p>Given an <strong>empty</strong> set of intervals, implement a data structure that can:</p>

<ul>
	<li><strong>Add</strong> an interval to the set of intervals.</li>
	<li><strong>Count</strong> the number of integers that are present in <strong>at least one</strong> interval.</li>
</ul>

<p>Implement the <code>CountIntervals</code> class:</p>

<ul>
	<li><code>CountIntervals()</code> Initializes the object with an empty set of intervals.</li>
	<li><code>void add(int left, int right)</code> Adds the interval <code>[left, right]</code> to the set of intervals.</li>
	<li><code>int count()</code> Returns the number of integers that are present in <strong>at least one</strong> interval.</li>
</ul>

<p><strong>Note</strong> that an interval <code>[left, right]</code> denotes all the integers <code>x</code> where <code>left &lt;= x &lt;= right</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CountIntervals&quot;, &quot;add&quot;, &quot;add&quot;, &quot;count&quot;, &quot;add&quot;, &quot;count&quot;]
[[], [2, 3], [7, 10], [], [5, 8], []]
<strong>Output</strong>
[null, null, null, 6, null, 8]

<strong>Explanation</strong>
CountIntervals countIntervals = new CountIntervals(); // initialize the object with an empty set of intervals. 
countIntervals.add(2, 3);  // add [2, 3] to the set of intervals.
countIntervals.add(7, 10); // add [7, 10] to the set of intervals.
countIntervals.count();    // return 6
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 7, 8, 9, and 10 are present in the interval [7, 10].
countIntervals.add(5, 8);  // add [5, 8] to the set of intervals.
countIntervals.count();    // return 8
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 5 and 6 are present in the interval [5, 8].
                           // the integers 7 and 8 are present in the intervals [5, 8] and [7, 10].
                           // the integers 9 and 10 are present in the interval [7, 10].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>add</code> and <code>count</code>.</li>
	<li>At least <strong>one</strong> call will be made to <code>count</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
