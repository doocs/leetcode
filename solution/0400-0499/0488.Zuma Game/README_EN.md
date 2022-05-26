# [488. Zuma Game](https://leetcode.com/problems/zuma-game)

[中文文档](/solution/0400-0499/0488.Zuma%20Game/README.md)

## Description

<p>You are playing a variation of the game Zuma.</p>

<p>In this variation of Zuma, there is a <strong>single row</strong> of colored balls on a board, where each ball can be colored red <code>&#39;R&#39;</code>, yellow <code>&#39;Y&#39;</code>, blue <code>&#39;B&#39;</code>, green <code>&#39;G&#39;</code>, or white <code>&#39;W&#39;</code>. You also have several colored balls in your hand.</p>

<p>Your goal is to <strong>clear all</strong> of the balls from the board. On each turn:</p>

<ul>
	<li>Pick <strong>any</strong> ball from your hand and insert it in between two balls in the row or on either end of the row.</li>
	<li>If there is a group of <strong>three or more consecutive balls</strong> of the <strong>same color</strong>, remove the group of balls from the board.
	<ul>
		<li>If this removal causes more groups of three or more of the same color to form, then continue removing each group until there are none left.</li>
	</ul>
	</li>
	<li>If there are no more balls on the board, then you win the game.</li>
	<li>Repeat this process until you either win or do not have any more balls in your hand.</li>
</ul>

<p>Given a string <code>board</code>, representing the row of balls on the board, and a string <code>hand</code>, representing the balls in your hand, return <em>the <strong>minimum</strong> number of balls you have to insert to clear all the balls from the board. If you cannot clear all the balls from the board using the balls in your hand, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WRRBBW&quot;, hand = &quot;RB&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to clear all the balls. The best you can do is:
- Insert &#39;R&#39; so the board becomes WRR<u>R</u>BBW. W<u>RRR</u>BBW -&gt; WBBW.
- Insert &#39;B&#39; so the board becomes WBB<u>B</u>W. W<u>BBB</u>W -&gt; WW.
There are still balls remaining on the board, and you are out of balls to insert.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WWRRBBWW&quot;, hand = &quot;WRBRW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> To make the board empty:
- Insert &#39;R&#39; so the board becomes WWRR<u>R</u>BBWW. WW<u>RRR</u>BBWW -&gt; WWBBWW.
- Insert &#39;B&#39; so the board becomes WWBB<u>B</u>WW. WW<u>BBB</u>WW -&gt; <u>WWWW</u> -&gt; empty.
2 balls from your hand were needed to clear the board.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;G&quot;, hand = &quot;GGGGG&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> To make the board empty:
- Insert &#39;G&#39; so the board becomes G<u>G</u>.
- Insert &#39;G&#39; so the board becomes GG<u>G</u>. <u>GGG</u> -&gt; empty.
2 balls from your hand were needed to clear the board.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= board.length &lt;= 16</code></li>
	<li><code>1 &lt;= hand.length &lt;= 5</code></li>
	<li><code>board</code> and <code>hand</code> consist of the characters <code>&#39;R&#39;</code>, <code>&#39;Y&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;W&#39;</code>.</li>
	<li>The initial row of balls on the board will <strong>not</strong> have any groups of three or more consecutive balls of the same color.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMinStep(self, board: str, hand: str) -> int:
        def remove(s):
            while len(s):
                next = re.sub(r'B{3,}|G{3,}|R{3,}|W{3,}|Y{3,}', '', s)
                if len(next) == len(s):
                    break
                s = next
            return s

        visited = set()
        q = deque([(board, hand)])
        while q:
            state, balls = q.popleft()
            if not state:
                return len(hand) - len(balls)
            for ball in set(balls):
                b = balls.replace(ball, '', 1)
                for i in range(1, len(state) + 1):
                    s = state[:i] + ball + state[i:]
                    s = remove(s)
                    if s not in visited:
                        visited.add(s)
                        q.append((s, b))
        return -1
```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
