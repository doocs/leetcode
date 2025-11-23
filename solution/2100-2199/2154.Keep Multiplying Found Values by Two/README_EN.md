---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2154.Keep%20Multiplying%20Found%20Values%20by%20Two/README_EN.md
rating: 1235
source: Weekly Contest 278 Q1
tags:
    - Array
    - Hash Table
    - Sorting
    - Simulation
---

<!-- problem:start -->

# [2154. Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two)

[中文文档](/solution/2100-2199/2154.Keep%20Multiplying%20Found%20Values%20by%20Two/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code>. You are also given an integer <code>original</code> which is the first number that needs to be searched for in <code>nums</code>.</p>

<p>You then do the following steps:</p>

<ol>
	<li>If <code>original</code> is found in <code>nums</code>, <strong>multiply</strong> it by two (i.e., set <code>original = 2 * original</code>).</li>
	<li>Otherwise, <strong>stop</strong> the process.</li>
	<li><strong>Repeat</strong> this process with the new number as long as you keep finding the number.</li>
</ol>

<p>Return <em>the <strong>final</strong> value of </em><code>original</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,3,6,1,12], original = 3
<strong>Output:</strong> 24
<strong>Explanation:</strong> 
- 3 is found in nums. 3 is multiplied by 2 to obtain 6.
- 6 is found in nums. 6 is multiplied by 2 to obtain 12.
- 12 is found in nums. 12 is multiplied by 2 to obtain 24.
- 24 is not found in nums. Thus, 24 is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9], original = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- 4 is not found in nums. Thus, 4 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], original &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{s}$ to record all the numbers in the array $\textit{nums}$.

Next, starting from $\textit{original}$, if $\textit{original}$ is in $\textit{s}$, we multiply $\textit{original}$ by $2$ until $\textit{original}$ is not in $\textit{s}$ anymore, then return $\textit{original}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findFinalValue(self, nums: List[int], original: int) -> int:
        s = set(nums)
        while original in s:
            original <<= 1
        return original
```

#### Java

```java
class Solution {

    public int findFinalValue(int[] nums, int original) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        while (s.contains(original)) {
            original <<= 1;
        }
        return original;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findFinalValue(vector<int>& nums, int original) {
        unordered_set<int> s(nums.begin(), nums.end());
        while (s.contains(original)) {
            original <<= 1;
        }
        return original;
    }
};
```

#### Go

```go
func findFinalValue(nums []int, original int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for s[original] {
		original <<= 1
	}
	return original
}
```

#### TypeScript

```ts
function findFinalValue(nums: number[], original: number): number {
    const s: Set<number> = new Set([...nums]);
    while (s.has(original)) {
        original <<= 1;
    }
    return original;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_final_value(nums: Vec<i32>, original: i32) -> i32 {
        use std::collections::HashSet;
        let s: HashSet<i32> = nums.into_iter().collect();
        let mut original = original;
        while s.contains(&original) {
            original <<= 1;
        }
        original
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
