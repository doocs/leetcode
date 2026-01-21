---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README.md
rating: 1714
source: 第 141 场双周赛 Q2
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [3315. 构造最小位运算数组 II](https://leetcode.cn/problems/construct-the-minimum-bitwise-array-ii)

[English Version](/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的<span data-keyword="prime">质数</span>数组&nbsp;<code>nums</code>&nbsp;。你的任务是返回一个长度为 <code>n</code>&nbsp;的数组 <code>ans</code>&nbsp;，对于每个下标 <code>i</code>&nbsp;，以下<strong>&nbsp;条件</strong>&nbsp;均成立：</p>

<ul>
	<li><code>ans[i] OR (ans[i] + 1) == nums[i]</code></li>
</ul>

<p>除此以外，你需要 <strong>最小化</strong>&nbsp;结果数组里每一个&nbsp;<code>ans[i]</code>&nbsp;。</p>

<p>如果没法找到符合 <strong>条件</strong>&nbsp;的&nbsp;<code>ans[i]</code>&nbsp;，那么&nbsp;<code>ans[i] = -1</code>&nbsp;。</p>

<p><strong>质数</strong>&nbsp;指的是一个大于 1 的自然数，且它只有 1 和自己两个因数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,3,5,7]</span></p>

<p><span class="example-io"><b>输出：</b>[-1,1,4,3]</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>&nbsp;，不存在&nbsp;<code>ans[0]</code>&nbsp;满足&nbsp;<code>ans[0] OR (ans[0] + 1) = 2</code>&nbsp;，所以&nbsp;<code>ans[0] = -1</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 1</code>&nbsp;，满足 <code>ans[1] OR (ans[1] + 1) = 3</code>&nbsp;的最小&nbsp;<code>ans[1]</code>&nbsp;为&nbsp;<code>1</code>&nbsp;，因为&nbsp;<code>1 OR (1 + 1) = 3</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 2</code>&nbsp;，满足 <code>ans[2] OR (ans[2] + 1) = 5</code>&nbsp;的最小 <code>ans[2]</code>&nbsp;为&nbsp;<code>4</code>&nbsp;，因为&nbsp;<code>4 OR (4 + 1) = 5</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 3</code>&nbsp;，满足&nbsp;<code>ans[3] OR (ans[3] + 1) = 7</code>&nbsp;的最小&nbsp;<code>ans[3]</code>&nbsp;为&nbsp;<code>3</code>&nbsp;，因为&nbsp;<code>3 OR (3 + 1) = 7</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [11,13,31]</span></p>

<p><span class="example-io"><b>输出：</b>[9,12,15]</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>&nbsp;，满足&nbsp;<code>ans[0] OR (ans[0] + 1) = 11</code> 的最小&nbsp;<code>ans[0]</code>&nbsp;为&nbsp;<code>9</code>&nbsp;，因为&nbsp;<code>9 OR (9 + 1) = 11</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 1</code>&nbsp;，满足&nbsp;<code>ans[1] OR (ans[1] + 1) = 13</code>&nbsp;的最小&nbsp;<code>ans[1]</code>&nbsp;为&nbsp;<code>12</code>&nbsp;，因为&nbsp;<code>12 OR (12 + 1) = 13</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 2</code>&nbsp;，满足&nbsp;<code>ans[2] OR (ans[2] + 1) = 31</code>&nbsp;的最小&nbsp;<code>ans[2]</code>&nbsp;为&nbsp;<code>15</code>&nbsp;，因为&nbsp;<code>15 OR (15 + 1) = 31</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums[i]</code>&nbsp;是一个质数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

对于一个整数 $a$，满足 $a \lor (a + 1)$ 的结果一定为奇数，因此，如果 $\text{nums[i]}$ 是偶数，那么 $\text{ans}[i]$ 一定不存在，直接返回 $-1$。本题中 $\textit{nums}[i]$ 是质数，判断是否是偶数，只需要判断是否等于 $2$ 即可。

如果 $\text{nums[i]}$ 是奇数，假设 $\text{nums[i]} = \text{0b1101101}$，由于 $a \lor (a + 1) = \text{nums[i]}$，等价于将 $a$ 的最后一个为 $0$ 的二进制位变为 $1$。那么求解 $a$，就等价于将 $\text{nums[i]}$ 的最后一个 $0$ 的下一位 $1$ 变为 $0$。我们只需要从低位（下标为 $1$）开始遍历，找到第一个为 $0$ 的二进制位，如果是第 $i$ 位，那么我们就将 $\text{nums[i]}$ 的第 $i - 1$ 位变为 $1$，即 $\text{ans}[i] = \text{nums[i]} \oplus 2^{i - 1}$。

遍历所有的 $\text{nums[i]}$，即可得到答案。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $\text{nums}$ 的长度和数组中的最大值。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []
        for x in nums:
            if x == 2:
                ans.append(-1)
            else:
                for i in range(1, 32):
                    if x >> i & 1 ^ 1:
                        ans.append(x ^ 1 << (i - 1))
                        break
        return ans
```

#### Java

```java
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums.get(i);
            if (x == 2) {
                ans[i] = -1;
            } else {
                for (int j = 1; j < 32; ++j) {
                    if ((x >> j & 1) == 0) {
                        ans[i] = x ^ 1 << (j - 1);
                        break;
                    }
                }
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
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans;
        for (int x : nums) {
            if (x == 2) {
                ans.push_back(-1);
            } else {
                for (int i = 1; i < 32; ++i) {
                    if (x >> i & 1 ^ 1) {
                        ans.push_back(x ^ 1 << (i - 1));
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minBitwiseArray(nums []int) (ans []int) {
	for _, x := range nums {
		if x == 2 {
			ans = append(ans, -1)
		} else {
			for i := 1; i < 32; i++ {
				if x>>i&1 == 0 {
					ans = append(ans, x^1<<(i-1))
					break
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function minBitwiseArray(nums: number[]): number[] {
    const ans: number[] = [];
    for (const x of nums) {
        if (x === 2) {
            ans.push(-1);
        } else {
            for (let i = 1; i < 32; ++i) {
                if (((x >> i) & 1) ^ 1) {
                    ans.push(x ^ (1 << (i - 1)));
                    break;
                }
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_bitwise_array(nums: Vec<i32>) -> Vec<i32> {
        let mut ans: Vec<i32> = Vec::new();
        for x in nums {
            if x == 2 {
                ans.push(-1);
            } else {
                for i in 1..32 {
                    if (((x >> i) & 1) ^ 1) != 0 {
                        ans.push(x ^ (1 << (i - 1)));
                        break;
                    }
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
