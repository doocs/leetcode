---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1588.Sum%20of%20All%20Odd%20Length%20Subarrays/README.md
rating: 1408
source: 第 35 场双周赛 Q1
tags:
    - 数组
    - 数学
    - 前缀和
---

<!-- problem:start -->

# [1588. 所有奇数长度子数组的和](https://leetcode.cn/problems/sum-of-all-odd-length-subarrays)

[English Version](/solution/1500-1599/1588.Sum%20of%20All%20Odd%20Length%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组&nbsp;<code>arr</code>&nbsp;，请你计算所有可能的奇数长度子数组的和。</p>

<p><strong>子数组</strong> 定义为原数组中的一个连续子序列。</p>

<p>请你返回 <code>arr</code>&nbsp;中 <strong>所有奇数长度子数组的和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,4,2,5,3]
<strong>输出：</strong>58
<strong>解释：</strong>所有奇数长度子数组和它们的和为：
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2]
<strong>输出：</strong>3
<strong>解释：</strong>总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [10,11,12]
<strong>输出：</strong>66
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你可以设计一个 O(n) 时间复杂度的算法解决此问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义两个长度为 $n$ 的数组 $f$ 和 $g$，其中 $f[i]$ 表示以 $\textit{arr}[i]$ 结尾的长度为奇数的子数组的和，而 $g[i]$ 表示以 $\textit{arr}[i]$ 结尾的长度为偶数的子数组的和。初始时 $f[0] = \textit{arr}[0]$，而 $g[0] = 0$。答案即为 $\sum_{i=0}^{n-1} f[i]$。

当 $i > 0$ 时，考虑 $f[i]$ 和 $g[i]$ 如何进行状态转移：

对于状态 $f[i]$，元素 $\textit{arr}[i]$ 可以与前面的 $g[i-1]$ 组成一个长度为奇数的子数组，一共可以组成的子数组个数为 $(i / 2) + 1$ 个，因此 $f[i] = g[i-1] + \textit{arr}[i] \times ((i / 2) + 1)$。

对于状态 $g[i]$，当 $i = 0$ 时，没有长度为偶数的子数组，因此 $g[0] = 0$；当 $i > 0$ 时，元素 $\textit{arr}[i]$ 可以与前面的 $f[i-1]$ 组成一个长度为偶数的子数组，一共可以组成的子数组个数为 $(i + 1) / 2$ 个，因此 $g[i] = f[i-1] + \textit{arr}[i] \times ((i + 1) / 2)$。

