---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/README_EN.md
rating: 1749
source: Biweekly Contest 113 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Two Pointers
    - Binary Search
    - Counting
---

<!-- problem:start -->

# [2856. Minimum Array Length After Pair Removals](https://leetcode.com/problems/minimum-array-length-after-pair-removals)

[中文文档](/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>num</code> sorted in non-decreasing order.</p>

<p>You can perform the following operation any number of times:</p>

<ul>
	<li>Choose <strong>two</strong> indices, <code>i</code> and <code>j</code>, where <code>nums[i] &lt; nums[j]</code>.</li>
	<li>Then, remove the elements at indices <code>i</code> and <code>j</code> from <code>nums</code>. The remaining elements retain their original order, and the array is re-indexed.</li>
</ul>

<p>Return the <strong>minimum</strong> length of <code>nums</code> after applying the operation zero or more times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/images/tcase1.gif" style="width: 160px; height: 70px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/images/tcase2.gif" style="width: 240px; height: 70px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1000000000,1000000000]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Since both numbers are equal, they cannot be removed.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,4,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/images/tcase3.gif" style="width: 210px; height: 70px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue (Max Heap)

We use a hash table $cnt$ to count the occurrence of each element in the array $nums$, then add each value in $cnt$ to a priority queue (max heap) $pq$. Each time we take out two elements $x$ and $y$ from $pq$, decrease their values by one. If the value after decrement is still greater than $0$, we add the decremented value back to $pq$. Each time we take out two elements from $pq$, it means we delete a pair of numbers from the array, so the length of the array decreases by $2$. When the size of $pq$ is less than $2$, we stop the deletion operation.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLengthAfterRemovals(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        pq = [-x for x in cnt.values()]
        heapify(pq)
        ans = len(nums)
        while len(pq) > 1:
            x, y = -heappop(pq), -heappop(pq)
            x -= 1
            y -= 1
            if x > 0:
                heappush(pq, -x)
            if y > 0:
                heappush(pq, -y)
            ans -= 2
        return ans
```

#### Java

```java
class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : cnt.values()) {
            pq.offer(x);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            x--;
            y--;
            if (x > 0) {
                pq.offer(x);
            }
            if (y > 0) {
                pq.offer(y);
            }
            ans -= 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLengthAfterRemovals(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        priority_queue<int> pq;
        for (auto& [_, v] : cnt) {
            pq.push(v);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.top();
            pq.pop();
            int y = pq.top();
            pq.pop();
            x--;
            y--;
            if (x > 0) {
                pq.push(x);
            }
            if (y > 0) {
                pq.push(y);
            }
            ans -= 2;
        }
        return ans;
    }
};
```

#### Go

```go
func minLengthAfterRemovals(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	h := &hp{}
	for _, x := range cnt {
		h.push(x)
	}
	ans := len(nums)
	for h.Len() > 1 {
		x, y := h.pop(), h.pop()
		if x > 1 {
			h.push(x - 1)
		}
		if y > 1 {
			h.push(y - 1)
		}
		ans -= 2
	}
	return ans
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
function minLengthAfterRemovals(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MaxPriorityQueue();
    for (const [_, v] of cnt) {
        pq.enqueue(v);
    }
    let ans = nums.length;
    while (pq.size() > 1) {
        let x = pq.dequeue().element;
        let y = pq.dequeue().element;
        if (--x > 0) {
            pq.enqueue(x);
        }
        if (--y > 0) {
            pq.enqueue(y);
        }
        ans -= 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
