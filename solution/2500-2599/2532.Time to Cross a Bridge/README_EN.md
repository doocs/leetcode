# [2532. Time to Cross a Bridge](https://leetcode.com/problems/time-to-cross-a-bridge)

[中文文档](/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README.md)

## Description

<p>There are <code>k</code> workers who want to move <code>n</code> boxes from an old warehouse to a new one. You are given the two integers <code>n</code> and <code>k</code>, and a 2D integer array <code>time</code> of size <code>k x 4</code> where <code>time[i] = [leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub>]</code>.</p>

<p>The warehouses are separated by a river and connected by a bridge. The old warehouse is on the right bank of the river, and the new warehouse is on the left bank of the river. Initially, all <code>k</code> workers are waiting on the left side of the bridge. To move the boxes, the <code>i<sup>th</sup></code> worker (<strong>0-indexed</strong>) can :</p>

<ul>
	<li>Cross the bridge from the left bank (new warehouse) to the right bank (old warehouse) in <code>leftToRight<sub>i</sub></code> minutes.</li>
	<li>Pick a box from the old warehouse and return to the bridge in <code>pickOld<sub>i</sub></code> minutes. Different workers can pick up their boxes simultaneously.</li>
	<li>Cross the bridge from the right bank (old warehouse) to the left bank (new warehouse) in <code>rightToLeft<sub>i</sub></code> minutes.</li>
	<li>Put the box in the new warehouse and return to the bridge in <code>putNew<sub>i</sub></code> minutes. Different workers can put their boxes simultaneously.</li>
</ul>

<p>A worker <code>i</code> is <strong>less efficient</strong> than a worker <code>j</code> if either condition is met:</p>

<ul>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> &gt; leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code></li>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> == leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code> and <code>i &gt; j</code></li>
</ul>

<p>The following rules regulate the movement of the workers through the bridge :</p>

<ul>
	<li>If a worker <code>x</code> reaches the bridge while another worker <code>y</code> is crossing the bridge, <code>x</code> waits at their side of the bridge.</li>
	<li>If the bridge is free, the worker waiting on the right side of the bridge gets to cross the bridge. If more than one worker is waiting on the right side, the one with <strong>the lowest efficiency</strong> crosses first.</li>
	<li>If the bridge is free and no worker is waiting on the right side, and at least one box remains at the old warehouse, the worker on the left side of the river gets to cross the bridge. If more than one worker is waiting on the left side, the one with <strong>the lowest efficiency</strong> crosses first.</li>
</ul>

<p>Return <em>the instance of time at which the last worker <strong>reaches the left bank</strong> of the river after all n boxes have been put in the new warehouse</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
<strong>Output:</strong> 6
<strong>Explanation: </strong>
From 0 to 1: worker 2 crosses the bridge from the left bank to the right bank.
From 1 to 2: worker 2 picks up a box from the old warehouse.
From 2 to 6: worker 2 crosses the bridge from the right bank to the left bank.
From 6 to 7: worker 2 puts a box at the new warehouse.
The whole process ends after 7 minutes. We return 6 because the problem asks for the instance of time at which the last worker reaches the left bank.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
<strong>Output:</strong> 50
<strong>Explanation:</strong> 
From 0 &nbsp;to 10: worker 1 crosses the bridge from the left bank to the right bank.
From 10 to 20: worker 1 picks up a box from the old warehouse.
From 10 to 11: worker 0 crosses the bridge from the left bank to the right bank.
From 11 to 20: worker 0 picks up a box from the old warehouse.
From 20 to 30: worker 1 crosses the bridge from the right bank to the left bank.
From 30 to 40: worker 1 puts a box at the new warehouse.
From 30 to 31: worker 0 crosses the bridge from the right bank to the left bank.
From 31 to 39: worker 0 puts a box at the new warehouse.
From 39 to 40: worker 0 crosses the bridge from the left bank to the right bank.
From 40 to 49: worker 0 picks up a box from the old warehouse.
From 49 to 50: worker 0 crosses the bridge from the right bank to the left bank.
From 50 to 58: worker 0 puts a box at the new warehouse.
The whole process ends after 58 minutes. We return 50 because the problem asks for the instance of time at which the last worker reaches the left bank.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>4</sup></code></li>
	<li><code>time.length == k</code></li>
	<li><code>time[i].length == 4</code></li>
	<li><code>1 &lt;= leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub> &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
