# [464. 我能赢吗](https://leetcode.cn/problems/can-i-win)

[English Version](/solution/0400-0499/0464.Can%20I%20Win/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 "100 game" 这个游戏中，两名玩家轮流选择从 <code>1</code> 到 <code>10</code> 的任意整数，累计整数和，先使得累计整数和 <strong>达到或超过</strong>&nbsp; 100 的玩家，即为胜者。</p>

<p>如果我们将游戏规则改为 “玩家 <strong>不能</strong> 重复使用整数” 呢？</p>

<p>例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 &gt;= 100。</p>

<p>给定两个整数&nbsp;<code>maxChoosableInteger</code>&nbsp;（整数池中可选择的最大数）和&nbsp;<code>desiredTotal</code>（累计和），若先出手的玩家能稳赢则返回 <code>true</code>&nbsp;，否则返回 <code>false</code> 。假设两位玩家游戏时都表现 <strong>最佳</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>maxChoosableInteger = 10, desiredTotal = 11
<strong>输出：</strong>false
<strong>解释：
</strong>无论第一个玩家选择哪个整数，他都会失败。
第一个玩家可以选择从 1 到 10 的整数。
如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
第二个玩家可以通过选择整数 10（那么累积和为 11 &gt;= desiredTotal），从而取得胜利.
同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>maxChoosableInteger = 10, desiredTotal = 0
<b>输出：</b>true
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>maxChoosableInteger = 10, desiredTotal = 1
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= maxChoosableInteger &lt;= 20</code></li>
	<li><code>0 &lt;= desiredTotal &lt;= 300</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        @cache
        def dfs(state, t):
            for i in range(1, maxChoosableInteger + 1):
                if (state >> i) & 1:
                    continue
                if t + i >= desiredTotal or not dfs(state | 1 << i, t + i):
                    return True
            return False

        s = (1 + maxChoosableInteger) * maxChoosableInteger // 2
        if s < desiredTotal:
            return False
        return dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int s = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (s < desiredTotal) {
            return false;
        }
        return dfs(0, 0, maxChoosableInteger, desiredTotal);
    }

    private boolean dfs(int state, int t, int maxChoosableInteger, int desiredTotal) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        boolean res = false;
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if (((state >> i) & 1) == 0) {
                if (t + i >= desiredTotal
                    || !dfs(state | 1 << i, t + i, maxChoosableInteger, desiredTotal)) {
                    res = true;
                    break;
                }
            }
        }
        memo.put(state, res);
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        int s = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (s < desiredTotal) return false;
        unordered_map<int, bool> memo;
        return dfs(0, 0, maxChoosableInteger, desiredTotal, memo);
    }

    bool dfs(int state, int t, int maxChoosableInteger, int desiredTotal, unordered_map<int, bool>& memo) {
        if (memo.count(state)) return memo[state];
        bool res = false;
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if ((state >> i) & 1) continue;
            if (t + i >= desiredTotal || !dfs(state | 1 << i, t + i, maxChoosableInteger, desiredTotal, memo)) {
                res = true;
                break;
            }
        }
        memo[state] = res;
        return res;
    }
};
```

### **Go**

```go
func canIWin(maxChoosableInteger int, desiredTotal int) bool {
	s := (1 + maxChoosableInteger) * maxChoosableInteger / 2
	if s < desiredTotal {
		return false
	}
	memo := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(state, t int) bool {
		if v, ok := memo[state]; ok {
			return v
		}
		res := false
		for i := 1; i <= maxChoosableInteger; i++ {
			if (state>>i)&1 == 1 {
				continue
			}
			if t+i >= desiredTotal || !dfs(state|1<<i, t+i) {
				res = true
				break
			}
		}
		memo[state] = res
		return res
	}
	return dfs(0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
