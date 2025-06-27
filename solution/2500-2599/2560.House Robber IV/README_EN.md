---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2560.House%20Robber%20IV/README_EN.md
rating: 2081
source: Weekly Contest 331 Q3
tags:
    - Greedy
    - Array
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [2560. House Robber IV](https://leetcode.com/problems/house-robber-iv)

[中文文档](/solution/2500-2599/2560.House%20Robber%20IV/README.md)

## Description

<!-- description:start -->

<p>There are several consecutive houses along a street, each of which has some money inside. There is also a robber, who wants to steal money from the homes, but he <strong>refuses to steal from adjacent homes</strong>.</p>

<p>The <strong>capability</strong> of the robber is the maximum amount of money he steals from one house of all the houses he robbed.</p>

<p>You are given an integer array <code>nums</code> representing how much money is stashed in each house. More formally, the <code>i<sup>th</sup></code> house from the left has <code>nums[i]</code> dollars.</p>

<p>You are also given an integer <code>k</code>, representing the <strong>minimum</strong> number of houses the robber will steal from. It is always possible to steal at least <code>k</code> houses.</p>

<p>Return <em>the <strong>minimum</strong> capability of the robber out of all the possible ways to steal at least </em><code>k</code><em> houses</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,9], k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
There are three ways to rob at least 2 houses:
- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
Therefore, we return min(5, 9, 9) = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9,3,1], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= (nums.length + 1)/2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Greedy

The problem is asking for the minimum stealing ability of the thief. We can use binary search to enumerate the stealing ability of the thief. For the enumerated ability $x$, we can use a greedy approach to determine whether the thief can steal at least $k$ houses. Specifically, we traverse the array from left to right. For the current house $i$ we are traversing, if $nums[i] \leq x$ and the difference between the index of $i$ and the last stolen house is greater than $1$, then the thief can steal house $i$. Otherwise, the thief cannot steal house $i$. We accumulate the number of stolen houses. If the number of stolen houses is greater than or equal to $k$, it means that the thief can steal at least $k$ houses, and at this time, the stealing ability $x$ of the thief might be the minimum. Otherwise, the stealing ability $x$ of the thief is not the minimum.

The time complexity is $O(n \times \log m)$, and the space complexity is $O(1)$. Where $n$ and $m$ are the length of the array $nums$ and the maximum value in the array $nums$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCapability(self, nums: List[int], k: int) -> int:
        def f(x):
            cnt, j = 0, -2
            for i, v in enumerate(nums):
                if v > x or i == j + 1:
                    continue
                cnt += 1
                j = i
            return cnt >= k

        return bisect_left(range(max(nums) + 1), True, key=f)
```

#### Java

```java
class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 0, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (f(nums, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int f(int[] nums, int x) {
        int cnt = 0, j = -2;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > x || i == j + 1) {
                continue;
            }
            ++cnt;
            j = i;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCapability(vector<int>& nums, int k) {
        auto f = [&](int x) {
            int cnt = 0, j = -2;
            for (int i = 0; i < nums.size(); ++i) {
                if (nums[i] > x || i == j + 1) {
                    continue;
                }
                ++cnt;
                j = i;
            }
            return cnt >= k;
        };
        int left = 0, right = *max_element(nums.begin(), nums.end());
        while (left < right) {
            int mid = (left + right) >> 1;
            if (f(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func minCapability(nums []int, k int) int {
	return sort.Search(1e9+1, func(x int) bool {
		cnt, j := 0, -2
		for i, v := range nums {
			if v > x || i == j+1 {
				continue
			}
			cnt++
			j = i
		}
		return cnt >= k
	})
}
```

#### TypeScript

```ts
function minCapability(nums: number[], k: number): number {
    const f = (mx: number): boolean => {
        let cnt = 0;
        let j = -2;
        for (let i = 0; i < nums.length; ++i) {
            if (nums[i] <= mx && i - j > 1) {
                ++cnt;
                j = i;
            }
        }
        return cnt >= k;
    };

    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        if (f(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
