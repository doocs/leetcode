---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3667.Sort%20Array%20By%20Absolute%20Value/README_EN.md
---

<!-- problem:start -->

# [3667. Sort Array By Absolute Value 🔒](https://leetcode.com/problems/sort-array-by-absolute-value)

[中文文档](/solution/3600-3699/3667.Sort%20Array%20By%20Absolute%20Value/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Rearrange elements of <code>nums</code> in <strong>non-decreasing</strong> order of their absolute value.</p>

<p>Return <strong>any</strong> rearranged array that satisfies this condition.</p>

<p><strong>Note</strong>: The absolute value of an integer x is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code></li>
	<li><code>-x</code> if <code>x &lt; 0</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,-1,-4,1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,1,3,-4,5]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The absolute values of elements in <code>nums</code> are 3, 1, 4, 1, 5 respectively.</li>
	<li>Rearranging them in increasing order, we get 1, 1, 3, 4, 5.</li>
	<li>This corresponds to <code>[-1, 1, 3, -4, 5]</code>. Another possible rearrangement is <code>[1, -1, 3, -4, 5].</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-100,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-100,100]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The absolute values of elements in <code>nums</code> are 100, 100 respectively.</li>
	<li>Rearranging them in increasing order, we get 100, 100.</li>
	<li>This corresponds to <code>[-100, 100]</code>. Another possible rearrangement is <code>[100, -100]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

We can use a custom sorting function to sort the array, where the sorting criterion is the absolute value of each element.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortByAbsoluteValue(self, nums: List[int]) -> List[int]:
        return sorted(nums, key=lambda x: abs(x))
```

#### Java

```java
class Solution {
    public int[] sortByAbsoluteValue(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .sorted(Comparator.comparingInt(Math::abs))
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortByAbsoluteValue(vector<int>& nums) {
        sort(nums.begin(), nums.end(), [](int a, int b) {
            return abs(a) < abs(b);
        });
        return nums;
    }
};
```

#### Go

```go
func sortByAbsoluteValue(nums []int) []int {
	slices.SortFunc(nums, func(a, b int) int {
		return abs(a) - abs(b)
	})
	return nums
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function sortByAbsoluteValue(nums: number[]): number[] {
    return nums.sort((a, b) => Math.abs(a) - Math.abs(b));
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_by_absolute_value(mut nums: Vec<i32>) -> Vec<i32> {
        nums.sort_by_key(|&x| x.abs());
        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
