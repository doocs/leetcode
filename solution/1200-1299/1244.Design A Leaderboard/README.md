---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1244.Design%20A%20Leaderboard/README.md
rating: 1354
tags:
    - 设计
    - 哈希表
    - 排序
---

# [1244. 力扣排行榜 🔒](https://leetcode.cn/problems/design-a-leaderboard)

[English Version](/solution/1200-1299/1244.Design%20A%20Leaderboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>新一轮的「力扣杯」编程大赛即将启动，为了动态显示参赛者的得分数据，需要设计一个排行榜 Leaderboard。</p>

<p>请你帮忙来设计这个 <code>Leaderboard</code> 类，使得它有如下 3 个函数：</p>

<ol>
	<li><code>addScore(playerId, score)</code>：

    <ul>
    	<li>假如参赛者已经在排行榜上，就给他的当前得分增加 <code>score</code> 点分值并更新排行。</li>
    	<li>假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 <code>score</code>。</li>
    </ul>
    </li>
    <li><code>top(K)</code>：返回前 <code>K</code> 名参赛者的 <strong>得分总和</strong>。</li>
    <li><code>reset(playerId)</code>：将指定参赛者的成绩清零（换句话说，将其从排行榜中删除）。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。</li>

</ol>

<p>请注意，在初始状态下，排行榜是空的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
<strong>输出：</strong>
[null,null,null,null,null,null,73,null,null,null,141]

<strong>解释： </strong>
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= playerId, K <= 10000</code></li>
	<li>题目保证 <code>K</code> 小于或等于当前参赛者的数量</li>
	<li><code>1 <= score <= 100</code></li>
	<li>最多进行 <code>1000</code> 次函数调用</li>
</ul>

## 解法

### 方法一：哈希表 + 有序列表

我们用哈希表 $d$ 记录每个参赛者的分数，用有序列表 $rank$ 记录所有参赛者的分数。

当调用 `addScore` 函数时，我们先判断参赛者是否在哈希表 $d$ 中，如果不在，我们将其分数加入有序列表 $rank$ 中，否则我们先将其分数从有序列表 $rank$ 中删除，再将其分数加入有序列表 $rank$ 中，最后更新哈希表 $d$ 中的分数。时间复杂度 $O(\log n)$。

当调用 `top` 函数时，我们直接返回有序列表 $rank$ 中前 $K$ 个元素的和。时间复杂度 $O(K \times \log n)$。

当调用 `reset` 函数时，我们先移除哈希表 $d$ 中的参赛者，再将其分数从有序列表 $rank$ 中移除。时间复杂度 $O(\log n)$。

空间复杂度 $O(n)$。其中 $n$ 为参赛者的数量。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Leaderboard:
    def __init__(self):
        self.d = defaultdict(int)
        self.rank = SortedList()

    def addScore(self, playerId: int, score: int) -> None:
        if playerId not in self.d:
            self.d[playerId] = score
            self.rank.add(score)
        else:
            self.rank.remove(self.d[playerId])
            self.d[playerId] += score
            self.rank.add(self.d[playerId])

    def top(self, K: int) -> int:
        return sum(self.rank[-K:])

    def reset(self, playerId: int) -> None:
        self.rank.remove(self.d.pop(playerId))


# Your Leaderboard object will be instantiated and called as such:
# obj = Leaderboard()
# obj.addScore(playerId,score)
# param_2 = obj.top(K)
# obj.reset(playerId)
```

```java
class Leaderboard {
    private Map<Integer, Integer> d = new HashMap<>();
    private TreeMap<Integer, Integer> rank = new TreeMap<>((a, b) -> b - a);

    public Leaderboard() {
    }

    public void addScore(int playerId, int score) {
        d.merge(playerId, score, Integer::sum);
        int newScore = d.get(playerId);
        if (newScore != score) {
            rank.merge(newScore - score, -1, Integer::sum);
        }
        rank.merge(newScore, 1, Integer::sum);
    }

    public int top(int K) {
        int ans = 0;
        for (var e : rank.entrySet()) {
            int score = e.getKey(), cnt = e.getValue();
            cnt = Math.min(cnt, K);
            ans += score * cnt;
            K -= cnt;
            if (K == 0) {
                break;
            }
        }
        return ans;
    }

    public void reset(int playerId) {
        int score = d.remove(playerId);
        if (rank.merge(score, -1, Integer::sum) == 0) {
            rank.remove(score);
        }
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
```

```cpp
class Leaderboard {
public:
    Leaderboard() {
    }

    void addScore(int playerId, int score) {
        d[playerId] += score;
        int newScore = d[playerId];
        if (newScore != score) {
            rank.erase(rank.find(newScore - score));
        }
        rank.insert(newScore);
    }

    int top(int K) {
        int ans = 0;
        for (auto& x : rank) {
            ans += x;
            if (--K == 0) {
                break;
            }
        }
        return ans;
    }

    void reset(int playerId) {
        int score = d[playerId];
        d.erase(playerId);
        rank.erase(rank.find(score));
    }

private:
    unordered_map<int, int> d;
    multiset<int, greater<int>> rank;
};

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard* obj = new Leaderboard();
 * obj->addScore(playerId,score);
 * int param_2 = obj->top(K);
 * obj->reset(playerId);
 */
```

```rust
use std::collections::BTreeMap;

#[allow(dead_code)]
struct Leaderboard {
    /// This also keeps track of the top K players since it's implicitly sorted
    record_map: BTreeMap<i32, i32>,
}

impl Leaderboard {
    #[allow(dead_code)]
    fn new() -> Self {
        Self {
            record_map: BTreeMap::new(),
        }
    }

    #[allow(dead_code)]
    fn add_score(&mut self, player_id: i32, score: i32) {
        if self.record_map.contains_key(&player_id) {
            // The player exists, just add the score
            self.record_map.insert(player_id, self.record_map.get(&player_id).unwrap() + score);
        } else {
            // Add the new player to the map
            self.record_map.insert(player_id, score);
        }
    }

    #[allow(dead_code)]
    fn top(&self, k: i32) -> i32 {
        let mut cur_vec: Vec<(i32, i32)> = self.record_map
            .iter()
            .map(|(k, v)| (*k, *v))
            .collect();
        cur_vec.sort_by(|lhs, rhs| { rhs.1.cmp(&lhs.1) });
        // Iterate reversely for K
        let mut sum = 0;
        let mut i = 0;
        for (_, value) in &cur_vec {
            if i == k {
                break;
            }
            sum += value;
            i += 1;
        }

        sum
    }

    #[allow(dead_code)]
    fn reset(&mut self, player_id: i32) {
        // The player is ensured to exist in the board
        // Just set the score to 0
        self.record_map.insert(player_id, 0);
    }
}
```

<!-- tabs:end -->

<!-- end -->
