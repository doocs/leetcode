---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3396.Minimum%20Number%20of%20Operations%20to%20Make%20Elements%20in%20Array%20Distinct/README_EN.md
---

<!-- problem:start -->

# [3396. Minimum Number of Operations to Make Elements in Array Distinct](https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct)

[中文文档](/solution/3300-3399/3396.Minimum%20Number%20of%20Operations%20to%20Make%20Elements%20in%20Array%20Distinct/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. You need to ensure that the elements in the array are <strong>distinct</strong>. To achieve this, you can perform the following operation any number of times:</p>

<ul>
	<li>Remove 3 elements from the beginning of the array. If the array has fewer than 3 elements, remove all remaining elements.</li>
</ul>

<p><strong>Note</strong> that an empty array is considered to have distinct elements. Return the <strong>minimum</strong> number of operations needed to make the elements in the array distinct.<!-- notionvc: 210ee4f2-90af-4cdf-8dbc-96d1fa8f67c7 --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,2,3,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first operation, the first 3 elements are removed, resulting in the array <code>[4, 2, 3, 3, 5, 7]</code>.</li>
	<li>In the second operation, the next 3 elements are removed, resulting in the array <code>[3, 5, 7]</code>, which has distinct elements.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,6,4,4]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first operation, the first 3 elements are removed, resulting in the array <code>[4, 4]</code>.</li>
	<li>In the second operation, all remaining elements are removed, resulting in an empty array.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array already contains distinct elements. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Reverse Traversal

We can traverse the array $\textit{nums}$ in reverse order and use a hash table $\textit{s}$ to record the elements that have already been traversed. When we encounter an element $\textit{nums}[i]$, if $\textit{nums}[i]$ is already in the hash table $\textit{s}$, it means we need to remove all elements from $\textit{nums}[0..i]$. The number of operations required is $\left\lfloor \frac{i}{3} \right\rfloor + 1$. Otherwise, we add $\textit{nums}[i]$ to the hash table $\textit{s}$ and continue to the next element.

After traversing, if no duplicate elements are found, the elements in the array are already distinct, and no operations are needed, so the answer is $0$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        s = set()
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] in s:
                return i // 3 + 1
            s.add(nums[i])
        return 0
```

#### Java

```java
class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.add(nums[i]);
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = nums.size() - 1; ~i; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.insert(nums[i]);
        }
        return 0;
    }
};
```

#### Go

```go
func minimumOperations(nums []int) int {
	s := map[int]bool{}
	for i := len(nums) - 1; i >= 0; i-- {
		if s[nums[i]] {
			return i/3 + 1
		}
		s[nums[i]] = true
	}
	return 0
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[]): number {
    const s = new Set<number>();
    for (let i = nums.length - 1; ~i; --i) {
        if (s.has(nums[i])) {
            return Math.ceil((i + 1) / 3);
        }
        s.add(nums[i]);
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