最终答案即为 $\sum_{i=0}^{n-1} f[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        n = len(arr)
        f = [0] * n
        g = [0] * n
        ans = f[0] = arr[0]
        for i in range(1, n):
            f[i] = g[i - 1] + arr[i] * (i // 2 + 1)
            g[i] = f[i - 1] + arr[i] * ((i + 1) // 2)
            ans += f[i]
        return ans
```

#### Java

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] f = new int[n];
        int[] g = new int[n];
        int ans = f[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            f[i] = g[i - 1] + arr[i] * (i / 2 + 1);
            g[i] = f[i - 1] + arr[i] * ((i + 1) / 2);
            ans += f[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        vector<int> f(n, arr[0]);
        vector<int> g(n);
        int ans = f[0];
        for (int i = 1; i < n; ++i) {
            f[i] = g[i - 1] + arr[i] * (i / 2 + 1);
            g[i] = f[i - 1] + arr[i] * ((i + 1) / 2);
            ans += f[i];
        }
        return ans;
    }
};
```

#### Go

```go
func sumOddLengthSubarrays(arr []int) (ans int) {
	n := len(arr)
	f := make([]int, n)
	g := make([]int, n)
	f[0] = arr[0]
	ans = f[0]
	for i := 1; i < n; i++ {
		f[i] = g[i-1] + arr[i]*(i/2+1)
		g[i] = f[i-1] + arr[i]*((i+1)/2)
		ans += f[i]
	}
	return
}
```

#### TypeScript

```ts
function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    const f: number[] = Array(n).fill(arr[0]);
    const g: number[] = Array(n).fill(0);
    let ans = f[0];
    for (let i = 1; i < n; ++i) {
        f[i] = g[i - 1] + arr[i] * ((i >> 1) + 1);
        g[i] = f[i - 1] + arr[i] * ((i + 1) >> 1);
        ans += f[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut f = vec![0; n];
        let mut g = vec![0; n];
        let mut ans = arr[0];
        f[0] = arr[0];
        for i in 1..n {
            f[i] = g[i - 1] + arr[i] * ((i as i32) / 2 + 1);
            g[i] = f[i - 1] + arr[i] * (((i + 1) as i32) / 2);
            ans += f[i];
        }
        ans
    }
}
```

#### C

```c
int sumOddLengthSubarrays(int* arr, int arrSize) {
    int n = arrSize;
    int f[n];
    int g[n];
    int ans = f[0] = arr[0];
    g[0] = 0;
    for (int i = 1; i < n; ++i) {
        f[i] = g[i - 1] + arr[i] * (i / 2 + 1);
        g[i] = f[i - 1] + arr[i] * ((i + 1) / 2);
        ans += f[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（空间优化）

我们注意到，状态 $f[i]$ 和 $g[i]$ 的值只与 $f[i - 1]$ 和 $g[i - 1]$ 有关，因此我们可以使用两个变量 $f$ 和 $g$ 分别记录 $f[i - 1]$ 和 $g[i - 1]$ 的值，从而优化空间复杂度。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        ans, f, g = arr[0], arr[0], 0
        for i in range(1, len(arr)):
            ff = g + arr[i] * (i // 2 + 1)
            gg = f + arr[i] * ((i + 1) // 2)
            f, g = ff, gg
            ans += f
        return ans
```

#### Java

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int ans = arr[0], f = arr[0], g = 0;
        for (int i = 1; i < arr.length; ++i) {
            int ff = g + arr[i] * (i / 2 + 1);
            int gg = f + arr[i] * ((i + 1) / 2);
            f = ff;
            g = gg;
            ans += f;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int ans = arr[0], f = arr[0], g = 0;
        for (int i = 1; i < arr.size(); ++i) {
            int ff = g + arr[i] * (i / 2 + 1);
            int gg = f + arr[i] * ((i + 1) / 2);
            f = ff;
            g = gg;
            ans += f;
        }
        return ans;
    }
};
```

#### Go

```go
func sumOddLengthSubarrays(arr []int) (ans int) {
	f, g := arr[0], 0
	ans = f
	for i := 1; i < len(arr); i++ {
		ff := g + arr[i]*(i/2+1)
		gg := f + arr[i]*((i+1)/2)
		f, g = ff, gg
		ans += f
	}
	return
}
```

#### TypeScript

```ts
function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let [ans, f, g] = [arr[0], arr[0], 0];
    for (let i = 1; i < n; ++i) {
        const ff = g + arr[i] * (Math.floor(i / 2) + 1);
        const gg = f + arr[i] * Math.floor((i + 1) / 2);
        [f, g] = [ff, gg];
        ans += f;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let mut ans = arr[0];
        let mut f = arr[0];
        let mut g = 0;
        for i in 1..arr.len() {
            let ff = g + arr[i] * ((i as i32) / 2 + 1);
            let gg = f + arr[i] * (((i + 1) as i32) / 2);
            f = ff;
            g = gg;
            ans += f;
        }
        ans
    }
}
```

#### C

```c
int sumOddLengthSubarrays(int* arr, int arrSize) {
    int ans = arr[0], f = arr[0], g = 0;
    for (int i = 1; i < arrSize; ++i) {
        int ff = g + arr[i] * (i / 2 + 1);
        int gg = f + arr[i] * ((i + 1) / 2);
        f = ff;
        g = gg;
        ans += f;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
