---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2779.Maximum%20Beauty%20of%20an%20Array%20After%20Applying%20Operation/README_EN.md
rating: 1638
source: Weekly Contest 354 Q2
tags:
    - Array
    - Binary Search
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [2779. Maximum Beauty of an Array After Applying Operation](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation)

[中文文档](/solution/2700-2799/2779.Maximum%20Beauty%20of%20an%20Array%20After%20Applying%20Operation/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> and a <strong>non-negative</strong> integer <code>k</code>.</p>

<p>In one operation, you can do the following:</p>

<ul>
	<li>Choose an index <code>i</code> that <strong>hasn&#39;t been chosen before</strong> from the range <code>[0, nums.length - 1]</code>.</li>
	<li>Replace <code>nums[i]</code> with any integer from the range <code>[nums[i] - k, nums[i] + k]</code>.</li>
</ul>

<p>The <strong>beauty</strong> of the array is the length of the longest subsequence consisting of equal elements.</p>

<p>Return <em>the <strong>maximum</strong> possible beauty of the array </em><code>nums</code><em> after applying the operation any number of times.</em></p>

<p><strong>Note</strong> that you can apply the operation to each index <strong>only once</strong>.</p>

<p>A&nbsp;<strong>subsequence</strong> of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,6,1,2], k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, we apply the following operations:
- Choose index 1, replace it with 4 (from range [4,8]), nums = [4,4,1,2].
- Choose index 3, replace it with 4 (from range [0,4]), nums = [4,4,1,4].
After the applied operations, the beauty of the array nums is 3 (subsequence consisting of indices 0, 1, and 3).
It can be proven that 3 is the maximum possible length we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1], k = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> In this example we don&#39;t have to apply any operations.
The beauty of the array nums is 4 (whole array).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We notice that for each operation, all elements within the interval $[nums[i]-k, nums[i]+k]$ will increase by $1$. Therefore, we can use a difference array to record the contributions of these operations to the beauty value.

In the problem, $nums[i]-k$ might be negative. We add $k$ to all elements to ensure the results are non-negative. Thus, we can create a difference array $d$ with a length of $\max(nums) + k \times 2 + 2$.

Next, we iterate through the array $nums$. For the current element $x$ being iterated, we increase $d[x]$ by $1$ and decrease $d[x+k\times2+1]$ by $1$. In this way, we can calculate the prefix sum for each position using the $d$ array, which represents the beauty value for each position. The maximum beauty value can then be found.

The time complexity is $O(M + 2 \times k + n)$, and the space complexity is $O(M + 2 \times k)$. Here, $n$ is the length of the array $nums$, and $M$ is the maximum value in the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBeauty(self, nums: List[int], k: int) -> int:
        m = max(nums) + k * 2 + 2
        d = [0] * m
        for x in nums:
            d[x] += 1
            d[x + k * 2 + 1] -= 1
        return max(accumulate(d))
```

#### Java

```java
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt() + k * 2 + 2;
        int[] d = new int[m];
        for (int x : nums) {
            d[x]++;
            d[x + k * 2 + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& nums, int k) {
        int m = *max_element(nums.begin(), nums.end()) + k * 2 + 2;
        vector<int> d(m);
        for (int x : nums) {
            d[x]++;
            d[x + k * 2 + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumBeauty(nums []int, k int) (ans int) {
	m := slices.Max(nums)
	m += k*2 + 2
	d := make([]int, m)
	for _, x := range nums {
		d[x]++
		d[x+k*2+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if ans < s {
			ans = s
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumBeauty(nums: number[], k: number): number {
    const m = Math.max(...nums) + k * 2 + 2;
    const d: number[] = Array(m).fill(0);
    for (const x of nums) {
        d[x]++;
        d[x + k * 2 + 1]--;
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        ans = Math.max(ans, s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
