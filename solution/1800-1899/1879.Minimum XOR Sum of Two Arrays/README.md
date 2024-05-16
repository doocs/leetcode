---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1879.Minimum%20XOR%20Sum%20of%20Two%20Arrays/README.md
rating: 2145
source: 第 53 场双周赛 Q4
tags:
    - 位运算
    - 数组
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [1879. 两个数组最小的异或值之和](https://leetcode.cn/problems/minimum-xor-sum-of-two-arrays)

[English Version](/solution/1800-1899/1879.Minimum%20XOR%20Sum%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code> ，它们长度都为 <code>n</code> 。</p>

<p>两个数组的 <strong>异或值之和</strong> 为 <code>(nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1])</code> （<strong>下标从 0 开始</strong>）。</p>

<ul>
	<li>比方说，<code>[1,2,3]</code> 和 <code>[3,2,1]</code> 的 <strong>异或值之和</strong> 等于 <code>(1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4</code> 。</li>
</ul>

<p>请你将 <code>nums2</code> 中的元素重新排列，使得 <strong>异或值之和</strong> <strong>最小</strong> 。</p>

<p>请你返回重新排列之后的 <strong>异或值之和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,2], nums2 = [2,3]
<b>输出：</b>2
<b>解释：</b>将 <code>nums2</code> 重新排列得到 <code>[3,2] 。</code>
异或值之和为 (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,0,3], nums2 = [5,3,4]
<b>输出：</b>8
<b>解释：</b>将 <code>nums2 重新排列得到</code> <code>[5,4,3] 。</code>
异或值之和为 (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩动态规划

我们注意到 $n \leq 14$，因此，我们可以考虑使用状态压缩动态规划的方法求解本题。

我们用一个长度为 $n$ 的二进制数表示当前的状态，第 $i$ 位为 $1$ 表示 $nums2[i]$ 已经被选择，为 $0$ 表示 $nums2[i]$ 还未被选择。

我们定义 $f[i][j]$ 表示 $nums1$ 的前 $i$ 个数中，选择了 $nums2$ 的 $i$ 个数，且当前所选数字的状态为 $j$ 时，数组 $nums1$ 和 $nums2$ 的异或值之和的最小值。初始时 $f[0][0]=0$，其余 $f[i][j]=+\infty$。

我们可以枚举 $nums1$ 的第 $i$ 个数 $x$，然后在 $[0,2^n)$ 的范围内枚举状态 $j$，转移方程为 $f[i][j]=\min(f[i][j],f[i-1][j\oplus 2^k]+(x\oplus nums2[k]))$，其中 $k$ 是 $j$ 的二进制表示中的某个 $1$ 所在的位置。

最后答案为 $f[n][2^n-1]$。

时间复杂度 $O(n^2 \times 2^n)$，空间复杂度 $O(n \times 2^n)$。其中 $n$ 是数组的长度。

我们注意到，状态 $f[i][j]$ 只与 $f[i-1][j\oplus 2^k]$ 有关，因此我们去掉第一维，将空间复杂度优化到 $O(2^n)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumXORSum(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums2)
        f = [[inf] * (1 << n) for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(nums1, 1):
            for j in range(1 << n):
                for k in range(n):
                    if j >> k & 1:
                        f[i][j] = min(f[i][j], f[i - 1][j ^ (1 << k)] + (x ^ nums2[k]))
        return f[-1][-1]
```

```java
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] f = new int[n + 1][1 << n];
        for (var g : f) {
            Arrays.fill(g, 1 << 30);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        f[i][j]
                            = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + (nums1[i - 1] ^ nums2[k]));
                    }
                }
            }
        }
        return f[n][(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minimumXORSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f[n + 1][1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        f[i][j] = min(f[i][j], f[i - 1][j ^ (1 << k)] + (nums1[i - 1] ^ nums2[k]));
                    }
                }
            }
        }
        return f[n][(1 << n) - 1];
    }
};
```

```go
func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 1<<n)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 1<<n; j++ {
			for k := 0; k < n; k++ {
				if j>>k&1 == 1 {
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+(nums1[i-1]^nums2[k]))
				}
			}
		}
	}
	return f[n][(1<<n)-1]
}
```

```ts
function minimumXORSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(1 << n).fill(1 << 30));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + (nums1[i - 1] ^ nums2[k]));
                }
            }
        }
    }
    return f[n][(1 << n) - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：状态压缩动态规划（枚举优化）

我们也可以直接在 $[0, 2^n)$ 范围内枚举状态 $i$，假设 $i$ 的二进制表示中有 $k$ 个 $1$，那么当前枚举的就是 $nums1$ 的第 $k$ 个数，下标为 $k-1$。状态转移方程为 $f[i]=\min(f[i],f[i\oplus 2^j]+(nums1[k-1]\oplus nums2[j]))$，其中 $j$ 是 $i$ 的二进制表示中的某个 $1$ 所在的位置。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumXORSum(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums2)
        f = [inf] * (1 << n)
        f[0] = 0
        for x in nums1:
            for j in range((1 << n) - 1, -1, -1):
                for k in range(n):
                    if j >> k & 1:
                        f[j] = min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]))
        return f[-1]
```

```java
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int x : nums1) {
            for (int j = (1 << n) - 1; j >= 0; --j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        f[j] = Math.min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                    }
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minimumXORSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int x : nums1) {
            for (int j = (1 << n) - 1; ~j; --j) {
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        f[j] = min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                    }
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

```go
func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for _, x := range nums1 {
		for j := (1 << n) - 1; j >= 0; j-- {
			for k := 0; k < n; k++ {
				if j>>k&1 == 1 {
					f[j] = min(f[j], f[j^(1<<k)]+(x^nums2[k]))
				}
			}
		}
	}
	return f[(1<<n)-1]
}
```

```ts
function minimumXORSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (const x of nums1) {
        for (let j = (1 << n) - 1; ~j; --j) {
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    f[j] = Math.min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                }
            }
        }
    }
    return f[(1 << n) - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

```python
class Solution:
    def minimumXORSum(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums2)
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            k = i.bit_count() - 1
            for j in range(n):
                if i >> j & 1:
                    f[i] = min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]))
        return f[-1]
```

```java
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int k = Integer.bitCount(i) - 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minimumXORSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int k = __builtin_popcount(i) - 1;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    f[i] = min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

```go
func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for i := 0; i < 1<<n; i++ {
		k := bits.OnesCount(uint(i)) - 1
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				f[i] = min(f[i], f[i^1<<j]+(nums1[k]^nums2[j]))
			}
		}
	}
	return f[(1<<n)-1]
}
```

```ts
function minimumXORSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        const k = bitCount(i) - 1;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                f[i] = Math.min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]));
            }
        }
    }
    return f[(1 << n) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
