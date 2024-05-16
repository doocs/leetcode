---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0977.Squares%20of%20a%20Sorted%20Array/README_EN.md
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array)

[中文文档](/solution/0900-0999/0977.Squares%20of%20a%20Sorted%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing</strong> order, return <em>an array of <strong>the squares of each number</strong> sorted in non-decreasing order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-4,-1,0,3,10]
<strong>Output:</strong> [0,1,9,16,100]
<strong>Explanation:</strong> After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-7,-3,2,3,11]
<strong>Output:</strong> [4,9,9,49,121]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><span>1 &lt;= nums.length &lt;= </span>10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Squaring each element and sorting the new array is very trivial, could you find an <code>O(n)</code> solution using a different approach?

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

Since the array $nums$ is already sorted in non-decreasing order, the square values of the negative numbers in the array are decreasing, and the square values of the positive numbers are increasing. We can use two pointers, each pointing to the ends of the array. Each time we compare the square values of the elements pointed to by the two pointers, we put the larger square value at the end of the result array.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        ans = []
        i, j = 0, len(nums) - 1
        while i <= j:
            a = nums[i] * nums[i]
            b = nums[j] * nums[j]
            if a > b:
                ans.append(a)
                i += 1
            else:
                ans.append(b)
                j -= 1
        return ans[::-1]
```

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j; --k) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a > b) {
                ans[k] = a;
                ++i;
            } else {
                ans[k] = b;
                --j;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0, j = n - 1, k = n - 1; i <= j; --k) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a > b) {
                ans[k] = a;
                ++i;
            } else {
                ans[k] = b;
                --j;
            }
        }
        return ans;
    }
};
```

```go
func sortedSquares(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i, j, k := 0, n-1, n-1; i <= j; k-- {
		a := nums[i] * nums[i]
		b := nums[j] * nums[j]
		if a > b {
			ans[k] = a
			i++
		} else {
			ans[k] = b
			j--
		}
	}
	return ans
}
```

```rust
impl Solution {
    pub fn sorted_squares(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![0; n];
        let (mut i, mut j) = (0, n - 1);
        for k in (0..n).rev() {
            let a = nums[i] * nums[i];
            let b = nums[j] * nums[j];
            if a > b {
                ans[k] = a;
                i += 1;
            } else {
                ans[k] = b;
                j -= 1;
            }
        }
        ans
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortedSquares = function (nums) {
    const n = nums.length;
    const ans = Array(n).fill(0);
    for (let i = 0, j = n - 1, k = n - 1; i <= j; --k) {
        const [a, b] = [nums[i] * nums[i], nums[j] * nums[j]];
        if (a > b) {
            ans[k] = a;
            ++i;
        } else {
            ans[k] = b;
            --j;
        }
    }
    return ans;
};
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function sortedSquares($nums) {
        $n = count($nums);
        $ans = array_fill(0, $n, 0);
        for ($i = 0, $j = $n - 1, $k = $n - 1; $i <= $j; --$k) {
            $a = $nums[$i] * $nums[$i];
            $b = $nums[$j] * $nums[$j];
            if ($a > $b) {
                $ans[$k] = $a;
                ++$i;
            } else {
                $ans[$k] = $b;
                --$j;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
