---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README.md
rating: 1381
source: 第 87 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [2410. 运动员和训练师的最大匹配数](https://leetcode.cn/problems/maximum-matching-of-players-with-trainers)

[English Version](/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>players</code>&nbsp;，其中&nbsp;<code>players[i]</code>&nbsp;表示第 <code>i</code>&nbsp;名运动员的 <strong>能力</strong>&nbsp;值，同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>trainers</code>&nbsp;，其中&nbsp;<code>trainers[j]</code>&nbsp;表示第 <code>j</code>&nbsp;名训练师的 <strong>训练能力值</strong>&nbsp;。</p>

<p>如果第 <code>i</code>&nbsp;名运动员的能力值 <strong>小于等于</strong>&nbsp;第 <code>j</code>&nbsp;名训练师的能力值，那么第&nbsp;<code>i</code>&nbsp;名运动员可以 <strong>匹配</strong>&nbsp;第&nbsp;<code>j</code>&nbsp;名训练师。除此以外，每名运动员至多可以匹配一位训练师，每位训练师最多可以匹配一位运动员。</p>

<p>请你返回满足上述要求&nbsp;<code>players</code>&nbsp;和 <code>trainers</code>&nbsp;的 <strong>最大</strong> 匹配数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>players = [4,7,9], trainers = [8,2,5,8]
<b>输出：</b>2
<b>解释：</b>
得到两个匹配的一种方案是：
- players[0] 与 trainers[0] 匹配，因为 4 &lt;= 8 。
- players[1] 与 trainers[3] 匹配，因为 7 &lt;= 8 。
可以证明 2 是可以形成的最大匹配数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>players = [1,1,1], trainers = [10]
<b>输出：</b>1
<b>解释：</b>
训练师可以匹配所有 3 个运动员
每个运动员至多只能匹配一个训练师，所以最大答案是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= players.length, trainers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= players[i], trainers[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 双指针

根据题目描述，每位运动员应该尽可能匹配能力值最接近的训练师，因此我们可以对运动员和训练师的能力值进行排序，然后使用双指针的方法进行匹配。

我们用两个指针 $i$ 和 $j$ 分别指向运动员和训练师的数组，初始时都指向数组的起始位置。然后我们逐个遍历运动员的能力值，如果当前训练师的能力值小于当前运动员的能力值，我们就将训练师的指针向右移动一位，直到找到一个能力值大于等于当前运动员的训练师。如果找不到这样的训练师，说明当前运动员无法匹配任何训练师，此时我们返回当前运动员的下标即可。否则，，我们可以将当前运动员和训练师匹配，然后将两个指针都向右移动一位。继续这个过程直到遍历完所有的运动员。

如果我们遍历完所有的运动员，说明所有的运动员都能匹配到训练师，此时我们返回运动员的数量即可。

时间复杂度 $O(m \times \log m + n \times \log n)$，空间复杂度 $O(\max(\log m, \log n))$。其中 $m$ 和 $n$ 分别为运动员和训练师的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j, n = 0, len(trainers)
        for i, p in enumerate(players):
            while j < n and trainers[j] < p:
                j += 1
            if j == n:
                return i
            j += 1
        return len(players)
```

#### Java

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int m = players.length, n = trainers.length;
        for (int i = 0, j = 0; i < m; ++i, ++j) {
            while (j < n && trainers[j] < players[i]) {
                ++j;
            }
            if (j == n) {
                return i;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        ranges::sort(players);
        ranges::sort(trainers);
        int m = players.size(), n = trainers.size();
        for (int i = 0, j = 0; i < m; ++i, ++j) {
            while (j < n && trainers[j] < players[i]) {
                ++j;
            }
            if (j == n) {
                return i;
            }
        }
        return m;
    }
};
```

#### Go

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	m, n := len(players), len(trainers)
	for i, j := 0, 0; i < m; i, j = i+1, j+1 {
		for j < n && trainers[j] < players[i] {
			j++
		}
		if j == n {
			return i
		}
	}
	return m
}
```

#### TypeScript

```ts
function matchPlayersAndTrainers(players: number[], trainers: number[]): number {
    players.sort((a, b) => a - b);
    trainers.sort((a, b) => a - b);
    const [m, n] = [players.length, trainers.length];
    for (let i = 0, j = 0; i < m; ++i, ++j) {
        while (j < n && trainers[j] < players[i]) {
            ++j;
        }
        if (j === n) {
            return i;
        }
    }
    return m;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
