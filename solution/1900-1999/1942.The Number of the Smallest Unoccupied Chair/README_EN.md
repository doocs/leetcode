# [1942. The Number of the Smallest Unoccupied Chair](https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair)

[中文文档](/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README.md)

## Description

<p>There is a party where <code>n</code> friends numbered from <code>0</code> to <code>n - 1</code> are attending. There is an <strong>infinite</strong> number of chairs in this party that are numbered from <code>0</code> to <code>infinity</code>. When a friend arrives at the party, they sit on the unoccupied chair with the <strong>smallest number</strong>.</p>

<ul>
	<li>For example, if chairs <code>0</code>, <code>1</code>, and <code>5</code> are occupied when a friend comes, they will sit on chair number <code>2</code>.</li>
</ul>

<p>When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.</p>

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>times</code> where <code>times[i] = [arrival<sub>i</sub>, leaving<sub>i</sub>]</code>, indicating the arrival and leaving times of the <code>i<sup>th</sup></code> friend respectively, and an integer <code>targetFriend</code>. All arrival times are <strong>distinct</strong>.</p>

<p>Return<em> the <strong>chair number</strong> that the friend numbered </em><code>targetFriend</code><em> will sit on</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> times = [[1,4],[2,3],[4,6]], targetFriend = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> times = [[3,10],[1,5],[2,6]], targetFriend = 0
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == times.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>times[i].length == 2</code></li>
	<li><code>1 &lt;= arrival<sub>i</sub> &lt; leaving<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= targetFriend &lt;= n - 1</code></li>
	<li>Each <code>arrival<sub>i</sub></code> time is <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        h = list(range(n))
        heapify(h)
        for i in range(n):
            times[i].append(i)
        times.sort()
        busy = []
        for a, b, i in times:
            while busy and busy[0][0] <= a:
                heappush(h, heappop(busy)[1])
            c = heappop(h)
            if i == targetFriend:
                return c
            heappush(busy, (b, c))
        return -1
```

### **Java**

```java
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] ts = new int[n][3];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; ++i) {
            ts[i] = new int[]{times[i][0], times[i][1], i};
            q.offer(i);
        }
        Arrays.sort(ts, (a, b) -> a[0] - b[0]);
        for (int[] t : ts) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.isEmpty() && busy.peek()[0] <= a) {
                q.offer(busy.poll()[1]);
            }
            int c = q.poll();
            if (i == targetFriend) {
                return c;
            }
            busy.offer(new int[]{b, c});
        }
        return -1;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        priority_queue<int, vector<int>, greater<int>> q;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        int n = times.size();
        for (int i = 0; i < n; ++i) {
            times[i].push_back(i);
            q.push(i);
        }
        sort(times.begin(), times.end());
        for (auto& t : times) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.empty() && busy.top().first <= a) {
                q.push(busy.top().second);
                busy.pop();
            }
            int c = q.top();
            q.pop();
            if (i == targetFriend) return c;
            busy.push({b, c});
        }
        return -1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
