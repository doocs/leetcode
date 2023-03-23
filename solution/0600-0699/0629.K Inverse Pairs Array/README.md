# [629. K 个逆序对数组](https://leetcode.cn/problems/k-inverse-pairs-array)

[English Version](/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出两个整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>k</code>，找出所有包含从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;的数字，且恰好拥有&nbsp;<code>k</code>&nbsp;个逆序对的不同的数组的个数。</p>

<p>逆序对的定义如下：对于数组的第<code>i</code>个和第&nbsp;<code>j</code>个元素，如果满<code>i</code>&nbsp;&lt;&nbsp;<code>j</code>且&nbsp;<code>a[i]</code>&nbsp;&gt;&nbsp;<code>a[j]</code>，则其为一个逆序对；否则不是。</p>

<p>由于答案可能很大，只需要返回 答案 mod 10<sup>9</sup>&nbsp;+ 7 的值。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 3, k = 0
<strong>输出:</strong> 1
<strong>解释:</strong> 
只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 3, k = 1
<strong>输出:</strong> 2
<strong>解释:</strong> 
数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>&nbsp;<code>n</code>&nbsp;的范围是 [1, 1000] 并且 <code>k</code> 的范围是 [0, 1000]。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 前缀和**

我们定义 $f[i][j]$ 表示数组长度为 $i$，逆序对数为 $j$ 的数组个数。初始时 $f[0][0] = 1$，其余 $f[i][j] = 0$。

接下来我们考虑如何得到 $f[i][j]$。

假设前 $i-1$ 个数已经确定，现在要插入数字 $i$，我们讨论 $i$ 插入到每个位置的情况：

-   如果 $i$ 插入到第 $1$ 个位置，那么逆序对增加了 $i-1$ 个，所以 $f[i][j]+=f[i-1][j-(i-1)]$。
-   如果 $i$ 插入到第 $2$ 个位置，那么逆序对增加了 $i-2$ 个，所以 $f[i][j]+=f[i-1][j-(i-2)]$。
-   ...
-   如果 $i$ 插入到第 $i-1$ 个位置，那么逆序对增加了 $1$ 个，所以 $f[i][j]+=f[i-1][j-1]$。
-   如果 $i$ 插入到第 $i$ 个位置，那么逆序对不变，所以 $f[i][j]+=f[i-1][j]$。

所以 $f[i][j]=\sum_{k=1}^{i}f[i-1][j-(i-k)]$。

我们注意到 $f[i][j]$ 的计算实际上涉及到前缀和，因此，我们可以使用前缀和优化计算过程。并且，由于 $f[i][j]$ 只与 $f[i-1][j]$ 有关，因此我们可以用一个一维数组来优化空间复杂度。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k)$。其中 $n$ 和 $k$ 分别为数组长度和逆序对数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        s = [0] * (k + 2)
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                f[j] = (s[j + 1] - s[max(0, j - (i - 1))]) % mod
            for j in range(1, k + 2):
                s[j] = (s[j - 1] + f[j - 1]) % mod
        return f[k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int kInversePairs(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        int[] s = new int[k + 2];
        f[0] = 1;
        Arrays.fill(s, 1);
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[Math.max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kInversePairs(int n, int k) {
        int f[k + 1];
        int s[k + 2];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        fill(s, s + k + 2, 1);
        s[0] = 0;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
};
```

### **Go**

```go
func kInversePairs(n int, k int) int {
	f := make([]int, k+1)
	s := make([]int, k+2)
	f[0] = 1
	for i, x := range f {
		s[i+1] = s[i] + x
	}
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[j] = (s[j+1] - s[max(0, j-(i-1))] + mod) % mod
		}
		for j := 1; j <= k+1; j++ {
			s[j] = (s[j-1] + f[j-1]) % mod
		}
	}
	return f[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function kInversePairs(n: number, k: number): number {
    const f: number[] = new Array(k + 1).fill(0);
    f[0] = 1;
    const s: number[] = new Array(k + 2).fill(1);
    s[0] = 0;
    const mod: number = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[j] = (s[j + 1] - s[Math.max(0, j - (i - 1))] + mod) % mod;
        }
        for (let j = 1; j <= k + 1; ++j) {
            s[j] = (s[j - 1] + f[j - 1]) % mod;
        }
    }
    return f[k];
}
```

### **...**

```

```

<!-- tabs:end -->
