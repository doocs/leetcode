---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README_EN.md
rating: 1395
source: Weekly Contest 376 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [2966. Divide Array Into Arrays With Max Difference](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference)

[中文文档](/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of size <code>n</code> where <code>n</code> is a multiple of 3 and a positive integer <code>k</code>.</p>

<p>Divide the array <code>nums</code> into <code>n / 3</code> arrays of size <strong>3</strong> satisfying the following condition:</p>

<ul>
	<li>The difference between <strong>any</strong> two elements in one array is <strong>less than or equal</strong> to <code>k</code>.</li>
</ul>

<p>Return a <strong>2D</strong> array containing the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,4,8,7,9,3,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,1,3],[3,4,5],[7,8,9]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The difference between any two elements in each array is less than or equal to 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,2,2,5,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>Different ways to divide <code>nums</code> into 2 arrays of size 3 are:</p>

<ul>
	<li>[[2,2,2],[2,4,5]] (and its permutations)</li>
	<li>[[2,2,4],[2,2,5]] (and its permutations)</li>
</ul>

<p>Because there are four 2s there will be an array with the elements 2 and 5 no matter how we divide it. since <code>5 - 2 = 3 &gt; k</code>, the condition is not satisfied and so there is no valid division.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The difference between any two elements in each array is less than or equal to 14.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n </code>is a multiple of 3</li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

First, we sort the array. Then, we take out three elements each time. If the difference between the maximum and minimum values of these three elements is greater than $k$, then the condition cannot be satisfied, and we return an empty array. Otherwise, we add the array composed of these three elements to the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divideArray(self, nums: List[int], k: int) -> List[List[int]]:
        nums.sort()
        ans = []
        n = len(nums)
        for i in range(0, n, 3):
            t = nums[i : i + 3]
            if t[2] - t[0] > k:
                return []
            ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][];
        for (int i = 0; i < n; i += 3) {
            int[] t = Arrays.copyOfRange(nums, i, i + 3);
            if (t[2] - t[0] > k) {
                return new int[][] {};
            }
            ans[i / 3] = t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> divideArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        int n = nums.size();
        for (int i = 0; i < n; i += 3) {
            vector<int> t = {nums[i], nums[i + 1], nums[i + 2]};
            if (t[2] - t[0] > k) {
                return {};
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

#### Go

```go
func divideArray(nums []int, k int) [][]int {
	sort.Ints(nums)
	ans := [][]int{}
	for i := 0; i < len(nums); i += 3 {
		t := slices.Clone(nums[i : i+3])
		if t[2]-t[0] > k {
			return [][]int{}
		}
		ans = append(ans, t)
	}
	return ans
}
```

#### TypeScript

```ts
function divideArray(nums: number[], k: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    for (let i = 0; i < nums.length; i += 3) {
        const t = nums.slice(i, i + 3);
        if (t[2] - t[0] > k) {
            return [];
        }
        ans.push(t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
