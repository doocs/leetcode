---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1313.Decompress%20Run-Length%20Encoded%20List/README_EN.md
rating: 1317
source: Biweekly Contest 17 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1313. Decompress Run-Length Encoded List](https://leetcode.com/problems/decompress-run-length-encoded-list)

[中文文档](/solution/1300-1399/1313.Decompress%20Run-Length%20Encoded%20List/README.md)

## Description

<!-- description:start -->

<p>We are given a list <code>nums</code> of integers representing a list compressed with run-length encoding.</p>

<p>Consider each adjacent pair&nbsp;of elements <code>[freq, val] = [nums[2*i], nums[2*i+1]]</code>&nbsp;(with <code>i &gt;= 0</code>).&nbsp; For each such pair, there are <code>freq</code> elements with value <code>val</code> concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.</p>

<p>Return the decompressed list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> [2,4,4,4]
<strong>Explanation:</strong> The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,3]
<strong>Output:</strong> [1,3,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
	<li><code><font face="monospace">1 &lt;= nums[i] &lt;= 100</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process described in the problem. Traverse the array $\textit{nums}$ from left to right, each time taking out two numbers $\textit{freq}$ and $\textit{val}$, then repeat $\textit{val}$ $\textit{freq}$ times, and add these $\textit{freq}$ $\textit{val}$s to the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. We only need to traverse the array $\textit{nums}$ once. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        return [nums[i + 1] for i in range(0, len(nums), 2) for _ in range(nums[i])]
```

#### Java

```java
class Solution {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; ++j) {
                ans.add(nums[i + 1]);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> decompressRLElist(vector<int>& nums) {
        vector<int> ans;
        for (int i = 0; i < nums.size(); i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                ans.push_back(nums[i + 1]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func decompressRLElist(nums []int) (ans []int) {
	for i := 1; i < len(nums); i += 2 {
		for j := 0; j < nums[i-1]; j++ {
			ans = append(ans, nums[i])
		}
	}
	return
}
```

#### TypeScript

```ts
function decompressRLElist(nums: number[]): number[] {
    const ans: number[] = [];
    for (let i = 0; i < nums.length; i += 2) {
        for (let j = 0; j < nums[i]; j++) {
            ans.push(nums[i + 1]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn decompress_rl_elist(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = Vec::new();
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let freq = nums[i];
            let val = nums[i + 1];
            for _ in 0..freq {
                ans.push(val);
            }
            i += 2;
        }
        ans
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* decompressRLElist(int* nums, int numsSize, int* returnSize) {
    int n = 0;
    for (int i = 0; i < numsSize; i += 2) {
        n += nums[i];
    }
    int* ans = (int*) malloc(n * sizeof(int));
    *returnSize = n;
    int k = 0;
    for (int i = 0; i < numsSize; i += 2) {
        int freq = nums[i];
        int val = nums[i + 1];
        for (int j = 0; j < freq; j++) {
            ans[k++] = val;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
