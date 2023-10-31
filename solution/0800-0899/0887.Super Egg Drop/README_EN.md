# [887. Super Egg Drop](https://leetcode.com/problems/super-egg-drop)

[中文文档](/solution/0800-0899/0887.Super%20Egg%20Drop/README.md)

## Description

<p>You are given <code>k</code> identical eggs and you have access to a building with <code>n</code> floors labeled from <code>1</code> to <code>n</code>.</p>

<p>You know that there exists a floor <code>f</code> where <code>0 &lt;= f &lt;= n</code> such that any egg dropped at a floor <strong>higher</strong> than <code>f</code> will <strong>break</strong>, and any egg dropped <strong>at or below</strong> floor <code>f</code> will <strong>not break</strong>.</p>

<p>Each move, you may take an unbroken egg and drop it from any floor <code>x</code> (where <code>1 &lt;= x &lt;= n</code>). If the egg breaks, you can no longer use it. However, if the egg does not break, you may <strong>reuse</strong> it in future moves.</p>

<p>Return <em>the <strong>minimum number of moves</strong> that you need to determine <strong>with certainty</strong> what the value of </em><code>f</code> is.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 1, n = 2
<strong>Output:</strong> 2
<strong>Explanation: </strong>
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 2, n = 6
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 3, n = 14
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i < 1:
                return 0
            if j == 1:
                return i
            l, r = 1, i
            while l < r:
                mid = (l + r + 1) >> 1
                a = dfs(mid - 1, j - 1)
                b = dfs(i - mid, j)
                if a <= b:
                    l = mid
                else:
                    r = mid - 1
            return max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1

        return dfs(n, k)
```

```python
class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            f[i][1] = i
        for i in range(1, n + 1):
            for j in range(2, k + 1):
                l, r = 1, i
                while l < r:
                    mid = (l + r + 1) >> 1
                    a, b = f[mid - 1][j - 1], f[i - mid][j]
                    if a <= b:
                        l = mid
                    else:
                        r = mid - 1
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1
        return f[n][k]
```

### **Java**

```java
class Solution {
    private int[][] f;

    public int superEggDrop(int k, int n) {
        f = new int[n + 1][k + 1];
        return dfs(n, k);
    }

    private int dfs(int i, int j) {
        if (i < 1) {
            return 0;
        }
        if (j == 1) {
            return i;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int l = 1, r = i;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int a = dfs(mid - 1, j - 1);
            int b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1;
    }
}
```

```java
class Solution {
    public int superEggDrop(int k, int n) {
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i < 1) {
                return 0;
            }
            if (j == 1) {
                return i;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int l = 1, r = i;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                int a = dfs(mid - 1, j - 1);
                int b = dfs(i - mid, j);
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return f[i][j] = max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1;
        };
        return dfs(n, k);
    }
};
```

```cpp
class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
};
```

### **Go**

```go
func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 1 {
			return 0
		}
		if j == 1 {
			return i
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		l, r := 1, i
		for l < r {
			mid := (l + r + 1) >> 1
			a, b := dfs(mid-1, j-1), dfs(i-mid, j)
			if a <= b {
				l = mid
			} else {
				r = mid - 1
			}
		}
		f[i][j] = max(dfs(l-1, j-1), dfs(i-l, j)) + 1
		return f[i][j]
	}
	return dfs(n, k)
}
```

```go
func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 2; j <= k; j++ {
			l, r := 1, i
			for l < r {
				mid := (l + r + 1) >> 1
				a, b := f[mid-1][j-1], f[i-mid][j]
				if a <= b {
					l = mid
				} else {
					r = mid - 1
				}
			}
			f[i][j] = max(f[l-1][j-1], f[i-l][j]) + 1
		}
	}
	return f[n][k]
}
```

### **TypeScript**

```ts
function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i < 1) {
            return 0;
        }
        if (j === 1) {
            return i;
        }
        if (f[i][j]) {
            return f[i][j];
        }
        let l = 1;
        let r = i;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            const a = dfs(mid - 1, j - 1);
            const b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1);
    };
    return dfs(n, k);
}
```

```ts
function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 2; j <= k; ++j) {
            let l = 1;
            let r = i;
            while (l < r) {
                const mid = (l + r + 1) >> 1;
                const a = f[mid - 1][j - 1];
                const b = f[i - mid][j];
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
        }
    }
    return f[n][k];
}
```

### **...**

```

```

<!-- tabs:end -->
