# [294. 翻转游戏 II](https://leetcode.cn/problems/flip-game-ii)

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

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    int n;
    unordered_map<ll, bool> memo;

    bool canWin(string currentState) {
        n = currentState.size();
        ll mask = 0;
        for (int i = 0; i < n; ++i) if (currentState[i] == '+') mask |= 1ll << i;
        return dfs(mask);
    }

    bool dfs(ll mask) {
        if (memo.count(mask)) return memo[mask];
        for (int i = 0; i < n - 1; ++i)
        {
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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
