# [1687. Delivering Boxes from Storage to Ports](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports)

[中文文档](/solution/1600-1699/1687.Delivering%20Boxes%20from%20Storage%20to%20Ports/README.md)

## Description

<p>You have the task of delivering some boxes from storage to their ports using only one ship. However, this ship has a <strong>limit</strong> on the <strong>number of boxes</strong> and the <strong>total weight</strong> that it can carry.</p>

<p>You are given an array <code>boxes</code>, where <code>boxes[i] = [ports<sub>​​i</sub>​, weight<sub>i</sub>]</code>, and three integers <code>portsCount</code>, <code>maxBoxes</code>, and <code>maxWeight</code>.</p>

<ul>
	<li><code>ports<sub>​​i</sub></code> is the port where you need to deliver the <code>i<sup>th</sup></code> box and <code>weights<sub>i</sub></code> is the weight of the <code>i<sup>th</sup></code> box.</li>
	<li><code>portsCount</code> is the number of ports.</li>
	<li><code>maxBoxes</code> and <code>maxWeight</code> are the respective box and weight limits of the ship.</li>
</ul>

<p>The boxes need to be delivered <strong>in the order they are given</strong>. The ship will follow these steps:</p>

<ul>
	<li>The ship will take some number of boxes from the <code>boxes</code> queue, not violating the <code>maxBoxes</code> and <code>maxWeight</code> constraints.</li>
	<li>For each loaded box <strong>in order</strong>, the ship will make a <strong>trip</strong> to the port the box needs to be delivered to and deliver it. If the ship is already at the correct port, no <strong>trip</strong> is needed, and the box can immediately be delivered.</li>
	<li>The ship then makes a return <strong>trip</strong> to storage to take more boxes from the queue.</li>
</ul>

<p>The ship must end at storage after all the boxes have been delivered.</p>

<p>Return <em>the <strong>minimum</strong> number of <strong>trips</strong> the ship needs to make to deliver all boxes to their respective ports.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal strategy is as follows: 
- The ship takes all the boxes in the queue, goes to port 1, then port 2, then port 1 again, then returns to storage. 4 trips.
So the total number of trips is 4.
Note that the first and third boxes cannot be delivered together because the boxes need to be delivered in order (i.e. the second box needs to be delivered at port 2 before the third box).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal strategy is as follows: 
- The ship takes the first box, goes to port 1, then returns to storage. 2 trips.
- The ship takes the second, third and fourth boxes, goes to port 3, then returns to storage. 2 trips.
- The ship takes the fifth box, goes to port 3, then returns to storage. 2 trips.
So the total number of trips is 2 + 2 + 2 = 6.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal strategy is as follows:
- The ship takes the first and second boxes, goes to port 1, then returns to storage. 2 trips.
- The ship takes the third and fourth boxes, goes to port 2, then returns to storage. 2 trips.
- The ship takes the fifth and sixth boxes, goes to port 3, then returns to storage. 2 trips.
So the total number of trips is 2 + 2 + 2 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= boxes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= portsCount, maxBoxes, maxWeight &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ports<sub>​​i</sub> &lt;= portsCount</code></li>
	<li><code>1 &lt;= weights<sub>i</sub> &lt;= maxWeight</code></li>
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
