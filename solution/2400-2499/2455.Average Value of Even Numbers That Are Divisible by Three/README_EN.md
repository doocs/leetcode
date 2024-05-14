---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README_EN.md
rating: 1151
tags:
    - Array
    - Math
---

# [2455. Average Value of Even Numbers That Are Divisible by Three](https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three)

[中文文档](/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README.md)

## Description

<p>Given an integer array <code>nums</code> of <strong>positive</strong> integers, return <em>the average value of all even integers that are divisible by</em> <code>3</code><i>.</i></p>

<p>Note that the <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided by <code>n</code> and <strong>rounded down</strong> to the nearest integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,6,10,12,15]
<strong>Output:</strong> 9
<strong>Explanation:</strong> 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,7,10]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no single number that satisfies the requirement, so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Simulation

We notice that an even number divisible by $3$ must be a multiple of $6$. Therefore, we only need to traverse the array, count the sum and the number of all multiples of $6$, and then calculate the average.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def averageValue(self, nums: List[int]) -> int:
        s = n = 0
        for x in nums:
            if x % 6 == 0:
                s += x
                n += 1
        return 0 if n == 0 else s // n
```

```java
class Solution {
    public int averageValue(int[] nums) {
        int s = 0, n = 0;
        for (int x : nums) {
            if (x % 6 == 0) {
                s += x;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
}
```

```cpp
class Solution {
public:
    int averageValue(vector<int>& nums) {
        int s = 0, n = 0;
        for (int x : nums) {
            if (x % 6 == 0) {
                s += x;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
};
```

```go
func averageValue(nums []int) int {
	var s, n int
	for _, x := range nums {
		if x%6 == 0 {
			s += x
			n++
		}
	}
	if n == 0 {
		return 0
	}
	return s / n
}
```

```ts
function averageValue(nums: number[]): number {
    let s = 0;
    let n = 0;
    for (const x of nums) {
        if (x % 6 === 0) {
            s += x;
            ++n;
        }
    }
    return n === 0 ? 0 : ~~(s / n);
}
```

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut s = 0;
        let mut n = 0;
        for x in nums.iter() {
            if x % 6 == 0 {
                s += x;
                n += 1;
            }
        }
        if n == 0 {
            return 0;
        }
        s / n
    }
}
```

```c
int averageValue(int* nums, int numsSize) {
    int s = 0, n = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] % 6 == 0) {
            s += nums[i];
            ++n;
        }
    }
    return n == 0 ? 0 : s / n;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let filtered_nums: Vec<i32> = nums
            .iter()
            .cloned()
            .filter(|&n| n % 6 == 0)
            .collect();

        if filtered_nums.is_empty() {
            return 0;
        }

        filtered_nums.iter().sum::<i32>() / (filtered_nums.len() as i32)
    }
}
```

<!-- tabs:end -->

<!-- end -->
