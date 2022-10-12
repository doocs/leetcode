# [857. Minimum Cost to Hire K Workers](https://leetcode.com/problems/minimum-cost-to-hire-k-workers)

[中文文档](/solution/0800-0899/0857.Minimum%20Cost%20to%20Hire%20K%20Workers/README.md)

## Description

<p>There are <code>n</code> workers. You are given two integer arrays <code>quality</code> and <code>wage</code> where <code>quality[i]</code> is the quality of the <code>i<sup>th</sup></code> worker and <code>wage[i]</code> is the minimum wage expectation for the <code>i<sup>th</sup></code> worker.</p>

<p>We want to hire exactly <code>k</code> workers to form a paid group. To hire a group of <code>k</code> workers, we must pay them according to the following rules:</p>

<ol>
	<li>Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.</li>
	<li>Every worker in the paid group must be paid at least their minimum wage expectation.</li>
</ol>

<p>Given the integer <code>k</code>, return <em>the least amount of money needed to form a paid group satisfying the above conditions</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> quality = [10,20,5], wage = [70,50,30], k = 2
<strong>Output:</strong> 105.00000
<strong>Explanation:</strong> We pay 70 to 0<sup>th</sup> worker and 35 to 2<sup>nd</sup> worker.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
<strong>Output:</strong> 30.66667
<strong>Explanation:</strong> We pay 4 to 0<sup>th</sup> worker, 13.33333 to 2<sup>nd</sup> and 3<sup>rd</sup> workers separately.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == quality.length == wage.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= quality[i], wage[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mincostToHireWorkers(self, quality: List[int], wage: List[int], k: int) -> float:
        t = sorted(zip(quality, wage), key=lambda x: x[1] / x[0])
        ans, tot = inf, 0
        h = []
        for q, w in t:
            tot += q
            heappush(h, -q)
            if len(h) == k:
                ans = min(ans, w / q * tot)
                tot += heappop(h)
        return ans
```

### **Java**

```java
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Pair[] t = new Pair[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new Pair(quality[i], wage[i]);
        }
        Arrays.sort(t, (a, b) -> Double.compare(a.x, b.x));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        double ans = 1e9;
        int tot = 0;
        for (var e : t) {
            tot += e.q;
            pq.offer(e.q);
            if (pq.size() == k) {
                ans = Math.min(ans, tot * e.x);
                tot -= pq.poll();
            }
        }
        return ans;
    }
}

class Pair {
    double x;
    int q;

    Pair(int q, int w) {
        this.q = q;
        this.x = (double) w / q;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double mincostToHireWorkers(vector<int>& quality, vector<int>& wage, int k) {
        int n = quality.size();
        vector<pair<double, int>> t(n);
        for (int i = 0; i < n; ++i) {
            t[i] = {(double) wage[i] / quality[i], quality[i]};
        }
        sort(t.begin(), t.end());
        priority_queue<int> pq;
        double ans = 1e9;
        int tot = 0;
        for (auto& [x, q] : t) {
            tot += q;
            pq.push(q);
            if (pq.size() == k) {
                ans = min(ans, tot * x);
                tot -= pq.top();
                pq.pop();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mincostToHireWorkers(quality []int, wage []int, k int) float64 {
	t := []pair{}
	for i, q := range quality {
		t = append(t, pair{float64(wage[i]) / float64(q), q})
	}
	sort.Slice(t, func(i, j int) bool { return t[i].x < t[j].x })
	tot := 0
	var ans float64 = 1e9
	pq := hp{}
	for _, e := range t {
		tot += e.q
		heap.Push(&pq, e.q)
		if pq.Len() == k {
			ans = min(ans, float64(tot)*e.x)
			tot -= heap.Pop(&pq).(int)
		}
	}
	return ans
}

func min(a, b float64) float64 {
	if a < b {
		return a
	}
	return b
}

type pair struct {
	x float64
	q int
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
```

### **...**

```

```

<!-- tabs:end -->
