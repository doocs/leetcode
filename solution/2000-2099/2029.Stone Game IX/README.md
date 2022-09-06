# [2029. 石子游戏 IX](https://leetcode.cn/problems/stone-game-ix)

[English Version](/solution/2000-2099/2029.Stone%20Game%20IX/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 <code>stones</code> ，其中 <code>stones[i]</code> 是第 <code>i</code> 个石子的价值。</p>

<p>Alice 和 Bob 轮流进行自己的回合，<strong>Alice</strong> 先手。每一回合，玩家需要从 <code>stones</code>&nbsp;中移除任一石子。</p>

<ul>
	<li>如果玩家移除石子后，导致 <strong>所有已移除石子</strong> 的价值&nbsp;<strong>总和</strong> 可以被 3 整除，那么该玩家就 <strong>输掉游戏</strong> 。</li>
	<li>如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。</li>
</ul>

<p>假设两位玩家均采用&nbsp;<strong>最佳</strong> 决策。如果 Alice 获胜，返回 <code>true</code> ；如果 Bob 获胜，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [2,1]
<strong>输出：</strong>true
<strong>解释：</strong>游戏进行如下：
- 回合 1：Alice 可以移除任意一个石子。
- 回合 2：Bob 移除剩下的石子。 
已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [2]
<strong>输出：</strong>false
<strong>解释：</strong>Alice 会移除唯一一个石子，已移除石子的值总和为 2 。 
由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>stones = [5,1,2,4,3]
<strong>输出：</strong>false
<strong>解释：</strong>Bob 总会获胜。其中一种可能的游戏进行方式如下：
- 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
- 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
- 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
- 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
- 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= stones[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于我们只关心总和能否被 3 整除，我们可以将 stones 按照模 3 的结果进行分组计数。

根据题意，第一回合不能移除 0，否则直接输掉游戏，因此第一回合只能移除 1 或者 2。我们可以枚举这两种情况，如果其中一种可以让 Alice 获胜就返回 true，否则返回 false。

下面以第一回合移除 1 来说明。在不考虑移除 0 的前提下，后面的移除由于要满足总和不能被 3 整除，因此移除的石子是固定的，整体构成一个 112121212… 循环的序列。

对于 0，由于移除之后不会改变总和模 3 的结果，因此不会改变后续 1 和 2 的移除顺序，所以我们可以在序列的任意非开头位置插入 0。

两人为了不让自己输掉，必然会按照上述序列进行，直到没有石子，或某一方只能移除导致总和被 3 整除的石子时分出胜负。因此我们需要求出让总和不能被 3 整除的最大的回合数，这相当于 112121212... 序列的最长长度，加上 0 的个数。

若该回合数为奇数，且还有剩余石子，那么下一回合要轮到 Bob 移除石子，且他只能移除一枚让总和被 3 整除的石子，于是 Alice 获胜；否则 Bob 获胜。

对于第一回合移除 2 的情况，同样会构成一个 221212121... 循环的序列，做法同上。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(c):
            if c[1] == 0:
                return False
            c[1] -= 1
            turn = 1 + min(c[1], c[2]) * 2 + c[0]
            if c[1] > c[2]:
                turn += 1
                c[1] -= 1
            return turn % 2 == 1 and c[1] != c[2]

        c = [0] * 3
        for s in stones:
            c[s % 3] += 1
        c1 = [c[0], c[2], c[1]]
        return check(c) or check(c1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c = new int[3];
        for (int s : stones) {
            ++c[s % 3];
        }
        int[] t = new int[] {c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    private boolean check(int[] c) {
        if (c[1] == 0) {
            return false;
        }
        --c[1];
        int turn = 1 + Math.min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c(3);
        for (int s : stones) ++c[s % 3];
        vector<int> t = {c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    bool check(vector<int>& c) {
        if (c[1] == 0) return false;
        --c[1];
        int turn = 1 + min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
};
```

### **Go**

```go
func stoneGameIX(stones []int) bool {
	check := func(c [3]int) bool {
		if c[1] == 0 {
			return false
		}
		c[1]--
		turn := 1 + min(c[1], c[2])*2 + c[0]
		if c[1] > c[2] {
			c[1]--
			turn++
		}
		return turn%2 == 1 && c[1] != c[2]
	}
	c := [3]int{}
	for _, s := range stones {
		c[s%3]++
	}
	return check(c) || check([3]int{c[0], c[2], c[1]})
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
