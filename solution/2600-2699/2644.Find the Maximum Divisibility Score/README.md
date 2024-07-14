---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2644.Find%20the%20Maximum%20Divisibility%20Score/README.md
rating: 1257
source: 第 341 场周赛 Q2
tags:
    - 数组
---

<!-- problem:start -->

# [2644. 找出可整除性得分最大的整数](https://leetcode.cn/problems/find-the-maximum-divisibility-score)

[English Version](/solution/2600-2699/2644.Find%20the%20Maximum%20Divisibility%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums</code> 和 <code>divisors</code> 。</p>

<p><code>divisors[i]</code> 的 <strong>可整除性得分</strong> 等于满足 <code>nums[j]</code> 能被 <code>divisors[i]</code> 整除的下标 <code>j</code> 的数量。</p>

<p>返回 <strong>可整除性得分</strong> 最大的整数 <code>divisors[i]</code> 。如果有多个整数具有最大得分，则返回数值最小的一个。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,9,15,50], divisors = [5,3,7,2]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>divisors[0]</code>&nbsp;的可整除性分数为 2 因为&nbsp;<code>nums[2]</code> 和&nbsp;<code>nums[3]</code>&nbsp;能被 5 整除。</p>

<p><code>divisors[1]</code> 的可整除性分数为 2 因为&nbsp;<code>nums[1]</code>&nbsp;和&nbsp;<code>nums[2]</code>&nbsp;能被 3 整除。</p>

<p><code>divisors[2]</code> 的可整除性分数为 0 因为&nbsp;<code>nums</code>&nbsp;中没有数字能被 7 整除。</p>

<p><code>divisors[3]</code> 的可整除性分数为 2 因为 <code>nums[0]</code> 和&nbsp;<code>nums[3]</code>&nbsp;能够被 2 整除。</p>

<p>因为&nbsp;<code>divisors[0]</code>&nbsp;、<code>divisor[1]</code> 和&nbsp;<code>divisors[3]</code>&nbsp;有相同的可整除性分数，我们返回更小的那个&nbsp;<code>divisors[3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,7,9,3,9], divisors = [5,2,3]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><code>divisors[0]</code> 的可整除性分数为 0&nbsp;因为&nbsp;<code>nums</code>&nbsp;中没有数字能被 5 整除。</p>

<p><code>divisors[1]</code> 的可整除性分数为 1 因为只有 <code>nums[0]</code>&nbsp;能被 2 整除。</p>

<p><code>divisors[2]</code> 的可整除性分数为 3 因为&nbsp;<code>nums[2]</code>&nbsp;，<code>nums[3]</code>&nbsp;和&nbsp;<code>nums[4]</code>&nbsp;能被 3 整除。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [20,14,21,10], divisors = [10,16,20]</span></p>

<p><strong>输出：</strong><span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p><code>divisors[0]</code> 的可整除性分数为 2 因为&nbsp;<code>nums[0]</code>&nbsp;和&nbsp;<code>nums[3]</code> 能被 10 整除。</p>

<p><code>divisors[1]</code> 的可整除性分数为 0 因为&nbsp;<code>nums</code>&nbsp;中没有数字能被 16&nbsp;整除。</p>

<p><code>divisors[2]</code> 的可整除性分数为 1 因为&nbsp;<code>nums[0]</code>&nbsp;能被 20&nbsp;整除。</p>

<p>因为&nbsp;<code>divisors[0]</code>&nbsp;的可整除性分数最大，我们返回&nbsp;<code>divisors[0]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, divisors.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], divisors[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举 $divisors$ 中的每个元素 $div$，计算 $nums$ 中有多少个元素能被 $div$ 整除，记为 $cnt$。

-   如果 $cnt$ 大于当前最大的可整除性得分 $mx$，则更新 $mx = cnt$，并且更新 $ans = div$。
-   如果 $cnt$ 等于 $mx$ 并且 $div$ 小于 $ans$，则更新 $ans = div$。

最后返回 $ans$ 即可。

时间复杂度 $(m \times n)$，其中 $m$ 和 $n$ 分别是 $nums$ 和 $divisors$ 的长度。空间复杂度 $O(1)$。

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
