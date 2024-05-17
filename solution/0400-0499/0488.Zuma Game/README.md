---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0488.Zuma%20Game/README.md
tags:
    - 栈
    - 广度优先搜索
    - 记忆化搜索
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [488. 祖玛游戏](https://leetcode.cn/problems/zuma-game)

[English Version](/solution/0400-0499/0488.Zuma%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在参与祖玛游戏的一个变种。</p>

<p>在这个祖玛游戏变体中，桌面上有 <strong>一排</strong> 彩球，每个球的颜色可能是：红色 <code>'R'</code>、黄色 <code>'Y'</code>、蓝色 <code>'B'</code>、绿色 <code>'G'</code> 或白色 <code>'W'</code> 。你的手中也有一些彩球。</p>

<p>你的目标是 <strong>清空</strong> 桌面上所有的球。每一回合：</p>

<ul>
	<li>从你手上的彩球中选出 <strong>任意一颗</strong> ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。</li>
	<li>接着，如果有出现 <strong>三个或者三个以上</strong> 且 <strong>颜色相同</strong> 的球相连的话，就把它们移除掉。
	<ul>
		<li>如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。</li>
	</ul>
	</li>
	<li>如果桌面上所有球都被移除，则认为你赢得本场游戏。</li>
	<li>重复这个过程，直到你赢了游戏或者手中没有更多的球。</li>
</ul>

<p>给你一个字符串 <code>board</code> ，表示桌面上最开始的那排球。另给你一个字符串 <code>hand</code> ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 <strong>最少</strong> 球数。如果不能移除桌上所有的球，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>board = "WRRBBW", hand = "RB"
<strong>输出：</strong>-1
<strong>解释：</strong>无法移除桌面上的所有球。可以得到的最好局面是：
- 插入一个 'R' ，使桌面变为 WRR<em><strong>R</strong></em>BBW 。W<em><strong>RRR</strong></em>BBW -&gt; WBBW
- 插入一个 'B' ，使桌面变为 WBB<em><strong>B</strong></em>W 。W<em><strong>BBB</strong></em>W -&gt; WW
桌面上还剩着球，没有其他球可以插入。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = "WWRRBBWW", hand = "WRBRW"
<strong>输出：</strong>2
<strong>解释：</strong>要想清空桌面上的球，可以按下述步骤：
- 插入一个 'R' ，使桌面变为 WWRR<strong><em>R</em></strong>BBWW 。WW<em><strong>RRR</strong></em>BBWW -&gt; WWBBWW
- 插入一个 'B' ，使桌面变为 WWBB<em><strong>B</strong></em>WW 。WW<em><strong>BBB</strong></em>WW -&gt; <em><strong>WWWW</strong></em> -&gt; empty
只需从手中出 2 个球就可以清空桌面。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>board = "G", hand = "GGGGG"
<strong>输出：</strong>2
<strong>解释：</strong>要想清空桌面上的球，可以按下述步骤：
- 插入一个 'G' ，使桌面变为 G<em><strong>G</strong></em> 。
- 插入一个 'G' ，使桌面变为 GG<em><strong>G</strong></em> 。<em><strong>GGG</strong></em> -&gt; empty
只需从手中出 2 个球就可以清空桌面。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>board = "RBYYBBRRB", hand = "YRBGB"
<strong>输出：</strong>3
<strong>解释：</strong>要想清空桌面上的球，可以按下述步骤：
- 插入一个 'Y' ，使桌面变为 RBYY<em><strong>Y</strong></em>BBRRB 。RB<em><strong>YYY</strong></em>BBRRB -&gt; R<em><strong>BBB</strong></em>RRB -&gt; <em><strong>RRR</strong></em>B -&gt; B
- 插入一个 'B' ，使桌面变为 B<em><strong>B</strong></em> 。
- 插入一个 'B' ，使桌面变为 BB<em><strong>B</strong></em> 。<em><strong>BBB</strong></em> -&gt; empty
只需从手中出 3 个球就可以清空桌面。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= board.length &lt;= 16</code></li>
	<li><code>1 &lt;= hand.length &lt;= 5</code></li>
	<li><code>board</code> 和 <code>hand</code> 由字符 <code>'R'</code>、<code>'Y'</code>、<code>'B'</code>、<code>'G'</code> 和 <code>'W'</code> 组成</li>
	<li>桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
