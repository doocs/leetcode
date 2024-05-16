---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0283.Move%20Zeroes/README_EN.md
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [283. Move Zeroes](https://leetcode.com/problems/move-zeroes)

[中文文档](/solution/0200-0299/0283.Move%20Zeroes/README.md)

## Description

<p>Given an integer array <code>nums</code>, move all <code>0</code>&#39;s to the end of it while maintaining the relative order of the non-zero elements.</p>

<p><strong>Note</strong> that you must do this in-place without making a copy of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,3,12]
<strong>Output:</strong> [1,3,12,0,0]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you minimize the total number of operations done?

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers $i$ and $j$, where pointer $i$ points to the end of the sequence that has been processed, and pointer $j$ points to the head of the sequence to be processed. Initially, $i=-1$.

Next, we traverse $j \in [0,n)$, if $nums[j] \neq 0$, then we swap the next number pointed by pointer $i$ with $nums[j]$, and move $i$ forward. Continue to traverse until $j$ reaches the end of the array, all non-zero elements of the array are moved to the front of the array in the original order, and all zero elements are moved to the end of the array.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        i = -1
        for j, x in enumerate(nums):
            if x:
                i += 1
                nums[i], nums[j] = nums[j], nums[i]
```

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int i = -1, n = nums.length;
        for (int j = 0; j < n; ++j) {
            if (nums[j] != 0) {
                int t = nums[++i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int i = -1, n = nums.size();
        for (int j = 0; j < n; ++j) {
            if (nums[j]) {
                swap(nums[++i], nums[j]);
            }
        }
    }
};
```

```go
func moveZeroes(nums []int) {
	i := -1
	for j, x := range nums {
		if x != 0 {
			i++
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
}
```

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function moveZeroes(nums: number[]): void {
    const n = nums.length;
    let i = 0;
    for (let j = 0; j < n; j++) {
        if (nums[j]) {
            if (j > i) {
                [nums[i], nums[j]] = [nums[j], 0];
            }
            i++;
        }
    }
}
```

```rust
impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut i = 0;
        for j in 0..nums.len() {
            if nums[j] != 0 {
                if j > i {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i += 1;
            }
        }
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let i = -1;
    for (let j = 0; j < nums.length; ++j) {
        if (nums[j]) {
            const t = nums[++i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
};
```

```c
void moveZeroes(int* nums, int numsSize) {
    int i = 0;
    for (int j = 0; j < numsSize; j++) {
        if (nums[j] != 0) {
            if (j > i) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
            i++;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
