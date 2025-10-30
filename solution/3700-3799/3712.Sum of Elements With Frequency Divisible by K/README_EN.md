---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3712.Sum%20of%20Elements%20With%20Frequency%20Divisible%20by%20K/README_EN.md
rating: 1198
source: Weekly Contest 471 Q1
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3712. Sum of Elements With Frequency Divisible by K](https://leetcode.com/problems/sum-of-elements-with-frequency-divisible-by-k)

[中文文档](/solution/3700-3799/3712.Sum%20of%20Elements%20With%20Frequency%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return an integer denoting the <strong>sum</strong> of all elements in <code>nums</code> whose <strong><span data-keyword="frequency-array">frequency</span></strong> is divisible by <code>k</code>, or 0 if there are no such elements.</p>

<p><strong>Note:</strong> An element is included in the sum <strong>exactly</strong> as many times as it appears in the array if its total frequency is divisible by <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,3,3,3,3,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The number 1 appears once (odd frequency).</li>
	<li>The number 2 appears twice (even frequency).</li>
	<li>The number 3 appears four times (even frequency).</li>
	<li>The number 4 appears once (odd frequency).</li>
</ul>

<p>So, the total sum is <code>2 + 2 + 3 + 3 + 3 + 3 = 16</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no elements that appear an even number of times, so the total sum is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4,4,1,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The number 1 appears once.</li>
	<li>The number 2 appears once.</li>
	<li>The number 3 appears once.</li>
	<li>The number 4 appears three times.</li>
</ul>

<p>So, the total sum is <code>4 + 4 + 4 = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We use a hash table $\textit{cnt}$ to record the frequency of each number. We traverse the array $\textit{nums}$, and for each number $x$, we increment $\textit{cnt}[x]$ by $1$.

Then, we traverse the hash table $\textit{cnt}$. For each element $x$, if its frequency $\textit{cnt}[x]$ is divisible by $k$, we add $x$ multiplied by its frequency to the result.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(m)$, where $m$ is the number of distinct elements in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumDivisibleByK(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        return sum(x * v for x, v in cnt.items() if v % k == 0)
```

#### Java

```java
class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v % k == 0) {
                ans += x * v;
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
    int sumDivisibleByK(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            if (v % k == 0) {
                ans += x * v;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sumDivisibleByK(nums []int, k int) (ans int) {
    cnt := map[int]int{}
    for _, x := range nums {
        cnt[x]++
    }
    for x, v := range cnt {
        if v%k == 0 {
            ans += x * v
        }
    }
    return
}
```

#### TypeScript

```ts
function sumDivisibleByK(nums: number[], k: number): number {
    const cnt = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        if (v % k === 0) {
            ans += x * v;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
