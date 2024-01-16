# [1659. Maximize Grid Happiness](https://leetcode.com/problems/maximize-grid-happiness)

[中文文档](/solution/1600-1699/1659.Maximize%20Grid%20Happiness/README.md)

## Description

<p>You are given four integers, <code>m</code>, <code>n</code>, <code>introvertsCount</code>, and <code>extrovertsCount</code>. You have an <code>m x n</code> grid, and there are two types of people: introverts and extroverts. There are <code>introvertsCount</code> introverts and <code>extrovertsCount</code> extroverts.</p>

<p>You should decide how many people you want to live in the grid and assign each of them one grid cell. Note that you <strong>do not</strong> have to have all the people living in the grid.</p>

<p>The <strong>happiness</strong> of each person is calculated as follows:</p>

<ul>
	<li>Introverts <strong>start</strong> with <code>120</code> happiness and <strong>lose</strong> <code>30</code> happiness for each neighbor (introvert or extrovert).</li>
	<li>Extroverts <strong>start</strong> with <code>40</code> happiness and <strong>gain</strong> <code>20</code> happiness for each neighbor (introvert or extrovert).</li>
</ul>

<p>Neighbors live in the directly adjacent cells north, east, south, and west of a person&#39;s cell.</p>

<p>The <strong>grid happiness</strong> is the <strong>sum</strong> of each person&#39;s happiness. Return<em> the <strong>maximum possible grid happiness</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1659.Maximize%20Grid%20Happiness/images/grid_happiness.png" style="width: 261px; height: 121px;" />
<pre>
<strong>Input:</strong> m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
<strong>Output:</strong> 240
<strong>Explanation:</strong> Assume the grid is 1-indexed with coordinates (row, column).
We can put the introvert in cell (1,1) and put the extroverts in cells (1,3) and (2,3).
- Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0 neighbors) = 120
- Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
- Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
The grid happiness is 120 + 60 + 60 = 240.
The above figure shows the grid in this example with each person&#39;s happiness. The introvert stays in the light green cell while the extroverts live on the light purple cells.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
<strong>Output:</strong> 260
<strong>Explanation:</strong> Place the two introverts in (1,1) and (3,1) and the extrovert at (2,1).
- Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
- Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2 neighbors) = 80
- Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
The grid happiness is 90 + 80 + 90 = 260.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
<strong>Output:</strong> 240
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 5</code></li>
	<li><code>0 &lt;= introvertsCount, extrovertsCount &lt;= min(m * n, 6)</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def getMaxGridHappiness(
        self, m: int, n: int, introvertsCount: int, extrovertsCount: int
    ) -> int:
        @cache
        def dfs(i: int, pre: int, ic: int, ec: int) -> int:
            if i == m or (ic == 0 and ec == 0):
                return 0
            ans = 0
            for cur in range(mx):
                if ix[cur] <= ic and ex[cur] <= ec:
                    a = f[cur] + g[pre][cur]
                    b = dfs(i + 1, cur, ic - ix[cur], ec - ex[cur])
                    ans = max(ans, a + b)
            return ans

        mx = pow(3, n)
        f = [0] * mx
        g = [[0] * mx for _ in range(mx)]
        h = [[0, 0, 0], [0, -60, -10], [0, -10, 40]]
        bits = [[0] * n for _ in range(mx)]
        ix = [0] * mx
        ex = [0] * mx
        for i in range(mx):
            mask = i
            for j in range(n):
                mask, x = divmod(mask, 3)
                bits[i][j] = x
                if x == 1:
                    ix[i] += 1
                    f[i] += 120
                elif x == 2:
                    ex[i] += 1
                    f[i] += 40
                if j:
                    f[i] += h[x][bits[i][j - 1]]
        for i in range(mx):
            for j in range(mx):
                for k in range(n):
                    g[i][j] += h[bits[i][k]][bits[j][k]]
        return dfs(0, 0, introvertsCount, extrovertsCount)
