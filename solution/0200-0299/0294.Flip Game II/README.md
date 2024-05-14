---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0294.Flip%20Game%20II/README.md
tags:
    - 记忆化搜索
    - 数学
    - 动态规划
    - 回溯
    - 博弈
---

# [294. 翻转游戏 II 🔒](https://leetcode.cn/problems/flip-game-ii)

[English Version](/solution/0200-0299/0294.Flip%20Game%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：</p>

<p>给你一个字符串 <code>currentState</code> ，其中只含 <code>'+'</code> 和 <code>'-'</code> 。你和朋友轮流将&nbsp;<strong>连续 </strong>的两个&nbsp;<code>"++"</code>&nbsp;反转成&nbsp;<code>"--"</code> 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。默认每个人都会采取最优策略。</p>

<p>请你写出一个函数来判定起始玩家 <strong>是否存在必胜的方案</strong> ：如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>currentState = "++++"
<strong>输出：</strong>true
<strong>解释：</strong>起始玩家可将中间的 <code>"++"</code> 翻转变为 <code>"+--+" 从而得胜。</code></pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>currentState = "+"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= currentState.length &lt;= 60</code></li>
	<li><code>currentState[i]</code> 不是 <code>'+'</code> 就是 <code>'-'</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>请推导你算法的时间复杂度。</p>

## 解法

### 方法一：状态压缩 + 记忆化搜索

<!-- tabs:start -->

```python
class Solution:
    def canWin(self, currentState: str) -> bool:
        @cache
        def dfs(mask):
            for i in range(n - 1):
                if (mask & (1 << i)) == 0 or (mask & (1 << (i + 1)) == 0):
                    continue
                if dfs(mask ^ (1 << i) ^ (1 << (i + 1))):
                    continue
                return True
            return False

        mask, n = 0, len(currentState)
        for i, c in enumerate(currentState):
            if c == '+':
                mask |= 1 << i
        return dfs(mask)
```

```java
class Solution {
    private int n;
    private Map<Long, Boolean> memo = new HashMap<>();

    public boolean canWin(String currentState) {
        long mask = 0;
        n = currentState.length();
        for (int i = 0; i < n; ++i) {
            if (currentState.charAt(i) == '+') {
                mask |= 1 << i;
            }
        }
        return dfs(mask);
    }

    private boolean dfs(long mask) {
        if (memo.containsKey(mask)) {
            return memo.get(mask);
        }
        for (int i = 0; i < n - 1; ++i) {
            if ((mask & (1 << i)) == 0 || (mask & (1 << (i + 1))) == 0) {
                continue;
            }
            if (dfs(mask ^ (1 << i) ^ (1 << (i + 1)))) {
                continue;
            }
            memo.put(mask, true);
            return true;
        }
        memo.put(mask, false);
        return false;
    }
}
```

```cpp
using ll = long long;

class Solution {
public:
    int n;
    unordered_map<ll, bool> memo;

    bool canWin(string currentState) {
        n = currentState.size();
        ll mask = 0;
        for (int i = 0; i < n; ++i)
            if (currentState[i] == '+') mask |= 1ll << i;
        return dfs(mask);
    }

    bool dfs(ll mask) {
        if (memo.count(mask)) return memo[mask];
        for (int i = 0; i < n - 1; ++i) {
            if ((mask & (1ll << i)) == 0 || (mask & (1ll << (i + 1))) == 0) continue;
            if (dfs(mask ^ (1ll << i) ^ (1ll << (i + 1)))) continue;
            memo[mask] = true;
            return true;
        }
        memo[mask] = false;
        return false;
    }
};
```

```go
func canWin(currentState string) bool {
	n := len(currentState)
	memo := map[int]bool{}
	mask := 0
	for i, c := range currentState {
		if c == '+' {
			mask |= 1 << i
		}
	}
	var dfs func(int) bool
	dfs = func(mask int) bool {
		if v, ok := memo[mask]; ok {
			return v
		}
		for i := 0; i < n-1; i++ {
			if (mask&(1<<i)) == 0 || (mask&(1<<(i+1))) == 0 {
				continue
			}
			if dfs(mask ^ (1 << i) ^ (1 << (i + 1))) {
				continue
			}
			memo[mask] = true
			return true
		}
		memo[mask] = false
		return false
	}
	return dfs(mask)
}
```

<!-- tabs:end -->

### 方法二：Sprague-Grundy 定理

Sprague-Grundy 定理为游戏的每一个状态定义了一个 Sprague-Grundy 数（简称 SG 数），游戏状态的组合相当于 SG 数的异或运算。

Sprague-Grundy 定理的完整表述如下：

若一个游戏满足以下条件：

1. 双人、回合制
1. 信息完全公开
1. 无随机因素
1. 必然在有限步内结束，且每步的走法数有限
1. 没有平局
1. 双方可采取的行动及胜利目标都相同
1. 这个胜利目标是自己亲手达成终局状态，或者说走最后一步者为胜（normal play）

则游戏中的每个状态可以按如下规则赋予一个非负整数，称为 Sprague-Grundy 数，即 $SG(A)=mex\{SG(B)|A->B\}$。（式中 $A$, $B$ 代表状态，代表 $A$ 状态经一步行动可以到达 $B$ 状态，而 $mex$ 表示一个集合所不包含的最小非负整数）

SG 数有如下性质：

1. SG 数为 0 的状态，后手必胜；SG 数为正的状态，先手必胜；
1. 若一个母状态可以拆分成多个相互独立的子状态，则母状态的 SG 数等于各个子状态的 SG 数的异或。

参考资料：[Sprague-Grundy 定理是怎么想出来的](https://zhuanlan.zhihu.com/p/20611132)

时间复杂度 $O(n^2)$。

<!-- tabs:start -->

```python
class Solution:
    def canWin(self, currentState: str) -> bool:
        def win(i):
            if sg[i] != -1:
                return sg[i]
            vis = [False] * n
            for j in range(i - 1):
                vis[win(j) ^ win(i - j - 2)] = True
            for j in range(n):
                if not vis[j]:
                    sg[i] = j
                    return j
            return 0

        n = len(currentState)
        sg = [-1] * (n + 1)
        sg[0] = sg[1] = 0
        ans = i = 0
        while i < n:
            j = i
            while j < n and currentState[j] == '+':
                j += 1
            ans ^= win(j - i)
            i = j + 1
        return ans > 0
```

```java
class Solution {
    private int n;
    private int[] sg;

    public boolean canWin(String currentState) {
        n = currentState.length();
        sg = new int[n + 1];
        Arrays.fill(sg, -1);
        int i = 0;
        int ans = 0;
        while (i < n) {
            int j = i;
            while (j < n && currentState.charAt(j) == '+') {
                ++j;
            }
            ans ^= win(j - i);
            i = j + 1;
        }
        return ans > 0;
    }

    private int win(int i) {
        if (sg[i] != -1) {
            return sg[i];
        }
        boolean[] vis = new boolean[n];
        for (int j = 0; j < i - 1; ++j) {
            vis[win(j) ^ win(i - j - 2)] = true;
        }
        for (int j = 0; j < n; ++j) {
            if (!vis[j]) {
                sg[i] = j;
                return j;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    bool canWin(string currentState) {
        int n = currentState.size();
        vector<int> sg(n + 1, -1);
        sg[0] = 0, sg[1] = 0;

        function<int(int)> win = [&](int i) {
            if (sg[i] != -1) return sg[i];
            vector<bool> vis(n);
            for (int j = 0; j < i - 1; ++j) vis[win(j) ^ win(i - j - 2)] = true;
            for (int j = 0; j < n; ++j)
                if (!vis[j]) return sg[i] = j;
            return 0;
        };

        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && currentState[j] == '+') ++j;
            ans ^= win(j - i);
            i = j + 1;
        }
        return ans > 0;
    }
};
```

```go
func canWin(currentState string) bool {
	n := len(currentState)
	sg := make([]int, n+1)
	for i := range sg {
		sg[i] = -1
	}
	var win func(i int) int
	win = func(i int) int {
		if sg[i] != -1 {
			return sg[i]
		}
		vis := make([]bool, n)
		for j := 0; j < i-1; j++ {
			vis[win(j)^win(i-j-2)] = true
		}
		for j := 0; j < n; j++ {
			if !vis[j] {
				sg[i] = j
				return j
			}
		}
		return 0
	}
	ans, i := 0, 0
	for i < n {
		j := i
		for j < n && currentState[j] == '+' {
			j++
		}
		ans ^= win(j - i)
		i = j + 1
	}
	return ans > 0
}
```

<!-- tabs:end -->

<!-- end -->
