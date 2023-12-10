# [2960. Count Tested Devices After Test Operations](https://leetcode.com/problems/count-tested-devices-after-test-operations)

[中文文档](/solution/2900-2999/2960.Count%20Tested%20Devices%20After%20Test%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>batteryPercentages</code> having length <code>n</code>, denoting the battery percentages of <code>n</code> <strong>0-indexed</strong> devices.</p>

<p>Your task is to test each device <code>i</code> <strong>in order</strong> from <code>0</code> to <code>n - 1</code>, by performing the following test operations:</p>

<ul>
	<li>If <code>batteryPercentages[i]</code> is <strong>greater</strong> than <code>0</code>:

    <ul>
    	<li><strong>Increment</strong> the count of tested devices.</li>
    	<li><strong>Decrease</strong> the battery percentage of all devices with indices <code>j</code> in the range <code>[i + 1, n - 1]</code> by <code>1</code>, ensuring their battery percentage <strong>never goes below</strong> <code>0</code>, i.e, <code>batteryPercentages[j] = max(0, batteryPercentages[j] - 1)</code>.</li>
    	<li>Move to the next device.</li>
    </ul>
    </li>
    <li>Otherwise, move to the next device without performing any test.</li>

</ul>

<p>Return <em>an integer denoting the number of devices that will be tested after performing the test operations in order.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> batteryPercentages = [1,1,2,1,3]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Performing the test operations in order starting from device 0:
At device 0, batteryPercentages[0] &gt; 0, so there is now 1 tested device, and batteryPercentages becomes [1,0,1,0,2].
At device 1, batteryPercentages[1] == 0, so we move to the next device without testing.
At device 2, batteryPercentages[2] &gt; 0, so there are now 2 tested devices, and batteryPercentages becomes [1,0,1,0,1].
At device 3, batteryPercentages[3] == 0, so we move to the next device without testing.
At device 4, batteryPercentages[4] &gt; 0, so there are now 3 tested devices, and batteryPercentages stays the same.
So, the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> batteryPercentages = [0,1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Performing the test operations in order starting from device 0:
At device 0, batteryPercentages[0] == 0, so we move to the next device without testing.
At device 1, batteryPercentages[1] &gt; 0, so there is now 1 tested device, and batteryPercentages becomes [0,1,1].
At device 2, batteryPercentages[2] &gt; 0, so there are now 2 tested devices, and batteryPercentages stays the same.
So, the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == batteryPercentages.length &lt;= 100 </code></li>
	<li><code>0 &lt;= batteryPercentages[i] &lt;= 100</code></li>
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
