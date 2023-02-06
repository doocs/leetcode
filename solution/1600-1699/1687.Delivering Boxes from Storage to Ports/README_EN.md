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

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
