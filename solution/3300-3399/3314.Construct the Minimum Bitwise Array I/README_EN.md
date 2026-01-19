---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3314.Construct%20the%20Minimum%20Bitwise%20Array%20I/README_EN.md
rating: 1378
source: Biweekly Contest 141 Q1
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [3314. Construct the Minimum Bitwise Array I](https://leetcode.com/problems/construct-the-minimum-bitwise-array-i)

[中文文档](/solution/3300-3399/3314.Construct%20the%20Minimum%20Bitwise%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of <code>n</code> <span data-keyword="prime-number">prime</span> integers.</p>

<p>You need to construct an array <code>ans</code> of length <code>n</code>, such that, for each index <code>i</code>, the bitwise <code>OR</code> of <code>ans[i]</code> and <code>ans[i] + 1</code> is equal to <code>nums[i]</code>, i.e. <code>ans[i] OR (ans[i] + 1) == nums[i]</code>.</p>

<p>Additionally, you must <strong>minimize</strong> each value of <code>ans[i]</code> in the resulting array.</p>

<p>If it is <em>not possible</em> to find such a value for <code>ans[i]</code> that satisfies the <strong>condition</strong>, then set <code>ans[i] = -1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,1,4,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, as there is no value for <code>ans[0]</code> that satisfies <code>ans[0] OR (ans[0] + 1) = 2</code>, so <code>ans[0] = -1</code>.</li>
	<li>For <code>i = 1</code>, the smallest <code>ans[1]</code> that satisfies <code>ans[1] OR (ans[1] + 1) = 3</code> is <code>1</code>, because <code>1 OR (1 + 1) = 3</code>.</li>
	<li>For <code>i = 2</code>, the smallest <code>ans[2]</code> that satisfies <code>ans[2] OR (ans[2] + 1) = 5</code> is <code>4</code>, because <code>4 OR (4 + 1) = 5</code>.</li>
	<li>For <code>i = 3</code>, the smallest <code>ans[3]</code> that satisfies <code>ans[3] OR (ans[3] + 1) = 7</code> is <code>3</code>, because <code>3 OR (3 + 1) = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [11,13,31]</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,12,15]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, the smallest <code>ans[0]</code> that satisfies <code>ans[0] OR (ans[0] + 1) = 11</code> is <code>9</code>, because <code>9 OR (9 + 1) = 11</code>.</li>
	<li>For <code>i = 1</code>, the smallest <code>ans[1]</code> that satisfies <code>ans[1] OR (ans[1] + 1) = 13</code> is <code>12</code>, because <code>12 OR (12 + 1) = 13</code>.</li>
	<li>For <code>i = 2</code>, the smallest <code>ans[2]</code> that satisfies <code>ans[2] OR (ans[2] + 1) = 31</code> is <code>15</code>, because <code>15 OR (15 + 1) = 31</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i]</code> is a prime number.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

For an integer $a$, the result of $a \lor (a + 1)$ is always odd. Therefore, if $\text{nums[i]}$ is even, then $\text{ans}[i]$ does not exist, and we directly return $-1$. In this problem, $\textit{nums}[i]$ is a prime number, so to check if it is even, we only need to check if it equals $2$.

If $\text{nums[i]}$ is odd, suppose $\text{nums[i]} = \text{0b1101101}$. Since $a \lor (a + 1) = \text{nums[i]}$, this is equivalent to changing the last $0$ bit of $a$ to $1$. To solve for $a$, we need to change the bit after the last $0$ in $\text{nums[i]}$ to $0$. We start traversing from the least significant bit (index $1$) and find the first $0$ bit. If it is at position $i$, we change the $(i - 1)$-th bit of $\text{nums[i]}$ to $1$, i.e., $\text{ans}[i] = \text{nums[i]} \oplus 2^{i - 1}$.

By traversing all elements in $\text{nums}$, we can obtain the answer.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array $\text{nums}$ and the maximum value in the array, respectively. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

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
        let mut ans = Vec::with_capacity(nums.len());
        for x in nums {
            if x == 2 {
                ans.push(-1);
            } else {
                for i in 1..32 {
                    if (((x >> i) & 1) ^ 1) == 1 {
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
