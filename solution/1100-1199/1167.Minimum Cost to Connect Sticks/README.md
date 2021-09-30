# [1167. 连接棒材的最低费用](https://leetcode-cn.com/problems/minimum-cost-to-connect-sticks)

[English Version](/solution/1100-1199/1167.Minimum%20Cost%20to%20Connect%20Sticks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>为了装修新房，你需要加工一些长度为正整数的棒材 。棒材以数组 <code>sticks</code> 的形式给出，其中 <code>sticks[i]</code> 是第 <code>i</code> 根棒材的长度。</p>

<p>如果要将长度分别为 <code>x</code> 和 <code>y</code> 的两根棒材连接在一起，你需要支付 <code>x + y</code> 的费用。 由于施工需要，你必须将所有棒材连接成一根。</p>

<p>返回你把所有棒材 <code>sticks</code> 连成一根所需要的最低费用。注意你可以任意选择棒材连接的顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sticks = [2,4,3]
<strong>输出：</strong>14
<strong>解释：</strong>从 sticks = [2,4,3] 开始。
1. 连接 2 和 3 ，费用为 2 + 3 = 5 。现在 sticks = [5,4]
2. 连接 5 和 4 ，费用为 5 + 4 = 9 。现在 sticks = [9]
所有棒材已经连成一根，总费用 5 + 9 = 14
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sticks = [1,8,3,5]
<strong>输出：</strong>30
<strong>解释：</strong>从 sticks = [1,8,3,5] 开始。
1. 连接 1 和 3 ，费用为 1 + 3 = 4 。现在 sticks = [4,8,5]
2. 连接 4 和 5 ，费用为 4 + 5 = 9 。现在 sticks = [9,8]
3. 连接 9 和 8 ，费用为 9 + 8 = 17 。现在 sticks = [17]
所有棒材已经连成一根，总费用 4 + 9 + 17 = 30
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sticks = [5]
<strong>输出：</strong>0
<strong>解释：</strong>只有一根棒材，不必再连接。总费用 0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code><span>1 <= sticks.length <= 10<sup>4</sup></span></code></li>
	<li><code><span>1 <= sticks[i] <= 10<sup>4</sup></span></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

优先队列。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def connectSticks(self, sticks: List[int]) -> int:
        h = []
        for s in sticks:
            heapq.heappush(h, s)
        res = 0
        while len(h) > 1:
            val = heapq.heappop(h) + heapq.heappop(h)
            res += val
            heapq.heappush(h, val)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        priority_queue <int, vector <int>, greater <int> > pq;
        for (int x: sticks) pq.push(x);
        int res = 0;
        while (pq.size() > 1)
        {
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