```

```java
class Solution {
    private int m;
    private int mx;
    private int[] f;
    private int[][] g;
    private int[][] bits;
    private int[] ix;
    private int[] ex;
    private Integer[][][][] memo;
    private final int[][] h = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        mx = (int) Math.pow(3, n);
        f = new int[mx];
        g = new int[mx][mx];
        bits = new int[mx][n];
        ix = new int[mx];
        ex = new int[mx];
        memo = new Integer[m][mx][introvertsCount + 1][extrovertsCount + 1];
        for (int i = 0; i < mx; ++i) {
            int mask = i;
            for (int j = 0; j < n; ++j) {
                int x = mask % 3;
                mask /= 3;
                bits[i][j] = x;
                if (x == 1) {
                    ix[i]++;
                    f[i] += 120;
                } else if (x == 2) {
                    ex[i]++;
                    f[i] += 40;
                }
                if (j > 0) {
                    f[i] += h[x][bits[i][j - 1]];
                }
            }
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < mx; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += h[bits[i][k]][bits[j][k]];
                }
            }
        }
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }

    private int dfs(int i, int pre, int ic, int ec) {
        if (i == m || (ic == 0 && ec == 0)) {
            return 0;
        }
        if (memo[i][pre][ic][ec] != null) {
            return memo[i][pre][ic][ec];
        }
        int ans = 0;
        for (int cur = 0; cur < mx; ++cur) {
            if (ix[cur] <= ic && ex[cur] <= ec) {
                ans = Math.max(
                    ans, f[cur] + g[pre][cur] + dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]));
            }
        }
        return memo[i][pre][ic][ec] = ans;
    }
}
```

```cpp
class Solution {
public:
    int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int mx = pow(3, n);
        int f[mx];
        int g[mx][mx];
        int bits[mx][n];
        int ix[mx];
        int ex[mx];
        int memo[m][mx][introvertsCount + 1][extrovertsCount + 1];
        int h[3][3] = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
        memset(f, 0, sizeof(f));
        memset(g, 0, sizeof(g));
        memset(bits, 0, sizeof(bits));
        memset(ix, 0, sizeof(ix));
        memset(ex, 0, sizeof(ex));
        memset(memo, -1, sizeof(memo));
        for (int i = 0; i < mx; ++i) {
            int mask = i;
            for (int j = 0; j < n; ++j) {
                int x = mask % 3;
                mask /= 3;
                bits[i][j] = x;
                if (x == 1) {
                    ix[i]++;
                    f[i] += 120;
                } else if (x == 2) {
                    ex[i]++;
                    f[i] += 40;
                }
                if (j) {
                    f[i] += h[x][bits[i][j - 1]];
                }
            }
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < mx; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += h[bits[i][k]][bits[j][k]];
                }
            }
        }
        function<int(int, int, int, int)> dfs = [&](int i, int pre, int ic, int ec) {
            if (i == m || (ic == 0 && ec == 0)) {
                return 0;
            }
            if (memo[i][pre][ic][ec] != -1) {
                return memo[i][pre][ic][ec];
            }
            int ans = 0;
            for (int cur = 0; cur < mx; ++cur) {
                if (ix[cur] <= ic && ex[cur] <= ec) {
                    ans = max(ans, f[cur] + g[pre][cur] + dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]));
                }
            }
            return memo[i][pre][ic][ec] = ans;
        };
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }
};
```

```go
func getMaxGridHappiness(m int, n int, introvertsCount int, extrovertsCount int) int {
	mx := int(math.Pow(3, float64(n)))
	f := make([]int, mx)
	g := make([][]int, mx)
	h := [3][3]int{{0, 0, 0}, {0, -60, -10}, {0, -10, 40}}
	bits := make([][]int, mx)
	ix := make([]int, mx)
	ex := make([]int, mx)
	memo := make([][][][]int, m)
	for i := range g {
		g[i] = make([]int, mx)
		bits[i] = make([]int, n)
	}
	for i := range memo {
		memo[i] = make([][][]int, mx)
		for j := range memo[i] {
			memo[i][j] = make([][]int, introvertsCount+1)
			for k := range memo[i][j] {
				memo[i][j][k] = make([]int, extrovertsCount+1)
				for l := range memo[i][j][k] {
					memo[i][j][k][l] = -1
				}
			}
		}
	}
	for i := 0; i < mx; i++ {
		mask := i
		for j := 0; j < n; j++ {
			x := mask % 3
			mask /= 3
			bits[i][j] = x
			if x == 1 {
				ix[i]++
				f[i] += 120
			} else if x == 2 {
				ex[i]++
				f[i] += 40
			}
			if j > 0 {
				f[i] += h[x][bits[i][j-1]]
			}
		}
	}
	for i := 0; i < mx; i++ {
		for j := 0; j < mx; j++ {
			for k := 0; k < n; k++ {
				g[i][j] += h[bits[i][k]][bits[j][k]]
			}
		}
	}
	var dfs func(int, int, int, int) int
	dfs = func(i, pre, ic, ec int) int {
		if i == m || (ic == 0 && ec == 0) {
			return 0
		}
		if memo[i][pre][ic][ec] != -1 {
			return memo[i][pre][ic][ec]
		}
		ans := 0
		for cur := 0; cur < mx; cur++ {
			if ix[cur] <= ic && ex[cur] <= ec {
				ans = max(ans, f[cur]+g[pre][cur]+dfs(i+1, cur, ic-ix[cur], ec-ex[cur]))
			}
		}
		memo[i][pre][ic][ec] = ans
		return ans
	}
	return dfs(0, 0, introvertsCount, extrovertsCount)
}
```

```ts
function getMaxGridHappiness(
    m: number,
    n: number,
    introvertsCount: number,
    extrovertsCount: number,
): number {
    const mx = 3 ** n;
    const f: number[] = Array(mx).fill(0);
    const g: number[][] = Array(mx)
        .fill(0)
        .map(() => Array(mx).fill(0));
    const h: number[][] = [
        [0, 0, 0],
        [0, -60, -10],
        [0, -10, 40],
    ];
    const bits: number[][] = Array(mx)
        .fill(0)
        .map(() => Array(n).fill(0));
    const ix: number[] = Array(mx).fill(0);
    const ex: number[] = Array(mx).fill(0);
    const memo: number[][][][] = Array(m)
        .fill(0)
        .map(() =>
            Array(mx)
                .fill(0)
                .map(() =>
                    Array(introvertsCount + 1)
                        .fill(0)
                        .map(() => Array(extrovertsCount + 1).fill(-1)),
                ),
        );
    for (let i = 0; i < mx; ++i) {
        let mask = i;
        for (let j = 0; j < n; ++j) {
            const x = mask % 3;
            mask = Math.floor(mask / 3);
            bits[i][j] = x;
            if (x === 1) {
                ix[i] += 1;
                f[i] += 120;
            } else if (x === 2) {
                ex[i] += 1;
                f[i] += 40;
            }
            if (j > 0) {
                f[i] += h[x][bits[i][j - 1]];
            }
        }
    }
    for (let i = 0; i < mx; ++i) {
        for (let j = 0; j < mx; ++j) {
            for (let k = 0; k < n; ++k) {
                g[i][j] += h[bits[i][k]][bits[j][k]];
            }
        }
    }
    const dfs = (i: number, pre: number, ic: number, ec: number): number => {
        if (i === m || (ic === 0 && ec === 0)) {
            return 0;
        }
        if (memo[i][pre][ic][ec] !== -1) {
            return memo[i][pre][ic][ec];
        }
        let ans = 0;
        for (let cur = 0; cur < mx; ++cur) {
            if (ix[cur] <= ic && ex[cur] <= ec) {
                const a = f[cur] + g[pre][cur];
                const b = dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]);
                ans = Math.max(ans, a + b);
            }
        }
        return (memo[i][pre][ic][ec] = ans);
    };
    return dfs(0, 0, introvertsCount, extrovertsCount);
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def getMaxGridHappiness(
        self, m: int, n: int, introvertsCount: int, extrovertsCount: int
    ) -> int:
        @cache
        def dfs(pos: int, pre: int, ic: int, ec: int) -> int:
            if pos == m * n or (ic == 0 and ec == 0):
                return 0
            ans = 0
            up = pre // p
            left = 0 if pos % n == 0 else pre % 3
            for i in range(3):
                if (i == 1 and ic == 0) or (i == 2 and ec == 0):
                    continue
                cur = pre % p * 3 + i
                a = h[up][i] + h[left][i]
                b = dfs(pos + 1, cur, ic - (i == 1), ec - (i == 2))
                c = 0
                if i == 1:
                    c = 120
                elif i == 2:
                    c = 40
                ans = max(ans, a + b + c)
            return ans

        p = pow(3, n - 1)
        h = [[0, 0, 0], [0, -60, -10], [0, -10, 40]]
        return dfs(0, 0, introvertsCount, extrovertsCount)
