---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1406.Stone%20Game%20III/README.md
rating: 2026
source: 第 183 场周赛 Q4
tags:
    - 极小化极大
    - 数组
    - 数学
    - 动态规划
    - 博弈
---

<!-- problem:start -->

# [1406. 石子游戏 III](https://leetcode.cn/problems/stone-game-iii)

[English Version](/solution/1400-1499/1406.Stone%20Game%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 继续他们的石子游戏。几堆石子 <strong>排成一行</strong> ，每堆石子都对应一个得分，由数组 <code>stoneValue</code> 给出。</p>

<p>Alice 和 Bob 轮流取石子，<strong>Alice</strong> 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 <strong>1、2 或 3 堆石子</strong> 。比赛一直持续到所有石头都被拿走。</p>

<p>每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 <strong>0</strong> 。</p>

<p>比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。</p>

<p>假设 Alice 和 Bob 都采取 <strong>最优策略</strong> 。</p>

<p>如果 Alice 赢了就返回 <code>"Alice"</code> <em>，</em>Bob 赢了就返回<em> </em><code>"Bob"</code><em>，</em>分数相同返回 <code>"Tie"</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2,3,7]
<strong>输出：</strong>"Bob"
<strong>解释：</strong>Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2,3,-9]
<strong>输出：</strong>"Alice"
<strong>解释：</strong>Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
注意，他们都应该采取 <strong>最优策略 </strong>，所以在这里 Alice 将选择能够使她获胜的方案。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2,3,6]
<strong>输出：</strong>"Tie"
<strong>解释：</strong>Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stoneValue.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-1000&nbsp;&lt;= stoneValue[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i)$，表示当前玩家在 $[i, n)$ 范围内进行游戏时，可以获得的最大得分差值。如果 $dfs(0) \gt 0$，则表示先手玩家 Alice 可以获胜；如果 $dfs(0) \lt 0$，则表示后手玩家 Bob 可以获胜；否则，表示两人打成平局。

函数 $dfs(i)$ 的执行逻辑如下：

- 如果 $i \geq n$，说明当前没有石子可以拿了，直接返回 $0$ 即可；
- 否则，我们枚举当前玩家拿走的最后一堆石子的下标 $j$，其中 $i \le j \lt \min(i + 3, n)$，即当前玩家拿走下标范围 $[i, j]$ 内的所有石子，得分之和为 $\sum_{k=i}^{j} \textit{stoneValue}[k]$，而另一个玩家在下一轮可以获得的得分差值为 $dfs(j + 1)$，从而当前玩家可以获得的得分差值为 $\sum_{k=i}^{j} \textit{stoneValue}[k] - dfs(j + 1)$。我们要使得当前玩家的得分差值最大，因此可以用 $\max$ 函数得到最大得分差值，即：

$$
dfs(i) = \max_{i \le j < \min(i+3, n)} \left\{\sum_{k=i}^{j} \textit{stoneValue}[k] - dfs(j + 1)\right\}
$$

为了防止重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是石子的堆数。

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
