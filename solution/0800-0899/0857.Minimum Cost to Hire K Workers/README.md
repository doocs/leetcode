# [857. 雇佣 K 名工人的最低成本](https://leetcode.cn/problems/minimum-cost-to-hire-k-workers)

[English Version](/solution/0800-0899/0857.Minimum%20Cost%20to%20Hire%20K%20Workers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code>&nbsp;名工人。&nbsp;给定两个数组&nbsp;<code>quality</code>&nbsp;和&nbsp;<code>wage</code>&nbsp;，其中，<code>quality[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;名工人的工作质量，其最低期望工资为&nbsp;<code>wage[i]</code>&nbsp;。</p>

<p>现在我们想雇佣&nbsp;<code>k</code>&nbsp;名工人组成一个<em>工资组。</em>在雇佣&nbsp;一组 <code>k</code>&nbsp;名工人时，我们必须按照下述规则向他们支付工资：</p>

<ol>
	<li>对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。</li>
	<li>工资组中的每名工人至少应当得到他们的最低期望工资。</li>
</ol>

<p>给定整数 <code>k</code> ，返回 <em>组成满足上述条件的付费群体所需的最小金额&nbsp;</em>。在实际答案的&nbsp;<code>10<sup>-5</sup></code>&nbsp;以内的答案将被接受。。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>quality = [10,20,5], wage = [70,50,30], k = 2
<strong>输出： </strong>105.00000
<strong>解释：</strong> 我们向 0 号工人支付 70，向 2 号工人支付 35。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入： </strong>quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
<strong>输出： </strong>30.66667
<strong>解释： </strong>我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == quality.length == wage.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= quality[i], wage[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（大根堆）**

我们假设选取了某一个工资组，总工作质量为 `tot`，总支付金额为 `c`。每个工人的工作质量为 $q_i$，工资为 $w_i$。那么，对于此工资组的每个工人，均满足 $c\times \frac{q_i}{tot} \ge w_i$。即 $c\ge tot\times \frac{w_i}{q_i}$。

在总工作质量 `tot` 固定的情况下，支付的金额取决于权重 $\frac{w_i}{q_i}$ 的最大值。

我们可以从小到大枚举权重 $\frac{w_i}{q_i}$ 作为工资组的最大值，此时工资组其他人员只需要在权重小于等于这个值的集合中，选取工作质量最小的 $k-1$ 名工人来组成工资组即可。因此，可以用优先队列（最大堆）维护工作质量最小的 $k-1$ 名工人。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 是工人数。

相似题目：[1383. 最大的团队表现值](/solution/1300-1399/1383.Maximum%20Performance%20of%20a%20Team/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
