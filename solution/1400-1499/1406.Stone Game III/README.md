# [1406. 石子游戏 III](https://leetcode.cn/problems/stone-game-iii)

[English Version](/solution/1400-1499/1406.Stone%20Game%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 <code>stoneValue</code> 给出。</p>

<p>Alice 和 Bob 轮流取石子，<strong>Alice</strong> 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 <strong>1、2 或 3 堆石子</strong> 。比赛一直持续到所有石头都被拿走。</p>

<p>每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 <strong>0</strong> 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。</p>

<p>假设 Alice 和 Bob 都采取 <strong>最优策略</strong> 。如果 Alice 赢了就返回 <em>&quot;Alice&quot;</em> <em>，</em>Bob 赢了就返回<em> &quot;Bob&quot;，</em>平局（分数相同）返回 <em>&quot;Tie&quot;</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>values = [1,2,3,7]
<strong>输出：</strong>&quot;Bob&quot;
<strong>解释：</strong>Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>values = [1,2,3,-9]
<strong>输出：</strong>&quot;Alice&quot;
<strong>解释：</strong>Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
注意，他们都应该采取 <strong>最优策略 </strong>，所以在这里 Alice 将选择能够使她获胜的方案。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>values = [1,2,3,6]
<strong>输出：</strong>&quot;Tie&quot;
<strong>解释：</strong>Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>values = [1,2,3,-1,-2,-3,7]
<strong>输出：</strong>&quot;Alice&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>values = [-1,-2,-3]
<strong>输出：</strong>&quot;Tie&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= values.length &lt;= 50000</code></li>
	<li><code>-1000&nbsp;&lt;= values[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 记忆化搜索**

我们先预处理出数组 $stoneValue$ 的前缀和数组 $s$，其中 $s[i]$ 表示 $stoneValue$ 数组前 $i$ 个元素之和。

接下来，我们设计一个函数 $dfs(i)$，表示当前玩家从 $stoneValue$ 数组下标 $i$ 开始拿石子时，能够获得的最大分数。如果当前玩家拿走了 $stoneValue$ 数组下标 $i$ 开始的 $j$ 堆石子，那么下一个玩家能够获得的最大分数为 $dfs(i + j)$，为了使当前玩家获得的分数最大化，我们需要让 $dfs(i + j)$ 最小化，即 $dfs(i) = s[n] - s[i] - min(dfs(i + j))$，其中 $s[n]$ 表示 $stoneValue$ 数组所有元素之和。

最后，玩家 $Alice$ 的分数为 $dfs(0)$，玩家 $Bob$ 的分数为 $s[n] - dfs(0)$，如果 $Alice$ 的分数大于 $Bob$ 的分数，那么 $Alice$ 获胜；如果 $Alice$ 的分数小于 $Bob$ 的分数，那么 $Bob$ 获胜；如果 $Alice$ 的分数等于 $Bob$ 的分数，那么平局。

过程中，我们可以使用记忆化搜索，避免重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $stoneValue$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stoneValue):
                return 0
            t = min(dfs(i + j) for j in range(1, 4))
            return s[-1] - s[i] - t

        s = list(accumulate(stoneValue, initial=0))
        a = dfs(0)
        b = s[-1] - a
        if a == b:
            return 'Tie'
        return 'Alice' if a > b else 'Bob'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[] s;
    private Integer[] f;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        s = new int[n + 1];
        f = new Integer[n];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : a > b ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int t = 1 << 30;
        for (int j = 1; j < 4; ++j) {
            t = Math.min(t, dfs(i + j));
        }
        f[i] = s[n] - s[i] - t;
        return f[i];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != 0x3f3f3f3f) {
                return f[i];
            }
            int t = 1 << 30;
            for (int j = 1; j < 4; ++j) {
                t = min(t, dfs(i + j));
            }
            return f[i] = s[n] - s[i] - t;
        };
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : (a > b ? "Alice" : "Bob");
    }
};
```

### **Go**

```go
func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	const inf = 1 << 30
	f := make([]int, n)
	for i := range f {
		f[i] = inf
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != inf {
			return f[i]
		}
		t := inf
		for j := 1; j <= 3; j++ {
			t = min(t, dfs(i+j))
		}
		f[i] = s[n] - s[i] - t
		return f[i]
	}
	a := dfs(0)
	b := s[n] - a
	if a == b {
		return "Tie"
	}
	if a > b {
		return "Alice"
	}
	return "Bob"
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
