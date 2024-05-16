---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0488.Zuma%20Game/README_EN.md
tags:
    - Stack
    - Breadth-First Search
    - Memoization
    - String
    - Dynamic Programming
---

<!-- problem:start -->

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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WRRBBW&quot;, hand = &quot;RB&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to clear all the balls. The best you can do is:
- Insert &#39;R&#39; so the board becomes WRR<u>R</u>BBW. W<u>RRR</u>BBW -&gt; WBBW.
- Insert &#39;B&#39; so the board becomes WBB<u>B</u>W. W<u>BBB</u>W -&gt; WW.
There are still balls remaining on the board, and you are out of balls to insert.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WWRRBBWW&quot;, hand = &quot;WRBRW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> To make the board empty:
- Insert &#39;R&#39; so the board becomes WWRR<u>R</u>BBWW. WW<u>RRR</u>BBWW -&gt; WWBBWW.
- Insert &#39;B&#39; so the board becomes WWBB<u>B</u>WW. WW<u>BBB</u>WW -&gt; <u>WWWW</u> -&gt; empty.
2 balls from your hand were needed to clear the board.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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

```java
class Solution {
    public int findMinStep(String board, String hand) {
        final Zuma zuma = Zuma.create(board, hand);
        final HashSet<Long> visited = new HashSet<>();
        final ArrayList<Zuma> init = new ArrayList<>();

        visited.add(zuma.board());
        init.add(zuma);
        return bfs(init, 0, visited);
    }

    private int bfs(ArrayList<Zuma> curr, int k, HashSet<Long> visited) {
        if (curr.isEmpty()) {
            return -1;
        }

        final ArrayList<Zuma> next = new ArrayList<>();

        for (Zuma zuma : curr) {
            ArrayList<Zuma> neib = zuma.getNextLevel(k, visited);
            if (neib == null) {
                return k + 1;
            }

            next.addAll(neib);
        }
        return bfs(next, k + 1, visited);
    }
}

record Zuma(long board, long hand) {
    public static Zuma create(String boardStr, String handStr) {
        return new Zuma(Zuma.encode(boardStr, false), Zuma.encode(handStr, true));
    }

    public ArrayList<Zuma> getNextLevel(int depth, HashSet<Long> visited) {
        final ArrayList<Zuma> next = new ArrayList<>();
        final ArrayList<long[]> handList = this.buildHandList();
        final long[] boardList = new long[32];
        final int size = this.buildBoardList(boardList);

        for (long[] pair : handList) {
            for (int i = 0; i < size; ++i) {
                final long rawBoard = pruningCheck(boardList[i], pair[0], i * 3, depth);
                if (rawBoard == -1) {
                    continue;
                }

                final long nextBoard = updateBoard(rawBoard);
                if (nextBoard == 0) {
                    return null;
                }

                if (pair[1] == 0 || visited.contains(nextBoard)) {
                    continue;
                }

                visited.add(nextBoard);
                next.add(new Zuma(nextBoard, pair[1]));
            }
        }
        return next;
    }

    private long pruningCheck(long insBoard, long ball, int pos, int depth) {
        final long L = (insBoard >> (pos + 3)) & 0x7;
        final long R = (insBoard >> (pos - 3)) & 0x7;

        if (depth == 0 && (ball != R) && (L != R) || depth > 0 && (ball != R)) {
            return -1;
        }
        return insBoard | (ball << pos);
    }

    private long updateBoard(long board) {
        long stack = 0;

        for (int i = 0; i < 64; i += 3) {
            final long curr = (board >> i) & 0x7;
            final long top = (stack) &0x7;

            // pop (if possible)
            if ((top > 0) && (curr != top) && (stack & 0x3F) == ((stack >> 3) & 0x3F)) {
                stack >>= 9;
                if ((stack & 0x7) == top) stack >>= 3;
            }

            if (curr == 0) {
                // done
                break;
            }
            // push and continue
            stack = (stack << 3) | curr;
        }
        return stack;
    }

    private ArrayList<long[]> buildHandList() {
        final ArrayList<long[]> handList = new ArrayList<>();
        long prevBall = 0;
        long ballMask = 0;

        for (int i = 0; i < 16; i += 3) {
            final long currBall = (this.hand >> i) & 0x7;
            if (currBall == 0) {
                break;
            }

            if (currBall != prevBall) {
                prevBall = currBall;
                handList.add(
                    new long[] {currBall, ((this.hand >> 3) & ~ballMask) | (this.hand & ballMask)});
            }
            ballMask = (ballMask << 3) | 0x7;
        }
        return handList;
    }

    private int buildBoardList(long[] buffer) {
        int ptr = 0;
        long ballMask = 0x7;
        long insBoard = this.board << 3;
        buffer[ptr++] = insBoard;

        while (true) {
            final long currBall = this.board & ballMask;
            if (currBall == 0) {
                break;
            }

            ballMask <<= 3;
            insBoard = (insBoard | currBall) & ~ballMask;
            buffer[ptr++] = insBoard;
        }
        return ptr;
    }

    private static long encode(String stateStr, boolean sortFlag) {
        final char[] stateChars = stateStr.toCharArray();
        if (sortFlag) {
            Arrays.sort(stateChars);
        }

        long stateBits = 0;
        for (char ch : stateChars) {
            stateBits = (stateBits << 3) | Zuma.encode(ch);
        }
        return stateBits;
    }

    private static long encode(char ch) {
        return switch (ch) {
            case 'R' -> 0x1;
            case 'G' -> 0x2;
            case 'B' -> 0x3;
            case 'W' -> 0x4;
            case 'Y' -> 0x5;
            case ' ' -> 0x0;
            default  ->
                throw new IllegalArgumentException("Invalid char: " + ch);
        };
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
