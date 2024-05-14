# [1908. Game of Nim 🔒](https://leetcode.com/problems/game-of-nim)

[中文文档](/solution/1900-1999/1908.Game%20of%20Nim/README.md)

<!-- tags:Bit Manipulation,Brainteaser,Array,Math,Dynamic Programming,Game Theory -->

<!-- difficulty:Medium -->

## Description

<p>Alice and Bob take turns playing a game with <strong>Alice starting first</strong>.</p>

<p>In this game, there are <code>n</code> piles of stones. On each player&#39;s turn, the player should remove any <strong>positive</strong> number of stones from a non-empty pile <strong>of his or her choice</strong>. The first player who cannot make a move loses, and the other player wins.</p>

<p>Given an integer array <code>piles</code>, where <code>piles[i]</code> is the number of stones in the <code>i<sup>th</sup></code> pile, return <code>true</code><em> if Alice wins, or </em><code>false</code><em> if Bob wins</em>.</p>

<p>Both Alice and Bob play <strong>optimally</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [1]
<strong>Output:</strong> true
<strong>Explanation:</strong> There is only one possible scenario:
- On the first turn, Alice removes one stone from the first pile. piles = [0].
- On the second turn, there are no stones left for Bob to remove. Alice wins.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [1,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that Bob will always win. One possible scenario is:
- On the first turn, Alice removes one stone from the first pile. piles = [0,1].
- On the second turn, Bob removes one stone from the second pile. piles = [0,0].
- On the third turn, there are no stones left for Alice to remove. Bob wins.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [1,2,3]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that Bob will always win. One possible scenario is:
- On the first turn, Alice removes three stones from the third pile. piles = [1,2,0].
- On the second turn, Bob removes one stone from the second pile. piles = [1,1,0].
- On the third turn, Alice removes one stone from the first pile. piles = [0,1,0].
- On the fourth turn, Bob removes one stone from the second pile. piles = [0,0,0].
- On the fifth turn, there are no stones left for Alice to remove. Bob wins.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 7</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you find a linear time solution? Although the linear time solution may be beyond the scope of an interview, it could be interesting to know.</p>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def nimGame(self, piles: List[int]) -> bool:
        @cache
        def dfs(st):
            lst = list(st)
            for i, x in enumerate(lst):
                for j in range(1, x + 1):
                    lst[i] -= j
                    if not dfs(tuple(lst)):
                        return True
                    lst[i] += j
            return False

        return dfs(tuple(piles))
```

```java
class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();
    private int[] p = new int[8];

    public Solution() {
        p[0] = 1;
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
    }

    public boolean nimGame(int[] piles) {
        return dfs(piles);
    }

    private boolean dfs(int[] piles) {
        int st = f(piles);
        if (memo.containsKey(st)) {
            return memo.get(st);
        }
        for (int i = 0; i < piles.length; ++i) {
            for (int j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.put(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.put(st, false);
        return false;
    }

    private int f(int[] piles) {
        int st = 0;
        for (int i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    }
}
```

```cpp
class Solution {
public:
    bool nimGame(vector<int>& piles) {
        unordered_map<int, int> memo;
        int p[8] = {1};
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
        auto f = [&](vector<int>& piles) {
            int st = 0;
            for (int i = 0; i < piles.size(); ++i) {
                st += piles[i] * p[i];
            }
            return st;
        };
        function<bool(vector<int>&)> dfs = [&](vector<int>& piles) {
            int st = f(piles);
            if (memo.count(st)) {
                return memo[st];
            }
            for (int i = 0; i < piles.size(); ++i) {
                for (int j = 1; j <= piles[i]; ++j) {
                    piles[i] -= j;
                    if (!dfs(piles)) {
                        piles[i] += j;
                        return memo[st] = true;
                    }
                    piles[i] += j;
                }
            }
            return memo[st] = false;
        };
        return dfs(piles);
    }
};
```

```go
func nimGame(piles []int) bool {
	memo := map[int]bool{}
	p := make([]int, 8)
	p[0] = 1
	for i := 1; i < 8; i++ {
		p[i] = p[i-1] * 8
	}
	f := func(piles []int) int {
		st := 0
		for i, x := range piles {
			st += x * p[i]
		}
		return st
	}
	var dfs func(piles []int) bool
	dfs = func(piles []int) bool {
		st := f(piles)
		if v, ok := memo[st]; ok {
			return v
		}
		for i, x := range piles {
			for j := 1; j <= x; j++ {
				piles[i] -= j
				if !dfs(piles) {
					piles[i] += j
					memo[st] = true
					return true
				}
				piles[i] += j
			}
		}
		memo[st] = false
		return false
	}
	return dfs(piles)
}
```

```ts
function nimGame(piles: number[]): boolean {
    const p: number[] = Array(8).fill(1);
    for (let i = 1; i < 8; ++i) {
        p[i] = p[i - 1] * 8;
    }
    const f = (piles: number[]): number => {
        let st = 0;
        for (let i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    };
    const memo: Map<number, boolean> = new Map();
    const dfs = (piles: number[]): boolean => {
        const st = f(piles);
        if (memo.has(st)) {
            return memo.get(st)!;
        }
        for (let i = 0; i < piles.length; ++i) {
            for (let j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.set(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.set(st, false);
        return false;
    };
    return dfs(piles);
}
```

<!-- tabs:end -->

<!-- end -->
