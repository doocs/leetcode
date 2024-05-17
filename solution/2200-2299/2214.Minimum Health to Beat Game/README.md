---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2214.Minimum%20Health%20to%20Beat%20Game/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2214. 通关游戏所需的最低生命值 🔒](https://leetcode.cn/problems/minimum-health-to-beat-game)

[English Version](/solution/2200-2299/2214.Minimum%20Health%20to%20Beat%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在玩一个有 <code>n</code> 个关卡的游戏，从 <code>0</code> 到 <code>n - 1</code>。给你一个&nbsp;<strong>下标从 0&nbsp;开始&nbsp;</strong>的整数数组 <code>damage</code>，其中 <code>damage[i]</code> 是你完成第 <code>i</code> 个关卡所损失的生命值。</p>

<p>你也会得到一个整数 <code>armor</code>。你最多只能在&nbsp;<strong>任何&nbsp;</strong>等级使用&nbsp;<strong>一次&nbsp;</strong>护甲技能，这将保护你免受 <strong>最多</strong>&nbsp;<code>armor</code> 伤害。</p>

<p>你必须按顺序完成关卡，并且你的生命值必须一直&nbsp;<strong>大于</strong> <code>0</code> 才能通关。</p>

<p>返回<em>你开始通关所需的最低生命值。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> damage = [2,7,4,3], armor = 4
<strong>输出:</strong> 13
<strong>解释:</strong> 从 13 生命值开始通关游戏的最佳方法是:
第 1 回合，受到 2 点伤害。你还有 13 - 2 = 11 生命值。
第 2 回合，受到 7 点伤害。你还有 11 - 7 = 4 生命值。
第 3 回合，使用你的护甲保护你免受 4 点伤害。你有 4 - 0 = 4 生命值。
第 4 回合，受到 3 点伤害。你还有 4 - 3 = 1 生命值。
注意，13 是你开始时通关游戏所需的最低生命值。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> damage = [2,5,3,4], armor = 7
<strong>输出:</strong> 10
<strong>解释:</strong> 从 10 生命值开始通关游戏的最佳方法是:
第 1 回合，受到 2 点伤害。你还有 10 - 2 = 8 生命值。
第 2 回合，使用护甲保护自己免受 5 点伤害。你还有 8 - 0 = 8 生命值。
第 3 回合，受到 3 点伤害。你还有 8 - 3 = 5 生命值。
第 4 回合，受到 4 点伤害。你还有 5 - 4 = 1 生命值。
注意，10 是你开始通关所需的最低生命值。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> damage = [3,3,3], armor = 0
<strong>输出:</strong> 10
<strong>解释:</strong> 从 10 生命值开始通关游戏的最佳方法是:
第 1 回合，受到 2 点伤害。你还有 10 - 3 = 7 生命值。
第 2 回合，受到 3 点伤害。你还有 7 - 3 = 4 生命值。
第 3 回合， 受到 3 点伤害。你还有 4 - 3 = 1 生命值。
注意你没有使用护甲技能。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == damage.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= damage[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= armor &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以贪心地选择在伤害值最大的回合中使用一次护甲技能，假设伤害值最大为 $mx$，那么我们可以免受 $min(mx, armor)$ 的伤害，因此我们需要的最小生命值为 $sum(damage) - min(mx, armor) + 1$。

时间复杂度 $O(n)$，其中 $n$ 为数组 `damage` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumHealth(self, damage: List[int], armor: int) -> int:
        return sum(damage) - min(max(damage), armor) + 1
```

#### Java

```java
class Solution {
    public long minimumHealth(int[] damage, int armor) {
        long s = 0;
        int mx = damage[0];
        for (int v : damage) {
            s += v;
            mx = Math.max(mx, v);
        }
        return s - Math.min(mx, armor) + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumHealth(vector<int>& damage, int armor) {
        long long s = 0;
        int mx = damage[0];
        for (int& v : damage) {
            s += v;
            mx = max(mx, v);
        }
        return s - min(mx, armor) + 1;
    }
};
```

#### Go

```go
func minimumHealth(damage []int, armor int) int64 {
	var s int64
	var mx int
	for _, v := range damage {
		s += int64(v)
		mx = max(mx, v)
	}
	return s - int64(min(mx, armor)) + 1
}
```

#### TypeScript

```ts
function minimumHealth(damage: number[], armor: number): number {
    let s = 0;
    let mx = 0;
    for (const v of damage) {
        mx = Math.max(mx, v);
        s += v;
    }
    return s - Math.min(mx, armor) + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
