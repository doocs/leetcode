---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2411.Smallest%20Subarrays%20With%20Maximum%20Bitwise%20OR/README_EN.md
rating: 1938
source: Biweekly Contest 87 Q3
tags:
    - Bit Manipulation
    - Array
    - Binary Search
    - Sliding Window
---

<!-- problem:start -->

# [2411. Smallest Subarrays With Maximum Bitwise OR](https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or)

[中文文档](/solution/2400-2499/2411.Smallest%20Subarrays%20With%20Maximum%20Bitwise%20OR/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of length <code>n</code>, consisting of non-negative integers. For each index <code>i</code> from <code>0</code> to <code>n - 1</code>, you must determine the size of the <strong>minimum sized</strong> non-empty subarray of <code>nums</code> starting at <code>i</code> (<strong>inclusive</strong>) that has the <strong>maximum</strong> possible <strong>bitwise OR</strong>.</p>

<ul>
	<li>In other words, let <code>B<sub>ij</sub></code> be the bitwise OR of the subarray <code>nums[i...j]</code>. You need to find the smallest subarray starting at <code>i</code>, such that bitwise OR of this subarray is equal to <code>max(B<sub>ik</sub>)</code> where <code>i &lt;= k &lt;= n - 1</code>.</li>
</ul>

<p>The bitwise OR of an array is the bitwise OR of all the numbers in it.</p>

<p>Return <em>an integer array </em><code>answer</code><em> of size </em><code>n</code><em> where </em><code>answer[i]</code><em> is the length of the <strong>minimum</strong> sized subarray starting at </em><code>i</code><em> with <strong>maximum</strong> bitwise OR.</em></p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,2,1,3]
<strong>Output:</strong> [3,3,2,2,1]
<strong>Explanation:</strong>
The maximum possible bitwise OR starting at any index is 3. 
- Starting at index 0, the shortest subarray that yields it is [1,0,2].
- Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
- Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
- Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
- Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
Therefore, we return [3,3,2,2,1]. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2]
<strong>Output:</strong> [2,1]
<strong>Explanation:
</strong>Starting at index 0, the shortest subarray that yields the maximum bitwise OR is of length 2.
Starting at index 1, the shortest subarray that yields the maximum bitwise OR is of length 1.
Therefore, we return [2,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

To find the shortest subarray starting at position $i$ that maximizes the bitwise OR operation, we need to maximize the number of $1$s in the result.

We use an array $f$ of size $32$ to record the earliest position of each bit $1$.

We traverse the array $nums[i]$ in reverse order. For the $j$-th bit of $nums[i]$, if it is $1$, then $f[j]$ is $i$. Otherwise, if $f[j]$ is not $-1$, it means that a number satisfying the $j$-th bit as $1$ is found on the right, so we update the length.

The time complexity is $O(n \times \log m)$, where $n$ is the length of the array $nums$, and $m$ is the maximum value in the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [1] * n
        f = [-1] * 32
        for i in range(n - 1, -1, -1):
            t = 1
            for j in range(32):
                if (nums[i] >> j) & 1:
                    f[j] = i
                elif f[j] != -1:
                    t = max(t, f[j] - i + 1)
            ans[i] = t
        return ans
```

#### Java

```java
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] f = new int[32];
        Arrays.fill(f, -1);
        for (int i = n - 1; i >= 0; --i) {
            int t = 1;
            for (int j = 0; j < 32; ++j) {
                if (((nums[i] >> j) & 1) == 1) {
                    f[j] = i;
                } else if (f[j] != -1) {
                    t = Math.max(t, f[j] - i + 1);
                }
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> smallestSubarrays(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(32, -1);
        vector<int> ans(n);
        for (int i = n - 1; ~i; --i) {
            int t = 1;
            for (int j = 0; j < 32; ++j) {
                if ((nums[i] >> j) & 1) {
                    f[j] = i;
                } else if (f[j] != -1) {
                    t = max(t, f[j] - i + 1);
                }
            }
            ans[i] = t;
        }
        return ans;
    }
};
```

#### Go

```go
func smallestSubarrays(nums []int) []int {
	n := len(nums)
	f := make([]int, 32)
	for i := range f {
		f[i] = -1
	}
	ans := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		t := 1
		for j := 0; j < 32; j++ {
			if ((nums[i] >> j) & 1) == 1 {
				f[j] = i
			} else if f[j] != -1 {
				t = max(t, f[j]-i+1)
			}
		}
		ans[i] = t
	}
	return ans
}
```

#### TypeScript

```ts
function smallestSubarrays(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = Array(n).fill(1);
    const f: number[] = Array(32).fill(-1);

    for (let i = n - 1; i >= 0; i--) {
        let t = 1;
        for (let j = 0; j < 32; j++) {
            if ((nums[i] >> j) & 1) {
                f[j] = i;
            } else if (f[j] !== -1) {
                t = Math.max(t, f[j] - i + 1);
            }
        }
        ans[i] = t;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_subarrays(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![1; n];
        let mut f = vec![-1; 32];

        for i in (0..n).rev() {
            let mut t = 1;
            for j in 0..32 {
                if (nums[i] >> j) & 1 != 0 {
                    f[j] = i as i32;
                } else if f[j] != -1 {
                    t = t.max(f[j] - i as i32 + 1);
                }
            }
            ans[i] = t;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
