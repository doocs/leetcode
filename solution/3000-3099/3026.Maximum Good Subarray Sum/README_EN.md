---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3026.Maximum%20Good%20Subarray%20Sum/README_EN.md
rating: 1816
source: Biweekly Contest 123 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [3026. Maximum Good Subarray Sum](https://leetcode.com/problems/maximum-good-subarray-sum)

[中文文档](/solution/3000-3099/3026.Maximum%20Good%20Subarray%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of length <code>n</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> of <code>nums</code> is called <strong>good</strong> if the <strong>absolute difference</strong> between its first and last element is <strong>exactly</strong> <code>k</code>, in other words, the subarray <code>nums[i..j]</code> is good if <code>|nums[i] - nums[j]| == k</code>.</p>

<p>Return <em>the <strong>maximum</strong> sum of a <strong>good</strong> subarray of </em><code>nums</code>. <em>If there are no good subarrays</em><em>, return </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], k = 1
<strong>Output:</strong> 11
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 1 for a good subarray. All the good subarrays are: [1,2], [2,3], [3,4], [4,5], and [5,6]. The maximum subarray sum is 11 for the subarray [5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,3,2,4,5], k = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 3 for a good subarray. All the good subarrays are: [-1,3,2], and [2,4,5]. The maximum subarray sum is 11 for the subarray [2,4,5].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4], k = 2
<strong>Output:</strong> -6
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 2 for a good subarray. All the good subarrays are: [-1,-2,-3], and [-2,-3,-4]. The maximum subarray sum is -6 for the subarray [-1,-2,-3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

We use a hash table $p$ to record the sum $s$ of the prefix array $nums[0..i-1]$ for $nums[i]$. If there are multiple identical $nums[i]$, we only keep the smallest $s$. Initially, we set $p[nums[0]]$ to $0$. In addition, we use a variable $s$ to record the current prefix sum, initially $s = 0$. Initialize the answer $ans$ to $-\infty$.

Next, we enumerate $nums[i]$, and maintain a variable $s$ to represent the sum of $nums[0..i]$. If $nums[i] - k$ is in $p$, then we have found a good subarray, and update the answer to $ans = \max(ans, s - p[nums[i] - k])$. Similarly, if $nums[i] + k$ is in $p$, then we have also found a good subarray, and update the answer to $ans = \max(ans, s - p[nums[i] + k])$. Then, if $i + 1 \lt n$ and $nums[i + 1]$ is not in $p$, or $p[nums[i + 1]] \gt s$, we set $p[nums[i + 1]]$ to $s$.

Finally, if $ans = -\infty$, then we return $0$, otherwise return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        ans = -inf
        p = {nums[0]: 0}
        s, n = 0, len(nums)
        for i, x in enumerate(nums):
            s += x
            if x - k in p:
                ans = max(ans, s - p[x - k])
            if x + k in p:
                ans = max(ans, s - p[x + k])
            if i + 1 < n and (nums[i + 1] not in p or p[nums[i + 1]] > s):
                p[nums[i + 1]] = s
        return 0 if ans == -inf else ans
```

#### Java

```java
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Long> p = new HashMap<>();
        p.put(nums[0], 0L);
        long s = 0;
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (p.containsKey(nums[i] - k)) {
                ans = Math.max(ans, s - p.get(nums[i] - k));
            }
            if (p.containsKey(nums[i] + k)) {
                ans = Math.max(ans, s - p.get(nums[i] + k));
            }
            if (i + 1 < n && (!p.containsKey(nums[i + 1]) || p.get(nums[i + 1]) > s)) {
                p.put(nums[i + 1], s);
            }
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, long long> p;
        p[nums[0]] = 0;
        long long s = 0;
        const int n = nums.size();
        long long ans = LONG_LONG_MIN;
        for (int i = 0;; ++i) {
            s += nums[i];
            auto it = p.find(nums[i] - k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            it = p.find(nums[i] + k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            if (i + 1 == n) {
                break;
            }
            it = p.find(nums[i + 1]);
            if (it == p.end() || it->second > s) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == LONG_LONG_MIN ? 0 : ans;
    }
};
```

#### Go

```go
func maximumSubarraySum(nums []int, k int) int64 {
	p := map[int]int64{nums[0]: 0}
	var s int64 = 0
	n := len(nums)
	var ans int64 = math.MinInt64
	for i, x := range nums {
		s += int64(x)
		if t, ok := p[nums[i]-k]; ok {
			ans = max(ans, s-t)
		}
		if t, ok := p[nums[i]+k]; ok {
			ans = max(ans, s-t)
		}
		if i+1 == n {
			break
		}
		if t, ok := p[nums[i+1]]; !ok || s < t {
			p[nums[i+1]] = s
		}
	}
	if ans == math.MinInt64 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function maximumSubarraySum(nums: number[], k: number): number {
    const p: Map<number, number> = new Map();
    p.set(nums[0], 0);
    let ans: number = -Infinity;
    let s: number = 0;
    const n: number = nums.length;
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        if (p.has(nums[i] - k)) {
            ans = Math.max(ans, s - p.get(nums[i] - k)!);
        }
        if (p.has(nums[i] + k)) {
            ans = Math.max(ans, s - p.get(nums[i] + k)!);
        }
        if (i + 1 < n && (!p.has(nums[i + 1]) || p.get(nums[i + 1])! > s)) {
            p.set(nums[i + 1], s);
        }
    }
    return ans === -Infinity ? 0 : ans;
}
```

#### C#

```cs
public class Solution {
    public long MaximumSubarraySum(int[] nums, int k) {
        Dictionary<int, long> p = new Dictionary<int, long>();
        p[nums[0]] = 0L;
        long s = 0;
        int n = nums.Length;
        long ans = long.MinValue;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (p.ContainsKey(nums[i] - k)) {
                ans = Math.Max(ans, s - p[nums[i] - k]);
            }
            if (p.ContainsKey(nums[i] + k)) {
                ans = Math.Max(ans, s - p[nums[i] + k]);
            }
            if (i + 1 < n && (!p.ContainsKey(nums[i + 1]) || p[nums[i + 1]] > s)) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == long.MinValue ? 0 : ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
