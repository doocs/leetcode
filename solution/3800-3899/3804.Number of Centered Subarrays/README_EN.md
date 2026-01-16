---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3804.Number%20of%20Centered%20Subarrays/README_EN.md
rating: 1393
source: Weekly Contest 484 Q2
---

<!-- problem:start -->

# [3804. Number of Centered Subarrays](https://leetcode.com/problems/number-of-centered-subarrays)

[中文文档](/solution/3800-3899/3804.Number%20of%20Centered%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of <code>nums</code> is called <strong>centered</strong> if the sum of its elements is <strong>equal to at least one</strong> element within that <strong>same subarray</strong>.</p>

<p>Return the number of <strong>centered subarrays</strong> of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>All single-element subarrays (<code>[-1]</code>, <code>[1]</code>, <code>[0]</code>) are centered.</li>
	<li>The subarray <code>[1, 0]</code> has a sum of 1, which is present in the subarray.</li>
	<li>The subarray <code>[-1, 1, 0]</code> has a sum of 0, which is present in the subarray.</li>
	<li>Thus, the answer is 5.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Only single-element subarrays (<code>[2]</code>, <code>[-3]</code>) are centered.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We enumerate all starting indices $i$ of subarrays, then starting from index $i$, we enumerate the ending index $j$ of the subarray, calculate the sum $s$ of elements in the subarray $nums[i \ldots j]$, and add all elements in the subarray to the hash table $\textit{st}$. After each enumeration, we check if $s$ appears in the hash table $\textit{st}$. If it does, it means the subarray $nums[i \ldots j]$ is a centered subarray, and we increment the answer by $1$.

The time complexity is $O(n^2)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def centeredSubarrays(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            st = set()
            s = 0
            for j in range(i, n):
                s += nums[j]
                st.add(nums[j])
                if s in st:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> st = new HashSet<>();
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                st.add(nums[j]);
                if (st.contains(s)) {
                    ans++;
                }
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
    int centeredSubarrays(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            unordered_set<int> st;
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                st.insert(nums[j]);
                if (st.count(s)) {
                    ans++;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func centeredSubarrays(nums []int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		st := make(map[int]struct{})
		s := 0
		for j := i; j < n; j++ {
			s += nums[j]
			st[nums[j]] = struct{}{}
			if _, ok := st[s]; ok {
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function centeredSubarrays(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const st = new Set<number>();
        let s = 0;
        for (let j = i; j < n; j++) {
            s += nums[j];
            st.add(nums[j]);
            if (st.has(s)) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
