---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README_EN.md
rating: 1346
source: Biweekly Contest 183 Q1
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [3936. Minimum Swaps to Move Zeros to End](https://leetcode.com/problems/minimum-swaps-to-move-zeros-to-end)

[中文文档](/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>In one operation, you can choose any two <strong>distinct</strong> indices <code>i</code> and <code>j</code> and swap <code>nums[i]</code> and <code>nums[j]</code>.</p>

<p>Return an integer denoting the <strong>minimum</strong> number of operations required to move all 0s to the end of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,0,3,12]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We perform the following swap operations:</p>

<ul>
	<li>Swap <code>nums[0]</code> and <code>nums[3]</code>, giving <code>nums = [3, 1, 0, 0, 12]</code>.</li>
	<li>Swap <code>nums[2]</code> and <code>nums[4]</code>, giving <code>nums = [3, 1, 12, 0, 0]</code>.</li>
</ul>

<p>Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>We perform the following swap operations:</p>

<ul>
	<li>Swap <code>nums[0]</code> and <code>nums[3]</code>, giving <code>nums = [2, 1, 0, 0]</code>.</li>
</ul>

<p>Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array already satisfies the condition. Therefore, no swap operations are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers $i$ and $j$ pointing to the beginning and end of the array respectively. Each time, we move $i$ to the right until we find a 0, and move $j$ to the left until we find a non-zero number. If $i < j$, we swap the two elements and increment the answer by 1. We repeat this process until $i \geq j$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSwaps(self, nums: list[int]) -> int:
        ans = 0
        n = len(nums)
        i, j = 0, n - 1
        while i < j:
            while i < n and nums[i] != 0:
                i += 1
            while j and nums[j] == 0:
                j -= 1
            if i >= j:
                break
            ans += 1
            i += 1
            j -= 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            while (i < n && nums[i] != 0) {
                ++i;
            }

            while (j > 0 && nums[j] == 0) {
                --j;
            }

            if (i >= j) {
                break;
            }

            ++ans;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            while (i < n && nums[i] != 0) {
                ++i;
            }

            while (j > 0 && nums[j] == 0) {
                --j;
            }

            if (i >= j) {
                break;
            }

            ++ans;
        }

        return ans;
    }
};
```

#### Go

```go
func minimumSwaps(nums []int) int {
	ans := 0
	n := len(nums)

	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		for i < n && nums[i] != 0 {
			i++
		}

		for j > 0 && nums[j] == 0 {
			j--
		}

		if i >= j {
			break
		}

		ans++
	}

	return ans
}
```

#### TypeScript

```ts
function minimumSwaps(nums: number[]): number {
    let ans = 0;
    const n = nums.length;

    let i = 0;
    let j = n - 1;

    while (i < j) {
        while (i < n && nums[i] !== 0) {
            ++i;
        }

        while (j > 0 && nums[j] === 0) {
            --j;
        }

        if (i >= j) {
            break;
        }

        ++ans;
        ++i;
        --j;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
