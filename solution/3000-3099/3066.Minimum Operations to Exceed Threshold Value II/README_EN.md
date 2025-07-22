---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3066.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20II/README_EN.md
rating: 1399
source: Biweekly Contest 125 Q2
tags:
    - Array
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3066. Minimum Operations to Exceed Threshold Value II](https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii)

[中文文档](/solution/3000-3099/3066.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, and an integer <code>k</code>.</p>

<p>You are allowed to perform some operations on <code>nums</code>, where in a single operation, you can:</p>

<ul>
	<li>Select the two <strong>smallest</strong> integers <code>x</code> and <code>y</code> from <code>nums</code>.</li>
	<li>Remove <code>x</code> and <code>y</code> from <code>nums</code>.</li>
	<li>Insert <code>(min(x, y) * 2 + max(x, y))</code> at any position in the array.</li>
</ul>

<p><strong>Note</strong> that you can only apply the described operation if <code>nums</code> contains <strong>at least</strong> two elements.</p>

<p>Return the <strong>minimum</strong> number of operations needed so that all elements of the array are <strong>greater than or equal to</strong> <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,11,10,1,3], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ol>
	<li>In the first operation, we remove elements 1 and 2, then add <code>1 * 2 + 2</code> to <code>nums</code>. <code>nums</code> becomes equal to <code>[4, 11, 10, 3]</code>.</li>
	<li>In the second operation, we remove elements 3 and 4, then add <code>3 * 2 + 4</code> to <code>nums</code>. <code>nums</code> becomes equal to <code>[10, 11, 10]</code>.</li>
</ol>

<p>At this stage, all the elements of nums are greater than or equal to 10 so we can stop.&nbsp;</p>

<p>It can be shown that 2 is the minimum number of operations needed so that all elements of the array are greater than or equal to 10.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,4,9], k = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ol>
	<li>After one operation, <code>nums</code> becomes equal to <code>[2, 4, 9, 3]</code>.&nbsp;</li>
	<li>After two operations, <code>nums</code> becomes equal to <code>[7, 4, 9]</code>.&nbsp;</li>
	<li>After three operations, <code>nums</code> becomes equal to <code>[15, 9]</code>.&nbsp;</li>
	<li>After four operations, <code>nums</code> becomes equal to <code>[33]</code>.</li>
</ol>

<p>At this stage, all the elements of <code>nums</code> are greater than 20 so we can stop.&nbsp;</p>

<p>It can be shown that 4 is the minimum number of operations needed so that all elements of the array are greater than or equal to 20.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that an answer always exists. That is, after performing some number of operations, all elements of the array are greater than or equal to <code>k</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Min Heap)

We can use a priority queue (min heap) to simulate this process.

Specifically, we first add the elements in the array to the priority queue `pq`. Then we continuously take out the two smallest elements `x` and `y` from the priority queue, and put `min(x, y) * 2 + max(x, y)` back into the priority queue. After each operation, we increase the operation count by one. We stop the operation when the number of elements in the queue is less than 2 or the smallest element in the queue is greater than or equal to `k`.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        heapify(nums)
        ans = 0
        while len(nums) > 1 and nums[0] < k:
            x, y = heappop(nums), heappop(nums)
            heappush(nums, x * 2 + y)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer((long) x);
        }
        int ans = 0;
        for (; pq.size() > 1 && pq.peek() < k; ++ans) {
            long x = pq.poll(), y = pq.poll();
            pq.offer(x * 2 + y);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        using ll = long long;
        priority_queue<ll, vector<ll>, greater<ll>> pq;
        for (int x : nums) {
            pq.push(x);
        }
        int ans = 0;
        for (; pq.size() > 1 && pq.top() < k; ++ans) {
            ll x = pq.top();
            pq.pop();
            ll y = pq.top();
            pq.pop();
            pq.push(x * 2 + y);
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) (ans int) {
	pq := &hp{nums}
	heap.Init(pq)
	for ; pq.Len() > 1 && pq.IntSlice[0] < k; ans++ {
		x, y := heap.Pop(pq).(int), heap.Pop(pq).(int)
		heap.Push(pq, x*2+y)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[0 : n-1]
	return x
}
func (h *hp) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    const pq = new MinPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    for (; pq.size() > 1 && pq.front().element < k; ++ans) {
        const x = pq.dequeue().element;
        const y = pq.dequeue().element;
        pq.enqueue(x * 2 + y);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, k: i32) -> i32 {
        let mut pq = BinaryHeap::new();

        for &x in &nums {
            pq.push(-(x as i64));
        }

        let mut ans = 0;

        while pq.len() > 1 && -pq.peek().unwrap() < k as i64 {
            let x = -pq.pop().unwrap();
            let y = -pq.pop().unwrap();
            pq.push(-(x * 2 + y));
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
