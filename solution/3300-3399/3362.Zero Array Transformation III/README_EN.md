---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3362.Zero%20Array%20Transformation%20III/README_EN.md
rating: 2423
source: Biweekly Contest 144 Q3
tags:
    - Greedy
    - Array
    - Prefix Sum
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3362. Zero Array Transformation III](https://leetcode.com/problems/zero-array-transformation-iii)

[中文文档](/solution/3300-3399/3362.Zero%20Array%20Transformation%20III/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D array <code>queries</code> where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>Each <code>queries[i]</code> represents the following action on <code>nums</code>:</p>

<ul>
	<li>Decrement the value at each index in the range <code>[l<sub>i</sub>, r<sub>i</sub>]</code> in <code>nums</code> by <strong>at most</strong><strong> </strong>1.</li>
	<li>The amount by which the value is decremented can be chosen <strong>independently</strong> for each index.</li>
</ul>

<p>A <strong>Zero Array</strong> is an array with all its elements equal to 0.</p>

<p>Return the <strong>maximum </strong>number of elements that can be removed from <code>queries</code>, such that <code>nums</code> can still be converted to a <strong>zero array</strong> using the <em>remaining</em> queries. If it is not possible to convert <code>nums</code> to a <strong>zero array</strong>, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing <code>queries[2]</code>, <code>nums</code> can still be converted to a zero array.</p>

<ul>
	<li>Using <code>queries[0]</code>, decrement <code>nums[0]</code> and <code>nums[2]</code> by 1 and <code>nums[1]</code> by 0.</li>
	<li>Using <code>queries[1]</code>, decrement <code>nums[0]</code> and <code>nums[2]</code> by 1 and <code>nums[1]</code> by 0.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can remove <code>queries[2]</code> and <code>queries[3]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], queries = [[0,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums</code> cannot be converted to a zero array even after using all the queries.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Difference Array + Priority Queue

We want to "remove" as many interval queries as possible, while ensuring that for each position $i$, the number of selected queries covering it, $s(i)$, is at least the original array value $\textit{nums}[i]$, so that the value at that position can be reduced to 0 or below. If for some position $i$ we cannot satisfy $s(i) \ge \textit{nums}[i]$, it means that no matter how many more queries we select, it is impossible to make that position zero, so we return $-1$.

To achieve this, we traverse the queries in order of their left endpoints and maintain:

1. **Difference array** `d`: Used to record where the currently applied queries take effect—when we "apply" a query on the interval $[l, r]$, we immediately do `d[l] += 1` and `d[r+1] -= 1`. This way, when traversing to index $i$, the prefix sum tells us how many queries cover $i$.
2. **Max heap** `pq`: Stores the right endpoints of the current "candidate" interval queries (store as negative numbers to simulate a max heap in Python's min heap). Why choose the "latest ending" interval? Because it can cover farther positions. Our greedy strategy is: **at each $i$, only pick the longest interval from the heap when necessary to increase coverage**, so that more intervals are available for subsequent positions.

The specific steps are as follows:

1. Sort `queries` by the left endpoint `l` in ascending order;
2. Initialize the difference array `d` with length `n+1` (to handle the decrement at `r+1`), and set the current coverage count `s=0`, heap pointer `j=0`;
3. For $i=0$ to $n-1$:

    - First, add `d[i]` to `s` to update the current coverage count;
    - Push all queries $[l, r]$ with left endpoint $\le i$ into the max heap `pq` (store `-r`), and advance `j`;
    - While the current coverage `s` is less than the required value `nums[i]`, and the heap is not empty, and the top interval in the heap still covers $i$ (i.e., $-pq[0] \ge i$):

        1. Pop the top of the heap (the longest interval), which is equivalent to "applying" this query;
        2. Increment `s` by 1 and do `d[r+1] -= 1` (so that after passing $r$, the coverage count automatically decreases);

    - Repeat the above steps until `s \ge nums[i]` or no more intervals can be selected;
    - If at this point `s < nums[i]`, it means it is impossible to make position $i$ zero, so return $-1$.

4. After traversing all positions, the intervals remaining in the heap are those that were **not popped**, i.e., the queries that are truly **retained** (not used for the "zeroing" task). The heap size is the answer.

The time complexity is $O(n + m \times \log m)$, and the space complexity is $O(n + m)$, where $n$ is the length of the array and $m$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRemoval(self, nums: List[int], queries: List[List[int]]) -> int:
        queries.sort()
        pq = []
        d = [0] * (len(nums) + 1)
        s = j = 0
        for i, x in enumerate(nums):
            s += d[i]
            while j < len(queries) and queries[j][0] <= i:
                heappush(pq, -queries[j][1])
                j += 1
            while s < x and pq and -pq[0] >= i:
                s += 1
                d[-heappop(pq) + 1] -= 1
            if s < x:
                return -1
        return len(pq)
```

#### Java

```java
class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        int[] d = new int[n + 1];
        int s = 0, j = 0;
        for (int i = 0; i < n; i++) {
            s += d[i];
            while (j < queries.length && queries[j][0] <= i) {
                pq.offer(queries[j][1]);
                j++;
            }
            while (s < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                s++;
                d[pq.poll() + 1]--;
            }
            if (s < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxRemoval(vector<int>& nums, vector<vector<int>>& queries) {
        sort(queries.begin(), queries.end());
        priority_queue<int> pq;
        int n = nums.size();
        vector<int> d(n + 1, 0);
        int s = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            while (j < queries.size() && queries[j][0] <= i) {
                pq.push(queries[j][1]);
                ++j;
            }
            while (s < nums[i] && !pq.empty() && pq.top() >= i) {
                ++s;
                int end = pq.top();
                pq.pop();
                --d[end + 1];
            }
            if (s < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }
};
```

#### Go

```go
func maxRemoval(nums []int, queries [][]int) int {
	sort.Slice(queries, func(i, j int) bool {
		return queries[i][0] < queries[j][0]
	})

	var h hp
	heap.Init(&h)

	n := len(nums)
	d := make([]int, n+1)
	s, j := 0, 0

	for i := 0; i < n; i++ {
		s += d[i]
		for j < len(queries) && queries[j][0] <= i {
			heap.Push(&h, queries[j][1])
			j++
		}
		for s < nums[i] && h.Len() > 0 && h.IntSlice[0] >= i {
			s++
			end := heap.Pop(&h).(int)
			if end+1 < len(d) {
				d[end+1]--
			}
		}
		if s < nums[i] {
			return -1
		}
	}

	return h.Len()
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
```

#### TypeScript

```ts
function maxRemoval(nums: number[], queries: number[][]): number {
    queries.sort((a, b) => a[0] - b[0]);
    const pq = new MaxPriorityQueue<number>();
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let [s, j] = [0, 0];
    for (let i = 0; i < n; i++) {
        s += d[i];
        while (j < queries.length && queries[j][0] <= i) {
            pq.enqueue(queries[j][1]);
            j++;
        }
        while (s < nums[i] && !pq.isEmpty() && pq.front() >= i) {
            s++;
            d[pq.dequeue() + 1]--;
        }
        if (s < nums[i]) {
            return -1;
        }
    }
    return pq.size();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
