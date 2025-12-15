---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/README_EN.md
rating: 2105
source: Biweekly Contest 74 Q4
tags:
    - String
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [2209. Minimum White Tiles After Covering With Carpets](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets)

[中文文档](/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed binary</strong> string <code>floor</code>, which represents the colors of tiles on a floor:</p>

<ul>
	<li><code>floor[i] = &#39;0&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>black</strong>.</li>
	<li>On the other hand, <code>floor[i] = &#39;1&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>white</strong>.</li>
</ul>

<p>You are also given <code>numCarpets</code> and <code>carpetLen</code>. You have <code>numCarpets</code> <strong>black</strong> carpets, each of length <code>carpetLen</code> tiles. Cover the tiles with the given carpets such that the number of <strong>white</strong> tiles still visible is <strong>minimum</strong>. Carpets may overlap one another.</p>

<p>Return <em>the <strong>minimum</strong> number of white tiles still visible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex1-1.png" style="width: 400px; height: 73px;" />
<pre>
<strong>Input:</strong> floor = &quot;10110101&quot;, numCarpets = 2, carpetLen = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex2.png" style="width: 353px; height: 123px;" />
<pre>
<strong>Input:</strong> floor = &quot;11111&quot;, numCarpets = 2, carpetLen = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
Note that the carpets are able to overlap one another.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= carpetLen &lt;= floor.length &lt;= 1000</code></li>
	<li><code>floor[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= numCarpets &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $\textit{dfs}(i, j)$ to represent the minimum number of white tiles that are not covered starting from index $i$ using $j$ carpets. The answer is $\textit{dfs}(0, \textit{numCarpets})$.

For index $i$, we discuss the following cases:

- If $i \ge n$, it means all tiles have been covered, return $0$;
- If $\textit{floor}[i] = 0$, then we do not need to use a carpet, just skip it, i.e., $\textit{dfs}(i, j) = \textit{dfs}(i + 1, j)$;
- If $j = 0$, then we can directly use the prefix sum array $s$ to calculate the number of remaining uncovered white tiles, i.e., $\textit{dfs}(i, j) = s[n] - s[i]$;
- If $\textit{floor}[i] = 1$, then we can choose to use a carpet or not, and take the minimum of the two, i.e., $\textit{dfs}(i, j) = \min(\textit{dfs}(i + 1, j), \textit{dfs}(i + \textit{carpetLen}, j - 1))$.

We use memoization search to solve this problem.

The time complexity is $O(n \times m)$, and the space complexity is $O(n \times m)$. Here, $n$ and $m$ are the length of the string $\textit{floor}$ and the value of $\textit{numCarpets}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumWhiteTiles(self, floor: str, numCarpets: int, carpetLen: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= n:
                return 0
            if floor[i] == "0":
                return dfs(i + 1, j)
            if j == 0:
                return s[-1] - s[i]
            return min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1))

        n = len(floor)
        s = [0] * (n + 1)
        for i, c in enumerate(floor):
            s[i + 1] = s[i] + int(c == "1")
        ans = dfs(0, numCarpets)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[] s;
    private int n;
    private int k;

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        n = floor.length();
        f = new Integer[n][numCarpets + 1];
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor.charAt(i) == '1' ? 1 : 0);
        }
        k = carpetLen;
        return dfs(0, numCarpets);
    }

    private int dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (j == 0) {
            return s[n] - s[i];
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        if (s[i + 1] == s[i]) {
            return dfs(i + 1, j);
        }
        int ans = Math.min(1 + dfs(i + 1, j), dfs(i + k, j - 1));
        f[i][j] = ans;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumWhiteTiles(string floor, int numCarpets, int carpetLen) {
        int n = floor.size();
        vector<vector<int>> f(n, vector<int>(numCarpets + 1, -1));
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor[i] == '1');
        }
        auto dfs = [&](this auto&& dfs, int i, int j) -> int {
            if (i >= n) {
                return 0;
            }
            if (j == 0) {
                return s[n] - s[i];
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            if (s[i + 1] == s[i]) {
                return dfs(i + 1, j);
            }
            int ans = min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1));
            f[i][j] = ans;
            return ans;
        };
        return dfs(0, numCarpets);
    }
};
```

#### Go

```go
func minimumWhiteTiles(floor string, numCarpets int, carpetLen int) int {
	n := len(floor)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, numCarpets+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	s := make([]int, n+1)
	for i, c := range floor {
		s[i+1] = s[i] + int(c-'0')
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			return 0
		}
		if j == 0 {
			return s[n] - s[i]
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		if s[i+1] == s[i] {
			return dfs(i+1, j)
		}
		ans := min(1+dfs(i+1, j), dfs(i+carpetLen, j-1))
		f[i][j] = ans
		return ans
	}
	return dfs(0, numCarpets)
}
```

#### TypeScript

```ts
function minimumWhiteTiles(floor: string, numCarpets: number, carpetLen: number): number {
    const n = floor.length;
    const f: number[][] = Array.from({ length: n }, () => Array(numCarpets + 1).fill(-1));
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + (floor[i] === '1' ? 1 : 0);
    }
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return 0;
        }
        if (j === 0) {
            return s[n] - s[i];
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        if (s[i + 1] === s[i]) {
            return dfs(i + 1, j);
        }
        const ans = Math.min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1));
        f[i][j] = ans;
        return ans;
    };
    return dfs(0, numCarpets);
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_white_tiles(floor: String, num_carpets: i32, carpet_len: i32) -> i32 {
        let n = floor.len();
        let a: Vec<u8> = floor.bytes().collect();
        let m = num_carpets as usize;
        let k = carpet_len as usize;

        let mut s = vec![0i32; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + if a[i] == b'1' { 1 } else { 0 };
        }

        let mut f = vec![vec![-1; m + 1]; n];

        fn dfs(
            i: usize,
            j: usize,
            n: usize,
            k: usize,
            s: &Vec<i32>,
            f: &mut Vec<Vec<i32>>,
            a: &Vec<u8>,
        ) -> i32 {
            if i >= n {
                return 0;
            }
            if j == 0 {
                return s[n] - s[i];
            }
            if f[i][j] != -1 {
                return f[i][j];
            }

            if s[i + 1] == s[i] {
                let v = dfs(i + 1, j, n, k, s, f, a);
                f[i][j] = v;
                return v;
            }

            let t1 = 1 + dfs(i + 1, j, n, k, s, f, a);
            let ni = i + k;
            let t2 = dfs(ni, j - 1, n, k, s, f, a);

            let t = t1.min(t2);
            f[i][j] = t;
            t
        }

        dfs(0, m, n, k, &s, &mut f, &a)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
