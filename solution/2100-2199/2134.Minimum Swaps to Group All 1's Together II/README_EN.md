---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2134.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together%20II/README_EN.md
rating: 1748
source: Weekly Contest 275 Q2
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [2134. Minimum Swaps to Group All 1's Together II](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii)

[中文文档](/solution/2100-2199/2134.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together%20II/README.md)

## Description

<!-- description:start -->

<p>A <strong>swap</strong> is defined as taking two <strong>distinct</strong> positions in an array and swapping the values in them.</p>

<p>A <strong>circular</strong> array is defined as an array where we consider the <strong>first</strong> element and the <strong>last</strong> element to be <strong>adjacent</strong>.</p>

<p>Given a <strong>binary</strong> <strong>circular</strong> array <code>nums</code>, return <em>the minimum number of swaps required to group all </em><code>1</code><em>&#39;s present in the array together at <strong>any location</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0,1,1,0,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Here are a few of the ways to group all the 1&#39;s together:
[0,<u>0</u>,<u>1</u>,1,1,0,0] using 1 swap.
[0,1,<u>1</u>,1,<u>0</u>,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1&#39;s together with 0 swaps.
Thus, the minimum number of swaps required is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,1,0,0,1,1,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Here are a few of the ways to group all the 1&#39;s together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1&#39;s together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,0,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All the 1&#39;s are already grouped together due to the circular property of the array.
Thus, the minimum number of swaps required is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

First, we count the number of $1$s in the array, denoted as $k$. The problem is actually asking for a circular subarray of length $k$ that contains the maximum number of $1$s. Therefore, the minimum number of swaps is $k$ minus the maximum number of $1$s in that subarray.

We can solve this problem using a sliding window. First, we count the number of $1$s in the first $k$ elements of the array, denoted as $cnt$. Then, we maintain a sliding window of length $k$. Each time we move the window one position to the right, we update $cnt$ and simultaneously update the maximum $cnt$ value, i.e., $mx = \max(mx, cnt)$. Finally, the answer is $k - mx$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        k = nums.count(1)
        mx = cnt = sum(nums[:k])
        n = len(nums)
        for i in range(k, n + k):
            cnt += nums[i % n]
            cnt -= nums[(i - k + n) % n]
            mx = max(mx, cnt)
        return k - mx
```

#### Java

```java
class Solution {
    public int minSwaps(int[] nums) {
        int k = Arrays.stream(nums).sum();
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += nums[i];
        }
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.max(mx, cnt);
        }
        return k - mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int k = accumulate(nums.begin(), nums.end(), 0);
        int n = nums.size();
        int cnt = accumulate(nums.begin(), nums.begin() + k, 0);
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = max(mx, cnt);
        }
        return k - mx;
    }
};
```

#### Go

```go
func minSwaps(nums []int) int {
	k := 0
	for _, x := range nums {
		k += x
	}
	cnt := 0
	for i := 0; i < k; i++ {
		cnt += nums[i]
	}
	mx := cnt
	n := len(nums)
	for i := k; i < n+k; i++ {
		cnt += nums[i%n] - nums[(i-k+n)%n]
		mx = max(mx, cnt)
	}
	return k - mx
}
```

#### TypeScript

```ts
function minSwaps(nums: number[]): number {
    const n = nums.length;
    const k = nums.reduce((a, b) => a + b, 0);
    let cnt = k - nums.slice(0, k).reduce((a, b) => a + b, 0);
    let min = cnt;

    for (let i = k; i < n + k; i++) {
        cnt += nums[i - k] - nums[i % n];
        min = Math.min(min, cnt);
    }

    return min;
}
```

#### JavaScript

```ts
function minSwaps(nums) {
    const n = nums.length;
    const k = nums.reduce((a, b) => a + b, 0);
    let cnt = k - nums.slice(0, k).reduce((a, b) => a + b, 0);
    let min = cnt;

    for (let i = k; i < n + k; i++) {
        cnt += nums[i - k] - nums[i % n];
        min = Math.min(min, cnt);
    }

    return min;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_swaps(nums: Vec<i32>) -> i32 {
        let k: i32 = nums.iter().sum();
        let n: usize = nums.len();
        let mut cnt: i32 = 0;
        for i in 0..k {
            cnt += nums[i as usize];
        }
        let mut mx: i32 = cnt;
        for i in k..(n as i32) + k {
            cnt += nums[(i % (n as i32)) as usize]
                - nums[((i - k + (n as i32)) % (n as i32)) as usize];
            mx = mx.max(cnt);
        }
        return k - mx;
    }
}
```

#### C#

```cs
public class Solution {
    public int MinSwaps(int[] nums) {
        int k = nums.Sum();
        int n = nums.Length;
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += nums[i];
        }
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.Max(mx, cnt);
        }
        return k - mx;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Prefix Sum

<!-- tabs:start -->

#### TypeScript

```ts
function minSwaps(nums: number[]): number {
    const n = nums.length;

    const getMin = (x: 0 | 1) => {
        const prefixSum = Array(n + 1).fill(0);
        for (let i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (nums[i - 1] === x);
        }

        const length = prefixSum[n];
        let ans = Number.POSITIVE_INFINITY;
        for (let l = 0, r = length; r <= n; l++, r++) {
            const min = length - (prefixSum[r] - prefixSum[l]);
            ans = Math.min(ans, min);
        }

        return ans;
    };

    return Math.min(getMin(0), getMin(1));
}
```

#### JavaScript

```js
function minSwaps(nums) {
    const n = nums.length;

    const getMin = x => {
        const prefixSum = Array(n + 1).fill(0);
        for (let i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (nums[i - 1] === x);
        }

        const length = prefixSum[n];
        let ans = Number.POSITIVE_INFINITY;
        for (let l = 0, r = length; r <= n; l++, r++) {
            const min = length - (prefixSum[r] - prefixSum[l]);
            ans = Math.min(ans, min);
        }

        return ans;
    };

    return Math.min(getMin(0), getMin(1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
