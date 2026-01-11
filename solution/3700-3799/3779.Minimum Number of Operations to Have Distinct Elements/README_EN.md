---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3779.Minimum%20Number%20of%20Operations%20to%20Have%20Distinct%20Elements/README_EN.md
rating: 1444
source: Biweekly Contest 172 Q1
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3779. Minimum Number of Operations to Have Distinct Elements](https://leetcode.com/problems/minimum-number-of-operations-to-have-distinct-elements)

[中文文档](/solution/3700-3799/3779.Minimum%20Number%20of%20Operations%20to%20Have%20Distinct%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>In one operation, you remove the <strong>first three elements</strong> of the current array. If there are fewer than three elements remaining, <strong>all</strong> remaining elements are removed.</p>

<p>Repeat this operation until the array is empty or contains no duplicate values.</p>

<p>Return an integer denoting the number of operations required.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,8,3,6,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>In the first operation, we remove the first three elements. The remaining elements <code>[6, 5, 8]</code> are all distinct, so we stop. Only one operation is needed.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After one operation, the array becomes empty, which meets the stopping condition.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,5,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All elements in the array are distinct, therefore no operations are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Reverse Traversal

We can traverse the array $\textit{nums}$ in reverse order and use a hash table $\textit{st}$ to record the elements we have already traversed. When we traverse to element $\textit{nums}[i]$, if $\textit{nums}[i]$ is already in the hash table $\textit{st}$, it means we need to remove all elements in $\textit{nums}[0..i]$, and the number of operations required is $\left\lfloor \frac{i}{3} \right\rfloor + 1$. Otherwise, we add $\textit{nums}[i]$ to the hash table $\textit{st}$ and continue to traverse the next element.

After the traversal is complete, if no duplicate elements are found, then all elements in the array are already distinct, no operations are needed, and the answer is $0$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        st = set()
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] in st:
                return i // 3 + 1
            st.add(nums[i])
        return 0
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (!st.add(nums[i])) {
                return i / 3 + 1;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_set<int> st;
        for (int i = nums.size() - 1; ~i; --i) {
            if (st.contains(nums[i])) {
                return i / 3 + 1;
            }
            st.insert(nums[i]);
        }
        return 0;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	st := make(map[int]struct{})
	for i := len(nums) - 1; i >= 0; i-- {
		if _, ok := st[nums[i]]; ok {
			return i/3 + 1
		}
		st[nums[i]] = struct{}{}
	}
	return 0
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const st = new Set<number>();
    for (let i = nums.length - 1; i >= 0; i--) {
        if (st.has(nums[i])) {
            return Math.floor(i / 3) + 1;
        }
        st.add(nums[i]);
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
