# [2188. Minimum Time to Finish the Race](https://leetcode.com/problems/minimum-time-to-finish-the-race)

[中文文档](/solution/2100-2199/2188.Minimum%20Time%20to%20Finish%20the%20Race/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>tires</code> where <code>tires[i] = [f<sub>i</sub>, r<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> tire can finish its <code>x<sup>th</sup></code> successive lap in <code>f<sub>i</sub> * r<sub>i</sub><sup>(x-1)</sup></code> seconds.</p>

<ul>
	<li>For example, if <code>f<sub>i</sub> = 3</code> and <code>r<sub>i</sub> = 2</code>, then the tire would finish its <code>1<sup>st</sup></code> lap in <code>3</code> seconds, its <code>2<sup>nd</sup></code> lap in <code>3 * 2 = 6</code> seconds, its <code>3<sup>rd</sup></code> lap in <code>3 * 2<sup>2</sup> = 12</code> seconds, etc.</li>
</ul>

<p>You are also given an integer <code>changeTime</code> and an integer <code>numLaps</code>.</p>

<p>The race consists of <code>numLaps</code> laps and you may start the race with <strong>any</strong> tire. You have an <strong>unlimited</strong> supply of each tire and after every lap, you may <strong>change</strong> to any given tire (including the current tire type) if you wait <code>changeTime</code> seconds.</p>

<p>Return<em> the <strong>minimum</strong> time to finish the race.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
<strong>Output:</strong> 21
<strong>Explanation:</strong> 
Lap 1: Start with tire 0 and finish the lap in 2 seconds.
Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in another 2 seconds.
Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
The minimum time to complete the race is 21 seconds.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
<strong>Output:</strong> 25
<strong>Explanation:</strong> 
Lap 1: Start with tire 1 and finish the lap in 2 seconds.
Lap 2: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
Lap 3: Change tires to a new tire 1 for 6 seconds and then finish the lap in another 2 seconds.
Lap 4: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
Lap 5: Change tires to tire 0 for 6 seconds then finish the lap in another 1 second.
Total time = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 seconds.
The minimum time to complete the race is 25 seconds. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tires.length &lt;= 10<sup>5</sup></code></li>
	<li><code>tires[i].length == 2</code></li>
	<li><code>1 &lt;= f<sub>i</sub>, changeTime &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= numLaps &lt;= 1000</code></li>
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
