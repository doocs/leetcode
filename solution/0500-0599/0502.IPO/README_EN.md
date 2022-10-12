# [502. IPO](https://leetcode.com/problems/ipo)

[中文文档](/solution/0500-0599/0502.IPO/README.md)

## Description

<p>Suppose LeetCode will start its <strong>IPO</strong> soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the <strong>IPO</strong>. Since it has limited resources, it can only finish at most <code>k</code> distinct projects before the <strong>IPO</strong>. Help LeetCode design the best way to maximize its total capital after finishing at most <code>k</code> distinct projects.</p>

<p>You are given <code>n</code> projects where the <code>i<sup>th</sup></code> project has a pure profit <code>profits[i]</code> and a minimum capital of <code>capital[i]</code> is needed to start it.</p>

<p>Initially, you have <code>w</code> capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.</p>

<p>Pick a list of <strong>at most</strong> <code>k</code> distinct projects from given projects to <strong>maximize your final capital</strong>, and return <em>the final maximized capital</em>.</p>

<p>The answer is guaranteed to fit in a 32-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= w &lt;= 10<sup>9</sup></code></li>
	<li><code>n == profits.length</code></li>
	<li><code>n == capital.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= profits[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= capital[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaximizedCapital(self, k: int, w: int, profits: List[int], capital: List[int]) -> int:
        h1 = [(c, p) for c, p in zip(capital, profits)]
        heapify(h1)
        h2 = []
        while k:
            while h1 and h1[0][0] <= w:
                heappush(h2, -heappop(h1)[1])
            if not h2:
                break
            w -= heappop(h2)
            k -= 1
        return w
```

### **Java**

```java
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = capital.length;
        PriorityQueue<int[]> q1 = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; ++i) {
            q1.offer(new int[] {capital[i], profits[i]});
        }
        PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> b - a);
        while (k-- > 0) {
            while (!q1.isEmpty() && q1.peek()[0] <= w) {
                q2.offer(q1.poll()[1]);
            }
            if (q2.isEmpty()) {
                break;
            }
            w += q2.poll();
        }
        return w;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        priority_queue<pii, vector<pii>, greater<pii>> q1;
        int n = profits.size();
        for (int i = 0; i < n; ++i) {
            q1.push({capital[i], profits[i]});
        }
        priority_queue<int> q2;
        while (k--) {
            while (!q1.empty() && q1.top().first <= w) {
                q2.push(q1.top().second);
                q1.pop();
            }
            if (q2.empty()) {
                break;
            }
            w += q2.top();
            q2.pop();
        }
        return w;
    }
};
```

### **Go**

```go
func findMaximizedCapital(k int, w int, profits []int, capital []int) int {
	q1 := hp2{}
	for i, c := range capital {
		heap.Push(&q1, pair{c, profits[i]})
	}
	q2 := hp{}
	for k > 0 {
		for len(q1) > 0 && q1[0].c <= w {
			heap.Push(&q2, heap.Pop(&q1).(pair).p)
		}
		if q2.Len() == 0 {
			break
		}
		w += heap.Pop(&q2).(int)
		k--
	}
	return w
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

type pair struct{ c, p int }
type hp2 []pair

func (h hp2) Len() int            { return len(h) }
func (h hp2) Less(i, j int) bool  { return h[i].c < h[j].c }
func (h hp2) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
