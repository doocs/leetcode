# [657. Robot Return to Origin](https://leetcode.com/problems/robot-return-to-origin)

[中文文档](/solution/0600-0699/0657.Robot%20Return%20to%20Origin/README.md)

## Description

<p>There is a robot starting at the position <code>(0, 0)</code>, the origin, on a 2D plane. Given a sequence of its moves, judge if this robot <strong>ends up at </strong><code>(0, 0)</code> after it completes its moves.</p>

<p>You are given a string <code>moves</code> that represents the move sequence of the robot where <code>moves[i]</code> represents its <code>i<sup>th</sup></code> move. Valid moves are <code>&#39;R&#39;</code> (right), <code>&#39;L&#39;</code> (left), <code>&#39;U&#39;</code> (up), and <code>&#39;D&#39;</code> (down).</p>

<p>Return <code>true</code><em> if the robot returns to the origin after it finishes all of its moves, or </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note</strong>: The way that the robot is &quot;facing&quot; is irrelevant. <code>&#39;R&#39;</code> will always make the robot move to the right once, <code>&#39;L&#39;</code> will always make it move left, etc. Also, assume that the magnitude of the robot&#39;s movement is the same for each move.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;UD&quot;
<strong>Output:</strong> true
<strong>Explanation</strong>: The robot moves up once, and then down once. All moves have the same magnitude, so it ended up at the origin where it started. Therefore, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;LL&quot;
<strong>Output:</strong> false
<strong>Explanation</strong>: The robot moves left twice. It ends up two &quot;moves&quot; to the left of the origin. We return false because it is not at the origin at the end of its moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>moves</code> only contains the characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code> and <code>&#39;R&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def judgeCircle(self, moves: str) -> bool:
        x = y = 0
        for c in moves:
            if c == 'R':
                x += 1
            elif c == 'L':
                x -= 1
            elif c == 'U':
                y += 1
            elif c == 'D':
                y -= 1
        return x == 0 and y == 0
```

### **Java**

```java
class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (int i = 0; i < moves.length(); ++i) {
            char c = moves.charAt(i);
            if (c == 'R') ++x;
            else if (c == 'L') --x;
            else if (c == 'U') ++y;
            else if (c == 'D') --y;
        }
        return x == 0 && y == 0;
    }
}
```

### **TypeScript**

```ts
function judgeCircle(moves: string): boolean {
    let x = 0,
        y = 0;
    const dir = {
        R: [1, 0],
        L: [-1, 0],
        U: [0, 1],
        D: [0, -1],
    };
    for (let u of moves) {
        const [dx, dy] = dir[u];
        x += dx;
        y += dy;
    }
    return !x && !y;
}
```

### **...**

```

```

<!-- tabs:end -->
