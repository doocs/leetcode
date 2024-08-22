---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README_EN.md
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)

[中文文档](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an array of positive integers <code>nums</code> and a positive integer <code>target</code>, return <em>the <strong>minimal length</strong> of a </em><span data-keyword="subarray-nonempty"><em>subarray</em></span><em> whose sum is greater than or equal to</em> <code>target</code>. If there is no such subarray, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 7, nums = [2,3,1,2,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [4,3] has the minimal length under the problem constraint.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 4, nums = [1,4,4]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution of which the time complexity is <code>O(n log(n))</code>.

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Binary Search

First, we preprocess the prefix sum array $s$ of the array $nums$, where $s[i]$ represents the sum of the first $i$ elements of the array $nums$. Since all elements in the array $nums$ are positive integers, the array $s$ is also monotonically increasing. Also, we initialize the answer $ans = n + 1$, where $n$ is the length of the array $nums$.

Next, we traverse the prefix sum array $s$. For each element $s[i]$, we can find the smallest index $j$ that satisfies $s[j] \geq s[i] + target$ by binary search. If $j \leq n$, it means that there exists a subarray that satisfies the condition, and we can update the answer, i.e., $ans = min(ans, j - i)$.

Finally, if $ans \leq n$, it means that there exists a subarray that satisfies the condition, return $ans$, otherwise return $0$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        ans = n + 1
        for i, x in enumerate(s):
            j = bisect_left(s, x + target)
            if j <= n:
                ans = min(ans, j - i)
        return ans if ans <= n else 0
```

#### Java

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = search(s, s[i] + target);
            if (j <= n) {
                ans = Math.min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }

    private int search(long[] nums, long x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = lower_bound(s.begin(), s.end(), s[i] + target) - s.begin();
            if (j <= n) {
                ans = min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }
};
```

#### Go

```go
func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := n + 1
	for i, x := range s {
		j := sort.SearchInts(s, x+target)
		if j <= n {
			ans = min(ans, j-i)
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = n + 1;
    const search = (x: number) => {
        let l = 0;
        let r = n + 1;
        while (l < r) {
            const mid = (l + r) >>> 1;
            if (s[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 0; i <= n; ++i) {
        const j = search(s[i] + target);
        if (j <= n) {
            ans = Math.min(ans, j - i);
        }
    }
    return ans === n + 1 ? 0 : ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = n + 1;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];

            while sum >= target {
                res = res.min(j - i + 1);
                sum -= nums[i];
                i += 1;
            }
        }
        if res == n + 1 {
            return 0;
        }
        res as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (s >= target) {
                ans = Math.Min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

We can use two pointers $j$ and $i$ to maintain a window, where the sum of all elements in the window is less than $target$. Initially, $j = 0$, and the answer $ans = n + 1$, where $n$ is the length of the array $nums$.

Next, the pointer $i$ starts to move to the right from $0$, moving one step each time. We add the element corresponding to the pointer $i$ to the window and update the sum of the elements in the window. If the sum of the elements in the window is greater than or equal to $target$, it means that the current subarray satisfies the condition, and we can update the answer, i.e., $ans = \min(ans, i - j + 1)$. Then we continuously remove the element $nums[j]$ from the window until the sum of the elements in the window is less than $target$, and then repeat the above process.

Finally, if $ans \leq n$, it means that there exists a subarray that satisfies the condition, return $ans$, otherwise return $0$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        l = s = 0
        ans = inf
        for r, x in enumerate(nums):
            s += x
            while s >= target:
                ans = min(ans, r - l + 1)
                s -= nums[l]
                l += 1
        return 0 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, n = nums.length;
        long s = 0;
        int ans = n + 1;
        for (int r = 0; r < n; ++r) {
            s += nums[r];
            while (s >= target) {
                ans = Math.min(ans, r - l + 1);
                s -= nums[l++];
            }
        }
        return ans > n ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int l = 0, n = nums.size();
        long long s = 0;
        int ans = n + 1;
        for (int r = 0; r < n; ++r) {
            s += nums[r];
            while (s >= target) {
                ans = min(ans, r - l + 1);
                s -= nums[l++];
            }
        }
        return ans > n ? 0 : ans;
    }
};
```

#### Go

```go
func minSubArrayLen(target int, nums []int) int {
	l, n := 0, len(nums)
	s, ans := 0, n+1
	for r, x := range nums {
		s += x
		for s >= target {
			ans = min(ans, r-l+1)
			s -= nums[l]
			l++
		}
	}
	if ans > n {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let [s, ans] = [0, n + 1];
    for (let l = 0, r = 0; r < n; ++r) {
        s += nums[r];
        while (s >= target) {
            ans = Math.min(ans, r - l + 1);
            s -= nums[l++];
        }
    }
    return ans > n ? 0 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
