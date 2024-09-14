---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2176.Count%20Equal%20and%20Divisible%20Pairs%20in%20an%20Array/README.md
rating: 1215
source: 第 72 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [2176. 统计数组中相等且可以被整除的数对](https://leetcode.cn/problems/count-equal-and-divisible-pairs-in-an-array)

[English Version](/solution/2100-2199/2176.Count%20Equal%20and%20Divisible%20Pairs%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回满足&nbsp;<code>0 &lt;= i &lt; j &lt; n</code>&nbsp;，<code>nums[i] == nums[j]</code> 且&nbsp;<code>(i * j)</code>&nbsp;能被&nbsp;<code>k</code>&nbsp;整除的数对&nbsp;<code>(i, j)</code>&nbsp;的&nbsp;<strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,1,2,2,2,1,3], k = 2
<b>输出：</b>4
<strong>解释：</strong>
总共有 4 对数符合所有要求：
- nums[0] == nums[6] 且 0 * 6 == 0 ，能被 2 整除。
- nums[2] == nums[3] 且 2 * 3 == 6 ，能被 2 整除。
- nums[2] == nums[4] 且 2 * 4 == 8 ，能被 2 整除。
- nums[3] == nums[4] 且 3 * 4 == 12 ，能被 2 整除。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4], k = 1
<b>输出：</b>0
<b>解释：</b>由于数组中没有重复数值，所以没有数对 (i,j) 符合所有要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们先在 $[0, n)$ 的范围内枚举下标 $j$，然后在 $[0, j)$ 的范围内枚举下标 $i$，统计满足 $\textit{nums}[i] = \textit{nums}[j]$ 且 $(i \times j) \bmod k = 0$ 的数对个数。

时间复杂度 $O(n^2)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, nums: List[int], k: int) -> int:
        ans = 0
        for j, y in enumerate(nums):
            for i, x in enumerate(nums[:j]):
                ans += int(x == y and i * j % k == 0)
        return ans
```

#### Java

```java
class Solution {
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        for (int j = 1; j < nums.length; ++j) {
            for (int i = 0; i < j; ++i) {
                ans += nums[i] == nums[j] && (i * j % k) == 0 ? 1 : 0;
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
    int countPairs(vector<int>& nums, int k) {
        int ans = 0;
        for (int j = 1; j < nums.size(); ++j) {
            for (int i = 0; i < j; ++i) {
                ans += nums[i] == nums[j] && (i * j % k) == 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(nums []int, k int) (ans int) {
	for j, y := range nums {
		for i, x := range nums[:j] {
			if x == y && (i*j%k) == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countPairs(nums: number[], k: number): number {
    let ans = 0;
    for (let j = 1; j < nums.length; ++j) {
        for (let i = 0; i < j; ++i) {
            if (nums[i] === nums[j] && (i * j) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        for j in 1..nums.len() {
            for (i, &x) in nums[..j].iter().enumerate() {
                if x == nums[j] && (i * j) as i32 % k == 0 {
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

#### C

```c
int countPairs(int* nums, int numsSize, int k) {
    int ans = 0;
    for (int j = 1; j < numsSize; ++j) {
        for (int i = 0; i < j; ++i) {
            ans += (nums[i] == nums[j] && (i * j % k) == 0);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
