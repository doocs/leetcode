# [874. 模拟行走机器人](https://leetcode.cn/problems/walking-robot-simulation)

[English Version](/solution/0800-0899/0874.Walking%20Robot%20Simulation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>机器人在一个无限大小的 XY 网格平面上行走，从点 <code>(0, 0)</code> 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 <code>commands</code> ：</p>

<ul>
	<li><code>-2</code> ：向左转 <code>90</code> 度</li>
	<li><code>-1</code> ：向右转 <code>90</code> 度</li>
	<li><code>1 <= x <= 9</code> ：向前移动 <code>x</code> 个单位长度</li>
</ul>

<p>在网格上有一些格子被视为障碍物 <code>obstacles</code> 。第 <code>i</code> 个障碍物位于网格点  <code>obstacles[i] = (x<sub>i</sub>, y<sub>i</sub>)</code> 。</p>

<p>机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。</p>

<p>返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 <code>5</code> ，则返回 <code>25</code> ）</p>

<div class="d-google dictRoot saladict-panel isAnimate">
<div>
<div class="MachineTrans-Text">
<div class="MachineTrans-Lines">
<div class="MachineTrans-Lines-collapse MachineTrans-lang-en"> </div>
</div>

<div class="MachineTrans-Lines">
<p class="MachineTrans-lang-zh-CN"><strong>注意：</strong></p>

<ul>
	<li class="MachineTrans-lang-zh-CN">北表示 <code>+Y</code> 方向。</li>
	<li class="MachineTrans-lang-zh-CN">东表示 <code>+X</code> 方向。</li>
	<li class="MachineTrans-lang-zh-CN">南表示 <code>-Y</code> 方向。</li>
	<li class="MachineTrans-lang-zh-CN">西表示 <code>-X</code> 方向。</li>
</ul>
</div>
</div>
</div>
</div>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>commands = [4,-1,3], obstacles = []
<strong>输出：</strong>25
<strong>解释：
</strong>机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 3 个单位，到达 (3, 4)
距离原点最远的是 (3, 4) ，距离为 3<sup>2</sup> + 4<sup>2</sup> = 25</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>commands = [4,-1,4,-2,4], obstacles = [[2,4]]
<strong>输出：</strong>65
<strong>解释</strong>：机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
4. 左转
5. 向北走 4 个单位，到达 (1, 8)
距离原点最远的是 (1, 8) ，距离为 1<sup>2</sup> + 8<sup>2</sup> = 65</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= commands.length <= 10<sup>4</sup></code></li>
	<li><code>commands[i]</code> is one of the values in the list <code>[-2,-1,1,2,3,4,5,6,7,8,9]</code>.</li>
	<li><code>0 <= obstacles.length <= 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> <= x<sub>i</sub>, y<sub>i</sub> <= 3 * 10<sup>4</sup></code></li>
	<li>答案保证小于 <code>2<sup>31</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        s = {(x, y) for x, y in obstacles}
        ans, p = 0, 1
        x = y = 0
        for v in commands:
            if v == -2:
                p = (p + 3) % 4
            elif v == -1:
                p = (p + 1) % 4
            else:
                for _ in range(v):
                    nx, ny = x + dirs[p][0], y + dirs[p][1]
                    if (nx, ny) in s:
                        break
                    x, y = nx, ny
                    ans = max(ans, x * x + y * y)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<String> s = new HashSet<>();
        for (int[] v : obstacles) {
            s.add(v[0] + "." + v[1]);
        }
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2) {
                p = (p + 3) % 4;
            } else if (v == -1) {
                p = (p + 1) % 4;
            } else {
                while (v-- > 0) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.contains(nx + "." + ny)) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        unordered_set<string> s;
        for (auto v : obstacles) s.insert(to_string(v[0]) + "." + to_string(v[1]));
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2)
                p = (p + 3) % 4;
            else if (v == -1)
                p = (p + 1) % 4;
            else {
                while (v--) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.count(to_string(nx) + "." + to_string(ny))) break;
                    x = nx;
                    y = ny;
                    ans = max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func robotSim(commands []int, obstacles [][]int) int {
	dirs := [][]int{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
	s := map[string]bool{}
	for _, v := range obstacles {
		t := strconv.Itoa(v[0]) + "." + strconv.Itoa(v[1])
		s[t] = true
	}
	ans, p := 0, 1
	x, y := 0, 0
	for _, v := range commands {
		if v == -2 {
			p = (p + 3) % 4
		} else if v == -1 {
			p = (p + 1) % 4
		} else {
			for i := 0; i < v; i++ {
				nx, ny := x+dirs[p][0], y+dirs[p][1]
				t := strconv.Itoa(nx) + "." + strconv.Itoa(ny)
				if s[t] {
					break
				}
				x, y = nx, ny
				ans = max(ans, x*x+y*y)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
