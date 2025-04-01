---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains%20II/README.md
---

<!-- problem:start -->

# [3506. Find Time Required to Eliminate Bacterial Strains II 🔒](https://leetcode.cn/problems/find-time-required-to-eliminate-bacterial-strains-ii)

[English Version](/solution/3500-3599/3506.Find%20Time%20Required%20to%20Eliminate%20Bacterial%20Strains%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer array <code>timeReq</code> and an integer <code>splitTime</code>.</p>

<p>In the microscopic world of the human body, the immune system faces an extraordinary challenge: combatting a rapidly multiplying bacterial colony that threatens the body&#39;s survival.</p>

<p>Initially, only one <strong>white blood cell</strong> (<strong>WBC</strong>) is deployed to eliminate the bacteria. However, the lone WBC quickly realizes it cannot keep up with the bacterial growth rate.</p>

<p>The WBC devises a clever strategy to fight the bacteria:</p>

<ul>
	<li>The <code>i<sup>th</sup></code> bacterial strain takes <code>timeReq[i]</code> units of time to be eliminated.</li>
	<li>A single WBC can eliminate <strong>only one</strong> bacterial strain. Afterwards, the WBC is exhausted and cannot perform any other tasks.</li>
	<li>A WBC can split itself into two WBCs, but this requires <code>splitTime</code> units of time. Once split, the two WBCs can work in <strong>parallel</strong> on eliminating the bacteria.</li>
	<li><em>Only one</em> WBC can work on a single bacterial strain. Multiple WBCs <strong>cannot</strong> attack one strain in parallel.</li>
</ul>

<p>You must determine the <strong>minimum</strong> time required to eliminate all the bacterial strains.</p>

<p><strong>Note</strong> that the bacterial strains can be eliminated in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timeReq = [10,4,5], splitTime = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The elimination process goes as follows:</p>

<ul>
	<li>Initially, there is a single WBC. The WBC splits into 2 WBCs after 2 units of time.</li>
	<li>One of the WBCs eliminates strain 0 at a time <code>t = 2 + 10 = 12.</code> The other WBC splits again, using 2 units of time.</li>
	<li>The 2 new WBCs eliminate the bacteria at times <code>t = 2 + 2 + 4</code> and <code>t = 2 + 2 + 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timeReq = [10,4], splitTime = 5</span></p>

<p><strong>Output:</strong>15</p>

<p><strong>Explanation:</strong></p>

<p>The elimination process goes as follows:</p>

<ul>
	<li>Initially, there is a single WBC. The WBC splits into 2 WBCs after 5 units of time.</li>
	<li>The 2 new WBCs eliminate the bacteria at times <code>t = 5 + 10</code> and <code>t = 5 + 4</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
    const pq = new MinPriorityQueue();
    for (const b of timeReq) {
        pq.enqueue(b);
    }
    while (pq.size() > 1) {
        pq.dequeue()!;
        pq.enqueue(pq.dequeue()! + splitTime);
    }
    return pq.dequeue()!;
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
