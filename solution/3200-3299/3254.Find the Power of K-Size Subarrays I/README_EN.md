---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3254.Find%20the%20Power%20of%20K-Size%20Subarrays%20I/README_EN.md
---

<!-- problem:start -->

# [3254. Find the Power of K-Size Subarrays I](https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i)

[中文文档](/solution/3200-3299/3254.Find%20the%20Power%20of%20K-Size%20Subarrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> of length <code>n</code> and a <em>positive</em> integer <code>k</code>.</p>

<p>The <strong>power</strong> of an array is defined as:</p>

<ul>
	<li>Its <strong>maximum</strong> element if <em>all</em> of its elements are <strong>consecutive</strong> and <strong>sorted</strong> in <strong>ascending</strong> order.</li>
	<li>-1 otherwise.</li>
</ul>

<p>You need to find the <strong>power</strong> of all <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code> of size <code>k</code>.</p>

<p>Return an integer array <code>results</code> of size <code>n - k + 1</code>, where <code>results[i]</code> is the <em>power</em> of <code>nums[i..(i + k - 1)]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,3,2,5], k = 3</span></p>

<p><strong>Output:</strong> [3,4,-1,-1,-1]</p>

<p><strong>Explanation:</strong></p>

<p>There are 5 subarrays of <code>nums</code> of size 3:</p>

<ul>
	<li><code>[1, 2, 3]</code> with the maximum element 3.</li>
	<li><code>[2, 3, 4]</code> with the maximum element 4.</li>
	<li><code>[3, 4, 3]</code> whose elements are <strong>not</strong> consecutive.</li>
	<li><code>[4, 3, 2]</code> whose elements are <strong>not</strong> sorted.</li>
	<li><code>[3, 2, 5]</code> whose elements are <strong>not</strong> consecutive.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,2,2,2], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,3,2,3,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,3,-1,3,-1]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We define an array $f$, where $f[i]$ represents the length of the continuous increasing subsequence ending at the $i$-th element. Initially, $f[i] = 1$.

Next, we traverse the array $\textit{nums}$ to calculate the values of the array $f$. If $nums[i] = nums[i - 1] + 1$, then $f[i] = f[i - 1] + 1$; otherwise, $f[i] = 1$.

Then, we traverse the array $f$ in the range $[k - 1, n)$. If $f[i] \ge k$, we add $\textit{nums}[i]$ to the answer array; otherwise, we add $-1$.

After the traversal, we return the answer array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ represents the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        f = [1] * n
        for i in range(1, n):
            if nums[i] == nums[i - 1] + 1:
                f[i] = f[i - 1] + 1
        return [nums[i] if f[i] >= k else -1 for i in range(k - 1, n)]
```

#### Java

```java
class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1] + 1) {
                f[i] = f[i - 1] + 1;
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = k - 1; i < n; ++i) {
            ans[i - k + 1] = f[i] >= k ? nums[i] : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> resultsArray(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = nums[i] == nums[i - 1] + 1 ? f[i - 1] + 1 : 1;
        }
        vector<int> ans;
        for (int i = k - 1; i < n; ++i) {
            ans.push_back(f[i] >= k ? nums[i] : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func resultsArray(nums []int, k int) (ans []int) {
	n := len(nums)
	f := make([]int, n)
	f[0] = 1
	for i := 1; i < n; i++ {
		if nums[i] == nums[i-1]+1 {
			f[i] = f[i-1] + 1
		} else {
			f[i] = 1
		}
	}
	for i := k - 1; i < n; i++ {
		if f[i] >= k {
			ans = append(ans, nums[i])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function resultsArray(nums: number[], k: number): number[] {
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        if (nums[i] === nums[i - 1] + 1) {
            f[i] = f[i - 1] + 1;
        }
    }
    const ans: number[] = [];
    for (let i = k - 1; i < n; ++i) {
        ans.push(f[i] >= k ? nums[i] : -1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
