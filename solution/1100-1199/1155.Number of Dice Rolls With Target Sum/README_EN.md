# [1155. Number of Dice Rolls With Target Sum](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum)

[中文文档](/solution/1100-1199/1155.Number%20of%20Dice%20Rolls%20With%20Target%20Sum/README.md)

## Description

<p>You have <code>n</code> dice, and each die has <code>k</code> faces numbered from <code>1</code> to <code>k</code>.</p>

<p>Given three integers <code>n</code>, <code>k</code>, and <code>target</code>, return <em>the number of possible ways (out of the </em><code>k<sup>n</sup></code><em> total ways) </em><em>to roll the dice, so the sum of the face-up numbers equals </em><code>target</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 6, target = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> You throw one die with 6 faces.
There is only one way to get a sum of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 6, target = 7
<strong>Output:</strong> 6
<strong>Explanation:</strong> You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 30, k = 30, target = 500
<strong>Output:</strong> 222616187
<strong>Explanation:</strong> The answer must be returned modulo 10<sup>9</sup> + 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 30</code></li>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
</ul>

## Solutions

**Solution 1: Dynamic Programming**

We define $f[i][j]$ as the number of ways to get a sum of $j$ using $i$ dice. Then, we can obtain the following state transition equation:

$$
f[i][j] = \sum_{h=1}^{\min(j, k)} f[i-1][j-h]
$$

where $h$ represents the number of points on the $i$-th die.

Initially, we have $f[0][0] = 1$, and the final answer is $f[n][target]$.

The time complexity is $O(n \times k \times target)$, and the space complexity is $O(n \times target)$.

We notice that the state $f[i][j]$ only depends on $f[i-1][]$, so we can use a rolling array to optimize the space complexity to $O(target)$.

<!-- tabs:start -->

### **Python3**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
