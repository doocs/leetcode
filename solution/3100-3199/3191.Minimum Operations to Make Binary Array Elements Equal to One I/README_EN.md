---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3191.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20I/README_EN.md
tags:
    - Bit Manipulation
    - Queue
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3191. Minimum Operations to Make Binary Array Elements Equal to One I](https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i)

[中文文档](/solution/3100-3199/3191.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="binary-array">binary array</span> <code>nums</code>.</p>

<p>You can do the following operation on the array <strong>any</strong> number of times (possibly zero):</p>

<ul>
	<li>Choose <strong>any</strong> 3 <strong>consecutive</strong> elements from the array and <strong>flip</strong> <strong>all</strong> of them.</li>
</ul>

<p><strong>Flipping</strong> an element means changing its value from 0 to 1, and from 1 to 0.</p>

<p>Return the <strong>minimum</strong> number of operations required to make all elements in <code>nums</code> equal to 1. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,1,1,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong><br />
We can do the following operations:</p>

<ul>
	<li>Choose the elements at indices 0, 1 and 2. The resulting array is <code>nums = [<u><strong>1</strong></u>,<u><strong>0</strong></u>,<u><strong>0</strong></u>,1,0,0]</code>.</li>
	<li>Choose the elements at indices 1, 2 and 3. The resulting array is <code>nums = [1,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<strong><u>0</u></strong>,0,0]</code>.</li>
	<li>Choose the elements at indices 3, 4 and 5. The resulting array is <code>nums = [1,1,1,<strong><u>1</u></strong>,<u><strong>1</strong></u>,<u><strong>1</strong></u>]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong><br />
It is impossible to make all elements equal to 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sequential Traversal + Simulation

We notice that the first position in the array that is $0$ must undergo a flip operation, otherwise, it cannot be turned into $1$. Therefore, we can sequentially traverse the array, and each time we encounter $0$, we flip the next two elements and accumulate one operation count.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = 0
        for i, x in enumerate(nums):
            if x == 0:
                if i + 2 >= len(nums):
                    return -1
                nums[i + 1] ^= 1
                nums[i + 2] ^= 1
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                if (i + 2 >= n) {
                    return -1;
                }
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                if (i + 2 >= n) {
                    return -1;
                }
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) (ans int) {
	for i, x := range nums {
		if x == 0 {
			if i+2 >= len(nums) {
				return -1
			}
			nums[i+1] ^= 1
			nums[i+2] ^= 1
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (nums[i] === 0) {
            if (i + 2 >= n) {
                return -1;
            }
            nums[i + 1] ^= 1;
            nums[i + 2] ^= 1;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
