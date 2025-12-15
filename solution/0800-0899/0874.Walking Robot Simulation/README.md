---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0874.Walking%20Robot%20Simulation/README.md
tags:
    - 数组
    - 哈希表
    - 模拟
---

<!-- problem:start -->

# [874. 模拟行走机器人](https://leetcode.cn/problems/walking-robot-simulation)

[English Version](/solution/0800-0899/0874.Walking%20Robot%20Simulation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>机器人在一个无限大小的 XY 网格平面上行走，从点&nbsp;<code>(0, 0)</code> 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 <code>commands</code> ：</p>

<ul>
	<li><code>-2</code> ：向左转&nbsp;<code>90</code> 度</li>
	<li><code>-1</code> ：向右转 <code>90</code> 度</li>
	<li><code>1 &lt;= x &lt;= 9</code> ：向前移动&nbsp;<code>x</code>&nbsp;个单位长度</li>
</ul>

<p>在网格上有一些格子被视为障碍物&nbsp;<code>obstacles</code> 。第 <code>i</code>&nbsp;个障碍物位于网格点 &nbsp;<code>obstacles[i] = (x<sub>i</sub>, y<sub>i</sub>)</code> 。</p>

<p>机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，并继续执行下一个命令。</p>

<p>返回机器人距离原点的 <strong>最大欧式距离</strong> 的 <strong>平方</strong> 。（即，如果距离为 <code>5</code> ，则返回 <code>25</code> ）</p>

<div class="d-google dictRoot saladict-panel isAnimate">
<div>
<div class="MachineTrans-Text">
<div class="MachineTrans-Lines">
<div class="MachineTrans-Lines-collapse MachineTrans-lang-en">&nbsp;</div>
</div>

<div class="MachineTrans-Lines">
<p class="MachineTrans-lang-zh-CN"><strong>注意：</strong></p>

<ul>
	<li class="MachineTrans-lang-zh-CN">北方表示 +Y 方向。</li>
	<li class="MachineTrans-lang-zh-CN">东方表示 +X 方向。</li>
	<li class="MachineTrans-lang-zh-CN">南方表示 -Y 方向。</li>
	<li class="MachineTrans-lang-zh-CN">西方表示 -X 方向。</li>
	<li class="MachineTrans-lang-zh-CN">原点 [0,0] 可能会有障碍物。</li>
</ul>
</div>
</div>
</div>
</div>

<p>&nbsp;</p>

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

<p><strong>示例&nbsp;2：</strong></p>

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

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>commands = [6,-1,-1,6], obstacles = []
<b>输出：</b>36
<b>解释：</b>机器人开始位于 (0, 0):
1. 向北移动 6 个单位，到达 (0, 6).
2. 右转
3. 右转
4. 向南移动 6 个单位，到达 (0, 0).
机器人距离原点最远的点是 (0, 6)，其距离的平方是 6<sup>2</sup> = 36 个单位。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= commands.length &lt;= 10<sup>4</sup></code></li>
	<li><code>commands[i]</code> 的值可以取 <code>-2</code>、<code>-1</code> 或者是范围 <code>[1, 9]</code> 内的一个整数。</li>
	<li><code>0 &lt;= obstacles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 3 * 10<sup>4</sup></code></li>
	<li>答案保证小于 <code>2<sup>31</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 模拟

我们定义一个长度为 $5$ 的方向数组 $dirs=[0, 1, 0, -1, 0]$，数组中的相邻两个元素表示一个方向。即 $(dirs[0], dirs[1])$ 表示向北，而 $(dirs[1], dirs[2])$ 表示向东，以此类推。

我们使用一个哈希表 $s$ 来存储所有障碍物的坐标，这样可以在 $O(1)$ 的时间内判断下一步是否会遇到障碍物。

另外，使用两个变量 $x$ 和 $y$ 来表示机器人当前所在的坐标，初始时 $x = y = 0$。变量 $k$ 表示机器人当前的方向，答案变量 $ans$ 表示机器人距离原点的最大欧式距离的平方。

接下来，我们遍历数组 $commands$ 中的每个元素 $c$：

- 如果 $c = -2$，表示机器人向左转 $90$ 度，即 $k = (k + 3) \bmod 4$；
- 如果 $c = -1$，表示机器人向右转 $90$ 度，即 $k = (k + 1) \bmod 4$；
- 否则，表示机器人向前移动 $c$ 个单位长度。我们将机器人当前的方向 $k$ 与方向数组 $dirs$ 结合，即可得到机器人在 $x$ 轴和 $y$ 轴上的增量。我们将 $c$ 个单位长度的增量分别累加到 $x$ 和 $y$ 上，并判断每次移动后的新坐标 $(nx, ny)$ 是否在障碍物的坐标中，如果不在，则更新答案 $ans$，否则停止模拟，进行下一条指令的模拟。

最后返回答案 $ans$ 即可。

时间复杂度 $O(C \times n + m)$，空间复杂度 $O(m)$。其中 $C$ 表示每次可以移动的最大步数，而 $n$ 和 $m$ 分别表示数组 $commands$ 和数组 $obstacles$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = (0, 1, 0, -1, 0)
        s = {(x, y) for x, y in obstacles}
        ans = k = 0
        x = y = 0
        for c in commands:
            if c == -2:
                k = (k + 3) % 4
            elif c == -1:
                k = (k + 1) % 4
            else:
                for _ in range(c):
                    nx, ny = x + dirs[k], y + dirs[k + 1]
                    if (nx, ny) in s:
                        break
                    x, y = nx, ny
                    ans = max(ans, x * x + y * y)
        return ans
```

#### Java

```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dirs = {0, 1, 0, -1, 0};
        Set<Integer> s = new HashSet<>(obstacles.length);
        for (var e : obstacles) {
            s.add(f(e[0], e[1]));
        }
        int ans = 0, k = 0;
        int x = 0, y = 0;
        for (int c : commands) {
            if (c == -2) {
                k = (k + 3) % 4;
            } else if (c == -1) {
                k = (k + 1) % 4;
            } else {
                while (c-- > 0) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (s.contains(f(nx, ny))) {
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

    private int f(int x, int y) {
        return x * 60010 + y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        int dirs[5] = {0, 1, 0, -1, 0};
        auto f = [](int x, int y) {
            return x * 60010 + y;
        };
        unordered_set<int> s;
        for (auto& e : obstacles) {
            s.insert(f(e[0], e[1]));
        }
        int ans = 0, k = 0;
        int x = 0, y = 0;
        for (int c : commands) {
            if (c == -2) {
                k = (k + 3) % 4;
            } else if (c == -1) {
                k = (k + 1) % 4;
            } else {
                while (c--) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (s.count(f(nx, ny))) {
                        break;
                    }
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

#### Go

```go
func robotSim(commands []int, obstacles [][]int) (ans int) {
	dirs := [5]int{0, 1, 0, -1, 0}
	type pair struct{ x, y int }
	s := map[pair]bool{}
	for _, e := range obstacles {
		s[pair{e[0], e[1]}] = true
	}
	var x, y, k int
	for _, c := range commands {
		if c == -2 {
			k = (k + 3) % 4
		} else if c == -1 {
			k = (k + 1) % 4
		} else {
			for ; c > 0 && !s[pair{x + dirs[k], y + dirs[k+1]}]; c-- {
				x += dirs[k]
				y += dirs[k+1]
				ans = max(ans, x*x+y*y)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function robotSim(commands: number[], obstacles: number[][]): number {
    const dirs = [0, 1, 0, -1, 0];
    const s: Set<number> = new Set();
    const f = (x: number, y: number) => x * 60010 + y;
    for (const [x, y] of obstacles) {
        s.add(f(x, y));
    }
    let [ans, x, y, k] = [0, 0, 0, 0];
    for (let c of commands) {
        if (c === -2) {
            k = (k + 3) % 4;
        } else if (c === -1) {
            k = (k + 1) % 4;
        } else {
            while (c-- > 0) {
                const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                if (s.has(f(nx, ny))) {
                    break;
                }
                [x, y] = [nx, ny];
                ans = Math.max(ans, x * x + y * y);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
