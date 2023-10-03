# [489. 扫地机器人](https://leetcode.cn/problems/robot-room-cleaner)

[English Version](/solution/0400-0499/0489.Robot%20Room%20Cleaner/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>房间（用格栅表示）中有一个扫地机器人。格栅中的每一个格子有空和障碍物两种可能。</p>

<p>扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。</p>

<p>当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。</p>

<p>请利用提供的4个API编写让机器人清理整个房间的算法。</p>

<pre>
interface Robot {
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

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0489.Robot%20Room%20Cleaner/images/1695782910-iCEGqJ-image.png" style="width: 644px; height: 405px;" /></strong></p>

<pre>
<strong>输入:</strong>
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
	<li>输入只用于初始化房间和机器人的位置。你需要“盲解”这个问题。换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。&nbsp;</li>
	<li>扫地机器人的初始位置一定是空地。</li>
	<li>扫地机器人的初始方向向上。</li>
	<li>所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。</li>
	<li>可以假定格栅的四周都被墙包围。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们不妨假设机器人的初始位置为 $(0, 0)$，方向为 $d=0$。我们将初始位置进行打扫，并标记为已访问。然后，我们依次选择上、右、下、左四个方向进行探索，每次探索前都先判断是否已经访问过，如果没有访问过，我们就朝着该方向前进一步，然后递归探索。如果已经访问过，我们就顺时针旋转 $90^\circ$，然后继续探索下一个方向。当我们探索完所有的方向后，我们需要回到上一个位置，这时我们只需要顺时针旋转 $180^\circ$，然后前进一步，再顺时针旋转 $180^\circ$ 即可。

时间复杂度 $O(4^{n-m})$，空间复杂度 $O(n-m)$。其中 $n$ 和 $m$ 分别是房间的数量以及障碍物的数量。

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

        def dfs(i, j, d):
            vis.add((i, j))
            robot.clean()
            for k in range(4):
                nd = (d + k) % 4
                x, y = i + dirs[nd], j + dirs[nd + 1]
                if (x, y) not in vis and robot.move():
                    dfs(x, y, nd)
                    robot.turnRight()
                    robot.turnRight()
                    robot.move()
                    robot.turnRight()
                    robot.turnRight()
                robot.turnRight()

        dirs = (-1, 0, 1, 0, -1)
        vis = set()
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
    private int[] dirs = {-1, 0, 1, 0, -1};
    private Set<List<Integer>> vis = new HashSet<>();
    private Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(0, 0, 0);
    }

    private void dfs(int i, int j, int d) {
        robot.clean();
        vis.add(List.of(i, j));
        for (int k = 0; k < 4; ++k) {
            int nd = (d + k) % 4;
            int x = i + dirs[nd], y = j + dirs[nd + 1];
            if (!vis.contains(List.of(x, y)) && robot.move()) {
                dfs(x, y, nd);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
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
    void cleanRoom(Robot& robot) {
        int dirs[5] = {-1, 0, 1, 0, -1};
        set<pair<int, int>> vis;
        function<void(int, int, int)> dfs = [&](int i, int j, int d) {
            robot.clean();
            vis.insert({i, j});
            for (int k = 0; k < 4; ++k) {
                int nd = (d + k) % 4;
                int x = i + dirs[nd], y = j + dirs[nd + 1];
                if (!vis.count({x, y}) && robot.move()) {
                    dfs(x, y, nd);
                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnRight();
                    robot.turnRight();
                }
                robot.turnRight();
            }
        };
        dfs(0, 0, 0);
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
	vis := map[[2]int]bool{}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(int, int, int)
	dfs = func(i, j, d int) {
		vis[[2]int{i, j}] = true
		robot.Clean()
		for k := 0; k < 4; k++ {
			nd := (d + k) % 4
			x, y := i+dirs[nd], j+dirs[nd+1]
			if !vis[[2]int{x, y}] && robot.Move() {
				dfs(x, y, nd)
				robot.TurnRight()
				robot.TurnRight()
				robot.Move()
				robot.TurnRight()
				robot.TurnRight()
			}
			robot.TurnRight()
		}
	}
	dfs(0, 0, 0)
}
```

### **TypeScript**

```ts
/**
 * class Robot {
 *      // Returns true if the cell in front is open and robot moves into the cell.
 *      // Returns false if the cell in front is blocked and robot stays in the current cell.
 * 		move(): boolean {}
 *
 *      // Robot will stay in the same cell after calling turnLeft/turnRight.
 *      // Each turn will be 90 degrees.
 * 		turnRight() {}
 *
 *      // Robot will stay in the same cell after calling turnLeft/turnRight.
 *      // Each turn will be 90 degrees.
 * 		turnLeft() {}
 *
 * 		// Clean the current cell.
 * 		clean(): {}
 * }
 */

function cleanRoom(robot: Robot) {
    const dirs = [-1, 0, 1, 0, -1];
    const vis = new Set<string>();
    const dfs = (i: number, j: number, d: number) => {
        vis.add(`${i}-${j}`);
        robot.clean();
        for (let k = 0; k < 4; ++k) {
            const nd = (d + k) % 4;
            const [x, y] = [i + dirs[nd], j + dirs[nd + 1]];
            if (!vis.has(`${x}-${y}`) && robot.move()) {
                dfs(x, y, nd);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    };
    dfs(0, 0, 0);
}
```

### **...**

```

```

<!-- tabs:end -->
