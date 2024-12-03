---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3369.Design%20an%20Array%20Statistics%20Tracker/README_EN.md
tags:
    - Queue
    - Hash Table
    - Binary Search
---

<!-- problem:start -->

# [3369. Design an Array Statistics Tracker 🔒](https://leetcode.com/problems/design-an-array-statistics-tracker)

[中文文档](/solution/3300-3399/3369.Design%20an%20Array%20Statistics%20Tracker/README.md)

## Description

<!-- description:start -->

<p>Design a data structure that keeps track of the values in it and answers some queries regarding their mean, median, and mode.</p>

<p>Implement the <code>StatisticsTracker</code> class.</p>

<ul>
	<li><code>StatisticsTracker()</code>: Initialize&nbsp;the <code>StatisticsTracker</code> object with an empty array.</li>
	<li><code>void addNumber(int number)</code>: Add&nbsp;<code>number</code> to the data structure.</li>
	<li><code>void removeFirstAddedNumber()</code>: Remove&nbsp;the earliest added number from the data structure.</li>
	<li><code>int getMean()</code>: Return&nbsp;the floored <strong>mean</strong> of the numbers in the data structure.</li>
	<li><code>int getMedian()</code>: Return&nbsp;the <strong>median</strong> of the numbers in the data structure.</li>
	<li><code>int getMode()</code>: Return&nbsp;the <strong>mode</strong> of the numbers in the data structure. If there are multiple modes, return the smallest one.</li>
</ul>

<p><strong>Note</strong>:</p>

<ul>
	<li>The <strong>mean</strong> of an array is the sum of all the values divided by the number of values in the array.</li>
	<li>The <strong>median</strong> of an array is the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the larger of the two values is taken.</li>
	<li>The <strong>mode</strong> of an array is the element that appears most often in the array.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;StatisticsTracker&quot;, &quot;addNumber&quot;, &quot;addNumber&quot;, &quot;addNumber&quot;, &quot;addNumber&quot;, &quot;getMean&quot;, &quot;getMedian&quot;, &quot;getMode&quot;, &quot;removeFirstAddedNumber&quot;, &quot;getMode&quot;]<br />
[[], [4], [4], [2], [3], [], [], [], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, null, null, 3, 4, 4, null, 2] </span></p>

<p><strong>Explanation</strong></p>
StatisticsTracker statisticsTracker = new StatisticsTracker();<br />
statisticsTracker.addNumber(4); // The data structure now contains [4]<br />
statisticsTracker.addNumber(4); // The data structure now contains [4, 4]<br />
statisticsTracker.addNumber(2); // The data structure now contains [4, 4, 2]<br />
statisticsTracker.addNumber(3); // The data structure now contains [4, 4, 2, 3]<br />
statisticsTracker.getMean(); // return 3<br />
statisticsTracker.getMedian(); // return 4<br />
statisticsTracker.getMode(); // return 4<br />
statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [4, 2, 3]<br />
statisticsTracker.getMode(); // return 2</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;StatisticsTracker&quot;, &quot;addNumber&quot;, &quot;addNumber&quot;, &quot;getMean&quot;, &quot;removeFirstAddedNumber&quot;, &quot;addNumber&quot;, &quot;addNumber&quot;, &quot;removeFirstAddedNumber&quot;, &quot;getMedian&quot;, &quot;addNumber&quot;, &quot;getMode&quot;]<br />
[[], [9], [5], [], [], [5], [6], [], [], [8], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 7, null, null, null, null, 6, null, 5] </span></p>

<p><strong>Explanation</strong></p>
StatisticsTracker statisticsTracker = new StatisticsTracker();<br />
statisticsTracker.addNumber(9); // The data structure now contains [9]<br />
statisticsTracker.addNumber(5); // The data structure now contains [9, 5]<br />
statisticsTracker.getMean(); // return 7<br />
statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [5]<br />
statisticsTracker.addNumber(5); // The data structure now contains [5, 5]<br />
statisticsTracker.addNumber(6); // The data structure now contains [5, 5, 6]<br />
statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [5, 6]<br />
statisticsTracker.getMedian(); // return 6<br />
statisticsTracker.addNumber(8); // The data structure now contains [5, 6, 8]<br />
statisticsTracker.getMode(); // return 5</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= number &lt;= 10<sup>9</sup></code></li>
	<li>At most, <code>10<sup>5</sup></code> calls will be made to <code>addNumber</code>, <code>removeFirstAddedNumber</code>, <code>getMean</code>, <code>getMedian</code>, and <code>getMode</code> in total.</li>
	<li><code>removeFirstAddedNumber</code>, <code>getMean</code>, <code>getMedian</code>, and <code>getMode</code> will be called only if there is at least one element in the data structure.</li>
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
