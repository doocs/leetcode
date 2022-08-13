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
<p><strong>Example 1:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Leaderboard:
    def __init__(self):
        self.player_scores = {}

    def addScore(self, playerId: int, score: int) -> None:
        self.player_scores[playerId] = self.player_scores.get(playerId, 0) + score

    def top(self, K: int) -> int:
        scores = sorted(list(self.player_scores.values()), reverse=True)
        return sum(scores[:K])

    def reset(self, playerId: int) -> None:
        self.player_scores[playerId] = 0


# Your Leaderboard object will be instantiated and called as such:
# obj = Leaderboard()
# obj.addScore(playerId,score)
# param_2 = obj.top(K)
# obj.reset(playerId)
```

### **Java**

```java
class Leaderboard {
    private Map<Integer, Integer> playerScores;

    public Leaderboard() {
        playerScores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        playerScores.put(playerId, playerScores.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        List<Integer> scores = new ArrayList<>(playerScores.values());
        Collections.sort(scores, Collections.reverseOrder());
        int res = 0;
        for (int i = 0; i < K; ++i) {
            res += scores.get(i);
        }
        return res;
    }

    public void reset(int playerId) {
        playerScores.put(playerId, 0);
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

### **...**

```

```

<!-- tabs:end -->
