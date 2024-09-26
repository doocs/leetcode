---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README_EN.md
rating: 1222
source: Weekly Contest 328 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [2535. Difference Between Element Sum and Digit Sum of an Array](https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array)

[中文文档](/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer array <code>nums</code>.</p>

<ul>
	<li>The <strong>element sum</strong> is the sum of all the elements in <code>nums</code>.</li>
	<li>The <strong>digit sum</strong> is the sum of all the digits (not necessarily distinct) that appear in <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>absolute</strong> difference between the <strong>element sum</strong> and <strong>digit sum</strong> of </em><code>nums</code>.</p>

<p><strong>Note</strong> that the absolute difference between two integers <code>x</code> and <code>y</code> is defined as <code>|x - y|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,15,6,3]
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The element sum of nums is 1 + 15 + 6 + 3 = 25.
The digit sum of nums is 1 + 1 + 5 + 6 + 3 = 16.
The absolute difference between the element sum and digit sum is |25 - 16| = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The element sum of nums is 1 + 2 + 3 + 4 = 10.
The digit sum of nums is 1 + 2 + 3 + 4 = 10.
The absolute difference between the element sum and digit sum is |10 - 10| = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

Solution 1: Simulation
We traverse the array $\textit{nums}$, calculate the sum of the elements $x$ and the sum of the digits $y$, and finally return $|x - y|$. Since $x$ is always greater than or equal to $y$, we can directly return $x - y$.

The time complexity is $O(n \times \log_{10} M)$, where $n$ and $M$ are the length of the array $\textit{nums}$ and the maximum value of the elements in the array, respectively. The space complexity is $O(1)$.

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
