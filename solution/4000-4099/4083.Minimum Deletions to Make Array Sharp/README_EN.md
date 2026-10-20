---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4083. Minimum Deletions to Make Array Sharp](https://leetcode.com/problems/minimum-deletions-to-make-array-sharp)

[中文文档](/solution/4000-4099/4083.Minimum%20Deletions%20to%20Make%20Array%20Sharp/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You may delete any number of elements from <code>nums</code>. The remaining elements keep their original order.</p>

<p>The resulting array <code>a</code> is <strong>sharp</strong> if the <strong>bitwise XOR</strong> of every pair of <strong>adjacent</strong> elements is <strong>non-zero</strong>. That is, <code>a[i] XOR a[i + 1] != 0</code> for every index <code>i</code> such that <code>0 &lt;= i &lt; a.length - 1</code>. An array with <strong>at most</strong> one element is sharp.</p>

<p>Return the <strong>minimum</strong> number of deletions needed to make <code>nums</code> sharp.</p>

<p><strong>Bitwise XOR</strong> compares the corresponding bits of two integers. A bit in the result is 1 if the corresponding bits differ, and 0 otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The pairs at indices <code>(0, 1)</code>, <code>(2, 3)</code>, and <code>(3, 4)</code> each have an XOR of 0.</li>
	<li>Deleting one 1 and two 2s leaves <code>[1, 2, 3]</code>, where <code>1 XOR 2 == 3</code> and <code>2 XOR 3 == 1</code> are both non-zero. Therefore, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4,9,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The pair at indices <code>(0, 1)</code> has an XOR of 0.</li>
	<li>Deleting either of these two 4s leaves <code>[4, 9, 4]</code>, where <code>4 XOR 9 == 13</code> and <code>9 XOR 4 == 13</code> are both non-zero.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Count Adjacent Equals

<!-- thinking:start -->

> **Thinking**
>
> $n$ is at most $100$, so comparing adjacent elements is cheap, but enumerating every deletion subset is exponential. Adjacent XOR is $0$ exactly when the two values are equal, so searching subsets is unnecessary.
>
> The bottleneck is a contiguous run of equal values. Relative order is fixed, so a run can keep only one element. Runs of different values are already separated by an unequal boundary, and deleting the extras inside a run does not make those runs interfere.
>
> A run of length $k$ costs $k-1$ deletions, and it contains exactly $k-1$ adjacent equal pairs. One left-to-right scan that counts positions where $\textit{nums}[i]=\textit{nums}[i-1]$ is the minimum number of deletions.

<!-- thinking:end -->

Two integers have bitwise XOR $0$ if and only if they are equal. The task is therefore to delete as few elements as possible, without changing the relative order, so that every pair of adjacent remaining elements is different.

Scan the array from left to right. If $\textit{nums}[i]=\textit{nums}[i-1]$, those two positions cannot both stay. Treat each contiguous run of equal values as one group. A group of length $k$ must lose $k-1$ elements, and that group contains exactly $k-1$ adjacent equal pairs. Elements on either side of a group boundary are already different, so keeping one element from each group still leaves unequal neighbors. The minimum number of deletions is the number of adjacent equal pairs.

The time complexity is $O(n)$ and the space complexity is $O(1)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletions(self, nums: list[int]) -> int:
        return sum(x == y for x, y in pairwise(nums))
```

#### Java

```java
class Solution {
    public int minDeletions(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
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
    int minDeletions(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] == nums[i - 1]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletions(nums []int) (ans int) {
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minDeletions(nums: number[]): number {
    let ans: number = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
