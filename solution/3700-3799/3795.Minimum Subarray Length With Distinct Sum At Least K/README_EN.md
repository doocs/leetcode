---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3795.Minimum%20Subarray%20Length%20With%20Distinct%20Sum%20At%20Least%20K/README_EN.md
rating: 1504
source: Biweekly Contest 173 Q2
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [3795. Minimum Subarray Length With Distinct Sum At Least K](https://leetcode.com/problems/minimum-subarray-length-with-distinct-sum-at-least-k)

[中文文档](/solution/3700-3799/3795.Minimum%20Subarray%20Length%20With%20Distinct%20Sum%20At%20Least%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return the <strong>minimum</strong> length of a <strong><span data-keyword="subarray-nonempty">subarray</span></strong> whose sum of the <strong>distinct</strong> values present in that subarray (each value counted once) is <strong>at least</strong> <code>k</code>. If no such subarray exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,3,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[2, 3]</code> has distinct elements <code>{2, 3}</code> whose sum is <code>2 + 3 = 5</code>, which is ​​​​​​​at least <code>k = 4</code>. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,3,4], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[3, 2]</code> has distinct elements <code>{3, 2}</code> whose sum is <code>3 + 2 = 5</code>, which is ​​​​​​​at least <code>k = 5</code>. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,4], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[5]</code> has distinct elements <code>{5}</code> whose sum is <code>5</code>, which is at least <code>k = 5</code>. Thus, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We use a hash table $\textit{cnt}$ to record the occurrence count of each element in the current window, and a variable $\textit{s}$ to record the sum of distinct elements in the current window. We use two pointers $l$ and $r$ to represent the left and right boundaries of the current window, both initially pointing to the beginning of the array. We initialize a variable $\textit{ans}$ to record the minimum length of a window that satisfies the condition, with an initial value of $n + 1$, where $n$ is the length of the array.

We continuously move the right pointer $r$, adding new elements into the window and updating $\textit{cnt}$ and $\textit{s}$. When $\textit{s}$ is greater than or equal to $k$, we try to move the left pointer $l$ to shrink the window, updating $\textit{cnt}$ and $\textit{s}$ accordingly, until $\textit{s}$ is less than $k$. During this process, we record the minimum length of windows that satisfy the condition.

Finally, if $\textit{ans} \gt n$, it means no valid window exists, and we return $-1$; otherwise we return $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        n = len(nums)
        ans = n + 1
        s = l = 0
        for r, x in enumerate(nums):
            cnt[x] += 1
            if cnt[x] == 1:
                s += x
            while s >= k:
                ans = min(ans, r - l + 1)
                cnt[nums[l]] -= 1
                if cnt[nums[l]] == 0:
                    s -= nums[l]
                l += 1
        return -1 if ans > n else ans
```

#### Java

```java
class Solution {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int ans = n + 1;
        Map<Integer, Integer> cnt = new HashMap<>();
        int l = 0;
        long s = 0;
        for (int r = 0; r < n; ++r) {
            if (cnt.merge(nums[r], 1, Integer::sum) == 1) {
                s += nums[r];
            }
            while (s >= k) {
                ans = Math.min(ans, r - l + 1);
                if (cnt.merge(nums[l], -1, Integer::sum) == 0) {
                    s -= nums[l];
                }
                ++l;
            }
        }
        return ans > n ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = n + 1;
        unordered_map<int, int> cnt;
        int l = 0;
        long long s = 0;
        for (int r = 0; r < n; ++r) {
            int x = nums[r];
            if (++cnt[x] == 1) {
                s += x;
            }
            while (s >= k) {
                ans = min(ans, r - l + 1);
                int y = nums[l];
                if (--cnt[y] == 0) {
                    s -= y;
                }
                ++l;
            }
        }
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minLength(nums []int, k int) int {
	n := len(nums)
	ans := n + 1
	cnt := map[int]int{}
	l := 0
	var s int64 = 0
	for r := 0; r < n; r++ {
		cnt[nums[r]]++
		if cnt[nums[r]] == 1 {
			s += int64(nums[r])
		}
		for s >= int64(k) {
			if r-l+1 < ans {
				ans = r - l + 1
			}
			if cnt[nums[l]]--; cnt[nums[l]] == 0 {
				s -= int64(nums[l])
			}
			l++
		}
	}
	if ans > n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minLength(nums: number[], k: number): number {
    const n = nums.length;
    let ans = n + 1;
    const cnt = new Map<number, number>();
    let l = 0;
    let s = 0;
    for (let r = 0; r < n; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        if (cnt.get(nums[r]) === 1) {
            s += nums[r];
        }
        while (s >= k) {
            ans = Math.min(ans, r - l + 1);
            cnt.set(nums[l], (cnt.get(nums[l]) ?? 0) - 1);
            if (cnt.get(nums[l]) === 0) {
                s -= nums[l];
            }
            ++l;
        }
    }
    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
