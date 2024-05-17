---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1962.Remove%20Stones%20to%20Minimize%20the%20Total/README.md
rating: 1418
source: 第 253 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [1962. 移除石子使总数最小](https://leetcode.cn/problems/remove-stones-to-minimize-the-total)

[English Version](/solution/1900-1999/1962.Remove%20Stones%20to%20Minimize%20the%20Total/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>piles</code> ，数组 <strong>下标从 0 开始</strong> ，其中 <code>piles[i]</code> 表示第 <code>i</code> 堆石子中的石子数量。另给你一个整数 <code>k</code> ，请你执行下述操作 <strong>恰好</strong> <code>k</code> 次：</p>

<ul>
	<li>选出任一石子堆 <code>piles[i]</code> ，并从中 <strong>移除</strong> <code>floor(piles[i] / 2)</code> 颗石子。</li>
</ul>

<p><strong>注意：</strong>你可以对 <strong>同一堆</strong> 石子多次执行此操作。</p>

<p>返回执行 <code>k</code> 次操作后，剩下石子的 <strong>最小</strong> 总数。</p>

<p><code>floor(x)</code> 为 <strong>小于</strong> 或 <strong>等于</strong> <code>x</code> 的 <strong>最大</strong> 整数。（即，对 <code>x</code> 向下取整）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [5,4,9], k = 2
<strong>输出：</strong>12
<strong>解释：</strong>可能的执行情景如下：
- 对第 2 堆石子执行移除操作，石子分布情况变成 [5,4,<strong><em>5</em></strong>] 。
- 对第 0 堆石子执行移除操作，石子分布情况变成 [<strong><em>3</em></strong>,4,5] 。
剩下石子的总数为 12 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>piles = [4,3,6,7], k = 3
<strong>输出：</strong>12
<strong>解释：</strong>可能的执行情景如下：
- 对第 2 堆石子执行移除操作，石子分布情况变成 [4,3,<strong><em>3</em></strong>,7] 。
- 对第 3 堆石子执行移除操作，石子分布情况变成 [4,3,3,<strong><em>4</em></strong>] 。
- 对第 0 堆石子执行移除操作，石子分布情况变成 [<strong><em>2</em></strong>,3,3,4] 。
剩下石子的总数为 12 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

根据题目描述，为了使得剩下的石子总数最小，我们需要尽可能多地移除石子堆中的石子。因此，每次应该选择数量最多的石子堆进行移除。

我们创建一个优先队列（大根堆） $pq$，用于存储石子堆的数量。初始时，将所有石子堆的数量加入优先队列。

接下来，我们进行 $k$ 次操作。在每一次操作中，我们取出优先队列的堆顶元素 $x$，将 $x$ 减半后重新加入优先队列。

在进行了 $k$ 次操作后，优先队列中所有元素的和即为答案。

时间复杂度 $O(n + k \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `piles` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        pq = [-x for x in piles]
        heapify(pq)
        for _ in range(k):
            heapreplace(pq, pq[0] // 2)
        return -sum(pq)
```

```java
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int x : piles) {
            pq.offer(x);
        }
        while (k-- > 0) {
            int x = pq.poll();
            pq.offer(x - x / 2);
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<int> pq;
        for (int x : piles) {
            pq.push(x);
        }
        while (k--) {
            int x = pq.top();
            pq.pop();
            pq.push(x - x / 2);
        }
        int ans = 0;
        while (!pq.empty()) {
            ans += pq.top();
            pq.pop();
        }
        return ans;
    }
};
```

```go
func minStoneSum(piles []int, k int) (ans int) {
	pq := &hp{piles}
	heap.Init(pq)
	for ; k > 0; k-- {
		x := pq.pop()
		pq.push(x - x/2)
	}
	for pq.Len() > 0 {
		ans += pq.pop()
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

```ts
function minStoneSum(piles: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    for (const x of piles) {
        pq.enqueue(x);
    }
    while (k--) {
        const x = pq.dequeue().element;
        pq.enqueue(x - ((x / 2) | 0));
    }
    let ans = 0;
    while (pq.size()) {
        ans += pq.dequeue().element;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
