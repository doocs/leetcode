# [1383. 最大的团队表现值](https://leetcode.cn/problems/maximum-performance-of-a-team)

[English Version](/solution/1300-1399/1383.Maximum%20Performance%20of%20a%20Team/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>公司有编号为 <code>1</code>&nbsp;到 <code>n</code>&nbsp;的 <code>n</code>&nbsp;个工程师，给你两个数组 <code>speed</code>&nbsp;和 <code>efficiency</code>&nbsp;，其中 <code>speed[i]</code>&nbsp;和 <code>efficiency[i]</code>&nbsp;分别代表第 <code>i</code>&nbsp;位工程师的速度和效率。请你返回由最多&nbsp;<code>k</code>&nbsp;个工程师组成的&nbsp;<strong>​​​​​​最大团队表现值</strong>&nbsp;，由于答案可能很大，请你返回结果对 <code>10^9 + 7</code> 取余后的结果。</p>

<p><strong>团队表现值</strong>&nbsp;的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
<strong>输出：</strong>60
<strong>解释：</strong>
我们选择工程师 2（speed=10 且 efficiency=4）和工程师 5（speed=5 且 efficiency=7）。他们的团队表现值为 performance = (10 + 5) * min(4, 7) = 60 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
<strong>输出：</strong>68
<strong>解释：
</strong>此示例与第一个示例相同，除了 k = 3 。我们可以选择工程师 1 ，工程师 2 和工程师 5 得到最大的团队表现值。表现值为 performance = (2 + 10 + 5) * min(5, 4, 7) = 68 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
<strong>输出：</strong>72
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>speed.length == n</code></li>
	<li><code>efficiency.length == n</code></li>
	<li><code>1 &lt;= speed[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= efficiency[i] &lt;= 10^8</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（小根堆）**

本题是求“速度和”与“效率最小值”乘积的最大值。变量有两个，我们可以从大到小枚举 `efficiency[i]` 作为效率最小值，在所有效率大于等于 `efficiency[i]` 的工程师中选取不超过 $k-1$ 个，让他们速度和最大。

时间复杂度 $O(n\log n)$。

相似题目：[857. 雇佣 K 名工人的最低成本](/solution/0800-0899/0857.Minimum%20Cost%20to%20Hire%20K%20Workers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
