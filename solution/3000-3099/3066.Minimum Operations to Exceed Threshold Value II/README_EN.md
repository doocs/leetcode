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

<p>In one operation, you will:</p>

<ul>
	<li>Take the two smallest integers <code>x</code> and <code>y</code> in <code>nums</code>.</li>
	<li>Remove <code>x</code> and <code>y</code> from <code>nums</code>.</li>
	<li>Add <code>min(x, y) * 2 + max(x, y)</code> anywhere in the array.</li>
</ul>

<p><strong>Note</strong> that you can only apply the described operation if <code>nums</code> contains at least two elements.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed so that all elements of the array are greater than or equal to</em> <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,11,10,1,3], k = 10
<strong>Output:</strong> 2
<strong>Explanation:</strong> In the first operation, we remove elements 1 and 2, then add 1 * 2 + 2 to nums. nums becomes equal to [4, 11, 10, 3].
In the second operation, we remove elements 3 and 4, then add 3 * 2 + 4 to nums. nums becomes equal to [10, 11, 10].
At this stage, all the elements of nums are greater than or equal to 10 so we can stop.
It can be shown that 2 is the minimum number of operations needed so that all elements of the array are greater than or equal to 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,4,9], k = 20
<strong>Output:</strong> 4
<strong>Explanation:</strong> After one operation, nums becomes equal to [2, 4, 9, 3].
After two operations, nums becomes equal to [7, 4, 9].
After three operations, nums becomes equal to [15, 9].
After four operations, nums becomes equal to [33].
At this stage, all the elements of nums are greater than 20 so we can stop.
It can be shown that 4 is the minimum number of operations needed so that all elements of the array are greater than or equal to 20.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that an answer always exists. That is, there exists some sequence of operations after which all elements of the array are greater than or equal to <code>k</code>.</li>
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
            heappush(nums, min(x, y) * 2 + max(x, y))
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
            pq.offer(Math.min(x, y) * 2 + Math.max(x, y));
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
            pq.push(min(x, y) * 2 + max(x, y));
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
		heap.Push(pq, min(x, y)*2+max(x, y))
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
        pq.enqueue(Math.min(x, y) * 2 + Math.max(x, y));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
