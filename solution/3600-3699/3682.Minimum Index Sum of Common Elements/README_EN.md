---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3682.Minimum%20Index%20Sum%20of%20Common%20Elements/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3682. Minimum Index Sum of Common Elements 🔒](https://leetcode.com/problems/minimum-index-sum-of-common-elements)

[中文文档](/solution/3600-3699/3682.Minimum%20Index%20Sum%20of%20Common%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of equal length <code>n</code>.</p>

<p>We define a pair of indices <code>(i, j)</code> as a <strong>good pair</strong> if <code>nums1[i] == nums2[j]</code>.</p>

<p>Return the <strong>minimum index sum</strong> <code>i + j</code> among all possible good pairs. If no such pairs exist, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [3,2,1], nums2 = [1,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Common elements between <code>nums1</code> and <code>nums2</code> are 1 and 3.</li>
	<li>For 3, <code>[i, j] = [0, 1]</code>, giving an index sum of <code>i + j = 1</code>.</li>
	<li>For 1, <code>[i, j] = [2, 0]</code>, giving an index sum of <code>i + j = 2</code>.</li>
	<li>The minimum index sum is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [5,1,2], nums2 = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Common elements between <code>nums1</code> and <code>nums2</code> are 1 and 2.</li>
	<li>For 1, <code>[i, j] = [1, 1]</code>, giving an index sum of <code>i + j = 2</code>.</li>
	<li>For 2, <code>[i, j] = [2, 0]</code>, giving an index sum of <code>i + j = 2</code>.</li>
	<li>The minimum index sum is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [6,4], nums2 = [7,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since no common elements between <code>nums1</code> and <code>nums2</code>, the output is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length == nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

We initialize a variable $\textit{ans}$ as infinity, representing the current minimum index sum, and use a hash map $\textit{d}$ to store the first occurrence index of each element in array $\textit{nums2}$.

Then we iterate through array $\textit{nums1}$. For each element $\textit{nums1}[i]$, if it exists in $\textit{d}$, we calculate the index sum $i + \textit{d}[\textit{nums1}[i]]$ and update $\textit{ans}$.

Finally, if $\textit{ans}$ is still infinity, it means no common element was found, so we return -1; otherwise, we return $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSum(self, nums1: List[int], nums2: List[int]) -> int:
        d = {}
        for i, x in enumerate(nums2):
            if x not in d:
                d[x] = i
        ans = inf
        for i, x in enumerate(nums1):
            if x in d:
                ans = min(ans, i + d[x])
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minimumSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        final int inf = 1 << 30;
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; i++) {
            d.putIfAbsent(nums2[i], i);
        }
        int ans = inf;
        for (int i = 0; i < n; i++) {
            if (d.containsKey(nums1[i])) {
                ans = Math.min(ans, i + d.get(nums1[i]));
            }
        }
        return ans == inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        const int inf = INT_MAX;
        unordered_map<int, int> d;
        for (int i = 0; i < n; i++) {
            if (!d.contains(nums2[i])) {
                d[nums2[i]] = i;
            }
        }
        int ans = inf;
        for (int i = 0; i < n; i++) {
            if (d.contains(nums1[i])) {
                ans = min(ans, i + d[nums1[i]]);
            }
        }
        return ans == inf ? -1 : ans;
    }
};
```

#### Go

```go
func minimumSum(nums1 []int, nums2 []int) int {
	const inf = 1 << 30
	d := make(map[int]int)
	for i, x := range nums2 {
		if _, ok := d[x]; !ok {
			d[x] = i
		}
	}
	ans := inf
	for i, x := range nums1 {
		if j, ok := d[x]; ok {
            ans = min(ans, i + j)
		}
	}
	if ans == inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minimumSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const inf = 1 << 30;
    const d = new Map<number, number>();
    for (let i = 0; i < n; i++) {
        if (!d.has(nums2[i])) {
            d.set(nums2[i], i);
        }
    }
    let ans = inf;
    for (let i = 0; i < n; i++) {
        if (d.has(nums1[i])) {
            ans = Math.min(ans, i + (d.get(nums1[i]) as number));
        }
    }
    return ans === inf ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
