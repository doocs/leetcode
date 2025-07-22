---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README.md
rating: 1416
source: 第 438 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 矩阵
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [3462. 提取至多 K 个元素的最大总和](https://leetcode.cn/problems/maximum-sum-with-at-most-k-elements)

[English Version](/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-pm-slice="1 3 []">给你一个大小为 <code>n x m</code>&nbsp;的二维矩阵&nbsp;<code>grid</code>&nbsp;，以及一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>limits</code>&nbsp;，和一个整数&nbsp;<code>k</code>&nbsp;。你的目标是从矩阵 <code>grid</code> 中提取出&nbsp;<strong>至多</strong> <code>k</code>&nbsp;个元素，并计算这些元素的最大总和，提取时需满足以下限制<b>：</b></p>

<ul data-spread="false">
	<li>
	<p>从 <code>grid</code>&nbsp;的第 <code>i</code> 行提取的元素数量不超过 <code>limits[i]</code> 。</p>
	</li>
</ul>

<p data-pm-slice="1 1 []">返回最大总和。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,2],[3,4]], limits = [1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><b>解释：</b></p>

<ul>
	<li>从第 2 行提取至多 2 个元素，取出 4 和 3 。</li>
	<li>至多提取 2 个元素时的最大总和&nbsp;<code>4 + 3 = 7</code>&nbsp;。</li>
</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">21</span></p>

<p><b>解释：</b></p>

<ul>
	<li>从第 1&nbsp;行提取至多 2 个元素，取出 7 。</li>
	<li>从第 2 行提取至多 2 个元素，取出&nbsp;8 和 6 。</li>
	<li>至多提取 3&nbsp;个元素时的最大总和 <code>7 + 8 + 6 = 21</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == grid.length == limits.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= limits[i] &lt;= m</code></li>
	<li><code>0 &lt;= k &lt;= min(n * m, sum(limits))</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（小根堆）

我们可以用一个优先队列（小根堆） $\textit{pq}$ 来维护最大的 $k$ 个元素。

遍历每一行，对每一行的元素进行排序，然后取出每一行最大的 $\textit{limit}$ 个元素，将这些元素加入 $\textit{pq}$ 中。如果 $\textit{pq}$ 的大小超过了 $k$，就将堆顶元素弹出。

最后，将 $\textit{pq}$ 中的元素相加即可。

时间复杂度 $O(n \times m \times (\log m + \log k))$，空间复杂度 $O(k)$。其中 $n$ 和 $m$ 分别为矩阵 $\textit{grid}$ 的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, grid: List[List[int]], limits: List[int], k: int) -> int:
        pq = []
        for nums, limit in zip(grid, limits):
            nums.sort()
            for _ in range(limit):
                heappush(pq, nums.pop())
                if len(pq) > k:
                    heappop(pq)
        return sum(pq)
```

#### Java

```java
class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = grid.length;
        for (int i = 0; i < n; ++i) {
            int[] nums = grid[i];
            int limit = limits[i];
            Arrays.sort(nums);
            for (int j = 0; j < limit; ++j) {
                pq.offer(nums[nums.length - j - 1]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        long ans = 0;
        for (int x : pq) {
            ans += x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSum(vector<vector<int>>& grid, vector<int>& limits, int k) {
        priority_queue<int, vector<int>, greater<int>> pq;
        int n = grid.size();

        for (int i = 0; i < n; ++i) {
            vector<int> nums = grid[i];
            int limit = limits[i];
            ranges::sort(nums);

            for (int j = 0; j < limit; ++j) {
                pq.push(nums[nums.size() - j - 1]);
                if (pq.size() > k) {
                    pq.pop();
                }
            }
        }

        long long ans = 0;
        while (!pq.empty()) {
            ans += pq.top();
            pq.pop();
        }

        return ans;
    }
};
```

#### Go

```go
type MinHeap []int

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func maxSum(grid [][]int, limits []int, k int) int64 {
	pq := &MinHeap{}
	heap.Init(pq)
	n := len(grid)

	for i := 0; i < n; i++ {
		nums := make([]int, len(grid[i]))
		copy(nums, grid[i])
		limit := limits[i]
		sort.Ints(nums)

		for j := 0; j < limit; j++ {
			heap.Push(pq, nums[len(nums)-j-1])
			if pq.Len() > k {
				heap.Pop(pq)
			}
		}
	}

	var ans int64 = 0
	for pq.Len() > 0 {
		ans += int64(heap.Pop(pq).(int))
	}

	return ans
}
```

#### TypeScript

```ts
function maxSum(grid: number[][], limits: number[], k: number): number {
    const pq = new MinPriorityQueue();
    const n = grid.length;
    for (let i = 0; i < n; i++) {
        const nums = grid[i];
        const limit = limits[i];
        nums.sort((a, b) => a - b);
        for (let j = 0; j < limit; j++) {
            pq.enqueue(nums[nums.length - j - 1]);
            if (pq.size() > k) {
                pq.dequeue();
            }
        }
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        ans += pq.dequeue() as number;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
