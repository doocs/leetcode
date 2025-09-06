---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3409.Longest%20Subsequence%20With%20Decreasing%20Adjacent%20Difference/README_EN.md
rating: 2500
source: Biweekly Contest 147 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3409. Longest Subsequence With Decreasing Adjacent Difference](https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference)

[中文文档](/solution/3400-3499/3409.Longest%20Subsequence%20With%20Decreasing%20Adjacent%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code>.</p>

<p>Your task is to find the length of the <strong>longest</strong> <span data-keyword="subsequence-array">subsequence</span> <code>seq</code> of <code>nums</code>, such that the <strong>absolute differences</strong> between<em> consecutive</em> elements form a <strong>non-increasing sequence</strong> of integers. In other words, for a subsequence <code>seq<sub>0</sub></code>, <code>seq<sub>1</sub></code>, <code>seq<sub>2</sub></code>, ..., <code>seq<sub>m</sub></code> of <code>nums</code>, <code>|seq<sub>1</sub> - seq<sub>0</sub>| &gt;= |seq<sub>2</sub> - seq<sub>1</sub>| &gt;= ... &gt;= |seq<sub>m</sub> - seq<sub>m - 1</sub>|</code>.</p>

<p>Return the length of such a subsequence.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [16,6,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The longest subsequence is <code>[16, 6, 3]</code> with the absolute adjacent differences <code>[10, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,5,3,4,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest subsequence is <code>[6, 4, 2, 1]</code> with the absolute adjacent differences <code>[2, 2, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,20,10,19,10,20]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The longest subsequence is <code>[10, 20, 10, 19, 10]</code> with the absolute adjacent differences <code>[10, 10, 9, 9]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 300</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        mx = max(nums)
        mn = min(nums)

        diff = mx - mn
        dp = [[0] * (diff + 1) for _ in range(mx + 1)]
        ans = 0

        for n in nums:
            maxnum = 1
            for i in range(diff, -1, -1):
                if n + i <= mx and dp[n + i][i] + 1 > maxnum:
                    maxnum = dp[n + i][i] + 1
                if n - i >= 0 and dp[n - i][i] + 1 > maxnum:
                    maxnum = dp[n - i][i] + 1

                dp[n][i] = maxnum
                ans = max(maxnum,ans)

        return ans
```

#### Java

```java

```

#### C++

```cpp
class Solution {
 public:
  int longestSubsequence(vector<int>& nums) {
    const int mx = ranges::max(nums);
    vector<vector<int>> dp(mx + 1, vector<int>(mx + 1));

    for (const int num : nums) {
      for (int prev = 1; prev <= mx; ++prev) {
        const int diff = abs(num - prev);
        dp[num][diff] = max(dp[num][diff], dp[prev][diff] + 1);
      }
      for (int j = mx - 1; j >= 0; --j)
        dp[num][j] = max(dp[num][j], dp[num][j + 1]);
    }

    return ranges::max_element(dp, ranges::less{}, [](const vector<int>& row) {
      return row[0];
    })->at(0);
  }
};
```

#### Go

```go
func longestSubsequence(nums []int) int {
	mx, mn := nums[0], nums[0]
	for _, v := range nums {
		if v > mx {
			mx = v
		}
		if v < mn {
			mn = v
		}
	}
	diff := mx - mn
	dp := make([][]int, mx+1)
	for i := range dp {
		dp[i] = make([]int, diff+1)
	}

	ans := 0

	for _, n := range nums {
		maxnum := 1
		for d := diff; d >= 0; d-- {
			if n+d <= mx && dp[n+d][d]+1 > maxnum {
				maxnum = dp[n+d][d] + 1
			}
			if n-d >= 0 && dp[n-d][d]+1 > maxnum {
				maxnum = dp[n-d][d] + 1
			}
			dp[n][d] = maxnum
			if maxnum > ans {
				ans = maxnum
			}
		}
	}

	return ans
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_subsequence(nums: Vec<i32>) -> i32 {
        let mx = *nums.iter().max().unwrap();
        let mn = *nums.iter().min().unwrap();
        let diff = (mx - mn) as usize;

        let mut dp = vec![vec![0; diff + 1]; (mx + 1) as usize];

        let mut ans = 0;

        for &n in nums.iter() {
            let n_usize = n as usize;
            let mut maxnum = 1;

            for d in (0..=diff).rev() {
                if n_usize + d <= mx as usize {
                    if dp[n_usize + d][d] + 1 > maxnum {
                        maxnum = dp[n_usize + d][d] + 1;
                    }
                }
                if n_usize >= d {
                    if dp[n_usize - d][d] + 1 > maxnum {
                        maxnum = dp[n_usize - d][d] + 1;
                    }
                }
                dp[n_usize][d] = maxnum;
                ans = ans.max(maxnum);
            }
        }

        ans
    }
}


```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
