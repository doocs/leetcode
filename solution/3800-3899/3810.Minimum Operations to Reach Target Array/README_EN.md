---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3810.Minimum%20Operations%20to%20Reach%20Target%20Array/README_EN.md
rating: 1492
source: Biweekly Contest 174 Q2
---

<!-- problem:start -->

# [3810. Minimum Operations to Reach Target Array](https://leetcode.com/problems/minimum-operations-to-reach-target-array)

[中文文档](/solution/3800-3899/3810.Minimum%20Operations%20to%20Reach%20Target%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums</code> and <code>target</code>, each of length <code>n</code>, where <code>nums[i]</code> is the current value at index <code>i</code> and <code>target[i]</code> is the desired value at index <code>i</code>.</p>

<p>You may perform the following operation any number of times (including zero):</p>

<ul>
	<li>Choose an integer value <code>x</code></li>
	<li>Find all <strong>maximal contiguous segments</strong> where <code>nums[i] == x</code> (a segment is <strong>maximal</strong> if it cannot be extended to the left or right while keeping all values equal to <code>x</code>)</li>
	<li>For each such segment <code>[l, r]</code>, update <strong>simultaneously</strong>:
	<ul>
		<li><code>nums[l] = target[l], nums[l + 1] = target[l + 1], ..., nums[r] = target[r]</code></li>
	</ul>
	</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make <code>nums</code> equal to <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], target = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Choose <code>x = 1</code>: maximal segment <code>[0, 0]</code> updated -&gt; nums becomes <code>[2, 2, 3]</code></li>
	<li>Choose <code>x = 2</code>: maximal segment <code>[0, 1]</code> updated (<code>nums[0]</code> stays 2, <code>nums[1]</code> becomes 1) -&gt; <code>nums</code> becomes <code>[2, 1, 3]</code></li>
	<li>Thus, 2 operations are required to convert <code>nums</code> to <code>target</code>.​​​​​​​​​​​​​​</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,4], target = [5,1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>x = 4</code>: maximal segments <code>[0, 0]</code> and <code>[2, 2]</code> updated (<code>nums[2]</code> stays 4) -&gt; <code>nums</code> becomes <code>[5, 1, 4]</code></li>
	<li>Thus, 1 operation is required to convert <code>nums</code> to <code>target</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,3,7], target = [5,5,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>x = 7</code>: maximal segments <code>[0, 0]</code> and <code>[2, 2]</code> updated -&gt; <code>nums</code> becomes <code>[5, 3, 9]</code></li>
	<li>Choose <code>x = 3</code>: maximal segment <code>[1, 1]</code> updated -&gt; <code>nums</code> becomes <code>[5, 5, 9]</code></li>
	<li>Thus, 2 operations are required to convert <code>nums</code> to <code>target</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

According to the problem description, we only need to count the number of distinct $\text{nums}[i]$ where $\text{nums}[i] \ne \text{target}[i]$. Therefore, we can use a hash table to store these distinct $\text{nums}[i]$ and finally return the size of the hash table.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], target: List[int]) -> int:
        s = {x for x, y in zip(nums, target) if x != y}
        return len(s)
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int[] target) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != target[i]) {
                s.add(nums[i]);
            }
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& target) {
        unordered_set<int> s;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] != target[i]) {
                s.insert(nums[i]);
            }
        }
        return s.size();
    }
};
```

#### Go

```go
func minOperations(nums []int, target []int) int {
	s := make(map[int]struct{})
	for i := 0; i < len(nums); i++ {
		if nums[i] != target[i] {
			s[nums[i]] = struct{}{}
		}
	}
	return len(s)
}
```

#### TypeScript

```ts
function minOperations(nums: number[], target: number[]): number {
    const s = new Set<number>();
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] !== target[i]) {
            s.add(nums[i]);
        }
    }
    return s.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
