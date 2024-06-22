---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1248.Count%20Number%20of%20Nice%20Subarrays/README_EN.md
rating: 1623
source: Weekly Contest 161 Q2
tags:
    - Array
    - Hash Table
    - Math
    - Sliding Window
---

<!-- problem:start -->

# [1248. Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays)

[中文文档](/solution/1200-1299/1248.Count%20Number%20of%20Nice%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>. A continuous subarray is called <strong>nice</strong> if there are <code>k</code> odd numbers on it.</p>

<p>Return <em>the number of <strong>nice</strong> sub-arrays</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,1,1], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6], k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no odd numbers in the array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,1,2,2,1,2,2,2], k = 2
<strong>Output:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Array or Hash Table

The problem asks for the number of subarrays that contain exactly $k$ odd numbers. We can calculate the number of odd numbers $t$ in each prefix array and record it in an array or hash table $cnt$. For each prefix array, we only need to find the number of prefix arrays with $t-k$ odd numbers, which is the number of subarrays ending with the current prefix array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubarrays(self, nums: List[int], k: int) -> int:
        cnt = Counter({0: 1})
        ans = t = 0
        for v in nums:
            t += v & 1
            ans += cnt[t - k]
            cnt[t] += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        int ans = 0, t = 0;
        for (int v : nums) {
            t += v & 1;
            if (t - k >= 0) {
                ans += cnt[t - k];
            }
            cnt[t]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> cnt(n + 1);
        cnt[0] = 1;
        int ans = 0, t = 0;
        for (int& v : nums) {
            t += v & 1;
            if (t - k >= 0) {
                ans += cnt[t - k];
            }
            cnt[t]++;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubarrays(nums []int, k int) (ans int) {
	n := len(nums)
	cnt := make([]int, n+1)
	cnt[0] = 1
	t := 0
	for _, v := range nums {
		t += v & 1
		if t >= k {
			ans += cnt[t-k]
		}
		cnt[t]++
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    cnt[0] = 1;
    let [t, ans] = [0, 0];
    for (const v of nums) {
        t += v & 1;
        ans += cnt[t - k] ?? 0;
        cnt[t] += 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
