---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1979.Find%20Greatest%20Common%20Divisor%20of%20Array/README.md
rating: 1184
source: 第 255 场周赛 Q1
tags:
    - 数组
    - 数学
    - 数论
---

<!-- problem:start -->

# [1979. 找出数组的最大公约数](https://leetcode.cn/problems/find-greatest-common-divisor-of-array)

[English Version](/solution/1900-1999/1979.Find%20Greatest%20Common%20Divisor%20of%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，返回数组中最大数和最小数的 <strong>最大公约数</strong> 。</p>

<p>两个数的&nbsp;<strong>最大公约数</strong> 是能够被两个数整除的最大正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,5,6,9,10]
<strong>输出：</strong>2
<strong>解释：</strong>
nums 中最小的数是 2
nums 中最大的数是 10
2 和 10 的最大公约数是 2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [7,5,6,8,3]
<strong>输出：</strong>1
<strong>解释：</strong>
nums 中最小的数是 3
nums 中最大的数是 8
3 和 8 的最大公约数是 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,3]
<strong>输出：</strong>3
<strong>解释：</strong>
nums 中最小的数是 3
nums 中最大的数是 3
3 和 3 的最大公约数是 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们根据题意模拟即可，即先找出数组 $\textit{nums}$ 中的最大值和最小值，然后求最大值和最小值的最大公约数。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findGCD(self, nums: List[int]) -> int:
        return gcd(max(nums), min(nums))
```

#### Java

```java
class Solution {
    public int findGCD(int[] nums) {
        int a = 1, b = 1000;
        for (int x : nums) {
            a = Math.max(a, x);
            b = Math.min(b, x);
        }
        return gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findGCD(vector<int>& nums) {
        auto [min, max] = ranges::minmax_element(nums);
        return gcd(*min, *max);
    }
};
```

#### Go

```go
func findGCD(nums []int) int {
	a, b := slices.Max(nums), slices.Min(nums)
	return gcd(a, b)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function findGCD(nums: number[]): number {
    const min = Math.min(...nums);
    const max = Math.max(...nums);
    return gcd(min, max);
}

function gcd(a: number, b: number): number {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_gcd(nums: Vec<i32>) -> i32 {
        let min_val = *nums.iter().min().unwrap();
        let max_val = *nums.iter().max().unwrap();
        gcd(min_val, max_val)
    }
}

fn gcd(mut a: i32, mut b: i32) -> i32 {
    while b != 0 {
        let temp = b;
        b = a % b;
        a = temp;
    }
    a
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
