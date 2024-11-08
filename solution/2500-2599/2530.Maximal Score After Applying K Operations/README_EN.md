---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2530.Maximal%20Score%20After%20Applying%20K%20Operations/README_EN.md
rating: 1386
source: Weekly Contest 327 Q2
tags:
    - Greedy
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2530. Maximal Score After Applying K Operations](https://leetcode.com/problems/maximal-score-after-applying-k-operations)

[中文文档](/solution/2500-2599/2530.Maximal%20Score%20After%20Applying%20K%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>. You have a <strong>starting score</strong> of <code>0</code>.</p>

<p>In one <strong>operation</strong>:</p>

<ol>
	<li>choose an index <code>i</code> such that <code>0 &lt;= i &lt; nums.length</code>,</li>
	<li>increase your <strong>score</strong> by <code>nums[i]</code>, and</li>
	<li>replace <code>nums[i]</code> with <code>ceil(nums[i] / 3)</code>.</li>
</ol>

<p>Return <em>the maximum possible <strong>score</strong> you can attain after applying <strong>exactly</strong></em> <code>k</code> <em>operations</em>.</p>

<p>The ceiling function <code>ceil(val)</code> is the least integer greater than or equal to <code>val</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,10,10,10,10], k = 5
<strong>Output:</strong> 50
<strong>Explanation:</strong> Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,3,3,3], k = 3
<strong>Output:</strong> 17
<strong>Explanation: </strong>You can do the following operations:
Operation 1: Select i = 1, so nums becomes [1,<strong><u>4</u></strong>,3,3,3]. Your score increases by 10.
Operation 2: Select i = 1, so nums becomes [1,<strong><u>2</u></strong>,3,3,3]. Your score increases by 4.
Operation 3: Select i = 2, so nums becomes [1,2,<u><strong>1</strong></u>,3,3]. Your score increases by 3.
The final score is 10 + 4 + 3 = 17.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Max Heap)

To maximize the sum of scores, we need to select the element with the maximum value at each step. Therefore, we can use a priority queue (max heap) to maintain the element with the maximum value.

At each step, we take out the element with the maximum value $v$ from the priority queue, add $v$ to the answer, and replace $v$ with $\lceil \frac{v}{3} \rceil$, and then add it to the priority queue. After repeating this process $k$ times, we return the answer.

The time complexity is $O(n + k \times \log n)$, and the space complexity is $O(n)$ or $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxKelements(self, nums: List[int], k: int) -> int:
        h = [-v for v in nums]
        heapify(h)
        ans = 0
        for _ in range(k):
            v = -heappop(h)
            ans += v
            heappush(h, -(ceil(v / 3)))
        return ans
```

#### Java

```java
class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int v : nums) {
            pq.offer(v);
        }
        long ans = 0;
        while (k-- > 0) {
            int v = pq.poll();
            ans += v;
            pq.offer((v + 2) / 3);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        priority_queue<int> pq(nums.begin(), nums.end());
        long long ans = 0;
        while (k--) {
            int v = pq.top();
            pq.pop();
            ans += v;
            pq.push((v + 2) / 3);
        }
        return ans;
    }
};
```

#### Go

```go
func maxKelements(nums []int, k int) (ans int64) {
	h := &hp{nums}
	heap.Init(h)
	for ; k > 0; k-- {
		v := h.pop()
		ans += int64(v)
		h.push((v + 2) / 3)
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

#### TypeScript

```ts
function maxKelements(nums: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    nums.forEach(num => pq.enqueue(num));
    let ans = 0;
    while (k > 0) {
        const v = pq.dequeue()!.element;
        ans += v;
        pq.enqueue(Math.floor((v + 2) / 3));
        k--;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_kelements(nums: Vec<i32>, k: i32) -> i64 {
        let mut pq = BinaryHeap::from(nums);
        let mut ans = 0;
        let mut k = k;
        while k > 0 {
            if let Some(v) = pq.pop() {
                ans += v as i64;
                pq.push((v + 2) / 3);
                k -= 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxKelements(self, nums: List[int], k: int) -> int:
        for i, v in enumerate(nums):
            nums[i] = -v
        heapify(nums)
        ans = 0
        for _ in range(k):
            ans -= heapreplace(nums, -ceil(-nums[0] / 3))
        return ans
```

#### C++

```cpp
class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        make_heap(nums.begin(), nums.end());
        long long ans = 0;
        while (k--) {
            int v = nums[0];
            ans += v;
            pop_heap(nums.begin(), nums.end());
            nums.back() = (v + 2) / 3;
            push_heap(nums.begin(), nums.end());
        }
        return ans;
    }
};
```

#### Go

```go
func maxKelements(nums []int, k int) (ans int64) {
	h := hp{nums}
	heap.Init(&h)
	for ; k > 0; k-- {
		ans += int64(h.IntSlice[0])
		h.IntSlice[0] = (h.IntSlice[0] + 2) / 3
		heap.Fix(&h, 0)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Push(any)             {}
func (hp) Pop() (_ any)         { return }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
