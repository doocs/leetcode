# [1244. 力扣排行榜](https://leetcode.cn/problems/design-a-leaderboard)

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

<!-- 这里可写通用的实现逻辑 -->

用哈希表存放每个 playerId 所对应的分数。

计算 topK 时，取出所有的分数，进行排序，获取前 K 个分数，累加得到结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
