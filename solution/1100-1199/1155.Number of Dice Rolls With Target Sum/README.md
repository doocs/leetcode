# [1155. 掷骰子等于目标和的方法数](https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum)

[English Version](/solution/1100-1199/1155.Number%20of%20Dice%20Rolls%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这里有&nbsp;<code>n</code>&nbsp;个一样的骰子，每个骰子上都有&nbsp;<code>k</code>&nbsp;个面，分别标号为&nbsp;<code>1</code>&nbsp;到 <code>k</code> 。</p>

<p>给定三个整数 <code>n</code> ,&nbsp; <code>k</code> 和&nbsp;<code>target</code>&nbsp;，返回可能的方式(从总共<em>&nbsp;</em><code>k<sup>n</sup></code><em>&nbsp;</em>种方式中)滚动骰子的数量，使正面朝上的数字之和等于<em>&nbsp;</em><code>target</code>&nbsp;。</p>

<p>答案可能很大，你需要对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code> <strong>取模</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 6, target = 3
<strong>输出：</strong>1
<strong>解释：</strong>你扔一个有 6 个面的骰子。
得到 3 的和只有一种方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 6, target = 7
<strong>输出：</strong>6
<strong>解释：</strong>你扔两个骰子，每个骰子有 6 个面。
得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 30, k = 30, target = 500
<strong>输出：</strong>222616187
<strong>解释：</strong>返回的结果必须是对 10<sup>9</sup> + 7 取模。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 30</code></li>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示使用 $i$ 个骰子，和为 $j$ 的方案数。那么我们可以得到状态转移方程：

$$
f[i][j] = \sum_{h=1}^{\min(j, k)} f[i-1][j-h]
$$

其中 $h$ 表示第 $i$ 个骰子的点数。

初始时 $f[0][0] = 1$，最终的答案即为 $f[n][target]$。

时间复杂度 $O(n \times k \times target)$，空间复杂度 $O(n \times target)$。

我们注意到，状态 $f[i][j]$ 只和 $f[i-1][]$ 有关，因此我们可以使用滚动数组的方式，将空间复杂度优化到 $O(target)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numRollsToTarget(self, n: int, k: int, target: int) -> int:
        f = [[0] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 1
        mod = 10**9 + 7
        for i in range(1, n + 1):
            for j in range(1, min(i * k, target) + 1):
                for h in range(1, min(j, k) + 1):
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % mod
        return f[n][target]
```

```python
class Solution:
    def numRollsToTarget(self, n: int, k: int, target: int) -> int:
        f = [1] + [0] * target
        mod = 10**9 + 7
        for i in range(1, n + 1):
            g = [0] * (target + 1)
            for j in range(1, min(i * k, target) + 1):
                for h in range(1, min(j, k) + 1):
                    g[j] = (g[j] + f[j - h]) % mod
            f = g
        return f[target]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(target, i * k); ++j) {
                for (int h = 1; h <= Math.min(j, k); ++h) {
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % mod;
                }
            }
        }
        return f[n][target];
    }
}
```

```java
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int[] g = new int[target + 1];
            for (int j = 1; j <= Math.min(target, i * k); ++j) {
                for (int h = 1; h <= Math.min(j, k); ++h) {
                    g[j] = (g[j] + f[j - h]) % mod;
                }
            }
            f = g;
        }
        return f[target];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numRollsToTarget(int n, int k, int target) {
        const int mod = 1e9 + 7;
        int f[n + 1][target + 1];
        memset(f, 0, sizeof f);
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(target, i * k); ++j) {
                for (int h = 1; h <= min(j, k); ++h) {
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % mod;
                }
            }
        }
        return f[n][target];
    }
};
```

```cpp
class Solution {
public:
    int numRollsToTarget(int n, int k, int target) {
        const int mod = 1e9 + 7;
        vector<int> f(target + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            vector<int> g(target + 1);
            for (int j = 1; j <= min(target, i * k); ++j) {
                for (int h = 1; h <= min(j, k); ++h) {
                    g[j] = (g[j] + f[j - h]) % mod;
                }
            }
            f = move(g);
        }
        return f[target];
    }
};
```

### **Go**

```go
func numRollsToTarget(n int, k int, target int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(target, i*k); j++ {
			for h := 1; h <= min(j, k); h++ {
				f[i][j] = (f[i][j] + f[i-1][j-h]) % mod
			}
		}
	}
	return f[n][target]
}
```

```go
func numRollsToTarget(n int, k int, target int) int {
	const mod int = 1e9 + 7
	f := make([]int, target+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		g := make([]int, target+1)
		for j := 1; j <= min(target, i*k); j++ {
			for h := 1; h <= min(j, k); h++ {
				g[j] = (g[j] + f[j-h]) % mod
			}
		}
		f = g
	}
	return f[target]
}
```

### **TypeScript**

```ts
function numRollsToTarget(n: number, k: number, target: number): number {
    const f = Array.from({ length: n + 1 }, () => Array(target + 1).fill(0));
    f[0][0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(i * k, target); ++j) {
            for (let h = 1; h <= Math.min(j, k); ++h) {
                f[i][j] = (f[i][j] + f[i - 1][j - h]) % mod;
            }
        }
    }
    return f[n][target];
}
```

```ts
function numRollsToTarget(n: number, k: number, target: number): number {
    const f = Array(target + 1).fill(0);
    f[0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        const g = Array(target + 1).fill(0);
        for (let j = 1; j <= Math.min(i * k, target); ++j) {
            for (let h = 1; h <= Math.min(j, k); ++h) {
                g[j] = (g[j] + f[j - h]) % mod;
            }
        }
        f.splice(0, target + 1, ...g);
    }
    return f[target];
}
```

### **Rust**

```rust
impl Solution {
    pub fn num_rolls_to_target(n: i32, k: i32, target: i32) -> i32 {
        let _mod = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let target = target as usize;
        let mut f = vec![vec![0; target + 1]; n + 1];
        f[0][0] = 1;

        for i in 1..=n {
            for j in 1..=target.min(i * k) {
                for h in 1..=j.min(k) {
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % _mod;
                }
            }
        }

        f[n][target]
    }
}
```

```rust
impl Solution {
    pub fn num_rolls_to_target(n: i32, k: i32, target: i32) -> i32 {
        let _mod = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let target = target as usize;
        let mut f = vec![0; target + 1];
        f[0] = 1;

        for i in 1..=n {
            let mut g = vec![0; target + 1];
            for j in 1..=target {
                for h in 1..=j.min(k) {
                    g[j] = (g[j] + f[j - h]) % _mod;
                }
            }
            f = g;
        }

        f[target]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
