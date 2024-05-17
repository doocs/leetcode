---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2644.Find%20the%20Maximum%20Divisibility%20Score/README_EN.md
rating: 1257
source: Weekly Contest 341 Q2
tags:
    - Array
---

<!-- problem:start -->

# [2644. Find the Maximum Divisibility Score](https://leetcode.com/problems/find-the-maximum-divisibility-score)

[中文文档](/solution/2600-2699/2644.Find%20the%20Maximum%20Divisibility%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums</code> and <code>divisors</code>.</p>

<p>The <strong>divisibility score</strong> of <code>divisors[i]</code> is the number of indices <code>j</code> such that <code>nums[j]</code> is divisible by <code>divisors[i]</code>.</p>

<p>Return <em>the integer</em> <code>divisors[i]</code> <em>with the maximum divisibility score</em>. If there is more than one integer with the maximum score, return the minimum of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,7,9,3,9], divisors = [5,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 0 since no number in nums is divisible by 5.
The divisibility score of divisors[1] is 1 since nums[0] is divisible by 2.
The divisibility score of divisors[2] is 3 since nums[2], nums[3], and nums[4] are divisible by 3.
Since divisors[2] has the maximum divisibility score, we return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [20,14,21,10], divisors = [5,7,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 2 since nums[0] and nums[3] are divisible by 5.
The divisibility score of divisors[1] is 2 since nums[1] and nums[2] are divisible by 7.
The divisibility score of divisors[2] is 2 since nums[0] and nums[3] are divisible by 5.
Since divisors[0], divisors[1], and divisors[2] all have the maximum divisibility score, we return the minimum of them (i.e., divisors[2]).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [12], divisors = [10,16]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 0 since no number in nums is divisible by 10.
The divisibility score of divisors[1] is 0 since no number in nums is divisible by 16.
Since divisors[0] and divisors[1] both have the maximum divisibility score, we return the minimum of them (i.e., divisors[0]).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, divisors.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], divisors[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each element $div$ in $divisors$, and calculate how many elements in $nums$ can be divided by $div$, denoted as $cnt$.

-   If $cnt$ is greater than the current maximum divisibility score $mx$, then update $mx = cnt$, and update $ans = div$.
-   If $cnt$ equals $mx$ and $div$ is less than $ans$, then update $ans = div$.

Finally, return $ans$.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the lengths of $nums$ and $divisors$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDivScore(self, nums: List[int], divisors: List[int]) -> int:
        ans, mx = divisors[0], 0
        for div in divisors:
            cnt = sum(x % div == 0 for x in nums)
            if mx < cnt:
                mx, ans = cnt, div
            elif mx == cnt and ans > div:
                ans = div
        return ans
```

#### Java

```java
class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int ans = divisors[0];
        int mx = 0;
        for (int div : divisors) {
            int cnt = 0;
            for (int x : nums) {
                if (x % div == 0) {
                    ++cnt;
                }
            }
            if (mx < cnt) {
                mx = cnt;
                ans = div;
            } else if (mx == cnt) {
                ans = Math.min(ans, div);
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
    int maxDivScore(vector<int>& nums, vector<int>& divisors) {
        int ans = divisors[0];
        int mx = 0;
        for (int div : divisors) {
            int cnt = 0;
            for (int x : nums) {
                cnt += x % div == 0;
            }
            if (mx < cnt) {
                mx = cnt;
                ans = div;
            } else if (mx == cnt) {
                ans = min(ans, div);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDivScore(nums []int, divisors []int) int {
	ans, mx := divisors[0], 0
	for _, div := range divisors {
		cnt := 0
		for _, x := range nums {
			if x%div == 0 {
				cnt++
			}
		}
		if mx < cnt {
			ans, mx = div, cnt
		} else if mx == cnt && ans > div {
			ans = div
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxDivScore(nums: number[], divisors: number[]): number {
    let ans: number = divisors[0];
    let mx: number = 0;
    for (const div of divisors) {
        const cnt = nums.reduce((a, b) => a + (b % div == 0 ? 1 : 0), 0);
        if (mx < cnt) {
            mx = cnt;
            ans = div;
        } else if (mx === cnt && ans > div) {
            ans = div;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_div_score(nums: Vec<i32>, divisors: Vec<i32>) -> i32 {
        let mut ans = divisors[0];
        let mut mx = 0;

        for &div in &divisors {
            let mut cnt = 0;

            for &n in &nums {
                if n % div == 0 {
                    cnt += 1;
                }
            }

            if cnt > mx || (cnt >= mx && div < ans) {
                mx = cnt;
                ans = div;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
