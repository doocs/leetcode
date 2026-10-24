---
comments: true
difficulty: 中等
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

<!-- thinking:start -->

> **思考**
>
> $n,m \le 10^5$，两两尝试配对会超时。训练师若能匹配更弱的运动员，也就能匹配更强的运动员，所以应把刚好够用的训练师分给当前最弱的运动员。
>
> 两边按能力升序排列后，从弱到强处理运动员，训练师指针只向前走：能力不足的跳过，遇到第一个够用的就配上一对。总时间由排序决定。

<!-- thinking:end -->

把运动员和训练师都按能力从小到大排序。指针 $i$ 和 $j$ 分别指向当前考虑到的运动员和训练师。

从最弱的运动员开始匹配。训练师能力低于这名运动员时，将 $j$ 右移，直到能力足够，或训练师已经用完。训练师用完后，后面的运动员只会更强，匹配结束，答案就是已经配上的人数 $i$。否则当前这一对可以匹配，两个指针各前进一步。所有运动员都匹配成功时，返回运动员人数。

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

#### Rust

```rust
impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        let n = trainers.len();
        for (i, &p) in players.iter().enumerate() {
            while j < n && trainers[j] < p {
                j += 1;
            }
            if j == n {
                return i as i32;
            }
            j += 1;
        }
        players.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：贪心 + 双指针（遍历训练师）

<!-- thinking:start -->

> **思考**
>
> 方法一在处理每名运动员时，还要用内层循环跳过能力不足的训练师，训练师用完则提前返回。改成遍历训练师之后，这两步都可以省去。
>
> 两边按能力升序排列，从弱到强查看训练师，指针停在最弱的未匹配运动员上。训练师能力不足时，剩余运动员只会更强，直接跳过。能力足够就完成匹配，指针前进一步。指针最终的位置就是匹配数，复杂度与方法一相同。

<!-- thinking:end -->

同样先把运动员和训练师按能力从小到大排序。指针 $j$ 指向尚未匹配的最弱运动员，初始为 $0$。

从弱到强查看每名训练师。若仍有运动员未匹配，且该运动员的能力不超过这名训练师，就配成一对，并将 $j$ 加一。否则这名训练师无法匹配剩下的任何运动员，直接跳过。遍历结束后，$j$ 就是最大匹配数。

时间复杂度 $O(m \times \log m + n \times \log n)$，空间复杂度 $O(\max(\log m, \log n))$。其中 $m$ 和 $n$ 分别为运动员和训练师的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j = 0
        for t in trainers:
            if j < len(players) and players[j] <= t:
                j += 1
        return j
```

#### Java

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int j = 0, m = players.length;
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
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
        int j = 0, m = players.size();
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
    }
};
```

#### Go

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	j, m := 0, len(players)
	for _, t := range trainers {
		if j < m && players[j] <= t {
			j++
		}
	}
	return j
}
```

#### TypeScript

```ts
function matchPlayersAndTrainers(players: number[], trainers: number[]): number {
    players.sort((a, b) => a - b);
    trainers.sort((a, b) => a - b);
    let j = 0;
    const m = players.length;
    for (const t of trainers) {
        if (j < m && players[j] <= t) {
            ++j;
        }
    }
    return j;
}
```

#### Rust

```rust
impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        for &t in &trainers {
            if j < players.len() && players[j] <= t {
                j += 1;
            }
        }
        j as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
