---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1687.Delivering%20Boxes%20from%20Storage%20to%20Ports/README_EN.md
rating: 2610
source: Biweekly Contest 41 Q4
tags:
    - Segment Tree
    - Queue
    - Array
    - Dynamic Programming
    - Prefix Sum
    - Monotonic Queue
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1687. Delivering Boxes from Storage to Ports](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports)

[中文文档](/solution/1600-1699/1687.Delivering%20Boxes%20from%20Storage%20to%20Ports/README.md)

## Description

<!-- description:start -->

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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal strategy is as follows: 
- The ship takes all the boxes in the queue, goes to port 1, then port 2, then port 1 again, then returns to storage. 4 trips.
So the total number of trips is 4.
Note that the first and third boxes cannot be delivered together because the boxes need to be delivered in order (i.e. the second box needs to be delivered at port 2 before the third box).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal strategy is as follows: 
- The ship takes the first box, goes to port 1, then returns to storage. 2 trips.
- The ship takes the second, third and fourth boxes, goes to port 3, then returns to storage. 2 trips.
- The ship takes the fifth box, goes to port 2, then returns to storage. 2 trips.
So the total number of trips is 2 + 2 + 2 = 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Monotonic Queue Optimization

We define $f[i]$ as the minimum number of trips required to transport the first $i$ boxes from the warehouse to the corresponding docks, so the answer is $f[n]$.

The boxes need to be transported in the order of the array. Each time, the truck will take out several consecutive boxes in order, then deliver them to the corresponding docks one by one. After all are delivered, it returns to the warehouse.

Therefore, we can enumerate the index $j$ of the last box transported in the last trip. Then $f[i]$ can be transferred from $f[j]$. During the transfer, we need to consider the following issues:

- When transferring from $f[j]$, the number of boxes on the truck cannot exceed $maxBoxes$
- When transferring from $f[j]$, the total weight of the boxes on the truck cannot exceed $maxWeight$

The state transition equation is:

$$
f[i] = \min_{j \in [i - maxBoxes, i - 1]} \left(f[j] + \sum_{k = j + 1}^i \textit{cost}(k)\right)
$$

Where $\sum_{k = j + 1}^i \textit{cost}(k)$ represents the number of trips required to deliver the boxes in $[j+1,..i]$ to their corresponding docks in one trip. This part of the trip count can be quickly calculated using prefix sums.

For example, suppose we take out boxes $1, 2, 3$ and need to deliver them to docks $4, 4, 5$. We first go from the warehouse to dock $4$, then from dock $4$ to dock $5$, and finally from dock $5$ back to the warehouse. It can be seen that it takes $2$ trips to go from the warehouse to the dock and from the dock back to the warehouse. The number of trips from dock to dock depends on whether the two adjacent docks are the same. If they are not the same, the number of trips will increase by $1$, otherwise it remains the same. Therefore, we can calculate the number of trips between docks using prefix sums, and add two trips for the start and end, to calculate the number of trips required to deliver the boxes in $[j+1,..i]$ to their corresponding docks.

The code implementation is as follows:

```python
# 33/39
class Solution:
    def boxDelivering(
        self, boxes: List[List[int]], portsCount: int, maxBoxes: int, maxWeight: int
    ) -> int:
        n = len(boxes)
        ws = list(accumulate((box[1] for box in boxes), initial=0))
        c = [int(a != b) for a, b in pairwise(box[0] for box in boxes)]
        cs = list(accumulate(c, initial=0))
        f = [inf] * (n + 1)
        f[0] = 0
        for i in range(1, n + 1):
            for j in range(max(0, i - maxBoxes), i):
                if ws[i] - ws[j] <= maxWeight:
                    f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2)
        return f[n]
```

```java
// 35/39
class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] ws = new long[n + 1];
        int[] cs = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) {
                cs[i + 1] = cs[i] + (p != boxes[i + 1][0] ? 1 : 0);
            }
        }
        int[] f = new int[n + 1];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = Math.max(0, i - maxBoxes); j < i; ++j) {
                if (ws[i] - ws[j] <= maxWeight) {
                    f[i] = Math.min(f[i], f[j] + cs[i - 1] - cs[j] + 2);
                }
            }
        }
        return f[n];
    }
}
```

```cpp
// 35/39
class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        long ws[n + 1];
        int cs[n];
        ws[0] = cs[0] = 0;
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) cs[i + 1] = cs[i] + (p != boxes[i + 1][0]);
        }
        int f[n + 1];
        memset(f, 0x3f, sizeof f);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = max(0, i - maxBoxes); j < i; ++j) {
                if (ws[i] - ws[j] <= maxWeight) {
                    f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2);
                }
            }
        }
        return f[n];
    }
};
```

