---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0799.Champagne%20Tower/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [799. Champagne Tower](https://leetcode.com/problems/champagne-tower)

[中文文档](/solution/0700-0799/0799.Champagne%20Tower/README.md)

## Description

<!-- description:start -->

<p>We stack glasses in a pyramid, where the <strong>first</strong> row has <code>1</code> glass, the <strong>second</strong> row has <code>2</code> glasses, and so on until the 100<sup>th</sup> row.&nbsp; Each glass holds one cup&nbsp;of champagne.</p>

<p>Then, some champagne is poured into the first glass at the top.&nbsp; When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.&nbsp; When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.&nbsp; (A glass at the bottom row has its excess champagne fall on the floor.)</p>

<p>For example, after one cup of champagne is poured, the top most glass is full.&nbsp; After two cups of champagne are poured, the two glasses on the second row are half full.&nbsp; After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.&nbsp; After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0799.Champagne%20Tower/images/tower.png" style="height: 241px; width: 350px;" /></p>

<p>Now after pouring some non-negative integer cups of champagne, return how full the <code>j<sup>th</sup></code> glass in the <code>i<sup>th</sup></code> row is (both <code>i</code> and <code>j</code> are 0-indexed.)</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> poured = 1, query_row = 1, query_glass = 1

<strong>Output:</strong> 0.00000

<strong>Explanation:</strong> We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> poured = 2, query_row = 1, query_glass = 1

<strong>Output:</strong> 0.50000

<strong>Explanation:</strong> We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> poured = 100000009, query_row = 33, query_glass = 17

<strong>Output:</strong> 1.00000

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>0 &lt;=&nbsp;poured &lt;= 10<sup>9</sup></code></li>

    <li><code>0 &lt;= query_glass &lt;= query_row&nbsp;&lt; 100</code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We directly simulate the process of pouring champagne.

Define a 2D array $f$, where $f[i][j]$ represents the amount of champagne in the $j$-th glass of the $i$-th layer. Initially, $f[0][0] = poured$.

For each layer, if the amount of champagne in the current glass $f[i][j]$ is greater than $1$, the champagne will flow to two glasses in the next layer. The amount flowing in is $\frac{f[i][j]-1}{2}$, which is the amount of champagne in the current glass minus $1$ and then divided by $2$. Then the amount of champagne in the current glass is updated to $1$.

After the simulation ends, return $f[query\_row][query\_glass]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$, where $n$ is the number of layers, i.e., $\text{query\_row}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        f = [[0] * 101 for _ in range(101)]
        f[0][0] = poured
        for i in range(query_row + 1):
            for j in range(i + 1):
                if f[i][j] > 1:
                    half = (f[i][j] - 1) / 2
                    f[i][j] = 1
                    f[i + 1][j] += half
                    f[i + 1][j + 1] += half
        return f[query_row][query_glass]
```

#### Java

```java
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] f = new double[101][101];
        f[0][0] = poured;
        for (int i = 0; i <= query_row; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (f[i][j] > 1) {
                    double half = (f[i][j] - 1) / 2.0;
                    f[i][j] = 1;
                    f[i + 1][j] += half;
                    f[i + 1][j + 1] += half;
                }
            }
        }
        return f[query_row][query_glass];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        double f[101][101] = {0.0};
        f[0][0] = poured;
        for (int i = 0; i <= query_row; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (f[i][j] > 1) {
                    double half = (f[i][j] - 1) / 2.0;
                    f[i][j] = 1;
                    f[i + 1][j] += half;
                    f[i + 1][j + 1] += half;
                }
            }
        }
        return f[query_row][query_glass];
    }
};
```

#### Go

```go
func champagneTower(poured int, query_row int, query_glass int) float64 {
	f := [101][101]float64{}
	f[0][0] = float64(poured)
	for i := 0; i <= query_row; i++ {
		for j := 0; j <= i; j++ {
			if f[i][j] > 1 {
				half := (f[i][j] - 1) / 2.0
				f[i][j] = 1
				f[i+1][j] += half
				f[i+1][j+1] += half
			}
		}
	}
	return f[query_row][query_glass]
}
```

#### TypeScript

```ts
function champagneTower(poured: number, query_row: number, query_glass: number): number {
    const f: number[][] = Array.from({ length: 101 }, () => Array(101).fill(0));
    f[0][0] = poured;
    for (let i = 0; i <= query_row; ++i) {
        for (let j = 0; j <= i; ++j) {
            if (f[i][j] > 1) {
                const half = (f[i][j] - 1) / 2.0;
                f[i][j] = 1;
                f[i + 1][j] += half;
                f[i + 1][j + 1] += half;
            }
        }
    }
    return f[query_row][query_glass];
}
```

#### Rust

```rust
impl Solution {
    pub fn champagne_tower(poured: i32, query_row: i32, query_glass: i32) -> f64 {
        let mut f = vec![vec![0.0; 101]; 101];
        f[0][0] = poured as f64;
        for i in 0..=query_row as usize {
            for j in 0..=i {
                if f[i][j] > 1.0 {
                    let half = (f[i][j] - 1.0) / 2.0;
                    f[i][j] = 1.0;
                    f[i + 1][j] += half;
                    f[i + 1][j + 1] += half;
                }
            }
        }
        f[query_row as usize][query_glass as usize]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Simulation (Space Optimization)

Since the amount of champagne in each layer only depends on the amount in the previous layer, we can use a rolling array approach to optimize space complexity, converting the 2D array to a 1D array.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the number of layers, i.e., $\text{query\_row}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        f = [poured]
        for i in range(1, query_row + 1):
            g = [0] * (i + 1)
            for j, v in enumerate(f):
                if v > 1:
                    half = (v - 1) / 2
                    g[j] += half
                    g[j + 1] += half
            f = g
        return min(1, f[query_glass])
```

#### Java

```java
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] f = {poured};
        for (int i = 1; i <= query_row; ++i) {
            double[] g = new double[i + 1];
            for (int j = 0; j < i; ++j) {
                if (f[j] > 1) {
                    double half = (f[j] - 1) / 2.0;
                    g[j] += half;
                    g[j + 1] += half;
                }
            }
            f = g;
        }
        return Math.min(1, f[query_glass]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        double f[101] = {(double) poured};
        double g[101];
        for (int i = 1; i <= query_row; ++i) {
            memset(g, 0, sizeof g);
            for (int j = 0; j < i; ++j) {
                if (f[j] > 1) {
                    double half = (f[j] - 1) / 2.0;
                    g[j] += half;
                    g[j + 1] += half;
                }
            }
            memcpy(f, g, sizeof g);
        }
        return min(1.0, f[query_glass]);
    }
};
```

#### Go

```go
func champagneTower(poured int, query_row int, query_glass int) float64 {
	f := []float64{float64(poured)}
	for i := 1; i <= query_row; i++ {
		g := make([]float64, i+1)
		for j, v := range f {
			if v > 1 {
				half := (v - 1) / 2.0
				g[j] += half
				g[j+1] += half
			}
		}
		f = g
	}
	return math.Min(1, f[query_glass])
}
```

#### TypeScript

```ts
function champagneTower(poured: number, query_row: number, query_glass: number): number {
    let f: number[] = [poured];
    for (let i = 1; i <= query_row; ++i) {
        const g: number[] = new Array(i + 1).fill(0);
        for (let j = 0; j < i; ++j) {
            if (f[j] > 1) {
                const half = (f[j] - 1) / 2.0;
                g[j] += half;
                g[j + 1] += half;
            }
        }
        f = g;
    }
    return Math.min(1, f[query_glass]);
}
```

#### Rust

```rust
impl Solution {
    pub fn champagne_tower(poured: i32, query_row: i32, query_glass: i32) -> f64 {
        let mut f = vec![poured as f64];
        for i in 1..=query_row {
            let mut g = vec![0.0; (i + 1) as usize];
            for j in 0..i as usize {
                if f[j] > 1.0 {
                    let half = (f[j] - 1.0) / 2.0;
                    g[j] += half;
                    g[j + 1] += half;
                }
            }
            f = g;
        }
        f[query_glass as usize].min(1.0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
