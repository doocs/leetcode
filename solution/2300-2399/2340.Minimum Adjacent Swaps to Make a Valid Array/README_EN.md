---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2340.Minimum%20Adjacent%20Swaps%20to%20Make%20a%20Valid%20Array/README_EN.md
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [2340. Minimum Adjacent Swaps to Make a Valid Array 🔒](https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array)

[中文文档](/solution/2300-2399/2340.Minimum%20Adjacent%20Swaps%20to%20Make%20a%20Valid%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p><strong>Swaps</strong> of <strong>adjacent</strong> elements are able to be performed on <code>nums</code>.</p>

<p>A <strong>valid</strong> array meets the following conditions:</p>

<ul>
	<li>The largest element (any of the largest elements if there are multiple) is at the rightmost position in the array.</li>
	<li>The smallest element (any of the smallest elements if there are multiple) is at the leftmost position in the array.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> swaps required to make </em><code>nums</code><em> a valid array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,5,3,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Perform the following swaps:
- Swap 1: Swap the 3<sup>rd</sup> and 4<sup>th</sup> elements, nums is then [3,4,5,<u><strong>3</strong></u>,<u><strong>5</strong></u>,1].
- Swap 2: Swap the 4<sup>th</sup> and 5<sup>th</sup> elements, nums is then [3,4,5,3,<u><strong>1</strong></u>,<u><strong>5</strong></u>].
- Swap 3: Swap the 3<sup>rd</sup> and 4<sup>th</sup> elements, nums is then [3,4,5,<u><strong>1</strong></u>,<u><strong>3</strong></u>,5].
- Swap 4: Swap the 2<sup>nd</sup> and 3<sup>rd</sup> elements, nums is then [3,4,<u><strong>1</strong></u>,<u><strong>5</strong></u>,3,5].
- Swap 5: Swap the 1<sup>st</sup> and 2<sup>nd</sup> elements, nums is then [3,<u><strong>1</strong></u>,<u><strong>4</strong></u>,5,3,5].
- Swap 6: Swap the 0<sup>th</sup> and 1<sup>st</sup> elements, nums is then [<u><strong>1</strong></u>,<u><strong>3</strong></u>,4,5,3,5].
It can be shown that 6 swaps is the minimum swaps required to make a valid array.
</pre>

<strong class="example">Example 2:</strong>

<pre>
<strong>Input:</strong> nums = [9]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array is already valid, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Maintain Index of Extremes + Case Analysis

We can use indices $i$ and $j$ to record the index of the first minimum value and the last maximum value in the array $\textit{nums}$, respectively. Traverse the array $\textit{nums}$ to update the values of $i$ and $j$.

Next, we need to consider the number of swaps.

- If $i = j$, it means the array $\textit{nums}$ is already a valid array, and no swaps are needed. Return $0$.
- If $i < j$, it means the minimum value in the array $\textit{nums}$ is to the left of the maximum value. The number of swaps needed is $i + n - 1 - j$, where $n$ is the length of the array $\textit{nums}$.
- If $i > j$, it means the minimum value in the array $\textit{nums}$ is to the right of the maximum value. The number of swaps needed is $i + n - 1 - j - 1$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSwaps(self, nums: List[int]) -> int:
        i = j = 0
        for k, v in enumerate(nums):
            if v < nums[i] or (v == nums[i] and k < i):
                i = k
            if v >= nums[j] or (v == nums[j] and k > j):
                j = k
        return 0 if i == j else i + len(nums) - 1 - j - (i > j)
```

#### Java

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
                i = k;
            }
            if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i + n - 1 - j - (i > j ? 1 : 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int n = nums.size();
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
                i = k;
            }
            if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i + n - 1 - j - (i > j);
    }
};
```

#### Go

```go
func minimumSwaps(nums []int) int {
	var i, j int
	for k, v := range nums {
		if v < nums[i] || (v == nums[i] && k < i) {
			i = k
		}
		if v > nums[j] || (v == nums[j] && k > j) {
			j = k
		}
	}
	if i == j {
		return 0
	}
	if i < j {
		return i + len(nums) - 1 - j
	}
	return i + len(nums) - 2 - j
}
```

#### TypeScript

```ts
function minimumSwaps(nums: number[]): number {
    let i = 0;
    let j = 0;
    const n = nums.length;
    for (let k = 0; k < n; ++k) {
        if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
            i = k;
        }
        if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
            j = k;
        }
    }
    return i == j ? 0 : i + n - 1 - j - (i > j ? 1 : 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
