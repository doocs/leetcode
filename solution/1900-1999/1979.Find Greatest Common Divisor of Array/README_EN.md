---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1979.Find%20Greatest%20Common%20Divisor%20of%20Array/README_EN.md
rating: 1184
source: Weekly Contest 255 Q1
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [1979. Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array)

[中文文档](/solution/1900-1999/1979.Find%20Greatest%20Common%20Divisor%20of%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, return<strong> </strong><em>the <strong>greatest common divisor</strong> of the smallest number and largest number in </em><code>nums</code>.</p>

<p>The <strong>greatest common divisor</strong> of two numbers is the largest positive integer that evenly divides both numbers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5,6,9,10]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The smallest number in nums is 2.
The largest number in nums is 10.
The greatest common divisor of 2 and 10 is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,5,6,8,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The smallest number in nums is 3.
The largest number in nums is 8.
The greatest common divisor of 3 and 8 is 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The smallest number in nums is 3.
The largest number in nums is 3.
The greatest common divisor of 3 and 3 is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate according to the problem description. First, find the maximum and minimum values in the array $\textit{nums}$, then find the greatest common divisor of the maximum and minimum values.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

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
