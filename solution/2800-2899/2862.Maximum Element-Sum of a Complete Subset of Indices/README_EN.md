---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README_EN.md
rating: 2291
source: Weekly Contest 363 Q4
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2862. Maximum Element-Sum of a Complete Subset of Indices](https://leetcode.com/problems/maximum-element-sum-of-a-complete-subset-of-indices)

[中文文档](/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README.md)

## Description

<p>You are given a <strong>1</strong><strong>-indexed</strong> array <code>nums</code>. Your task is to select a <strong>complete subset</strong> from <code>nums</code> where every pair of selected indices multiplied is a <span data-keyword="perfect-square">perfect square,</span>. i. e. if you select <code>a<sub>i</sub></code> and <code>a<sub>j</sub></code>, <code>i * j</code> must be a perfect square.</p>

<p>Return the <em>sum</em> of the complete subset with the <em>maximum sum</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,7,3,5,7,2,4,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>We select elements at indices 1 and 4 and <code>1 * 4</code> is a perfect square.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,10,3,8,1,13,7,9,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>We select elements at indices 1, 4, and 9. <code>1 * 4</code>, <code>1 * 9</code>, <code>4 * 9</code> are perfect squares.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We note that if a number can be expressed in the form of $k \times j^2$, then all numbers of this form have the same $k$.

Therefore, we can enumerate $k$ in the range $[1,..n]$, and then start enumerating $j$ from $1$, each time adding the value of $nums[k \times j^2 - 1]$ to $t$, until $k \times j^2 > n$. At this point, update the answer to $ans = \max(ans, t)$.

Finally, return the answer $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for k in range(1, n + 1):
            t = 0
            j = 1
            while k * j * j <= n:
                t += nums[k * j * j - 1]
                j += 1
            ans = max(ans, t)
        return ans
```

```java
class Solution {
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        boolean[] used = new boolean[n + 1];
        int bound = (int) Math.floor(Math.sqrt(n));
        int[] squares = new int[bound + 1];
        for (int i = 1; i <= bound + 1; i++) {
            squares[i - 1] = i * i;
        }
        for (int i = 1; i <= n; i++) {
            long res = 0;
            int idx = 0;
            int curr = i * squares[idx];
            while (curr <= n) {
                res += nums.get(curr - 1);
                curr = i * squares[++idx];
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }
}
```

```java
class Solution {
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        for (int k = 1; k <= n; ++k) {
            long t = 0;
            for (int j = 1; k * j * j <= n; ++j) {
                t += nums.get(k * j * j - 1);
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumSum(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int k = 1; k <= n; ++k) {
            long long t = 0;
            for (int j = 1; k * j * j <= n; ++j) {
                t += nums[k * j * j - 1];
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```go
func maximumSum(nums []int) (ans int64) {
	n := len(nums)
	for k := 1; k <= n; k++ {
		var t int64
		for j := 1; k*j*j <= n; j++ {
			t += int64(nums[k*j*j-1])
		}
		ans = max(ans, t)
	}
	return
}
```

```ts
function maximumSum(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    for (let k = 1; k <= n; ++k) {
        let t = 0;
        for (let j = 1; k * j * j <= n; ++j) {
            t += nums[k * j * j - 1];
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
