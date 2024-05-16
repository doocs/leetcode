---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1690.Stone%20Game%20VII/README_EN.md
rating: 1951
source: Weekly Contest 219 Q3
tags:
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [1690. Stone Game VII](https://leetcode.com/problems/stone-game-vii)

[中文文档](/solution/1600-1699/1690.Stone%20Game%20VII/README.md)

## Description

<p>Alice and Bob take turns playing a game, with <strong>Alice starting first</strong>.</p>

<p>There are <code>n</code> stones arranged in a row. On each player&#39;s turn, they can <strong>remove</strong> either the leftmost stone or the rightmost stone from the row and receive points equal to the <strong>sum</strong> of the remaining stones&#39; values in the row. The winner is the one with the higher score when there are no stones left to remove.</p>

<p>Bob found that he will always lose this game (poor Bob, he always loses), so he decided to <strong>minimize the score&#39;s difference</strong>. Alice&#39;s goal is to <strong>maximize the difference</strong> in the score.</p>

<p>Given an array of integers <code>stones</code> where <code>stones[i]</code> represents the value of the <code>i<sup>th</sup></code> stone <strong>from the left</strong>, return <em>the <strong>difference</strong> in Alice and Bob&#39;s score if they both play <strong>optimally</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [5,3,1,4,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
- Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
- Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
- Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
- Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
- Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
The score difference is 18 - 12 = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [7,90,5,1,100,10,10,2]
<strong>Output:</strong> 122</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stones.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Memorization Search

First, we preprocess to get the prefix sum array $s$, where $s[i]$ represents the total sum of the first $i$ stones.

Next, we design a function $dfs(i, j)$, which represents the score difference between the first and second players when the remaining stones are $stones[i], stones[i + 1], \dots, stones[j]$. The answer is $dfs(0, n - 1)$.

The calculation process of the function $dfs(i, j)$ is as follows:

-   If $i > j$, it means there are no stones currently, so return $0$;
-   Otherwise, the first player has two choices, which are to remove $stones[i]$ or $stones[j]$, and then calculate the score difference, i.e., $a = s[j + 1] - s[i + 1] - dfs(i + 1, j)$ and $b = s[j] - s[i] - dfs(i, j - 1)$. We take the larger of the two as the return value of $dfs(i, j)$.

During the process, we use memorization search, i.e., use an array $f$ to record the return value of the function $dfs(i, j)$, to avoid repeated calculations.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of stones.

<!-- tabs:start -->

```python
class Solution:
    def stoneGameVII(self, stones: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            a = s[j + 1] - s[i + 1] - dfs(i + 1, j)
            b = s[j] - s[i] - dfs(i, j - 1)
            return max(a, b)

        s = list(accumulate(stones, initial=0))
        ans = dfs(0, len(stones) - 1)
        dfs.cache_clear()
        return ans
```

```java
class Solution {
    private int[] s;
    private Integer[][] f;

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        s = new int[n + 1];
        f = new Integer[n][n];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
        int b = s[j] - s[i] - dfs(i, j - 1);
        return f[i][j] = Math.max(a, b);
    }
}
```

```cpp
class Solution {
public:
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
            int b = s[j] - s[i] - dfs(i, j - 1);
            return f[i][j] = max(a, b);
        };
        return dfs(0, n - 1);
    }
};
```

```go
func stoneGameVII(stones []int) int {
	n := len(stones)
	s := make([]int, n+1)
	f := make([][]int, n)
	for i, x := range stones {
		s[i+1] = s[i] + x
		f[i] = make([]int, n)
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		a := s[j+1] - s[i+1] - dfs(i+1, j)
		b := s[j] - s[i] - dfs(i, j-1)
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, n-1)
}
```

```ts
function stoneGameVII(stones: number[]): number {
    const n = stones.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + stones[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j]) {
            return f[i][j];
        }
        const a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
        const b = s[j] - s[i] - dfs(i, j - 1);
        return (f[i][j] = Math.max(a, b));
    };
    return dfs(0, n - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoization search in Solution 1 into dynamic programming. We define $f[i][j]$ as the score difference between the first and second players when the remaining stones are $stones[i], stones[i + 1], \dots, stones[j]$. Therefore, the answer is $f[0][n - 1]$.

The state transition equation is as follows:

$$
f[i][j] = \max(s[j + 1] - s[i + 1] - f[i + 1][j], s[j] - s[i] - f[i][j - 1])
$$

When calculating $f[i][j]$, we need to ensure that $f[i + 1][j]$ and $f[i][j - 1]$ have been calculated. Therefore, we need to enumerate $i$ in descending order and $j$ in ascending order.

Finally, the answer is $f[0][n - 1]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of stones.

<!-- tabs:start -->

```python
class Solution:
    def stoneGameVII(self, stones: List[int]) -> int:
        s = list(accumulate(stones, initial=0))
        n = len(stones)
        f = [[0] * n for _ in range(n)]
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                a = s[j + 1] - s[i + 1] - f[i + 1][j]
                b = s[j] - s[i] - f[i][j - 1]
                f[i][j] = max(a, b)
        return f[0][-1]
```

```java
class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                int a = s[j + 1] - s[i + 1] - f[i + 1][j];
                int b = s[j] - s[i] - f[i][j - 1];
                f[i][j] = Math.max(a, b);
            }
        }
        return f[0][n - 1];
    }
}
```

```cpp
class Solution {
public:
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();
        int s[n + 1];
        memset(s, 0, sizeof(s));
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                int a = s[j + 1] - s[i + 1] - f[i + 1][j];
                int b = s[j] - s[i] - f[i][j - 1];
                f[i][j] = max(a, b);
            }
        }
        return f[0][n - 1];
    }
};
```

```go
func stoneGameVII(stones []int) int {
	n := len(stones)
	s := make([]int, n+1)
	for i, x := range stones {
		s[i+1] = s[i] + x
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(s[j+1]-s[i+1]-f[i+1][j], s[j]-s[i]-f[i][j-1])
		}
	}
	return f[0][n-1]
}
```

```ts
function stoneGameVII(stones: number[]): number {
    const n = stones.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + stones[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 2; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = Math.max(s[j + 1] - s[i + 1] - f[i + 1][j], s[j] - s[i] - f[i][j - 1]);
        }
    }
    return f[0][n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
