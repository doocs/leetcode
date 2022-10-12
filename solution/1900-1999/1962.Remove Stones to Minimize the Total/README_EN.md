# [1962. Remove Stones to Minimize the Total](https://leetcode.com/problems/remove-stones-to-minimize-the-total)

[中文文档](/solution/1900-1999/1962.Remove%20Stones%20to%20Minimize%20the%20Total/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>piles</code>, where <code>piles[i]</code> represents the number of stones in the <code>i<sup>th</sup></code> pile, and an integer <code>k</code>. You should apply the following operation <strong>exactly</strong> <code>k</code> times:</p>

<ul>
	<li>Choose any <code>piles[i]</code> and <strong>remove</strong> <code>floor(piles[i] / 2)</code> stones from it.</li>
</ul>

<p><strong>Notice</strong> that you can apply the operation on the <strong>same</strong> pile more than once.</p>

<p>Return <em>the <strong>minimum</strong> possible total number of stones remaining after applying the </em><code>k</code><em> operations</em>.</p>

<p><code>floor(x)</code> is the <b>greatest</b> integer that is <strong>smaller</strong> than or <strong>equal</strong> to <code>x</code> (i.e., rounds <code>x</code> down).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [5,4,9], k = 2
<strong>Output:</strong> 12
<strong>Explanation:</strong>&nbsp;Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [5,4,<u>5</u>].
- Apply the operation on pile 0. The resulting piles are [<u>3</u>,4,5].
The total number of stones in [3,4,5] is 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [4,3,6,7], k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong>&nbsp;Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [4,3,<u>3</u>,7].
- Apply the operation on pile 3. The resulting piles are [4,3,3,<u>4</u>].
- Apply the operation on pile 0. The resulting piles are [<u>2</u>,3,3,4].
The total number of stones in [2,3,3,4] is 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        h = []
        for p in piles:
            heappush(h, -p)
        for _ in range(k):
            p = -heappop(h)
            heappush(h, -((p + 1) >> 1))
        return -sum(h)
```

### **Java**

```java
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        for (int p : piles) {
            q.offer(p);
        }
        while (k-- > 0) {
            int p = q.poll();
            q.offer((p + 1) >> 1);
        }
        int ans = 0;
        while (!q.isEmpty()) {
            ans += q.poll();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<int> q;
        for (int& p : piles) q.push(p);
        while (k--) {
            int p = q.top();
            q.pop();
            q.push((p + 1) >> 1);
        }
        int ans = 0;
        while (!q.empty()) {
            ans += q.top();
            q.pop();
        }
        return ans;
    }
};
```

### **Go**

```go
func minStoneSum(piles []int, k int) int {
    q := &hp{piles}
    heap.Init(q)
    for k > 0 {
        p := q.pop()
        q.push((p + 1) >> 1)
        k--
    }
    ans := 0
    for q.Len() > 0 {
        ans += q.pop()
    }
    return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

### **...**

```

```

<!-- tabs:end -->
