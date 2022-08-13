# [489. 扫地机器人](https://leetcode.cn/problems/robot-room-cleaner)

[English Version](/solution/0400-0499/0489.Robot%20Room%20Cleaner/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>房间（用格栅表示）中有一个扫地机器人。格栅中的每一个格子有空和障碍物两种可能。</p>

<p>扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。</p>

<p>当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。</p>

<p>请利用提供的4个API编写让机器人清理整个房间的算法。</p>

<pre>interface Robot {
&nbsp; // 若下一个方格为空，则返回true，并移动至该方格
&nbsp; // 若下一个方格为障碍物，则返回false，并停留在原地
&nbsp; boolean move();

  // 在调用turnLeft/turnRight后机器人会停留在原位置
&nbsp; // 每次转弯90度
&nbsp; void turnLeft();
&nbsp; void turnRight();

  // 清理所在方格
  void clean();
}
</pre>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

<strong>解析:</strong>
房间格栅用0或1填充。0表示障碍物，1表示可以通过。
机器人从row=1，col=3的初始位置出发。在左上角的一行以下，三列以右。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>输入只用于初始化房间和机器人的位置。你需要&ldquo;盲解&rdquo;这个问题。换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。&nbsp;</li>
	<li>扫地机器人的初始位置一定是空地。</li>
	<li>扫地机器人的初始方向向上。</li>
	<li>所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。</li>
	<li>可以假定格栅的四周都被墙包围。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

我们设定机器人起始位置 `(0, 0)`，朝向 d = 0。

将起始位置进行清扫，并进行标记（即清扫过的格子也算作障碍）；然后依次选择四个朝向 up，right，down 和 left 进行深度优先搜索，相邻的两个朝向仅差一次向右旋转的操作；

-   对于选择的朝向，检查下一个格子是否有障碍，如果没有，则向对应朝向移动一格，并开始新的搜索；
-   如果有，则向右旋转。

如果四个朝向都搜索完毕，则回溯到上一次搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        for (int k = 0; k < 4; ++k) {
            int nd = (d + k) % 4;
            int x = i + dirs[nd][0];
            int y = j + dirs[nd][1];
            if (!vis.count(to_string(x) + "," + to_string(y)) && robot.move()) {
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
