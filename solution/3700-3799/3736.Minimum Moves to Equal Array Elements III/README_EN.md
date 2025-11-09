---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3736.Minimum%20Moves%20to%20Equal%20Array%20Elements%20III/README_EN.md
---

<!-- problem:start -->

# [3736. Minimum Moves to Equal Array Elements III](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii)

[中文文档](/solution/3700-3799/3736.Minimum%20Moves%20to%20Equal%20Array%20Elements%20III/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>In one move, you may <strong>increase</strong> the value of any single element <code>nums[i]</code> by 1.</p>

<p>Return the <strong>minimum total</strong> number of <strong>moves</strong> required so that all elements in <code>nums</code> become <strong>equal</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>To make all elements equal:</p>

<ul>
	<li>Increase <code>nums[0] = 2</code> by 1 to make it 3.</li>
	<li>Increase <code>nums[1] = 1</code> by 1 to make it 2.</li>
	<li>Increase <code>nums[1] = 2</code> by 1 to make it 3.</li>
</ul>

<p>Now, all elements of <code>nums</code> are equal to 3. The minimum total moves is <code>3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>To make all elements equal:</p>

<ul>
	<li>Increase <code>nums[0] = 4</code> by 1 to make it 5.</li>
	<li>Increase <code>nums[1] = 4</code> by 1 to make it 5.</li>
</ul>

<p>Now, all elements of <code>nums</code> are equal to 5. The minimum total moves is <code>2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Calculate Sum and Maximum Value

This problem requires making all elements in the array equal, with each operation only able to increase a single element by 1. To minimize the number of operations, we should make all elements equal to the maximum value in the array.

Therefore, we can first calculate the maximum value $\textit{mx}$ and the sum of array elements $\textit{s}$. The number of operations required to make all elements equal to $\textit{mx}$ is $\textit{mx} \times n - \textit{s}$, where $n$ is the length of the array.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: List[int]) -> int:
        n = len(nums)
        mx = max(nums)
        s = sum(nums)
        return mx * n - s
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int n = nums.size();
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
};
```

#### Go

```go
func minMoves(nums []int) int {
	mx, s := 0, 0
	for _, x := range nums {
		mx = max(mx, x)
		s += x
	}
	return mx*len(nums) - s
}
```

#### TypeScript

```ts
function minMoves(nums: number[]): number {
    const n = nums.length;
    const mx = Math.max(...nums);
    const s = nums.reduce((a, b) => a + b, 0);
    return mx * n - s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
