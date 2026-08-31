---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1406.Stone%20Game%20III/README_EN.md
rating: 2026
source: Weekly Contest 183 Q4
tags:
    - Minimax
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
    - Zero-Sum Game
---

<!-- problem:start -->

# [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii)

[中文文档](/solution/1400-1499/1406.Stone%20Game%20III/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob continue their games with piles of stones. There are several stones <strong>arranged in a row</strong>, and each stone has an associated value which is an integer given in the array <code>stoneValue</code>.</p>

<p>Alice and Bob take turns, with Alice starting first. On each player&#39;s turn, that player can take <code>1</code>, <code>2</code>, or <code>3</code> stones from the <strong>first</strong> remaining stones in the row.</p>

<p>The score of each player is the sum of the values of the stones taken. The score of each player is <code>0</code> initially.</p>

<p>The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.</p>

<p>Assume Alice and Bob <strong>play optimally</strong>.</p>

<p>Return <code>&quot;Alice&quot;</code><em> if Alice will win, </em><code>&quot;Bob&quot;</code><em> if Bob will win, or </em><code>&quot;Tie&quot;</code><em> if they will end the game with the same score</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [1,2,3,7]
<strong>Output:</strong> &quot;Bob&quot;
<strong>Explanation:</strong> Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [1,2,3,-9]
<strong>Output:</strong> &quot;Alice&quot;
<strong>Explanation:</strong> Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob&#39;s score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob&#39;s score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [1,2,3,6]
<strong>Output:</strong> &quot;Tie&quot;
<strong>Explanation:</strong> Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stoneValue.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= stoneValue[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $dfs(i)$, which represents the maximum score difference that the current player can obtain when playing the game in the range $[i, n)$. If $dfs(0) > 0$, it means that the first player Alice can win; if $dfs(0) < 0$, it means that the second player Bob can win; otherwise, it means that the two players tie.

The execution logic of the function $dfs(i)$ is as follows:

- If $i \geq n$, it means that there are no stones to take now, so we can directly return $0$;
- Otherwise, we enumerate the index $j$ of the last pile taken by the current player, where $i \le j < \min(i + 3, n)$, i.e., the current player takes all the piles in the index range $[i, j]$ and gets a score of $\sum_{k=i}^{j} \textit{stoneValue}[k]$. Then the score difference that the other player can get in the next round is $dfs(j + 1)$, so the score difference that the current player can get is $\sum_{k=i}^{j} \textit{stoneValue}[k] - dfs(j + 1)$. We want to maximize the score difference of the current player, so we can use the $\max$ function to get the maximum score difference, that is:

$$
dfs(i) = \max_{i \le j < \min(i+3, n)} \left\{\sum_{k=i}^{j} \textit{stoneValue}[k] - dfs(j + 1)\right\}
$$

To prevent repeated calculations, we can use memoization search.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of piles of stones.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stoneValue):
                return 0
            ans = -inf
            s = 0
            for j in range(i, i + 3):
                if j >= len(stoneValue):
                    break
                s += stoneValue[j]
                ans = max(ans, s - dfs(j + 1))
            return ans

        res = dfs(0)
        if res == 0:
            return 'Tie'
        return 'Alice' if res > 0 else 'Bob'
```

#### Java

```java
class Solution {
    private int[] stoneValue;
    private Integer[] f;
    private int n;

    public String stoneGameIII(int[] stoneValue) {
        this.stoneValue = stoneValue;
        this.n = stoneValue.length;
        this.f = new Integer[n];

        int res = dfs(0);

        if (res == 0) {
            return "Tie";
        }
        return res > 0 ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }

        if (f[i] != null) {
            return f[i];
        }

        int ans = Integer.MIN_VALUE;
        int s = 0;

        for (int j = i; j < i + 3 && j < n; j++) {
            s += stoneValue[j];
            ans = Math.max(ans, s - dfs(j + 1));
        }

        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<int> f(n, INT_MIN);

        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }

            if (f[i] != INT_MIN) {
                return f[i];
            }

            int ans = INT_MIN;
            int s = 0;

            for (int j = i; j < i + 3 && j < n; j++) {
                s += stoneValue[j];
                ans = max(ans, s - dfs(dfs, j + 1));
            }

            return f[i] = ans;
        };

        int res = dfs(dfs, 0);

        if (res == 0) {
            return "Tie";
        }
        return res > 0 ? "Alice" : "Bob";
    }
};
```

#### Go

```go
func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	f := make([]int, n)

	for i := range f {
		f[i] = -1 << 30
	}

	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}

		if f[i] != -1<<30 {
			return f[i]
		}

		ans := -1 << 30
		s := 0

		for j := i; j < i+3 && j < n; j++ {
			s += stoneValue[j]
			ans = max(ans, s-dfs(j+1))
		}

		f[i] = ans
		return ans
	}

	res := dfs(0)

	if res == 0 {
		return "Tie"
	}
	if res > 0 {
		return "Alice"
	}
	return "Bob"
}
```

#### TypeScript

```ts
function stoneGameIII(stoneValue: number[]): string {
    const n = stoneValue.length;
    const f = new Array<number>(n).fill(Number.MIN_SAFE_INTEGER);

    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }

        if (f[i] !== Number.MIN_SAFE_INTEGER) {
            return f[i];
        }

        let ans = Number.MIN_SAFE_INTEGER;
        let s = 0;

        for (let j = i; j < i + 3 && j < n; j++) {
            s += stoneValue[j];
            ans = Math.max(ans, s - dfs(j + 1));
        }

        f[i] = ans;
        return ans;
    };

    const res = dfs(0);

    if (res === 0) {
        return 'Tie';
    }
    return res > 0 ? 'Alice' : 'Bob';
}
```

#### Rust

```rust
impl Solution {
    pub fn stone_game_iii(stone_value: Vec<i32>) -> String {
        let n = stone_value.len();
        let mut f = vec![None; n];

        fn dfs(i: usize, stone_value: &Vec<i32>, f: &mut Vec<Option<i32>>) -> i32 {
            if i >= stone_value.len() {
                return 0;
            }

            if let Some(v) = f[i] {
                return v;
            }

            let mut ans = i32::MIN;
            let mut s = 0;

            for j in i..(i + 3).min(stone_value.len()) {
                s += stone_value[j];
                ans = ans.max(s - dfs(j + 1, stone_value, f));
            }

            f[i] = Some(ans);
            ans
        }

        let res = dfs(0, &stone_value, &mut f);

        if res == 0 {
            "Tie".to_string()
        } else if res > 0 {
            "Alice".to_string()
        } else {
            "Bob".to_string()
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
