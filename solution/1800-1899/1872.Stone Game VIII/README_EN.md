---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1872.Stone%20Game%20VIII/README_EN.md
rating: 2439
source: Weekly Contest 242 Q4
tags:
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
    - Prefix Sum
---

<!-- problem:start -->

# [1872. Stone Game VIII](https://leetcode.com/problems/stone-game-viii)

[中文文档](/solution/1800-1899/1872.Stone%20Game%20VIII/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob take turns playing a game, with <strong>Alice starting first</strong>.</p>

<p>There are <code>n</code> stones arranged in a row. On each player&#39;s turn, while the number of stones is <strong>more than one</strong>, they will do the following:</p>

<ol>

    <li>Choose an integer <code>x &gt; 1</code>, and <strong>remove</strong> the leftmost <code>x</code> stones from the row.</li>

    <li>Add the <strong>sum</strong> of the <strong>removed</strong> stones&#39; values to the player&#39;s score.</li>

    <li>Place a <strong>new stone</strong>, whose value is equal to that sum, on the left side of the row.</li>

</ol>

<p>The game stops when <strong>only</strong> <strong>one</strong> stone is left in the row.</p>

<p>The <strong>score difference</strong> between Alice and Bob is <code>(Alice&#39;s score - Bob&#39;s score)</code>. Alice&#39;s goal is to <strong>maximize</strong> the score difference, and Bob&#39;s goal is the <strong>minimize</strong> the score difference.</p>

<p>Given an integer array <code>stones</code> of length <code>n</code> where <code>stones[i]</code> represents the value of the <code>i<sup>th</sup></code> stone <strong>from the left</strong>, return <em>the <strong>score difference</strong> between Alice and Bob if they both play <strong>optimally</strong>.</em></p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> stones = [-1,2,-3,4,-5]

<strong>Output:</strong> 5

<strong>Explanation:</strong>

- Alice removes the first 4 stones, adds (-1) + 2 + (-3) + 4 = 2 to her score, and places a stone of

  value 2 on the left. stones = [2,-5].

- Bob removes the first 2 stones, adds 2 + (-5) = -3 to his score, and places a stone of value -3 on

  the left. stones = [-3].

The difference between their scores is 2 - (-3) = 5.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> stones = [7,-6,5,10,5,-2,-6]

<strong>Output:</strong> 13

<strong>Explanation:</strong>

- Alice removes all stones, adds 7 + (-6) + 5 + 10 + 5 + (-2) + (-6) = 13 to her score, and places a

  stone of value 13 on the left. stones = [13].

The difference between their scores is 13 - 0 = 13.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> stones = [-10,-12]

<strong>Output:</strong> -22

<strong>Explanation:</strong>

- Alice can only make one move, which is to remove both stones. She adds (-10) + (-12) = -22 to her

  score and places a stone of value -22 on the left. stones = [-22].

The difference between their scores is (-22) - 0 = -22.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>n == stones.length</code></li>

    <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>

    <li><code>-10<sup>4</sup> &lt;= stones[i] &lt;= 10<sup>4</sup></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Memoization Search

According to the problem description, each time we take the leftmost $x$ stones, add their sum to our score, and then put a stone with this sum value on the leftmost side, it is equivalent to merging these $x$ stones into a stone with this sum value, and the prefix sum remains unchanged.

We can use a prefix sum array $s$ of length $n$ to represent the prefix sum of the array $stones$, where $s[i]$ represents the sum of the elements $stones[0..i]$.

Next, we design a function $dfs(i)$, which means that we currently take stones from $stones[i:]$, and return the maximum score difference that the current player can get.

The execution process of the function $dfs(i)$ is as follows:

- If $i \geq n - 1$, it means that we can only take all the stones at present, so we return $s[n - 1]$.
- Otherwise, we can choose to take all the stones from $stones[i + 1:]$, and the score difference obtained is $dfs(i + 1)$; we can also choose to take the stones $stones[:i]$, and the score difference obtained is $s[i] - dfs(i + 1)$. We take the maximum of the two situations, which is the maximum score difference that the current player can get.

Finally, we can get the score difference between Alice and Bob as $dfs(1)$, that is, Alice must start the game by taking stones from $stones[1:]$.

To avoid repeated calculations, we can use memoization search.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $stones$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stones) - 1:
                return s[-1]
            return max(dfs(i + 1), s[i] - dfs(i + 1))

        s = list(accumulate(stones))
        return dfs(1)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] s;
    private int n;

    public int stoneGameVIII(int[] stones) {
        n = stones.length;
        f = new Integer[n];
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        s = stones;
        return dfs(1);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return s[i];
        }
        if (f[i] == null) {
            f[i] = Math.max(dfs(i + 1), s[i] - dfs(i + 1));
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n - 1) {
                return stones[i];
            }
            if (f[i] == -1) {
                f[i] = max(dfs(i + 1), stones[i] - dfs(i + 1));
            }
            return f[i];
        };
        return dfs(1);
    }
};
```

#### Go

```go
func stoneGameVIII(stones []int) int {
	n := len(stones)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	for i := 1; i < n; i++ {
		stones[i] += stones[i-1]
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n-1 {
			return stones[i]
		}
		if f[i] == -1 {
			f[i] = max(dfs(i+1), stones[i]-dfs(i+1))
		}
		return f[i]
	}
	return dfs(1)
}
```

#### TypeScript

```ts
function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    const f: number[] = Array(n).fill(-1);
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    const dfs = (i: number): number => {
        if (i >= n - 1) {
            return stones[i];
        }
        if (f[i] === -1) {
            f[i] = Math.max(dfs(i + 1), stones[i] - dfs(i + 1));
        }
        return f[i];
    };
    return dfs(1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Prefix Sum + Dynamic Programming

We can also use dynamic programming to solve this problem.

Similar to Solution 1, we first use a prefix sum array $s$ of length $n$ to represent the prefix sum of the array $stones$, where $s[i]$ represents the sum of the elements $stones[0..i]$.

We define $f[i]$ to represent the maximum score difference that the current player can get when taking stones from $stones[i:]$.

If the player chooses to take the stones $stones[:i]$, then the score obtained is $s[i]$. At this time, the other player will take stones from $stones[i+1:]$, and the maximum score difference that the other player can get is $f[i+1]$. Therefore, the maximum score difference that the current player can get is $s[i] - f[i+1]$.

If the player chooses to take stones from $stones[i+1:]$, then the maximum score difference obtained is $f[i+1]$.

Therefore, we can get the state transition equation:

$$
f[i] = \max\{s[i] - f[i+1], f[i+1]\}
$$

Finally, we can get the score difference between Alice and Bob as $f[1]$, that is, Alice must start the game by taking stones from $stones[1:]$.

We notice that $f[i]$ is only related to $f[i+1]$, so we only need to use a variable $f$ to represent $f[i]$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $stones$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        s = list(accumulate(stones))
        f = s[-1]
        for i in range(len(s) - 2, 0, -1):
            f = max(f, s[i] - f)
        return f
```

#### Java

```java
class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f = stones[n - 1];
        for (int i = n - 2; i > 0; --i) {
            f = Math.max(f, stones[i] - f);
        }
        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f = stones.back();
        for (int i = n - 2; i; --i) {
            f = max(f, stones[i] - f);
        }
        return f;
    }
};
```

#### TypeScript

```ts
function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    let f = stones[n - 1];
    for (let i = n - 2; i; --i) {
        f = Math.max(f, stones[i] - f);
    }
    return f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
