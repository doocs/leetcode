# [489. Robot Room Cleaner](https://leetcode.com/problems/robot-room-cleaner)

[中文文档](/solution/0400-0499/0489.Robot%20Room%20Cleaner/README.md)

## Description

<p>Given a robot cleaner in a room modeled as a grid.</p>

<p>Each cell in the grid can be empty or blocked.</p>

<p>The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.</p>

<p>When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.</p>

<p>Design an algorithm to clean the entire room using only the 4 given APIs shown below.</p>

<pre>

interface Robot {

&nbsp; // returns true if next cell is open and robot moves into the cell.

&nbsp; // returns false if next cell is obstacle and robot stays on the current cell.

&nbsp; boolean move();



  // Robot will stay on the same cell after calling turnLeft/turnRight.

&nbsp; // Each turn will be 90 degrees.

&nbsp; void turnLeft();

&nbsp; void turnRight();



  // Clean the current cell.

  void clean();

}

</pre>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>

room = [

  [1,1,1,1,1,0,1,1],

  [1,1,1,1,1,0,1,1],

  [1,0,1,1,1,1,1,1],

  [0,0,0,1,0,0,0,0],

  [1,1,1,1,1,1,1,1]

],

row = 1,

col = 3



<strong>Explanation:</strong>

All grids in the room are marked by either 0 or 1.

0 means the cell is blocked, while 1 means the cell is accessible.

The robot initially starts at the position of row=1, col=3.

From the top left corner, its position is one row below and three columns right.

</pre>

<p><strong>Notes:</strong></p>

<ol>
	<li>The input is only given to initialize the room and the robot&#39;s position internally.&nbsp;You must solve this problem &quot;blindfolded&quot;. In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot&#39;s position.</li>
	<li>The robot&#39;s initial position will always be in an accessible cell.</li>
	<li>The initial direction of the robot will be facing up.</li>
	<li>All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.</li>
	<li>Assume all four edges of the grid are all surrounded by wall.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# """
# This is the robot's control interface.
# You should not implement it, or speculate about its implementation
# """
# class Robot:
#    def move(self):
#        """
#        Returns true if the cell in front is open and robot moves into the cell.
#        Returns false if the cell in front is blocked and robot stays in the current cell.
#        :rtype bool
#        """
#
#    def turnLeft(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def turnRight(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def clean(self):
#        """
#        Clean the current cell.
#        :rtype void
#        """

class Solution:
    def cleanRoom(self, robot):
        """
        :type robot: Robot
        :rtype: None
        """
        def back():
            robot.turnRight()
            robot.turnRight()
            robot.move()
            robot.turnRight()
            robot.turnRight()

        def dfs(i, j, d):
            vis.add((i, j))
            robot.clean()
            for k in range(4):
                nd = (d + k) % 4
                x, y = i + dirs[nd][0], j + dirs[nd][1]
                if (x, y) not in vis and robot.move():
                    dfs(x, y, nd)
                    back()
                robot.turnRight()

        vis = set()
        dirs = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        dfs(0, 0, 0)
```

### **Java**

```java
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    private Set<String> vis;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        vis = new HashSet<>();
        dfs(0, 0, 0, robot);
    }

    private void dfs(int i, int j, int d, Robot robot) {
        vis.add(i + "," + j);
        robot.clean();
        for (int k = 0; k < 4; ++k) {
            int nd = (d + k) % 4;
            int x = i + dirs[nd][0];
            int y = j + dirs[nd][1];
            if (!vis.contains(x + "," + y) && robot.move()) {
                dfs(x, y, nd, robot);
                back(robot);
            }
            robot.turnRight();
        }
    }

    private void back(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
```

### **C++**

```cpp
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * class Robot {
 *   public:
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     bool move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     void turnLeft();
 *     void turnRight();
 *
 *     // Clean the current cell.
 *     void clean();
 * };
 */

class Solution {
public:
    vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    void cleanRoom(Robot& robot) {
        unordered_set<string> vis;
        dfs(0, 0, 0, vis, robot);
    }

    void dfs(int i, int j, int d, unordered_set<string>& vis, Robot& robot) {
        vis.insert(to_string(i) + "," + to_string(j));
        robot.clean();
        for (int k = 0; k < 4; ++k)
        {
            int nd = (d + k) % 4;
            int x = i + dirs[nd][0];
            int y = j + dirs[nd][1];
            if (!vis.count(to_string(x) + "," + to_string(y)) && robot.move())
            {
                dfs(x, y, nd, vis, robot);
                back(robot);
            }
            robot.turnRight();
        }
    }

    void back(Robot& robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
};
```

### **Go**

```go
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * type Robot struct {
 * }
 *
 * // Returns true if the cell in front is open and robot moves into the cell.
 * // Returns false if the cell in front is blocked and robot stays in the current cell.
 * func (robot *Robot) Move() bool {}
 *
 * // Robot will stay in the same cell after calling TurnLeft/TurnRight.
 * // Each turn will be 90 degrees.
 * func (robot *Robot) TurnLeft() {}
 * func (robot *Robot) TurnRight() {}
 *
 * // Clean the current cell.
 * func (robot *Robot) Clean() {}
 */

func cleanRoom(robot *Robot) {
	vis := make(map[string]bool)
	dirs := [][]int{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
	back := func() {
		robot.TurnRight()
		robot.TurnRight()
		robot.Move()
		robot.TurnRight()
		robot.TurnRight()
	}
	var dfs func(i, j, d int)
	dfs = func(i, j, d int) {
		vis[strconv.Itoa(i)+","+strconv.Itoa(j)] = true
		robot.Clean()
		for k := 0; k < 4; k++ {
			nd := (d + k) % 4
			x, y := i+dirs[nd][0], j+dirs[nd][1]
			if !vis[strconv.Itoa(x)+","+strconv.Itoa(y)] && robot.Move() {
				dfs(x, y, nd)
				back()
			}
			robot.TurnRight()
		}
	}
	dfs(0, 0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
