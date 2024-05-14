# [2733. Neither Minimum nor Maximum](https://leetcode.com/problems/neither-minimum-nor-maximum)

[中文文档](/solution/2700-2799/2733.Neither%20Minimum%20nor%20Maximum/README.md)

<!-- tags:Array,Sorting -->

<!-- difficulty:Easy -->

## Description

<p>Given an integer array <code>nums</code> containing <strong>distinct</strong> <strong>positive</strong> integers, find and return <strong>any</strong> number from the array that is neither the <strong>minimum</strong> nor the <strong>maximum</strong> value in the array, or <strong><code>-1</code></strong> if there is no such number.</p>

<p>Return <em>the selected integer.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, the minimum value is 1 and the maximum value is 4. Therefore, either 2 or 3 can be valid answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Since there is no number in nums that is neither the maximum nor the minimum, we cannot select a number that satisfies the given condition. Therefore, there is no answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Since 2 is neither the maximum nor the minimum value in nums, it is the only valid answer. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li>All values in <code>nums</code> are distinct</li>
</ul>

## Solutions

### Solution 1: Simulation

First, we find the minimum and maximum values in the array, denoted as $mi$ and $mx$ respectively. Then, we traverse the array and find the first number that is not equal to $mi$ and not equal to $mx$, and return it.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findNonMinOrMax(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        return next((x for x in nums if x != mi and x != mx), -1)
```

```java
class Solution {
    public int findNonMinOrMax(int[] nums) {
        int mi = 100, mx = 0;
        for (int x : nums) {
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        for (int x : nums) {
            if (x != mi && x != mx) {
                return x;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int findNonMinOrMax(vector<int>& nums) {
        auto [mi, mx] = minmax_element(nums.begin(), nums.end());
        for (int x : nums) {
            if (x != *mi && x != *mx) {
                return x;
            }
        }
        return -1;
    }
};
```

```go
func findNonMinOrMax(nums []int) int {
	mi, mx := slices.Min(nums), slices.Max(nums)
	for _, x := range nums {
		if x != mi && x != mx {
			return x
		}
	}
	return -1
}
```

```rust
impl Solution {
    pub fn find_non_min_or_max(nums: Vec<i32>) -> i32 {
        let mut mi = 100;
        let mut mx = 0;

        for &ele in nums.iter() {
            if ele < mi {
                mi = ele;
            }
            if ele > mx {
                mx = ele;
            }
        }

        for &ele in nums.iter() {
            if ele != mi && ele != mx {
                return ele;
            }
        }

        -1
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def findNonMinOrMax(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        for x in nums:
            if x != mi and x != mx:
                return x
        return -1
```

<!-- tabs:end -->

<!-- end -->
