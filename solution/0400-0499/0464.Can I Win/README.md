---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0464.Can%20I%20Win/README.md
tags:
    - 位运算
    - 记忆化搜索
    - 数学
    - 动态规划
    - 状态压缩
    - 博弈
---

<!-- problem:start -->

# [464. 我能赢吗](https://leetcode.cn/problems/can-i-win)

[English Version](/solution/0400-0499/0464.Can%20I%20Win/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 记忆化搜索

我们首先判断可以选择的所有整数的和是否小于目标值，如果是，说明无论如何都无法赢，直接返回 `false`。

然后，我们设计一个函数 $\text{dfs}(mask, s)$，其中 `mask` 表示当前已选择的整数的状态，`s` 表示当前的累计和。函数返回值为当前玩家是否能赢。

函数 $\text{dfs}(mask, s)$ 的执行过程如下：

我们遍历 $1$ 到 $maxChoosableInteger$ 中的每个整数 $i$，如果 $i$ 还没有被选择，我们可以选择 $i$，如果选择 $i$ 后的累计和 $s + i$ 大于等于目标值 `desiredTotal`，或者对手选择 $i$ 后的结果是输的，那么当前玩家就是赢的，返回 `true`。

如果没有任何一个选择能让当前玩家赢，那么当前玩家就是输的，返回 `false`。

为了避免重复计算，我们使用一个哈希表 `f` 记录已经计算过的状态，键为 `mask`，值为当前玩家是否能赢。

时间复杂度 $O(2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 是 `maxChoosableInteger`。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        @cache
        def dfs(mask: int, s: int) -> bool:
            for i in range(1, maxChoosableInteger + 1):
                if mask >> i & 1 ^ 1:
                    if s + i >= desiredTotal or not dfs(mask | 1 << i, s + i):
                        return True
            return False

        if (1 + maxChoosableInteger) * maxChoosableInteger // 2 < desiredTotal:
            return False
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Map<Integer, Boolean> f = new HashMap<>();
    private int maxChoosableInteger;
    private int desiredTotal;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        this.maxChoosableInteger = maxChoosableInteger;
        this.desiredTotal = desiredTotal;
        return dfs(0, 0);
    }

    private boolean dfs(int mask, int s) {
        if (f.containsKey(mask)) {
            return f.get(mask);
        }
        for (int i = 0; i < maxChoosableInteger; ++i) {
            if ((mask >> i & 1) == 0) {
                if (s + i + 1 >= desiredTotal || !dfs(mask | 1 << i, s + i + 1)) {
                    f.put(mask, true);
                    return true;
                }
            }
        }
        f.put(mask, false);
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        unordered_map<int, int> f;
        function<bool(int, int)> dfs = [&](int mask, int s) {
            if (f.contains(mask)) {
                return f[mask];
            }
            for (int i = 0; i < maxChoosableInteger; ++i) {
                if (mask >> i & 1 ^ 1) {
                    if (s + i + 1 >= desiredTotal || !dfs(mask | 1 << i, s + i + 1)) {
                        return f[mask] = true;
                    }
                }
            }
            return f[mask] = false;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func canIWin(maxChoosableInteger int, desiredTotal int) bool {
	if (1+maxChoosableInteger)*maxChoosableInteger/2 < desiredTotal {
		return false
	}
	f := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(mask, s int) bool {
		if v, ok := f[mask]; ok {
			return v
		}
		for i := 1; i <= maxChoosableInteger; i++ {
			if mask>>i&1 == 0 {
				if s+i >= desiredTotal || !dfs(mask|1<<i, s+i) {
					f[mask] = true
					return true
				}
			}
		}
		f[mask] = false
		return false
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function canIWin(maxChoosableInteger: number, desiredTotal: number): boolean {
    if (((1 + maxChoosableInteger) * maxChoosableInteger) / 2 < desiredTotal) {
        return false;
    }
    const f: Record<string, boolean> = {};
    const dfs = (mask: number, s: number): boolean => {
        if (f.hasOwnProperty(mask)) {
            return f[mask];
        }
        for (let i = 1; i <= maxChoosableInteger; ++i) {
            if (((mask >> i) & 1) ^ 1) {
                if (s + i >= desiredTotal || !dfs(mask ^ (1 << i), s + i)) {
                    return (f[mask] = true);
                }
            }
        }
        return (f[mask] = false);
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
