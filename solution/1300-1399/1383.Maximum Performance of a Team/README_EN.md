# [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team)

[中文文档](/solution/1300-1399/1383.Maximum%20Performance%20of%20a%20Team/README.md)

## Description

<p>You are given two integers <code>n</code> and <code>k</code> and two integer arrays <code>speed</code> and <code>efficiency</code> both of length <code>n</code>. There are <code>n</code> engineers numbered from <code>1</code> to <code>n</code>. <code>speed[i]</code> and <code>efficiency[i]</code> represent the speed and efficiency of the <code>i<sup>th</sup></code> engineer respectively.</p>

<p>Choose <strong>at most</strong> <code>k</code> different engineers out of the <code>n</code> engineers to form a team with the maximum <strong>performance</strong>.</p>

<p>The performance of a team is the sum of their engineers&#39; speeds multiplied by the minimum efficiency among their engineers.</p>

<p>Return <em>the maximum performance of this team</em>. Since the answer can be a huge number, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
<strong>Output:</strong> 60
<strong>Explanation:</strong> 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
<strong>Output:</strong> 68
<strong>Explanation:
</strong>This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
<strong>Output:</strong> 72
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>speed.length == n</code></li>
	<li><code>efficiency.length == n</code></li>
	<li><code>1 &lt;= speed[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= efficiency[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPerformance(
        self, n: int, speed: List[int], efficiency: List[int], k: int
    ) -> int:
        t = sorted(zip(speed, efficiency), key=lambda x: -x[1])
        ans = tot = 0
        mod = 10**9 + 7
        h = []
        for s, e in t:
            tot += s
            ans = max(ans, tot * e)
            heappush(h, s)
            if len(h) == k:
                tot -= heappop(h)
        return ans % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] t = new int[n][2];
        for (int i = 0; i < n; ++i) {
            t[i] = new int[] {speed[i], efficiency[i]};
        }
        Arrays.sort(t, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        long tot = 0;
        long ans = 0;
        for (var x : t) {
            int s = x[0], e = x[1];
            tot += s;
            ans = Math.max(ans, tot * e);
            q.offer(s);
            if (q.size() == k) {
                tot -= q.poll();
            }
        }
        return (int) (ans % MOD);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPerformance(int n, vector<int>& speed, vector<int>& efficiency, int k) {
        vector<pair<int, int>> t(n);
        for (int i = 0; i < n; ++i) t[i] = {-efficiency[i], speed[i]};
        sort(t.begin(), t.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0, tot = 0;
        int mod = 1e9 + 7;
        for (auto& x : t) {
            int s = x.second, e = -x.first;
            tot += s;
            ans = max(ans, tot * e);
            q.push(s);
            if (q.size() == k) {
                tot -= q.top();
                q.pop();
            }
        }
        return (int) (ans % mod);
    }
};
```

### **Go**

```go
func maxPerformance(n int, speed []int, efficiency []int, k int) int {
	t := make([][]int, n)
	for i, s := range speed {
		t[i] = []int{s, efficiency[i]}
	}
	sort.Slice(t, func(i, j int) bool { return t[i][1] > t[j][1] })
	var mod int = 1e9 + 7
	ans, tot := 0, 0
	pq := hp{}
	for _, x := range t {
		s, e := x[0], x[1]
		tot += s
		ans = max(ans, tot*e)
		heap.Push(&pq, s)
		if pq.Len() == k {
			tot -= heap.Pop(&pq).(int)
		}
	}
	return ans % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
```

### **...**

```

```

<!-- tabs:end -->
