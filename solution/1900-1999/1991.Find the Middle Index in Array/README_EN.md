---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README_EN.md
rating: 1302
source: Biweekly Contest 60 Q1
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array)

[中文文档](/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, find the <strong>leftmost</strong> <code>middleIndex</code> (i.e., the smallest amongst all the possible ones).</p>

<p>A <code>middleIndex</code> is an index where <code>nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1]</code>.</p>

<p>If <code>middleIndex == 0</code>, the left side sum is considered to be <code>0</code>. Similarly, if <code>middleIndex == nums.length - 1</code>, the right side sum is considered to be <code>0</code>.</p>

<p>Return <em>the <strong>leftmost</strong> </em><code>middleIndex</code><em> that satisfies the condition, or </em><code>-1</code><em> if there is no such index</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-1,<u>8</u>,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,<u>4</u>]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no valid middleIndex.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as&nbsp;724:&nbsp;<a href="https://leetcode.com/problems/find-pivot-index/" target="_blank">https://leetcode.com/problems/find-pivot-index/</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We define two variables $l$ and $r$, representing the sum of elements to the left and right of index $i$ in the array $\textit{nums}$, respectively. Initially, $l = 0$ and $r = \sum_{i = 0}^{n - 1} \textit{nums}[i]$.

We traverse the array $\textit{nums}$, and for the current number $x$, we update $r = r - x$. If $l = r$ at this point, it means the current index $i$ is the middle index, and we return it directly. Otherwise, we update $l = l + x$ and continue to the next number.

If the traversal ends without finding a middle index, return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

Similar problems:

-   [0724. Find Pivot Index](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0724.Find%20Pivot%20Index/README_EN.md)
-   [2574. Left and Right Sum Differences](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMiddleIndex(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        for i, x in enumerate(nums):
            r -= x
            if l == r:
                return i
            l += x
        return -1
```

#### Java

```java
class Solution {
    public int findMiddleIndex(int[] nums) {
        int l = 0, r = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; ++i) {
            r -= nums[i];
            if (l == r) {
                return i;
            }
            l += nums[i];
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMiddleIndex(vector<int>& nums) {
        int l = 0, r = accumulate(nums.begin(), nums.end(), 0);
        for (int i = 0; i < nums.size(); ++i) {
            r -= nums[i];
            if (l == r) {
                return i;
            }
            l += nums[i];
        }
        return -1;
    }
};
```

#### Go

```go
func findMiddleIndex(nums []int) int {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	for i, x := range nums {
		r -= x
		if l == r {
			return i
		}
		l += x
	}
	return -1
}
```

#### TypeScript

```ts
function findMiddleIndex(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    for (let i = 0; i < nums.length; ++i) {
        r -= nums[i];
        if (l === r) {
            return i;
        }
        l += nums[i];
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_middle_index(nums: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r: i32 = nums.iter().sum();

        for (i, &x) in nums.iter().enumerate() {
            r -= x;
            if l == r {
                return i as i32;
            }
            l += x;
        }

        -1
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMiddleIndex = function (nums) {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    for (let i = 0; i < nums.length; ++i) {
        r -= nums[i];
        if (l === r) {
            return i;
        }
        l += nums[i];
    }
    return -1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
