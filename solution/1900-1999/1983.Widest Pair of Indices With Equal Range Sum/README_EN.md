---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README_EN.md
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [1983. Widest Pair of Indices With Equal Range Sum 🔒](https://leetcode.com/problems/widest-pair-of-indices-with-equal-range-sum)

[中文文档](/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>0-indexed</strong> binary arrays <code>nums1</code> and <code>nums2</code>. Find the <strong>widest</strong> pair of indices <code>(i, j)</code> such that <code>i &lt;= j</code> and <code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>.</p>

<p>The <strong>widest</strong> pair of indices is the pair with the <strong>largest</strong> <strong>distance</strong> between <code>i</code> and <code>j</code>. The <strong>distance</strong> between a pair of indices is defined as <code>j - i + 1</code>.</p>

<p>Return <em>the <strong>distance</strong> of the <strong>widest</strong> pair of indices. If no pair of indices meets the conditions, return </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
If i = 1 and j = 3:
nums1[1] + nums1[2] + nums1[3] = 1 + 0 + 1 = 2.
nums2[1] + nums2[2] + nums2[3] = 1 + 1 + 0 = 2.
The distance between i and j is j - i + 1 = 3 - 1 + 1 = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
If i = 1 and j = 1:
nums1[1] = 1.
nums2[1] = 1.
The distance between i and j is j - i + 1 = 1 - 1 + 1 = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], nums2 = [1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no pairs of indices that meet the requirements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>nums2[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

We observe that for any index pair $(i, j)$, if $nums1[i] + nums1[i+1] + ... + nums1[j] = nums2[i] + nums2[i+1] + ... + nums2[j]$, then $nums1[i] - nums2[i] + nums1[i+1] - nums2[i+1] + ... + nums1[j] - nums2[j] = 0$. If we subtract the corresponding elements of array $nums1$ and array $nums2$ to get a new array $nums$, the problem is transformed into finding the longest subarray in $nums$ such that the sum of the subarray is $0$. This can be solved using the prefix sum + hash table method.

We define a variable $s$ to represent the current prefix sum of $nums$, and use a hash table $d$ to store the first occurrence position of each prefix sum. Initially, $s = 0$ and $d[0] = -1$.

Next, we traverse each element $x$ in the array $nums$, calculate the value of $s$, and then check whether $s$ exists in the hash table. If $s$ exists in the hash table, it means that there is a subarray $nums[d[s]+1,..i]$ such that the sum of the subarray is $0$, and we update the answer to $\max(ans, i - d[s])$. Otherwise, we add the value of $s$ to the hash table, indicating that the first occurrence position of $s$ is $i$.

After the traversal, we can get the final answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def widestPairOfIndices(self, nums1: List[int], nums2: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            s += a - b
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

#### Java

```java
class Solution {
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int n = nums1.length;
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
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
    int widestPairOfIndices(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> d;
        d[0] = -1;
        int ans = 0, s = 0;
        int n = nums1.size();
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.count(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func widestPairOfIndices(nums1 []int, nums2 []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i := range nums1 {
		s += nums1[i] - nums2[i]
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
}
```

#### TypeScript

```ts
function widestPairOfIndices(nums1: number[], nums2: number[]): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    const n: number = nums1.length;
    let s: number = 0;
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        s += nums1[i] - nums2[i];
        if (d.has(s)) {
            ans = Math.max(ans, i - (d.get(s) as number));
        } else {
            d.set(s, i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
