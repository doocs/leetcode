# [1041. Robot Bounded In Circle](https://leetcode.com/problems/robot-bounded-in-circle)

[中文文档](/solution/1000-1099/1041.Robot%20Bounded%20In%20Circle/README.md)

## Description

<p>On an infinite plane, a robot initially stands at <code>(0, 0)</code> and faces north. Note that:</p>

<ul>
	<li>The <strong>north direction</strong> is the positive direction of the y-axis.</li>
	<li>The <strong>south direction</strong> is the negative direction of the y-axis.</li>
	<li>The <strong>east direction</strong> is the positive direction of the x-axis.</li>
	<li>The <strong>west direction</strong> is the negative direction of the x-axis.</li>
</ul>

<p>The robot can receive one of three instructions:</p>

<ul>
	<li><code>&quot;G&quot;</code>: go straight 1 unit.</li>
	<li><code>&quot;L&quot;</code>: turn 90 degrees to the left (i.e., anti-clockwise direction).</li>
	<li><code>&quot;R&quot;</code>: turn 90 degrees to the right (i.e., clockwise direction).</li>
</ul>

<p>The robot performs the <code>instructions</code> given in order, and repeats them forever.</p>

<p>Return <code>true</code> if and only if there exists a circle in the plane such that the robot never leaves the circle.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GGLLGG&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;G&quot;: move one step. Position: (0, 2). Direction: North.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
&quot;G&quot;: move one step. Position: (0, 1). Direction: South.
&quot;G&quot;: move one step. Position: (0, 0). Direction: South.
Repeating the instructions, the robot goes into the cycle: (0, 0) --&gt; (0, 1) --&gt; (0, 2) --&gt; (0, 1) --&gt; (0, 0).
Based on that, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GG&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;G&quot;: move one step. Position: (0, 2). Direction: North.
Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
Based on that, we return false.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GL&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
&quot;G&quot;: move one step. Position: (-1, 1). Direction: West.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
&quot;G&quot;: move one step. Position: (-1, 0). Direction: South.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
&quot;G&quot;: move one step. Position: (0, 0). Direction: East.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
Repeating the instructions, the robot goes into the cycle: (0, 0) --&gt; (0, 1) --&gt; (-1, 1) --&gt; (-1, 0) --&gt; (0, 0).
Based on that, we return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= instructions.length &lt;= 100</code></li>
	<li><code>instructions[i]</code> is <code>&#39;G&#39;</code>, <code>&#39;L&#39;</code> or, <code>&#39;R&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        cur, direction = 0, [0] * 4
        for ins in instructions:
            if ins == 'L':
                cur = (cur + 1) % 4
            elif ins == 'R':
                cur = (cur + 3) % 4
            else:
                direction[cur] += 1
        return cur != 0 or (
            direction[0] == direction[2] and direction[1] == direction[3]
        )
```

### **Java**

```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] direction = new int[4];
        int cur = 0;
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                cur = (cur + 1) % 4;
            } else if (c == 'R') {
                cur = (cur + 3) % 4;
            } else {
                ++direction[cur];
            }
        }
        return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isRobotBounded(string instructions) {
        vector<int> direction(4);
        int cur = 0;
        for (char c : instructions) {
            if (c == 'L')
                cur = (cur + 1) % 4;
            else if (c == 'R')
                cur = (cur + 3) % 4;
            else
                ++direction[cur];
        }
        return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3]);
    }
};
```

### **Go**

```go
func isRobotBounded(instructions string) bool {
	direction := make([]int, 4)
	cur := 0
	for _, ins := range instructions {
		if ins == 'L' {
			cur = (cur + 1) % 4
		} else if ins == 'R' {
			cur = (cur + 3) % 4
		} else {
			direction[cur]++
		}
	}
	return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3])
}
```

### **...**

```

```

<!-- tabs:end -->
