# [1167. Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks)

[中文文档](/solution/1100-1199/1167.Minimum%20Cost%20to%20Connect%20Sticks/README.md)

## Description

<p>You have some number of sticks with positive integer lengths. These lengths are given as an array&nbsp;<code>sticks</code>, where&nbsp;<code>sticks[i]</code>&nbsp;is the length of the&nbsp;<code>i<sup>th</sup></code>&nbsp;stick.</p>

<p>You can connect any two sticks of lengths <code>x</code> and <code>y</code> into one stick&nbsp;by paying a cost of <code>x + y</code>. You must connect&nbsp;all the sticks until there is only one stick remaining.</p>

<p>Return&nbsp;<em>the minimum cost of connecting all the given sticks into one stick in this way</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sticks = [2,4,3]
<strong>Output:</strong> 14
<strong>Explanation:</strong>&nbsp;You start with sticks = [2,4,3].
1. Combine sticks 2 and 3 for a cost of 2 + 3 = 5. Now you have sticks = [5,4].
2. Combine sticks 5 and 4 for a cost of 5 + 4 = 9. Now you have sticks = [9].
There is only one stick left, so you are done. The total cost is 5 + 9 = 14.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sticks = [1,8,3,5]
<strong>Output:</strong> 30
<strong>Explanation:</strong> You start with sticks = [1,8,3,5].
1. Combine sticks 1 and 3 for a cost of 1 + 3 = 4. Now you have sticks = [4,8,5].
2. Combine sticks 4 and 5 for a cost of 4 + 5 = 9. Now you have sticks = [9,8].
3. Combine sticks 9 and 8 for a cost of 9 + 8 = 17. Now you have sticks = [17].
There is only one stick left, so you are done. The total cost is 4 + 9 + 17 = 30.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sticks = [5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is only one stick, so you don&#39;t need to do anything. The total cost is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><span>1 &lt;= sticks.length &lt;= 10<sup>4</sup></span></code></li>
	<li><code><span>1 &lt;= sticks[i] &lt;= 10<sup>4</sup></span></code></li>
</ul>

## Solutions

Priority queue.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def connectSticks(self, sticks: List[int]) -> int:
        h = []
        for s in sticks:
            heappush(h, s)
        res = 0
        while len(h) > 1:
            val = heappop(h) + heappop(h)
            res += val
            heappush(h, val)
        return res
```

### **Java**

```java
class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.offer(s);
        }
        int res = 0;
        while (pq.size() > 1) {
            int val = pq.poll() + pq.poll();
            res += val;
            pq.offer(val);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int connectSticks(vector<int>& sticks) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int x : sticks) pq.push(x);
        int res = 0;
        while (pq.size() > 1) {
            int val = pq.top();
            pq.pop();
            val += pq.top();
            pq.pop();
            res += val;
            pq.push(val);
        }
        return res;
    }
};
```

### **Go**

```go
func connectSticks(sticks []int) int {
	h := IntHeap(sticks)
	heap.Init(&h)
	res := 0
	for h.Len() > 1 {
		val := heap.Pop(&h).(int)
		val += heap.Pop(&h).(int)
		res += val
		heap.Push(&h, val)
	}
	return res
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
