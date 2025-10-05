---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3702.Longest%20Subsequence%20With%20Non-Zero%20Bitwise%20XOR/README_EN.md
---

<!-- problem:start -->

# [3702. Longest Subsequence With Non-Zero Bitwise XOR](https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor)

[中文文档](/solution/3700-3799/3702.Longest%20Subsequence%20With%20Non-Zero%20Bitwise%20XOR/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return the length of the <strong>longest <span data-keyword="subsequence-array-nonempty">subsequence</span></strong> in <code>nums</code> whose bitwise <strong>XOR</strong> is <strong>non-zero</strong>. If no such <strong>subsequence</strong> exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One longest subsequence is <code>[2, 3]</code>. The bitwise XOR is computed as <code>2 XOR 3 = 1</code>, which is non-zero.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest subsequence is <code>[2, 3, 4]</code>. The bitwise XOR is computed as <code>2 XOR 3 XOR 4 = 5</code>, which is non-zero.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

If the bitwise XOR of all elements in the array is non-zero, then the entire array is the desired longest subsequence, with length equal to the array length.

If all elements in the array are zero, then there is no subsequence with non-zero bitwise XOR, so we return $0$.

Otherwise, we can remove one non-zero element from the array to make the bitwise XOR of the remaining elements non-zero. The length of the longest subsequence is the array length minus $1$.

The time complexity is $O(n)$, where $n$ is the length of array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        n = len(nums)
        xor = cnt0 = 0
        for x in nums:
            xor ^= x
            cnt0 += int(x == 0)
        if xor:
            return n
        if cnt0 == n:
            return 0
        return n - 1
```

#### Java

```java
class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0, cnt0 = 0;
        int n = nums.length;
        for (int x : nums) {
            xor ^= x;
            cnt0 += x == 0 ? 1 : 0;
        }
        if (xor != 0) {
            return n;
        }
        return cnt0 == n ? 0 : n - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        int xor_ = 0, cnt0 = 0;
        int n = nums.size();
        for (int x : nums) {
            xor_ ^= x;
            cnt0 += x == 0 ? 1 : 0;
        }
        if (xor_ != 0) {
            return n;
        }
        return cnt0 == n ? 0 : n - 1;
    }
};
```

#### Go

```go
func longestSubsequence(nums []int) int {
	var xor, cnt0 int
	for _, x := range nums {
		xor ^= x
		if x == 0 {
			cnt0++
		}
	}
	n := len(nums)
	if xor != 0 {
		return n
	}
	if cnt0 == n {
		return 0
	}
	return n - 1
}
```

#### TypeScript

```ts
function longestSubsequence(nums: number[]): number {
    let [xor, cnt0] = [0, 0];
    for (const x of nums) {
        xor ^= x;
        cnt0 += x === 0 ? 1 : 0;
    }
    const n = nums.length;
    if (xor) {
        return n;
    }
    if (cnt0 === n) {
        return 0;
    }
    return n - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
