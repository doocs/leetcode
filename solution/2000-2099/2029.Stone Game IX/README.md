---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2029.Stone%20Game%20IX/README.md
rating: 2277
source: 第 261 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 数学
    - 计数
    - 博弈
---

<!-- problem:start -->

# [2029. 石子游戏 IX](https://leetcode.cn/problems/stone-game-ix)

[English Version](/solution/2000-2099/2029.Stone%20Game%20IX/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 分情况讨论

由于玩家的目标是使得已移除石子的价值总和不能被 $3$ 整除，因此我们只需要考虑每个石子的价值对 $3$ 的余数即可。

我们用一个长度为 $3$ 的数组 $\textit{cnt}$ 维护当前剩余石子的价值对 $3$ 的余数的个数，其中 $\textit{cnt}[0]$ 表示余数为 $0$ 的个数，而 $\textit{cnt}[1]$ 和 $\textit{cnt}[2]$ 分别表示余数为 $1$ 和 $2$ 的个数。

在第一回合，Alice 不能移除余数为 $0$ 的石子，因为这样会使得已移除石子的价值总和能被 $3$ 整除。因此，Alice 只能移除余数为 $1$ 或 $2$ 的石子。

我们首先考虑 Alice 移除余数为 $1$ 的石子的情况。如果 Alice 移除了一个余数为 $1$ 的石子，石子 $0$ 对石子价值总和对 $3$ 的余数不会改变，因此价值对 $3$ 的余数为 $0$ 的石子可以在任意回合被移除，我们暂时不考虑。所以 Bob 也只能移除余数为 $1$ 的石子，之后 Alice 移除余数为 $2$ 的石子，依次进行，序列为 $1, 1, 2, 1, 2, \ldots$。在这种情况下，如果最终回合数为奇数，且还有剩余石子，那么 Alice 获胜，否则 Bob 获胜。

对于第一回合 Alice 移除余数为 $2$ 的石子的情况，我们可以得到类似的结论。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{stones}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(cnt: List[int]) -> bool:
            if cnt[1] == 0:
                return False
            cnt[1] -= 1
            r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0]
            if cnt[1] > cnt[2]:
                cnt[1] -= 1
                r += 1
            return r % 2 == 1 and cnt[1] != cnt[2]

        c1 = [0] * 3
        for x in stones:
            c1[x % 3] += 1
        c2 = [c1[0], c1[2], c1[1]]
        return check(c1) or check(c2)
```

#### Java

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c1 = new int[3];
        for (int x : stones) {
            c1[x % 3]++;
        }
        int[] c2 = {c1[0], c1[2], c1[1]};
        return check(c1) || check(c2);
    }

    private boolean check(int[] cnt) {
        if (--cnt[1] < 0) {
            return false;
        }
        int r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 == 1 && cnt[1] != cnt[2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c1(3);
        for (int x : stones) {
            ++c1[x % 3];
        }
        vector<int> c2 = {c1[0], c1[2], c1[1]};
        auto check = [](auto& cnt) -> bool {
            if (--cnt[1] < 0) {
                return false;
            }
            int r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0];
            if (cnt[1] > cnt[2]) {
                --cnt[1];
                ++r;
            }
            return r % 2 && cnt[1] != cnt[2];
        };
        return check(c1) || check(c2);
    }
};
```

#### Go

```go
func stoneGameIX(stones []int) bool {
	c1 := [3]int{}
	for _, x := range stones {
		c1[x%3]++
	}
	c2 := [3]int{c1[0], c1[2], c1[1]}
	check := func(cnt [3]int) bool {
		if cnt[1] == 0 {
			return false
		}
		cnt[1]--
		r := 1 + min(cnt[1], cnt[2])*2 + cnt[0]
		if cnt[1] > cnt[2] {
			cnt[1]--
			r++
		}
		return r%2 == 1 && cnt[1] != cnt[2]
	}
	return check(c1) || check(c2)
}
```

#### TypeScript

```ts
function stoneGameIX(stones: number[]): boolean {
    const c1: number[] = Array(3).fill(0);
    for (const x of stones) {
        ++c1[x % 3];
    }
    const c2: number[] = [c1[0], c1[2], c1[1]];
    const check = (cnt: number[]): boolean => {
        if (--cnt[1] < 0) {
            return false;
        }
        let r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 === 1 && cnt[1] !== cnt[2];
    };
    return check(c1) || check(c2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
