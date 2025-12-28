---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README_EN.md
rating: 1416
source: Weekly Contest 438 Q2
tags:
    - Greedy
    - Array
    - Matrix
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3462. Maximum Sum With at Most K Elements](https://leetcode.com/problems/maximum-sum-with-at-most-k-elements)

[中文文档](/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README.md)

## Description

<!-- description:start -->

<p data-pm-slice="1 3 []">You are given a 2D integer matrix <code>grid</code> of size <code>n x m</code>, an integer array <code>limits</code> of length <code>n</code>, and an integer <code>k</code>. The task is to find the <strong>maximum sum</strong> of <strong>at most</strong> <code>k</code> elements from the matrix <code>grid</code> such that:</p>

<ul data-spread="false">
	<li>
	<p>The number of elements taken from the <code>i<sup>th</sup></code> row of <code>grid</code> does not exceed <code>limits[i]</code>.</p>
	</li>
</ul>

<p data-pm-slice="1 1 []">Return the <strong>maximum sum</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[3,4]], limits = [1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>From the second row, we can take at most 2 elements. The elements taken are 4 and 3.</li>
	<li>The maximum possible sum of at most 2 selected elements is <code>4 + 3 = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">21</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>From the first row, we can take at most 2 elements. The element taken is 7.</li>
	<li>From the second row, we can take at most 2 elements. The elements taken are 8 and 6.</li>
	<li>The maximum possible sum of at most 3 selected elements is <code>7 + 8 + 6 = 21</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == limits.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= limits[i] &lt;= m</code></li>
	<li><code>0 &lt;= k &lt;= min(n * m, sum(limits))</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue (Min-Heap)

We can use a priority queue (min-heap) $\textit{pq}$ to maintain the largest $k$ elements.

Traverse each row, sort the elements in each row, and then take the largest $\textit{limit}$ elements from each row and add them to $\textit{pq}$. If the size of $\textit{pq}$ exceeds $k$, pop the top element of the heap.

Finally, sum the elements in $\textit{pq}$.

The time complexity is $O(n \times m \times (\log m + \log k))$, and the space complexity is $O(k)$. Here, $n$ and $m$ are the number of rows and columns of the matrix $\textit{grid}$, respectively.

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
    const pq = new MinPriorityQueue<number>();
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
        ans += pq.dequeue();
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
