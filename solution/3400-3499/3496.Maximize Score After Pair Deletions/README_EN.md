---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3496.Maximize%20Score%20After%20Pair%20Deletions/README_EN.md
---

<!-- problem:start -->

# [3496. Maximize Score After Pair Deletions 🔒](https://leetcode.com/problems/maximize-score-after-pair-deletions)

[中文文档](/solution/3400-3499/3496.Maximize%20Score%20After%20Pair%20Deletions/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code>. You <strong>must</strong> repeatedly perform one of the following operations while the array has more than two elements:</p>

<ul>
	<li>Remove the first two elements.</li>
	<li>Remove the last two elements.</li>
	<li>Remove the first and last element.</li>
</ul>

<p>For each operation, add the sum of the removed elements to your total score.</p>

<p>Return the <strong>maximum</strong> possible score you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible operations are:</p>

<ul>
	<li>Remove the first two elements <code>(2 + 4) = 6</code>. The remaining array is <code>[1]</code>.</li>
	<li>Remove the last two elements <code>(4 + 1) = 5</code>. The remaining array is <code>[2]</code>.</li>
	<li>Remove the first and last elements <code>(2 + 1) = 3</code>. The remaining array is <code>[4]</code>.</li>
</ul>

<p>The maximum score is obtained by removing the first two elements, resulting in a final score of 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,-1,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible operations are:</p>

<ul>
	<li>Remove the first and last elements <code>(5 + 2) = 7</code>. The remaining array is <code>[-1, 4]</code>.</li>
	<li>Remove the first two elements <code>(5 + -1) = 4</code>. The remaining array is <code>[4, 2]</code>.</li>
	<li>Remove the last two elements <code>(4 + 2) = 6</code>. The remaining array is <code>[5, -1]</code>.</li>
</ul>

<p>The maximum score is obtained by removing the first and last elements, resulting in a total score of 7.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Thinking

According to the problem description, each operation removes the two elements at the endpoints. Therefore, when the number of elements is odd, one element will eventually remain; when the number of elements is even, two consecutive elements in the array will eventually remain.

To maximize the score after deletions, we should minimize the remaining elements.

Thus, if the array $\textit{nums}$ has an odd number of elements, the answer is the sum of all elements $s$ in the array $\textit{nums}$ minus the minimum value $\textit{mi}$ in $\textit{nums}$; if the array $\textit{nums}$ has an even number of elements, the answer is the sum of all elements $s$ in the array $\textit{nums}$ minus the minimum sum of any two consecutive elements.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        s = sum(nums)
        if len(nums) & 1:
            return s - min(nums)
        return s - min(a + b for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        final int inf = 1 << 30;
        int n = nums.length;
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = Math.min(mi, nums[i]);
            if (i + 1 < n) {
                t = Math.min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        const int inf = 1 << 30;
        int n = nums.size();
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = min(mi, nums[i]);
            if (i + 1 < n) {
                t = min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	const inf = 1 << 30
	n := len(nums)
	s, mi, t := 0, inf, inf
	for i, x := range nums {
		s += x
		mi = min(mi, x)
		if i+1 < n {
			t = min(t, x+nums[i+1])
		}
	}
	if n%2 == 1 {
		return s - mi
	}
	return s - t
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const inf = Infinity;
    const n = nums.length;
    let [s, mi, t] = [0, inf, inf];
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        mi = Math.min(mi, nums[i]);
        if (i + 1 < n) {
            t = Math.min(t, nums[i] + nums[i + 1]);
        }
    }
    return n % 2 ? s - mi : s - t;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