```

```java
class Solution {
    private int m;
    private int n;
    private int p;
    private final int[][] h = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
    private Integer[][][][] memo;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        p = (int) Math.pow(3, n - 1);
        memo = new Integer[m * n][p * 3][introvertsCount + 1][extrovertsCount + 1];
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }

    private int dfs(int pos, int pre, int ic, int ec) {
        if (pos == m * n || (ic == 0 && ec == 0)) {
            return 0;
        }
        if (memo[pos][pre][ic][ec] != null) {
            return memo[pos][pre][ic][ec];
        }
        int ans = 0;
        int up = pre / p;
        int left = pos % n == 0 ? 0 : pre % 3;
        for (int i = 0; i < 3; ++i) {
            if (i == 1 && (ic == 0) || (i == 2 && ec == 0)) {
                continue;
            }
            int cur = pre % p * 3 + i;
            int a = h[up][i] + h[left][i];
            int b = dfs(pos + 1, cur, ic - (i == 1 ? 1 : 0), ec - (i == 2 ? 1 : 0));
            int c = i == 1 ? 120 : (i == 2 ? 40 : 0);
            ans = Math.max(ans, a + b + c);
        }
        return memo[pos][pre][ic][ec] = ans;
    }
}
```

```cpp
class Solution {
public:
    int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int h[3][3] = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
        int p = pow(3, n - 1);
        int memo[m * n][p * 3][introvertsCount + 1][extrovertsCount + 1];
        memset(memo, -1, sizeof(memo));
        function<int(int, int, int, int)> dfs = [&](int pos, int pre, int ic, int ec) {
            if (pos == m * n || (ic == 0 && ec == 0)) {
                return 0;
            }
            if (memo[pos][pre][ic][ec] != -1) {
                return memo[pos][pre][ic][ec];
            }
            int ans = 0;
            int up = pre / p;
            int left = pos % n == 0 ? 0 : pre % 3;
            for (int i = 0; i < 3; ++i) {
                if ((i == 1 && ic == 0) || (i == 2 && ec == 0)) {
                    continue;
                }
                int cur = pre % p * 3 + i;
                int a = h[up][i] + h[left][i];
                int b = dfs(pos + 1, cur, ic - (i == 1), ec - (i == 2));
                int c = i == 1 ? 120 : (i == 2 ? 40 : 0);
                ans = max(ans, a + b + c);
            }
            return memo[pos][pre][ic][ec] = ans;
        };
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }
};
```

```go
func getMaxGridHappiness(m int, n int, introvertsCount int, extrovertsCount int) int {
	p := int(math.Pow(3, float64(n-1)))
	h := [3][3]int{{0, 0, 0}, {0, -60, -10}, {0, -10, 40}}
	memo := make([][][][]int, m*n)
	for i := range memo {
		memo[i] = make([][][]int, p*3)
		for j := range memo[i] {
			memo[i][j] = make([][]int, introvertsCount+1)
			for k := range memo[i][j] {
				memo[i][j][k] = make([]int, extrovertsCount+1)
				for l := range memo[i][j][k] {
					memo[i][j][k][l] = -1
				}
			}
		}
	}
	var dfs func(int, int, int, int) int
	dfs = func(pos, pre, ic, ec int) int {
		if pos == m*n || (ic == 0 && ec == 0) {
			return 0
		}
		if memo[pos][pre][ic][ec] != -1 {
			return memo[pos][pre][ic][ec]
		}
		ans := 0
		up := pre / p
		left := pre % 3
		if pos%n == 0 {
			left = 0
		}
		for i := 0; i < 3; i++ {
			if (i == 1 && ic == 0) || (i == 2 && ec == 0) {
				continue
			}
			cur := pre%p*3 + i
			nic, nec := ic, ec
			c := 0
			if i == 1 {
				nic--
				c = 120
			} else if i == 2 {
				nec--
				c = 40
			}
			a := h[up][i] + h[left][i]
			b := dfs(pos+1, cur, nic, nec)
			ans = max(ans, a+b+c)
		}
		memo[pos][pre][ic][ec] = ans
		return ans
	}
	return dfs(0, 0, introvertsCount, extrovertsCount)
}
```

```ts
function getMaxGridHappiness(
    m: number,
    n: number,
    introvertsCount: number,
    extrovertsCount: number,
): number {
    const p = 3 ** (n - 1);
    const h: number[][] = [
        [0, 0, 0],
        [0, -60, -10],
        [0, -10, 40],
    ];
    const memo: number[][][][] = Array(m * n)
        .fill(0)
        .map(() =>
            Array(p * 3)
                .fill(0)
                .map(() =>
                    Array(introvertsCount + 1)
                        .fill(0)
                        .map(() => Array(extrovertsCount + 1).fill(-1)),
                ),
        );
    const dfs = (pos: number, pre: number, ic: number, ec: number): number => {
        if (pos === m * n || (ic === 0 && ec === 0)) {
            return 0;
        }
        if (memo[pos][pre][ic][ec] !== -1) {
            return memo[pos][pre][ic][ec];
        }
        let ans = 0;
        const up = Math.floor(pre / p);
        const left = pos % n == 0 ? 0 : pre % 3;
        for (let i = 0; i < 3; ++i) {
            if ((i === 1 && ic === 0) || (i === 2 && ec === 0)) {
                continue;
            }
            const cur = (pre % p) * 3 + i;
            const a = h[up][i] + h[left][i];
            const nic = i === 1 ? ic - 1 : ic;
            const nec = i === 2 ? ec - 1 : ec;
            const b = dfs(pos + 1, cur, nic, nec);
            const c = i === 1 ? 120 : i === 2 ? 40 : 0;
            ans = Math.max(ans, a + b + c);
        }
        return (memo[pos][pre][ic][ec] = ans);
    };
    return dfs(0, 0, introvertsCount, extrovertsCount);
}
```

<!-- tabs:end -->

<!-- end -->
