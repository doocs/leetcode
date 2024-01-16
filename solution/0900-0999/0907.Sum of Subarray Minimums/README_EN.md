# [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums)

[中文文档](/solution/0900-0999/0907.Sum%20of%20Subarray%20Minimums/README.md)

## Description

<p>Given an array of integers arr, find the sum of <code>min(b)</code>, where <code>b</code> ranges over every (contiguous) subarray of <code>arr</code>. Since the answer may be large, return the answer <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,2,4]
<strong>Output:</strong> 17
<strong>Explanation:</strong> 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [11,81,94,43,3]
<strong>Output:</strong> 444
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        n = len(arr)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(arr):
            while stk and arr[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)

        stk = []
        for i in range(n - 1, -1, -1):
            while stk and arr[stk[-1]] > arr[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        mod = 10**9 + 7
        return sum((i - left[i]) * (right[i] - i) * v for i, v in enumerate(arr)) % mod
```

```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i - left[i]) * (right[i] - i) % mod * arr[i] % mod;
            ans %= mod;
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int sumSubarrayMins(vector<int>& arr) {
        int n = arr.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && arr[stk.top()] >= arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && arr[stk.top()] > arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans += 1LL * (i - left[i]) * (right[i] - i) * arr[i] % mod;
            ans %= mod;
        }
        return ans;
    }
};
```

```go
func sumSubarrayMins(arr []int) (ans int) {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range arr {
		for len(stk) > 0 && arr[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && arr[stk[len(stk)-1]] > arr[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	const mod int = 1e9 + 7
	for i, v := range arr {
		ans += (i - left[i]) * (right[i] - i) * v % mod
		ans %= mod
	}
	return
}
```

```ts
function sumSubarrayMins(arr: number[]): number {
    const n: number = arr.length;
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length > 0 && arr[stk.at(-1)] >= arr[i]) {
            stk.pop();
        }
        if (stk.length > 0) {
            left[i] = stk.at(-1);
        }
        stk.push(i);
    }

    stk.length = 0;
    for (let i = n - 1; ~i; --i) {
        while (stk.length > 0 && arr[stk.at(-1)] > arr[i]) {
            stk.pop();
        }
        if (stk.length > 0) {
            right[i] = stk.at(-1);
        }
        stk.push(i);
    }

    const mod: number = 1e9 + 7;
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        ans += ((((i - left[i]) * (right[i] - i)) % mod) * arr[i]) % mod;
        ans %= mod;
    }
    return ans;
}
```

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn sum_subarray_mins(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut left = vec![-1; n];
        let mut right = vec![n as i32; n];
        let mut stk: VecDeque<usize> = VecDeque::new();

        for i in 0..n {
            while !stk.is_empty() && arr[*stk.back().unwrap()] >= arr[i] {
                stk.pop_back();
            }
            if let Some(&top) = stk.back() {
                left[i] = top as i32;
            }
            stk.push_back(i);
        }

        stk.clear();
        for i in (0..n).rev() {
            while !stk.is_empty() && arr[*stk.back().unwrap()] > arr[i] {
                stk.pop_back();
            }
            if let Some(&top) = stk.back() {
                right[i] = top as i32;
            }
            stk.push_back(i);
        }

        let MOD = 1_000_000_007;
        let mut ans: i64 = 0;
        for i in 0..n {
            ans +=
                ((((right[i] - (i as i32)) * ((i as i32) - left[i])) as i64) * (arr[i] as i64)) %
                MOD;
            ans %= MOD;
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
const MOD: i64 = (1e9 as i64) + 7;

impl Solution {
    pub fn sum_subarray_mins(arr: Vec<i32>) -> i32 {
        let n: usize = arr.len();
        let mut ret: i64 = 0;
        let mut left: Vec<i32> = vec![-1; n];
        let mut right: Vec<i32> = vec![n as i32; n];
        // Index stack, store the index of the value in the given array
        let mut stack: Vec<i32> = Vec::new();

        // Find the first element that's less than the current value for the left side
        // The default value of which is -1
        for i in 0..n {
            while !stack.is_empty() && arr[*stack.last().unwrap() as usize] >= arr[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                left[i] = *stack.last().unwrap();
            }
            stack.push(i as i32);
        }

        stack.clear();

        // Find the first element that's less or equal than the current value for the right side
        // The default value of which is n
        for i in (0..n).rev() {
            while !stack.is_empty() && arr[*stack.last().unwrap() as usize] > arr[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                right[i] = *stack.last().unwrap();
            }
            stack.push(i as i32);
        }

        // Traverse the array, to find the sum
        for i in 0..n {
            ret +=
                ((((right[i] - (i as i32)) * ((i as i32) - left[i])) as i64) * (arr[i] as i64)) %
                MOD;
            ret %= MOD;
        }

        (ret % (MOD as i64)) as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
