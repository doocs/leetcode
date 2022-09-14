# [502. IPO](https://leetcode.cn/problems/ipo)

[English Version](/solution/0500-0599/0502.IPO/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设 力扣（LeetCode）即将开始 <strong>IPO</strong> 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 <code>k</code> 个不同的项目。帮助 力扣 设计完成最多 <code>k</code> 个不同项目后得到最大总资本的方式。</p>

<p>给你 <code>n</code> 个项目。对于每个项目 <code>i</code><strong> </strong>，它都有一个纯利润 <code>profits[i]</code> ，和启动该项目需要的最小资本 <code>capital[i]</code> 。</p>

<p>最初，你的资本为 <code>w</code> 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。</p>

<p>总而言之，从给定项目中选择 <strong>最多</strong> <code>k</code> 个不同项目的列表，以 <strong>最大化最终资本</strong> ，并输出最终可获得的最多资本。</p>

<p>答案保证在 32 位有符号整数范围内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
<strong>输出：</strong>4
<strong>解释：
</strong>由于你的初始资本为 0，你仅可以从 0 号项目开始。
在完成后，你将获得 1 的利润，你的总资本将变为 1。
此时你可以选择开始 1 号或 2 号项目。
由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= w &lt;= 10<sup>9</sup></code></li>
	<li><code>n == profits.length</code></li>
	<li><code>n == capital.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= profits[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= capital[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（双堆）**

将每个项目放入优先队列 $q_1$ 中，按照启动资本从小到大排序。如果堆顶元素启动资本不超过当前已有的资金，则循环弹出，放入另一个优先队列 $q_2$ 中，按照纯利润从大到小排序。取出当前利润最大的项目，将其纯利润加入到当前资金中，重复上述操作 $k$ 次。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为项目数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
