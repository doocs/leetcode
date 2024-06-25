---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0995.Minimum%20Number%20of%20K%20Consecutive%20Bit%20Flips/README_EN.md
tags:
    - Bit Manipulation
    - Queue
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [995. Minimum Number of K Consecutive Bit Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips)

[中文文档](/solution/0900-0999/0995.Minimum%20Number%20of%20K%20Consecutive%20Bit%20Flips/README.md)

## Description

<!-- description:start -->

<p>You are given a binary array <code>nums</code> and an integer <code>k</code>.</p>

<p>A <strong>k-bit flip</strong> is choosing a <strong>subarray</strong> of length <code>k</code> from <code>nums</code> and simultaneously changing every <code>0</code> in the subarray to <code>1</code>, and every <code>1</code> in the subarray to <code>0</code>.</p>

<p>Return <em>the minimum number of <strong>k-bit flips</strong> required so that there is no </em><code>0</code><em> in the array</em>. If it is not possible, return <code>-1</code>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> Flip nums[0], then flip nums[2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0], k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0,1,0,1,1,0], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We notice that the result of reversing several consecutive elements is independent of the order of the reversals. Therefore, we can greedily consider the number of reversals needed at each position.

We can process the array from left to right.

Suppose we need to process position $i$, and the elements to the left of position $i$ have been processed. If the element at position $i$ is $0$, then we must perform a reversal operation, we need to reverse the elements in the interval $[i,..i+k-1]$. Here we use a difference array $d$ to maintain the number of reversals at each position, then to determine whether the current position $i$ needs to be reversed, we only need to see $s = \sum_{j=0}^{i}d[j]$ and the parity of $nums[i]$. If $s$ and $nums[i]$ have the same parity, then the element at position $i$ is still $0$ and needs to be reversed. At this time, we check whether $i+k$ exceeds the length of the array. If it exceeds the length of the array, then the target cannot be achieved, return $-1$. Otherwise, we increase $d[i]$ by $1$, decrease $d[i+k]$ by $1$, increase the answer by $1$, and increase $s$ by $1$.

In this way, when we have processed all the elements in the array, we can return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        n = len(nums)
        d = [0] * (n + 1)
        ans = s = 0
        for i, x in enumerate(nums):
            s += d[i]
            if s % 2 == x:
                if i + k > n:
                    return -1
                d[i] += 1
                d[i + k] -= 1
                s += 1
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n + 1];
        int ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s % 2 == nums[i]) {
                if (i + k > n) {
                    return -1;
                }
                ++d[i];
                --d[i + k];
                ++s;
                ++ans;
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
    int minKBitFlips(vector<int>& nums, int k) {
        int n = nums.size();
        int d[n + 1];
        memset(d, 0, sizeof(d));
        int ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s % 2 == nums[i]) {
                if (i + k > n) {
                    return -1;
                }
                ++d[i];
                --d[i + k];
                ++s;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minKBitFlips(nums []int, k int) (ans int) {
	n := len(nums)
	d := make([]int, n+1)
	s := 0
	for i, x := range nums {
		s += d[i]
		if s%2 == x {
			if i+k > n {
				return -1
			}
			d[i]++
			d[i+k]--
			s++
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        s += d[i];
        if (s % 2 === nums[i]) {
            if (i + k > n) {
                return -1;
            }
            d[i]++;
            d[i + k]--;
            s++;
            ans++;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_k_bit_flips(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut d = vec![0; n + 1];
        let mut ans = 0;
        let mut s = 0;
        for i in 0..n {
            s += d[i];
            if s % 2 == nums[i] {
                if i + (k as usize) > n {
                    return -1;
                }
                d[i] += 1;
                d[i + (k as usize)] -= 1;
                s += 1;
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window

We can use a variable $\text{flipped}$ to indicate whether the current position has been flipped. If $\text{flipped} = 1$, it means the current position has already been flipped; otherwise, it means the current position has not been flipped. For positions that have been flipped, we can set their value to $-1$, allowing us to distinguish which positions have been flipped.

Next, we traverse the array from left to right. For each position $i$, if $i \geq k$ and the element at position $i-k$ is $-1$, then the flip state of the current position should be the opposite of the flip state of the previous position. That is, $\text{flipped} = \text{flipped} \oplus 1$. If the element at the current position is the same as the current flip state, then we need to flip the current position. At this point, we check if $i+k$ exceeds the length of the array. If it does, then it is impossible to achieve the goal, and we return $-1$. Otherwise, we invert the current flip state, increase the answer by $1$, and set the element at the current position to $-1$.

By processing all elements in the array in this manner, we can return the answer upon completion.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        ans = flipped = 0
        for i, x in enumerate(nums):
            if i >= k and nums[i - k] == -1:
                flipped ^= 1
            if x == flipped:
                if i + k > len(nums):
                    return -1
                flipped ^= 1
                ans += 1
                nums[i] = -1
        return ans
```

#### Java

```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, flipped = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i - k] == -1) {
                flipped ^= 1;
            }
            if (flipped == nums[i]) {
                if (i + k > n) {
                    return -1;
                }
                flipped ^= 1;
                ++ans;
                nums[i] = -1;
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
    int minKBitFlips(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0, flipped = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i - k] == -1) {
                flipped ^= 1;
            }
            if (flipped == nums[i]) {
                if (i + k > n) {
                    return -1;
                }
                flipped ^= 1;
                ++ans;
                nums[i] = -1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minKBitFlips(nums []int, k int) (ans int) {
	flipped := 0
	for i, x := range nums {
		if i >= k && nums[i-k] == -1 {
			flipped ^= 1
		}
		if flipped == x {
			if i+k > len(nums) {
				return -1
			}
			flipped ^= 1
			ans++
			nums[i] = -1
		}
	}
	return
}
```

#### TypeScript

```ts
function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, flipped] = [0, 0];
    for (let i = 0; i < n; i++) {
        if (nums[i - k] === -1) {
            flipped ^= 1;
        }
        if (nums[i] === flipped) {
            if (i + k > n) {
                return -1;
            }
            flipped ^= 1;
            ++ans;
            nums[i] = -1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_k_bit_flips(mut nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut flipped = 0;
        let k = k as usize;

        for i in 0..nums.len() {
            if i >= k && nums[i - k] == -1 {
                flipped ^= 1;
            }
            if flipped == nums[i] {
                if i + k > nums.len() {
                    return -1;
                }
                flipped ^= 1;
                ans += 1;
                nums[i] = -1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
