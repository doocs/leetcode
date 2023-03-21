# [1510. 石子游戏 IV](https://leetcode.cn/problems/stone-game-iv)

[English Version](/solution/1500-1599/1510.Stone%20Game%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。</p>

<p>一开始，有 <code>n</code>&nbsp;个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 <strong>任意</strong>&nbsp;非零 <strong>平方数</strong>&nbsp;个石子。</p>

<p>如果石子堆里没有石子了，则无法操作的玩家输掉游戏。</p>

<p>给你正整数&nbsp;<code>n</code>&nbsp;，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回&nbsp;<code>True</code>&nbsp;，否则返回&nbsp;<code>False</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
<strong>解释：</strong>Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>false
<strong>解释：</strong>Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -&gt; 1 -&gt; 0）。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>true
<strong>解释：</strong>n 已经是一个平方数，Alice 可以一次全拿掉 4 个石子并赢得胜利（4 -&gt; 0）。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>false
<strong>解释：</strong>当 Bob 采取最优策略时，Alice 无法赢得比赛。
如果 Alice 一开始拿走 4 个石子， Bob 会拿走 1 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -&gt; 3 -&gt; 2 -&gt; 1 -&gt; 0）。
如果 Alice 一开始拿走 1 个石子， Bob 会拿走 4 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -&gt; 6 -&gt; 2 -&gt; 1 -&gt; 0）。</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>n = 17
<strong>输出：</strong>false
<strong>解释：</strong>如果 Bob 采取最优策略，Alice 无法赢得胜利。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i)$，表示当前石子堆中有 $i$ 个石子时，当前玩家是否能赢得比赛。如果当前玩家能赢得比赛，则返回 $true$，否则返回 $false$。那么答案即为 $dfs(n)$。

函数 $dfs(i)$ 的计算过程如下：

-   如果 $i \leq 0$，说明当前玩家无法进行任何操作，因此当前玩家输掉比赛，返回 $false$；
-   否则，枚举当前玩家可以拿走的石子数量 $j$，其中 $j$ 为平方数，如果当前玩家拿走 $j$ 个石子后，另一个玩家无法赢得比赛，则当前玩家赢得比赛，返回 $true$。如果枚举完所有的 $j$，都无法满足上述条件，则当前玩家输掉比赛，返回 $false$。

为了避免重复计算，我们可以使用记忆化搜索，即使用数组 $f$ 记录函数 $dfs(i)$ 的计算结果。

时间复杂度 $O(n \times \sqrt{n})$，空间复杂度 $O(n)$。其中 $n$ 为石子堆中石子的数量。

**方法二：动态规划**

我们也可以使用动态规划求解本题。

定义数组 $f$，其中 $f[i]$ 表示当前石子堆中有 $i$ 个石子时，当前玩家是否能赢得比赛。如果当前玩家能赢得比赛，则 $f[i]$ 为 $true$，否则为 $false$。那么答案即为 $f[n]$。

我们在 $[1,..n]$ 的范围内枚举 $i$，并在 $[1,..i]$ 的范围内枚举 $j$，其中 $j$ 为平方数，如果当前玩家拿走 $j$ 个石子后，另一个玩家无法赢得比赛，则当前玩家赢得比赛，即 $f[i] = true$。如果枚举完所有的 $j$，都无法满足上述条件，则当前玩家输掉比赛，即 $f[i] = false$。因此我们可以得到状态转移方程：

$$
f[i]=
\begin{cases}
true, & \text{if } \exists j \in [1,..i], j^2 \leq i \text{ and } f[i-j^2] = false\\
false, & \text{otherwise}
\end{cases}
$$

最后，我们返回 $f[n]$ 即可。

时间复杂度 $O(n \times \sqrt{n})$，空间复杂度 $O(n)$。其中 $n$ 为石子堆中石子的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        @cache
        def dfs(i):
            if i <= 0:
                return False
            j = 1
            while (k := (i - j * j)) >= 0:
                if not dfs(k):
                    return True
                j += 1
            return False

        return dfs(n)
```

```python
class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        f = [False] * (n + 1)
        for i in range(1, n + 1):
            j = 1
            while j <= i // j:
                if not f[i - j * j]:
                    f[i] = True
                    break
                j += 1
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Boolean[] f;

    public boolean winnerSquareGame(int n) {
        f = new Boolean[n + 1];
        return dfs(n);
    }

    private boolean dfs(int i) {
        if (i <= 0) {
            return false;
        }
        if (f[i] != null) {
            return f[i];
        }
        for (int j = 1; j <= i / j; ++j) {
            if (!dfs(i - j * j)) {
                return f[i] = true;
            }
        }
        return f[i] = false;
    }
}
```

```java
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i / j; ++j) {
                if (!f[i - j * j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool winnerSquareGame(int n) {
        int f[n + 1];
        memset(f, 0, sizeof(f));
        function<bool(int)> dfs = [&](int i) -> bool {
            if (i <= 0) {
                return false;
            }
            if (f[i] != 0) {
                return f[i] == 1;
            }
            for (int j = 1; j <= i / j; ++j) {
                if (!dfs(i - j * j)) {
                    f[i] = 1;
                    return true;
                }
            }
            f[i] = -1;
            return false;
        };
        return dfs(n);
    }
};
```

```cpp
class Solution {
public:
    bool winnerSquareGame(int n) {
        bool f[n + 1];
        memset(f, false, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i / j; ++j) {
                if (!f[i - j * j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func winnerSquareGame(n int) bool {
	f := make([]int, n+1)
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i <= 0 {
			return false
		}
		if f[i] != 0 {
			return f[i] == 1
		}
		for j := 1; j <= i/j; j++ {
			if !dfs(i - j*j) {
				f[i] = 1
				return true
			}
		}
		f[i] = -1
		return false
	}
	return dfs(n)
}
```

```go
func winnerSquareGame(n int) bool {
	f := make([]bool, n+1)
	for i := 1; i <= n; i++ {
		for j := 1; j <= i/j; j++ {
			if !f[i-j*j] {
				f[i] = true
				break
			}
		}
	}
	return f[n]
}
```

### **...**

```

```

<!-- tabs:end -->
