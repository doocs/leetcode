---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3781.Maximum%20Score%20After%20Binary%20Swaps/README_EN.md
rating: 1823
source: Biweekly Contest 172 Q3
tags:
    - Greedy
    - Array
    - String
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3781. Maximum Score After Binary Swaps](https://leetcode.com/problems/maximum-score-after-binary-swaps)

[中文文档](/solution/3700-3799/3781.Maximum%20Score%20After%20Binary%20Swaps/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a binary string <code>s</code> of the same length.</p>

<p>Initially, your score is 0. Each index <code>i</code> where <code>s[i] = &#39;1&#39;</code> contributes <code>nums[i]</code> to the score.</p>

<p>You may perform <strong>any</strong> number of operations (including zero). In one operation, you may choose an index <code>i</code> such that <code>0 &lt;= i &lt; n - 1</code>, where <code>s[i] = &#39;0&#39;</code>, and <code>s[i + 1] = &#39;1&#39;</code>, and swap these two characters.</p>

<p>Return an integer denoting the <strong>maximum possible score</strong> you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,5,2,3], s = &quot;01010&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>We can perform the following swaps:</p>

<ul>
	<li>Swap at index <code>i = 0</code>: <code>&quot;01010&quot;</code> changes to <code>&quot;10010&quot;</code></li>
	<li>Swap at index <code>i = 2</code>: <code>&quot;10010&quot;</code> changes to <code>&quot;10100&quot;</code></li>
</ul>

<p>Positions 0 and 2 contain <code>&#39;1&#39;</code>, contributing <code>nums[0] + nums[2] = 2 + 5 = 7</code>. This is maximum score achievable.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,7,2,9], s = &quot;0000&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no <code>&#39;1&#39;</code> characters in <code>s</code>, so no swaps can be performed. The score remains 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Max-Heap

According to the problem statement, each `'1'` can be swapped left any number of times, so each `'1'` can choose the largest unpicked number to its left. We can maintain these candidates with a max-heap.

Traverse the string $s$: for each position $i$, push the corresponding number $\textit{nums}[i]$ into the max-heap; if $s[i] = '1'$, pop the maximum from the heap and add it to the answer.

After the traversal, the accumulated sum is the maximum score.

The time complexity is $O(n \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int], s: str) -> int:
        ans = 0
        pq = []
        for x, c in zip(nums, s):
            heappush(pq, -x)
            if c == "1":
                ans -= heappop(pq)
        return ans
```

#### Java

```java
class Solution {
    public long maximumScore(int[] nums, String s) {
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            char c = s.charAt(i);
            pq.offer(x);
            if (c == '1') {
                ans += pq.poll();
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScore(vector<int>& nums, string s) {
        long long ans = 0;
        priority_queue<int> pq;
        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i];
            char c = s[i];
            pq.push(x);
            if (c == '1') {
                ans += pq.top();
                pq.pop();
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int, s string) int64 {
	var ans int64
	pq := &hp{}
	heap.Init(pq)
	for i, x := range nums {
		pq.push(x)
		if s[i] == '1' {
			ans += int64(pq.pop())
		}
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
function maximumScore(nums: number[], s: string): number {
    let ans = 0;
    const pq = new MaxPriorityQueue<number>();

    for (let i = 0; i < nums.length; i++) {
        const x = nums[i];
        const c = s[i];
        pq.enqueue(x);
        if (c === '1') {
            ans += pq.dequeue()!;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
