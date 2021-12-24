# [1882. Process Tasks Using Servers](https://leetcode.com/problems/process-tasks-using-servers)

[中文文档](/solution/1800-1899/1882.Process%20Tasks%20Using%20Servers/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>servers</code> and <code>tasks</code> of lengths <code>n</code>​​​​​​ and <code>m</code>​​​​​​ respectively. <code>servers[i]</code> is the <strong>weight</strong> of the <code>i<sup>​​​​​​th</sup></code>​​​​ server, and <code>tasks[j]</code> is the <strong>time needed</strong> to process the <code>j<sup>​​​​​​th</sup></code>​​​​ task <strong>in seconds</strong>.</p>

<p>You are running a simulation system that will shut down after all tasks are processed. Each server can only process one task at a time. You will be able to process the <code>j<sup>th</sup></code> task starting from the <code>j<sup>th</sup></code> second beginning with the <code>0<sup>th</sup></code> task at second <code>0</code>. To process task <code>j</code>, you assign it to the server with the <strong>smallest weight</strong> that is free, and in case of a tie, choose the server with the <strong>smallest index</strong>. If a free server gets assigned task <code>j</code> at second <code>t</code>,​​​​​​ it will be free again at the second <code>t + tasks[j]</code>.</p>

<p>If there are no free servers, you must wait until one is free and execute the free tasks <strong>as soon as possible</strong>. If <strong>multiple</strong> tasks need to be assigned, assign them in order of <strong>increasing index</strong>.</p>

<p>You may assign multiple tasks at the same second if there are multiple free servers.</p>

<p>Build an array <code>ans</code>​​​​ of length <code>m</code>, where <code>ans[j]</code> is the <strong>index</strong> of the server the <code>j<sup>​​​​​​th</sup></code> task will be assigned to.</p>

<p>Return <em>the array </em><code>ans</code>​​​​.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> servers = [3,3,2], tasks = [1,2,3,2,1,2]
<strong>Output:</strong> [2,2,0,2,1,2]
<strong>Explanation: </strong>Events in chronological order go as follows:
- At second 0, task 0 is added and processed using server 2 until second 1.
- At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
- At second 2, task 2 is added and processed using server 0 until second 5.
- At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
- At second 4, task 4 is added and processed using server 1 until second 5.
- At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
<strong>Output:</strong> [1,4,1,4,1,3,2]
<strong>Explanation: </strong>Events in chronological order go as follows: 
- At second 0, task 0 is added and processed using server 1 until second 2.
- At second 1, task 1 is added and processed using server 4 until second 2.
- At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4. 
- At second 3, task 3 is added and processed using server 4 until second 7.
- At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9. 
- At second 5, task 5 is added and processed using server 3 until second 7.
- At second 6, task 6 is added and processed using server 2 until second 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>servers.length == n</code></li>
	<li><code>tasks.length == m</code></li>
	<li><code>1 &lt;= n, m &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= servers[i], tasks[j] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        idle, busy = [], []
        for i, weight in enumerate(servers):
            heapq.heappush(idle, (weight, i))
        res = []
        for start, cost in enumerate(tasks):
            while busy and busy[0][0] <= start:
                _, s, i = heapq.heappop(busy)
                heapq.heappush(idle, (s, i))
            if idle:
                s, i = heapq.heappop(idle)
                heapq.heappush(busy, (start + cost, s, i))
            else:
                t, s, i = heapq.heappop(busy)
                heapq.heappush(busy, (t + cost, s, i))
            res.append(i)
        return res
```

### **Java**

```java
class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length, n = servers.length;
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] == b[1] ? a[2] - b[2] : a[1] - b[1];
            }
            return a[0] - b[0];
        });
        for (int i = 0; i < n; ++i) {
            idle.offer(new int[]{servers[i], i});
        }
        int[] res = new int[m];
        int j = 0;
        for (int start = 0; start < m; ++start) {
            int cost = tasks[start];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                int[] item = busy.poll();
                idle.offer(new int[]{item[1], item[2]});
            }
            if (!idle.isEmpty()) {
                int[] item = idle.poll();
                res[j++] = item[1];
                busy.offer(new int[]{start + cost, item[0], item[1]});
            } else {
                int[] item = busy.poll();
                res[j++] = item[2];
                busy.offer(new int[]{item[0] + cost, item[1], item[2]});
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
