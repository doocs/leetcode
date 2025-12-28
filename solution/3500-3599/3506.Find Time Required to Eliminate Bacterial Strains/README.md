---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains/README.md
tags:
    - 贪心
    - 数组
    - 数学
    - 堆（优先队列）
---

<!-- problem:start -->

# [3506. 查找消除细菌菌株所需时间 🔒](https://leetcode.cn/problems/find-time-required-to-eliminate-bacterial-strains)

[English Version](/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>timeReq</code>&nbsp;和一个整数&nbsp;<code>splitTime</code>。</p>

<p>在人体微观世界中，免疫系统面临着一项非凡的挑战：对抗快速繁殖的细菌群落，这对身体的生存构成威胁。</p>

<p>最初，只部署一个 <strong>白细胞</strong>（<strong>WBC</strong>）来消除细菌。然而，单独的白细胞很快意识到它无法跟上细菌的生长速度。</p>

<p>WBC制定了一种巧妙的策略来对抗细菌：</p>

<ul>
	<li>第 <code>i</code> 个细菌菌株需要 <code>timeReq[i]</code> 个时间单位来被消除。</li>
	<li>单个白细胞只能消除 <strong>一个</strong> 细菌菌株。之后，白细胞耗尽，无法执行任何其他任务。</li>
	<li>一个白细胞可以将自身分裂为两个白细胞，但这需要&nbsp;<code>splitTime</code>&nbsp;单位时间。一旦分裂，两个白细胞就可以 <strong>并行</strong> 消灭细菌。</li>
	<li>一个白细胞仅可以攻击一个细菌菌株。多个白细胞不能同时攻击一个菌株。</li>
</ul>

<p>您必须确定消除所有细菌菌株所需的 <strong>最短</strong> 时间。</p>

<p><strong>注意</strong>，细菌菌株可以按任何顺序消除。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>timeReq = [10,4,5], splitTime = 2</span></p>

<p><span class="example-io"><b>输出：</b>12</span></p>

<p><b>解释：</b></p>

<p>消除过程如下：</p>

<ul>
	<li>最初，有一个白细胞。经过 2 个时间单位后，白细胞分裂成 2 个白细胞。</li>
	<li>其中一个白细胞在&nbsp;<code>t = 2 + 10 = 12</code>&nbsp;时间内消除菌株 0。另一个白细胞使用 2 个单位时间再次分裂。</li>
	<li>2 个新的白细胞消灭细菌的时间是 <code>t = 2 + 2 + 4</code> 和&nbsp;<code>t = 2 + 2 + 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>timeReq = [10,4], splitTime = 5</span></p>

<p><b>输出：</b>15</p>

<p><strong>解释：</strong></p>

<p>消除过程如下：</p>

<ul>
	<li>最初，有一个白细胞。经过 5 个时间单位后，白细胞分裂成 2 个白细胞。</li>
	<li>2 个新的白细胞消灭细菌的时间是&nbsp;<code>t = 5 + 10</code> 和&nbsp;<code>t = 5 + 4</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= timeReq.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= timeReq[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= splitTime &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（小根堆）

先考虑只有一种细菌的情况，此时不需要分裂白细胞，直接让他去消灭细菌，时间花费为 $\textit{timeSeq}[0]$。

如果有两种细菌，此时需要把白细胞分裂为两种，然后让它们分别去消灭细菌，时间花费为 $\textit{splitTime} + \max(\textit{timeSeq}[0], \textit{timeSeq}[1])$。

如果有超过两种细菌，此时每一步都需要考虑将几个白细胞进行分裂，正向思维不好处理。

我们不妨采用逆向思维，不分裂白细胞，而是将细菌进行合并。我们选取任意两种细菌 $i$, $j$ 进行合并，合并成一种新的细菌的时间为 $\textit{splitTime} + \max(\textit{timeSeq}[i], \textit{timeSeq}[j])$。

为了让耗时长的细菌尽可能少参与到合并中，我们可以每次贪心地选取耗时最小的两种细菌进行合并。因此，我们可以维护一个小根堆，每次取出最小的两种细菌进行合并，直到只剩下一种细菌。最后剩下的这个细菌的消灭时间就是答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为细菌的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minEliminationTime(self, timeReq: List[int], splitTime: int) -> int:
        heapify(timeReq)
        while len(timeReq) > 1:
            heappop(timeReq)
            heappush(timeReq, heappop(timeReq) + splitTime)
        return timeReq[0]
```

#### Java

```java
class Solution {
    public long minEliminationTime(int[] timeReq, int splitTime) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        for (int x : timeReq) {
            q.offer((long) x);
        }
        while (q.size() > 1) {
            q.poll();
            q.offer(q.poll() + splitTime);
        }
        return q.poll();
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minEliminationTime(vector<int>& timeReq, int splitTime) {
        using ll = long long;
        priority_queue<ll, vector<ll>, greater<ll>> pq;
        for (int v : timeReq) {
            pq.push(v);
        }
        while (pq.size() > 1) {
            pq.pop();
            ll x = pq.top();
            pq.pop();
            pq.push(x + splitTime);
        }
        return pq.top();
    }
};
```

#### Go

```go
func minEliminationTime(timeReq []int, splitTime int) int64 {
	pq := hp{}
	for _, v := range timeReq {
		heap.Push(&pq, v)
	}
	for pq.Len() > 1 {
		heap.Pop(&pq)
		heap.Push(&pq, heap.Pop(&pq).(int)+splitTime)
	}
	return int64(pq.IntSlice[0])
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function minEliminationTime(timeReq: number[], splitTime: number): number {
    const pq = new MinPriorityQueue<number>();
    for (const b of timeReq) {
        pq.enqueue(b);
    }
    while (pq.size() > 1) {
        pq.dequeue();
        pq.enqueue(pq.dequeue() + splitTime);
    }
    return pq.dequeue();
}
```

#### Rust

```rust
use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_elimination_time(time_req: Vec<i32>, split_time: i32) -> i64 {
        let mut pq = BinaryHeap::new();
        for x in time_req {
            pq.push(Reverse(x as i64));
        }
        while pq.len() > 1 {
            pq.pop();
            let merged = pq.pop().unwrap().0 + split_time as i64;
            pq.push(Reverse(merged));
        }
        pq.pop().unwrap().0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
