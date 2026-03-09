---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3862.Find%20the%20Smallest%20Balanced%20Index/README_EN.md
---

<!-- problem:start -->

# [3862. Find the Smallest Balanced Index](https://leetcode.com/problems/find-the-smallest-balanced-index)

[中文文档](/solution/3800-3899/3862.Find%20the%20Smallest%20Balanced%20Index/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An index <code>i</code> is <strong>balanced</strong> if the sum of elements <strong>strictly</strong> to the left of <code>i</code> equals the product of elements <strong>strictly</strong> to the right of <code>i</code>.</p>

<p>If there are no elements to the left, the sum is considered as 0. Similarly, if there are no elements to the right, the product is considered as 1.</p>

<p>Return an integer denoting the <strong>smallest</strong> balanced index. If no balanced index exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>For index <code>i = 1</code>:</p>

<ul>
	<li>Left sum = <code>nums[0] = 2</code></li>
	<li>Right product = <code>nums[2] = 2</code></li>
	<li>Since the left sum equals the right product, index 1 is balanced.</li>
</ul>

<p>No smaller index satisfies the condition, so the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,8,2,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>For index <code>i = 2</code>:</p>

<ul>
	<li>Left sum = <code>2 + 8 = 10</code></li>
	<li>Right product = <code>2 * 5 = 10</code></li>
	<li>Since the left sum equals the right product, index 2 is balanced.</li>
</ul>

<p>No smaller index satisfies the condition, so the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>
For index <code>i = 0</code>:

<ul>
	<li>The left side is empty, so the left sum is 0.</li>
	<li>The right side is empty, so the right product is 1.</li>
	<li>Since the left sum does not equal the right product, index 0 is not balanced.</li>
</ul>
Therefore, no balanced index exists and the answer is -1.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We first compute the total sum $s$ of all elements in the array. Then we enumerate each index $i$ from right to left, maintaining a variable $p$ to record the product of all elements to the right of index $i$. When we reach index $i$, we first subtract $nums[i]$ from $s$, then check whether $s$ equals $p$; if so, we return index $i$. Next, we multiply $p$ by $nums[i]$. If $p$ is greater than or equal to $s$, the product will only keep growing and no balanced index can be found afterwards, so we can terminate the enumeration early.

If no balanced index is found after the enumeration, we return -1.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestBalancedIndex(self, nums: list[int]) -> int:
        s = sum(nums)
        p = 1
        for i in range(len(nums) - 1, -1, -1):
            s -= nums[i]
            if s == p:
                return i
            p *= nums[i]
            if p >= s:
                break
        return -1
```

#### Java

```java
class Solution {
    public int smallestBalancedIndex(int[] nums) {
        long s = 0, p = 1;
        for (int x : nums) {
            s += x;
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            s -= nums[i];
            if (s == p) {
                return i;
            }
            p *= nums[i];
            if (p >= s) {
                break;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestBalancedIndex(vector<int>& nums) {
        long long s = 0, p = 1;
        for (int x : nums) {
            s += x;
        }
        for (int i = nums.size() - 1; i >= 0; --i) {
            s -= nums[i];
            if (s == p) {
                return i;
            }
            p *= nums[i];
            if (p >= s) {
                break;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestBalancedIndex(nums []int) int {
	s, p := 0, 1
	for _, x := range nums {
		s += x
	}
	for i := len(nums) - 1; i >= 0; i-- {
		s -= nums[i]
		if s == p {
			return i
		}
		p *= nums[i]
		if p >= s {
			break
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestBalancedIndex(nums: number[]): number {
    let s = 0;
    for (const x of nums) {
        s += x;
    }
    for (let i = nums.length - 1, p = 1; i >= 0; --i) {
        s -= nums[i];
        if (s === p) {
            return i;
        }
        p *= nums[i];
        if (p >= s) {
            break;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
