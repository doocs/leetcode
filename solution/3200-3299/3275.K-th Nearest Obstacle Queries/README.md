---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3275.K-th%20Nearest%20Obstacle%20Queries/README.md
rating: 1419
source: 第 413 场周赛 Q2
tags:
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [3275. 第 K 近障碍物查询](https://leetcode.cn/problems/k-th-nearest-obstacle-queries)

[English Version](/solution/3200-3299/3275.K-th%20Nearest%20Obstacle%20Queries/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个无限大的二维平面。</p>

<p>给你一个正整数&nbsp;<code>k</code>&nbsp;，同时给你一个二维数组&nbsp;<code>queries</code>&nbsp;，包含一系列查询：</p>

<ul>
	<li><code>queries[i] = [x, y]</code>&nbsp;：在平面上坐标&nbsp;<code>(x, y)</code>&nbsp;处建一个障碍物，数据保证之前的查询 <strong>不会</strong> 在这个坐标处建立任何障碍物。</li>
</ul>

<p>每次查询后，你需要找到离原点第 <code>k</code>&nbsp;<strong>近</strong>&nbsp;障碍物到原点的 <strong>距离</strong>&nbsp;。</p>

<p>请你返回一个整数数组&nbsp;<code>results</code>&nbsp;，其中&nbsp;<code>results[i]</code>&nbsp;表示建立第 <code>i</code>&nbsp;个障碍物以后，离原地第 <code>k</code>&nbsp;近障碍物距离原点的距离。如果少于 <code>k</code>&nbsp;个障碍物，<code>results[i] == -1</code>&nbsp;。</p>

<p><strong>注意</strong>，一开始&nbsp;<strong>没有</strong>&nbsp;任何障碍物。</p>

<p>坐标在&nbsp;<code>(x, y)</code>&nbsp;处的点距离原点的距离定义为&nbsp;<code>|x| + |y|</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = [[1,2],[3,4],[2,3],[-3,0]], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>[-1,7,5,3]</span></p>

<p><strong>解释：</strong></p>

<p>最初，不存在障碍物。</p>

<ul>
	<li><code>queries[0]</code>&nbsp;之后，少于 2 个障碍物。</li>
	<li><code>queries[1]</code>&nbsp;之后，&nbsp;两个障碍物距离原点的距离分别为 3 和 7 。</li>
	<li><code>queries[2]</code>&nbsp;之后，障碍物距离原点的距离分别为 3 ，5 和 7 。</li>
	<li><code>queries[3]</code>&nbsp;之后，障碍物距离原点的距离分别为 3，3，5 和 7 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = [[5,5],[4,4],[3,3]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>[10,8,6]</span></p>

<p><b>解释：</b></p>

<ul>
	<li><code>queries[0]</code>&nbsp;之后，只有一个障碍物，距离原点距离为 10 。</li>
	<li><code>queries[1]</code>&nbsp;之后，障碍物距离原点距离分别为 8 和 10 。</li>
	<li><code>queries[2]</code>&nbsp;之后，障碍物距离原点的距离分别为 6， 8 和10 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>所有&nbsp;<code>queries[i]</code>&nbsp;互不相同。</li>
	<li><code>-10<sup>9</sup> &lt;= queries[i][0], queries[i][1] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（大根堆）

我们可以使用一个优先队列（大根堆）来维护离原点最近的 $k$ 个障碍物。

遍历 $\textit{queries}$，每次计算 $x$ 和 $y$ 的绝对值之和，然后将其加入优先队列。如果优先队列的大小超过 $k$，则弹出堆顶元素。如果当前优先队列的大小等于 $k$，则将堆顶元素加入答案数组，否则将 $-1$ 加入答案数组。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 为数组 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultsArray(self, queries: List[List[int]], k: int) -> List[int]:
        ans = []
        pq = []
        for i, (x, y) in enumerate(queries):
            heappush(pq, -(abs(x) + abs(y)))
            if i >= k:
                heappop(pq)
            ans.append(-pq[0] if i >= k - 1 else -1)
        return ans
```

#### Java

```java
class Solution {
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            pq.offer(x);
            if (i >= k) {
                pq.poll();
            }
            ans[i] = i >= k - 1 ? pq.peek() : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> resultsArray(vector<vector<int>>& queries, int k) {
        vector<int> ans;
        priority_queue<int> pq;
        for (const auto& q : queries) {
            int x = abs(q[0]) + abs(q[1]);
            pq.push(x);
            if (pq.size() > k) {
                pq.pop();
            }
            ans.push_back(pq.size() == k ? pq.top() : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func resultsArray(queries [][]int, k int) (ans []int) {
	pq := &hp{}
	for _, q := range queries {
		x := abs(q[0]) + abs(q[1])
		pq.push(x)
		if pq.Len() > k {
			pq.pop()
		}
		if pq.Len() == k {
			ans = append(ans, pq.IntSlice[0])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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

#### TypeScript

```ts
function resultsArray(queries: number[][], k: number): number[] {
    const pq = new MaxPriorityQueue<number>();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        pq.enqueue(Math.abs(x) + Math.abs(y));
        if (pq.size() > k) {
            pq.dequeue();
        }
        ans.push(pq.size() === k ? pq.front() : -1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