```go
// 35/39
func boxDelivering(boxes [][]int, portsCount int, maxBoxes int, maxWeight int) int {
	n := len(boxes)
	ws := make([]int, n+1)
	cs := make([]int, n)
	for i, box := range boxes {
		p, w := box[0], box[1]
		ws[i+1] = ws[i] + w
		if i < n-1 {
			t := 0
			if p != boxes[i+1][0] {
				t++
			}
			cs[i+1] = cs[i] + t
		}
	}
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = 1 << 30
		for j := max(0, i-maxBoxes); j < i; j++ {
			if ws[i]-ws[j] <= maxWeight {
				f[i] = min(f[i], f[j]+cs[i-1]-cs[j]+2)
			}
		}
	}
	return f[n]
}
```

The data scale of this problem reaches $10^5$, and the time complexity of the above code is $O(n^2)$, which will exceed the time limit. If we observe carefully:

$$
f[i] = \min(f[i], f[j] + cs[i - 1] - cs[j] + 2)
$$

In fact, we are looking for a $j$ in the window $[i-maxBoxes,..i-1]$ that minimizes the value of $f[j] - cs[j]$. To find the minimum value in a sliding window, a common method is to use a monotonic queue, which can get the minimum value that meets the condition in $O(1)$ time.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of boxes in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def boxDelivering(
        self, boxes: List[List[int]], portsCount: int, maxBoxes: int, maxWeight: int
    ) -> int:
        n = len(boxes)
        ws = list(accumulate((box[1] for box in boxes), initial=0))
        c = [int(a != b) for a, b in pairwise(box[0] for box in boxes)]
        cs = list(accumulate(c, initial=0))
        f = [0] * (n + 1)
        q = deque([0])
        for i in range(1, n + 1):
            while q and (i - q[0] > maxBoxes or ws[i] - ws[q[0]] > maxWeight):
                q.popleft()
            if q:
                f[i] = cs[i - 1] + f[q[0]] - cs[q[0]] + 2
            if i < n:
                while q and f[q[-1]] - cs[q[-1]] >= f[i] - cs[i]:
                    q.pop()
                q.append(i)
        return f[n]
```

#### Java

```java
class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] ws = new long[n + 1];
        int[] cs = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) {
                cs[i + 1] = cs[i] + (p != boxes[i + 1][0] ? 1 : 0);
            }
        }
        int[] f = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        for (int i = 1; i <= n; ++i) {
            while (!q.isEmpty()
                && (i - q.peekFirst() > maxBoxes || ws[i] - ws[q.peekFirst()] > maxWeight)) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                f[i] = cs[i - 1] + f[q.peekFirst()] - cs[q.peekFirst()] + 2;
            }
            if (i < n) {
                while (!q.isEmpty() && f[q.peekLast()] - cs[q.peekLast()] >= f[i] - cs[i]) {
                    q.pollLast();
                }
                q.offer(i);
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        long ws[n + 1];
        int f[n + 1];
        int cs[n];
        ws[0] = cs[0] = f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) cs[i + 1] = cs[i] + (p != boxes[i + 1][0]);
        }
        deque<int> q{{0}};
        for (int i = 1; i <= n; ++i) {
            while (!q.empty() && (i - q.front() > maxBoxes || ws[i] - ws[q.front()] > maxWeight)) q.pop_front();
            if (!q.empty()) f[i] = cs[i - 1] + f[q.front()] - cs[q.front()] + 2;
            if (i < n) {
                while (!q.empty() && f[q.back()] - cs[q.back()] >= f[i] - cs[i]) q.pop_back();
                q.push_back(i);
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func boxDelivering(boxes [][]int, portsCount int, maxBoxes int, maxWeight int) int {
	n := len(boxes)
	ws := make([]int, n+1)
	cs := make([]int, n)
	for i, box := range boxes {
		p, w := box[0], box[1]
		ws[i+1] = ws[i] + w
		if i < n-1 {
			t := 0
			if p != boxes[i+1][0] {
				t++
			}
			cs[i+1] = cs[i] + t
		}
	}
	f := make([]int, n+1)
	q := []int{0}
	for i := 1; i <= n; i++ {
		for len(q) > 0 && (i-q[0] > maxBoxes || ws[i]-ws[q[0]] > maxWeight) {
			q = q[1:]
		}
		if len(q) > 0 {
			f[i] = cs[i-1] + f[q[0]] - cs[q[0]] + 2
		}
		if i < n {
			for len(q) > 0 && f[q[len(q)-1]]-cs[q[len(q)-1]] >= f[i]-cs[i] {
				q = q[:len(q)-1]
			}
			q = append(q, i)
		}
	}
	return f[n]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
