---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0974.Subarray%20Sums%20Divisible%20by%20K/README_EN.md
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k)

[中文文档](/solution/0900-0999/0974.Subarray%20Sums%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of non-empty <strong>subarrays</strong> that have a sum divisible by </em><code>k</code>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,0,-2,-3,1], k = 5
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5], k = 9
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Prefix Sum

Suppose there exists $i \leq j$ such that the sum of $\textit{nums}[i,..j]$ is divisible by $k$. If we let $s_i$ represent the sum of $\textit{nums}[0,..i]$ and $s_j$ represent the sum of $\textit{nums}[0,..j]$, then $s_j - s_i$ is divisible by $k$, i.e., $(s_j - s_i) \bmod k = 0$, which means $s_j \bmod k = s_i \bmod k$. Therefore, we can use a hash table to count the number of prefix sums modulo $k$, allowing us to quickly determine if there exists a subarray that meets the condition.

We use a hash table $\textit{cnt}$ to count the number of prefix sums modulo $k$, where $\textit{cnt}[i]$ represents the number of prefix sums with a modulo $k$ value of $i$. Initially, $\textit{cnt}[0] = 1$. We use a variable $s$ to represent the prefix sum, initially $s = 0$.

Next, we traverse the array $\textit{nums}$ from left to right. For each element $x$, we calculate $s = (s + x) \bmod k$, then update the answer $\textit{ans} = \textit{ans} + \textit{cnt}[s]$, where $\textit{cnt}[s]$ represents the number of prefix sums with a modulo $k$ value of $s$. Finally, we increment the value of $\textit{cnt}[s]$ by $1$ and continue to the next element.

In the end, we return the answer $\textit{ans}$.

> Note: Since the value of $s$ can be negative, we can add $k$ to the result of $s \bmod k$ and then take modulo $k$ again to ensure that the value of $s$ is non-negative.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subarraysDivByK(self, nums: List[int], k: int) -> int:
        cnt = Counter({0: 1})
        ans = s = 0
        for x in nums:
            s = (s + x) % k
            ans += cnt[s]
            cnt[s] += 1
        return ans
```

#### Java

```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int ans = 0, s = 0;
        for (int x : nums) {
            s = ((s + x) % k + k) % k;
            ans += cnt.getOrDefault(s, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        unordered_map<int, int> cnt{{0, 1}};
        int ans = 0, s = 0;
        for (int& x : nums) {
            s = ((s + x) % k + k) % k;
            ans += cnt[s]++;
        }
        return ans;
    }
};
```

#### Go

```go
func subarraysDivByK(nums []int, k int) (ans int) {
	cnt := map[int]int{0: 1}
	s := 0
	for _, x := range nums {
		s = ((s+x)%k + k) % k
		ans += cnt[s]
		cnt[s]++
	}
	return
}
```

#### TypeScript

```ts
function subarraysDivByK(nums: number[], k: number): number {
    const cnt: { [key: number]: number } = { 0: 1 };
    let s = 0;
    let ans = 0;
    for (const x of nums) {
        s = (((s + x) % k) + k) % k;
        ans += cnt[s] || 0;
        cnt[s] = (cnt[s] || 0) + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
