---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3432.Count%20Partitions%20with%20Even%20Sum%20Difference/README_EN.md
tags:
    - Array
    - Math
    - Prefix Sum
---

<!-- problem:start -->

# [3432. Count Partitions with Even Sum Difference](https://leetcode.com/problems/count-partitions-with-even-sum-difference)

[中文文档](/solution/3400-3499/3432.Count%20Partitions%20with%20Even%20Sum%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>A <strong>partition</strong> is defined as an index <code>i</code> where <code>0 &lt;= i &lt; n - 1</code>, splitting the array into two <strong>non-empty</strong> subarrays such that:</p>

<ul>
	<li>Left subarray contains indices <code>[0, i]</code>.</li>
	<li>Right subarray contains indices <code>[i + 1, n - 1]</code>.</li>
</ul>

<p>Return the number of <strong>partitions</strong> where the <strong>difference</strong> between the <strong>sum</strong> of the left and right subarrays is <strong>even</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,10,3,7,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The 4 partitions are:</p>

<ul>
	<li><code>[10]</code>, <code>[10, 3, 7, 6]</code> with a sum difference of <code>10 - 26 = -16</code>, which is even.</li>
	<li><code>[10, 10]</code>, <code>[3, 7, 6]</code> with a sum difference of <code>20 - 16 = 4</code>, which is even.</li>
	<li><code>[10, 10, 3]</code>, <code>[7, 6]</code> with a sum difference of <code>23 - 13 = 10</code>, which is even.</li>
	<li><code>[10, 10, 3, 7]</code>, <code>[6]</code> with a sum difference of <code>30 - 6 = 24</code>, which is even.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No partition results in an even sum difference.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,6,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>All partitions result in an even sum difference.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We use two variables $l$ and $r$ to represent the sum of the left subarray and the right subarray, respectively. Initially, $l = 0$ and $r = \sum_{i=0}^{n-1} \textit{nums}[i]$.

Next, we traverse the first $n - 1$ elements. Each time, we add the current element to the left subarray and subtract it from the right subarray. Then, we check if $l - r$ is even. If it is, we increment the answer by one.

Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPartitions(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        ans = 0
        for x in nums[:-1]:
            l += x
            r -= x
            ans += (l - r) % 2 == 0
        return ans
```

#### Java

```java
class Solution {
    public int countPartitions(int[] nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
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
    int countPartitions(vector<int>& nums) {
        int l = 0, r = accumulate(nums.begin(), nums.end(), 0);
        int ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPartitions(nums []int) (ans int) {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	for _, x := range nums[:len(nums)-1] {
		l += x
		r -= x
		if (l-r)%2 == 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countPartitions(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = 0;
    for (const x of nums.slice(0, -1)) {
        l += x;
        r -= x;
        ans += (l - r) % 2 === 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
