# [1701. Average Waiting Time](https://leetcode.com/problems/average-waiting-time)

[中文文档](/solution/1700-1799/1701.Average%20Waiting%20Time/README.md)

## Description

<p>There is a restaurant with a single chef. You are given an array <code>customers</code>, where <code>customers[i] = [arrival<sub>i</sub>, time<sub>i</sub>]:</code></p>

<ul>
	<li><code>arrival<sub>i</sub></code> is the arrival time of the <code>i<sup>th</sup></code> customer. The arrival times are sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>time<sub>i</sub></code> is the time needed to prepare the order of the <code>i<sup>th</sup></code> customer.</li>
</ul>

<p>When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers <strong>in the order they were given in the input</strong>.</p>

<p>Return <em>the <strong>average</strong> waiting time of all customers</em>. Solutions within <code>10<sup>-5</sup></code> from the actual answer are considered accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> customers = [[1,2],[2,5],[4,3]]
<strong>Output:</strong> 5.00000
<strong>Explanation:
</strong>1) The first customer arrives at time 1, the chef takes his order and starts preparing it immediately at time 1, and finishes at time 3, so the waiting time of the first customer is 3 - 1 = 2.
2) The second customer arrives at time 2, the chef takes his order and starts preparing it at time 3, and finishes at time 8, so the waiting time of the second customer is 8 - 2 = 6.
3) The third customer arrives at time 4, the chef takes his order and starts preparing it at time 8, and finishes at time 11, so the waiting time of the third customer is 11 - 4 = 7.
So the average waiting time = (2 + 6 + 7) / 3 = 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = [[5,2],[5,4],[10,3],[20,1]]
<strong>Output:</strong> 3.25000
<strong>Explanation:
</strong>1) The first customer arrives at time 5, the chef takes his order and starts preparing it immediately at time 5, and finishes at time 7, so the waiting time of the first customer is 7 - 5 = 2.
2) The second customer arrives at time 5, the chef takes his order and starts preparing it at time 7, and finishes at time 11, so the waiting time of the second customer is 11 - 5 = 6.
3) The third customer arrives at time 10, the chef takes his order and starts preparing it at time 11, and finishes at time 14, so the waiting time of the third customer is 14 - 10 = 4.
4) The fourth customer arrives at time 20, the chef takes his order and starts preparing it immediately at time 20, and finishes at time 21, so the waiting time of the fourth customer is 21 - 20 = 1.
So the average waiting time = (2 + 6 + 4 + 1) / 4 = 3.25.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= customers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrival<sub>i</sub>, time<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>arrival<sub>i&nbsp;</sub>&lt;= arrival<sub>i+1</sub></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def averageWaitingTime(self, customers: List[List[int]]) -> float:
        f = total_waiting_time = 0
        for arrival, time in customers:
            f = max(arrival, f) + time
            total_waiting_time += f - arrival
        return total_waiting_time / len(customers)
```

### **Java**

```java
class Solution {
    public double averageWaitingTime(int[][] customers) {
        int f = 0;
        double totalWaitingTime = 0;
        for (int[] customer : customers) {
            f = Math.max(f, customer[0]) + customer[1];
            totalWaitingTime += (f - customer[0]);
        }
        return totalWaitingTime / customers.length;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
