---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4117. Minimum Range After Sum Operations](https://leetcode.com/problems/minimum-range-after-sum-operations)

[中文文档](/solution/4100-4199/4117.Minimum%20Range%20After%20Sum%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>a</code>, <code>b</code>, and <code>c</code>.</p>

<p>You may perform the following operation <strong>any</strong> number of times (including zero):</p>

<ul>
	<li>Choose one of the three integers and <strong>replace</strong> it with the sum of the other two integers. The other two integers remain unchanged.</li>
</ul>

<p>The <strong>range</strong> of three integers is defined as the difference between the <strong>maximum value</strong> and the <strong>minimum value</strong>.</p>

<p>Return the <strong>minimum range</strong> that can be obtained after performing the operation any number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 9, b = 2, c = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of operations which results in the minimum range is as follows:</p>

<ul>
	<li>Set <code>a</code> to <code>b + c = 2 + 5 = 7</code>. The three integers are now <code>a = 7</code>, <code>b = 2</code>, <code>c = 5</code>.</li>
	<li>The range of the three integers is <code>max(7, 2, 5) - min(7, 2, 5) = 7 - 2 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 5, b = 7, c = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Without doing any operations, the range of the three integers is <code>max(5, 7, 6) - min(5, 7, 6) = 7 - 5 = 2</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 3, b = 3, c = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Without doing any operations, the range of the three integers is <code>max(3, 3, 3) - min(3, 3, 3) = 3 - 3 = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

<!-- thinking:start -->

> **Thinking**
>
> Each operation replaces one integer by the sum of the other two positive integers. The values can keep growing, so enumerating every sequence of operations has no natural stopping point.
>
> The range is the maximum minus the minimum. A replacement is at least as large as either integer that stays, so the minimum never decreases. A smaller range then has to come from a smaller maximum.
>
> After the three integers are sorted, the only replacement that can lower the maximum writes the sum of the other two in its place, and the resulting range is the middle value. Replacing the smallest or the middle value raises the maximum, and further operations after replacing the maximum cannot produce a smaller range. The answer is the smaller of the original range and that middle value.

<!-- thinking:end -->

Store $a$, $b$, and $c$ in $\textit{nums}$ and sort them, so $\textit{nums}[0]\le\textit{nums}[1]\le\textit{nums}[2]$.

With no operation, the range is $\textit{nums}[2]-\textit{nums}[0]$. All three integers are positive. An operation replaces the chosen integer by the sum of the other two, so the new integer is at least as large as either integer that remains, and the minimum never decreases. Once the minimum cannot decrease, the range can shrink only when the maximum is replaced by the sum of the other two. The triple becomes $\textit{nums}[0]$, $\textit{nums}[1]$, and $\textit{nums}[0]+\textit{nums}[1]$, and the range equals $\textit{nums}[1]$. If that sum is still at least the old maximum, the maximum does not fall. Another operation from there makes the new maximum at least $\textit{nums}[0]+\textit{nums}[1]$, so the range is not smaller. Replacing the smallest or the middle integer by the sum of the other two produces a value greater than the old maximum, so that range is not smaller either.

The answer is therefore $\min(\textit{nums}[2]-\textit{nums}[0], \textit{nums}[1])$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRange(self, a: int, b: int, c: int) -> int:
        nums = [a, b, c]
        nums.sort()
        return min(nums[2] - nums[0], nums[1])
```

#### Java

```java
class Solution {
    public int minRange(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        return Math.min(nums[2] - nums[0], nums[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRange(int a, int b, int c) {
        vector<int> nums = {a, b, c};
        sort(nums.begin(), nums.end());
        return min(nums[2] - nums[0], nums[1]);
    }
};
```

#### Go

```go
func minRange(a int, b int, c int) int {
	nums := []int{a, b, c}
	sort.Ints(nums)
	return min(nums[2]-nums[0], nums[1])
}
```

#### TypeScript

```ts
function minRange(a: number, b: number, c: number): number {
    const nums = [a, b, c].sort((a, b) => a - b);
    return Math.min(nums[2] - nums[0], nums[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
