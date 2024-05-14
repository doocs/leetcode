# [75. Sort Colors](https://leetcode.com/problems/sort-colors)

[中文文档](/solution/0000-0099/0075.Sort%20Colors/README.md)

<!-- tags:Array,Two Pointers,Sorting -->

<!-- difficulty:Medium -->

## Description

<p>Given an array <code>nums</code> with <code>n</code> objects colored red, white, or blue, sort them <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> </strong>so that objects of the same color are adjacent, with the colors in the order red, white, and blue.</p>

<p>We will use the integers <code>0</code>, <code>1</code>, and <code>2</code> to represent the color red, white, and blue, respectively.</p>

<p>You must solve this problem without using the library&#39;s sort function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,2,1,1,0]
<strong>Output:</strong> [0,0,1,1,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,1]
<strong>Output:</strong> [0,1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> is either <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong>&nbsp;Could you come up with a one-pass algorithm using only&nbsp;constant extra space?</p>

## Solutions

### Solution 1: Three Pointers

We define three pointers $i$, $j$, and $k$. Pointer $i$ is used to point to the rightmost boundary of the elements with a value of $0$ in the array, and pointer $j$ is used to point to the leftmost boundary of the elements with a value of $2$ in the array. Initially, $i=-1$, $j=n$. Pointer $k$ is used to point to the current element being traversed, initially $k=0$.

When $k < j$, we perform the following operations:

-   If $nums[k] = 0$, then swap it with $nums[i+1]$, then increment both $i$ and $k$ by $1$;
-   If $nums[k] = 2$, then swap it with $nums[j-1]$, then decrement $j$ by $1$;
-   If $nums[k] = 1$, then increment $k$ by $1$.

After the traversal, the elements in the array are divided into three parts: $[0,i]$, $[i+1,j-1]$ and $[j,n-1]$.

The time complexity is $O(n)$, where $n$ is the length of the array. Only one traversal of the array is needed. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        i, j, k = -1, len(nums), 0
        while k < j:
            if nums[k] == 0:
                i += 1
                nums[i], nums[k] = nums[k], nums[i]
                k += 1
            elif nums[k] == 2:
                j -= 1
                nums[j], nums[k] = nums[k], nums[j]
            else:
                k += 1
```

```java
class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length, k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums, ++i, k++);
            } else if (nums[k] == 2) {
                swap(nums, --j, k);
            } else {
                ++k;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

```cpp
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int i = -1, j = nums.size(), k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums[++i], nums[k++]);
            } else if (nums[k] == 2) {
                swap(nums[--j], nums[k]);
            } else {
                ++k;
            }
        }
    }
};
```

```go
func sortColors(nums []int) {
	i, j, k := -1, len(nums), 0
	for k < j {
		if nums[k] == 0 {
			i++
			nums[i], nums[k] = nums[k], nums[i]
			k++
		} else if nums[k] == 2 {
			j--
			nums[j], nums[k] = nums[k], nums[j]
		} else {
			k++
		}
	}
}
```

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let i = -1;
    let j = nums.length;
    let k = 0;
    while (k < j) {
        if (nums[k] === 0) {
            ++i;
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        } else if (nums[k] === 2) {
            --j;
            [nums[j], nums[k]] = [nums[k], nums[j]];
        } else {
            ++k;
        }
    }
}
```

```rust
impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut i = -1;
        let mut j = nums.len();
        let mut k = 0;
        while k < j {
            if nums[k] == 0 {
                i += 1;
                nums.swap(i as usize, k as usize);
                k += 1;
            } else if nums[k] == 2 {
                j -= 1;
                nums.swap(j, k);
            } else {
                k += 1;
            }
        }
    }
}
```

```cs
public class Solution {
    public void SortColors(int[] nums) {
        int i = -1, j = nums.Length, k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums, ++i, k++);
            } else if (nums[k] == 2) {
                swap(nums, --j, k);
            } else {
                ++k;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

<!-- tabs:end -->

<!-- end -->
