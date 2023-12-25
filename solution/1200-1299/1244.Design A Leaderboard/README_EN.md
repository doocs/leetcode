# [1244. Design A Leaderboard](https://leetcode.com/problems/design-a-leaderboard)

[中文文档](/solution/1200-1299/1244.Design%20A%20Leaderboard/README.md)

## Description

<p>Design a Leaderboard class, which has 3 functions:</p>

<ol>
	<li><code>addScore(playerId, score)</code>: Update the leaderboard by adding <code>score</code> to the given player&#39;s score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given <code>score</code>.</li>
	<li><code>top(K)</code>: Return the score sum of the top <code>K</code> players.</li>
	<li><code>reset(playerId)</code>: Reset the score of the player with the given id&nbsp;to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.</li>
</ol>

<p>Initially, the leaderboard is empty.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<b>Input: </b>
[&quot;Leaderboard&quot;,&quot;addScore&quot;,&quot;addScore&quot;,&quot;addScore&quot;,&quot;addScore&quot;,&quot;addScore&quot;,&quot;top&quot;,&quot;reset&quot;,&quot;reset&quot;,&quot;addScore&quot;,&quot;top&quot;]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
<b>Output: </b>
[null,null,null,null,null,null,73,null,null,null,141]

<b>Explanation: </b>
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

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= playerId, K &lt;= 10000</code></li>
	<li>It&#39;s guaranteed that <code>K</code> is less than or equal to the current number of players.</li>
	<li><code>1 &lt;= score&nbsp;&lt;= 100</code></li>
	<li>There will be at most <code>1000</code>&nbsp;function calls.</li>
</ul>

## Solutions

**Solution 1: Hash Table + Ordered List**

We use a hash table $d$ to record the scores of each player, and an ordered list $rank$ to record the scores of all players.

When the `addScore` function is called, we first check if the player is in the hash table $d$. If not, we add their score to the ordered list $rank$. Otherwise, we first remove their score from the ordered list $rank$, then add their updated score to the ordered list $rank$, and finally update the score in the hash table $d$. The time complexity is $O(\log n)$.

When the `top` function is called, we directly return the sum of the first $K$ elements in the ordered list $rank$. The time complexity is $O(K \times \log n)$.

When the `reset` function is called, we first remove the player from the hash table $d$, then remove their score from the ordered list $rank$. The time complexity is $O(\log n)$.

The space complexity is $O(n)$, where $n$ is the number of players.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
