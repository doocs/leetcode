# [1708. Largest Subarray Length K 🔒](https://leetcode.com/problems/largest-subarray-length-k)

[中文文档](/solution/1700-1799/1708.Largest%20Subarray%20Length%20K/README.md)

<!-- tags:Greedy,Array -->

## Description

<p>An array <code>A</code> is larger than some array <code>B</code> if for the first index <code>i</code> where <code>A[i] != B[i]</code>, <code>A[i] &gt; B[i]</code>.</p>

<p>For example, consider <code>0</code>-indexing:</p>

<ul>
	<li><code>[1,3,2,4] &gt; [1,2,2,4]</code>, since at index <code>1</code>, <code>3 &gt; 2</code>.</li>
	<li><code>[1,4,4,4] &lt; [2,1,1,1]</code>, since at index <code>0</code>, <code>1 &lt; 2</code>.</li>
</ul>

<p>A subarray is a contiguous subsequence of the array.</p>

<p>Given an integer array <code>nums</code> of <strong>distinct</strong> integers, return the <strong>largest</strong> subarray of <code>nums</code> of length <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,5,2,3], k = 3
<strong>Output:</strong> [5,2,3]
<strong>Explanation:</strong> The subarrays of size 3 are: [1,4,5], [4,5,2], and [5,2,3].
Of these, [5,2,3] is the largest.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,5,2,3], k = 4
<strong>Output:</strong> [4,5,2,3]
<strong>Explanation:</strong> The subarrays of size 4 are: [1,4,5,2], and [4,5,2,3].
Of these, [4,5,2,3] is the largest.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,5,2,3], k = 1
<strong>Output:</strong> [5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> What if the integers in <code>nums</code> are not distinct?

## Solutions

### Solution 1: Simulation

All integers in the array are distinct, so we can first find the index of the maximum element in the range $[0,..n-k]$, and then take $k$ elements starting from this index.

The time complexity is $O(n)$, where $n$ is the length of the array. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def largestSubarray(self, nums: List[int], k: int) -> List[int]:
        i = nums.index(max(nums[: len(nums) - k + 1]))
        return nums[i : i + k]
```

```java
class Solution {
    public int[] largestSubarray(int[] nums, int k) {
        int j = 0;
        for (int i = 1; i < nums.length - k + 1; ++i) {
            if (nums[j] < nums[i]) {
                j = i;
            }
        }
        return Arrays.copyOfRange(nums, j, j + k);
    }
}
```

```cpp
class Solution {
public:
    vector<int> largestSubarray(vector<int>& nums, int k) {
        auto i = max_element(nums.begin(), nums.end() - k + 1);
        return {i, i + k};
    }
};
```

```go
func largestSubarray(nums []int, k int) []int {
	j := 0
	for i := 1; i < len(nums)-k+1; i++ {
		if nums[j] < nums[i] {
			j = i
		}
	}
	return nums[j : j+k]
}
```

```ts
function largestSubarray(nums: number[], k: number): number[] {
    let j = 0;
    for (let i = 1; i < nums.length - k + 1; ++i) {
        if (nums[j] < nums[i]) {
            j = i;
        }
    }
    return nums.slice(j, j + k);
}
```

```rust
impl Solution {
    pub fn largest_subarray(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut j = 0;
        for i in 1..=nums.len() - (k as usize) {
            if nums[i] > nums[j] {
                j = i;
            }
        }
        nums[j..j + (k as usize)].to_vec()
    }
}
```

<!-- tabs:end -->

<!-- end -->
