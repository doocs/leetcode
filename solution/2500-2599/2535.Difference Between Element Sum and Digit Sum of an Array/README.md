---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README.md
rating: 1222
source: 第 328 场周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [2535. 数组元素和与数字和的绝对差](https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array)

[English Version](/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组 <code>nums</code> 。</p>

<ul>
	<li><strong>元素和</strong> 是 <code>nums</code> 中的所有元素相加求和。</li>
	<li><strong>数字和</strong> 是&nbsp;<code>nums</code> 中每一个元素的每一数位（重复数位需多次求和）相加求和。</li>
</ul>

<p>返回 <strong>元素和</strong> 与 <strong>数字和</strong> 的绝对差。</p>

<p><strong>注意：</strong>两个整数 <code>x</code> 和 <code>y</code> 的绝对差定义为 <code>|x - y|</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,15,6,3]
<strong>输出：</strong>9
<strong>解释：</strong>
nums 的元素和是 1 + 15 + 6 + 3 = 25 。
nums 的数字和是 1 + 1 + 5 + 6 + 3 = 16 。
元素和与数字和的绝对差是 |25 - 16| = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>0
<strong>解释：</strong>
nums 的元素和是 1 + 2 + 3 + 4 = 10 。
nums 的数字和是 1 + 2 + 3 + 4 = 10 。
元素和与数字和的绝对差是 |10 - 10| = 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历数组 $\textit{nums}$，计算元素和 $x$ 和数字和 $y$，最后返回 $|x - y|$ 即可。由于 $x$ 一定大于等于 $y$，所以我们也可以直接返回 $x - y$。

时间复杂度 $O(n \times \log_{10} M)$，其中 $n$ 和 $M$ 分别是数组 $\textit{nums}$ 的长度和数组中元素的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def differenceOfSum(self, nums: List[int]) -> int:
        x = y = 0
        for v in nums:
            x += v
            while v:
                y += v % 10
                v //= 10
        return x - y
```

#### Java

```java
class Solution {
    public int differenceOfSum(int[] nums) {
        int x = 0, y = 0;
        for (int v : nums) {
            x += v;
            for (; v > 0; v /= 10) {
                y += v % 10;
            }
        }
        return x - y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int differenceOfSum(vector<int>& nums) {
        int x = 0, y = 0;
        for (int v : nums) {
            x += v;
            for (; v; v /= 10) {
                y += v % 10;
            }
        }
        return x - y;
    }
};
```

#### Go

```go
func differenceOfSum(nums []int) int {
	var x, y int
	for _, v := range nums {
		x += v
		for ; v > 0; v /= 10 {
			y += v % 10
		}
	}
	return x - y
}
```

#### TypeScript

```ts
function differenceOfSum(nums: number[]): number {
    let [x, y] = [0, 0];
    for (let v of nums) {
        x += v;
        for (; v; v = Math.floor(v / 10)) {
            y += v % 10;
        }
    }
    return x - y;
}
```

#### Rust

```rust
impl Solution {
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let mut x = 0;
        let mut y = 0;

        for &v in &nums {
            x += v;
            let mut num = v;
            while num > 0 {
                y += num % 10;
                num /= 10;
            }
        }

        x - y
    }
}
```

#### C

```c
int differenceOfSum(int* nums, int numsSize) {
    int x = 0, y = 0;
    for (int i = 0; i < numsSize; i++) {
        int v = nums[i];
        x += v;
        while (v > 0) {
            y += v % 10;
            v /= 10;
        }
    }
    return x - y;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
