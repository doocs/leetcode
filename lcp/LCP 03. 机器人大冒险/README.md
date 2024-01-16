# [LCP 03. 机器人大冒险](https://leetcode.cn/problems/programmable-robot)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣团队买了一个可编程机器人，机器人初始位置在原点<code>(0, 0)</code>。小伙伴事先给机器人输入一串指令<code>command</code>，机器人就会<strong>无限循环</strong>这条指令的步骤进行移动。指令有两种：</p>

<ol>
	<li><code>U</code>: 向<code>y</code>轴正方向移动一格</li>
	<li><code>R</code>: 向<code>x</code>轴正方向移动一格。</li>
</ol>

<p>不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用<code>obstacles</code>表示。机器人一旦碰到障碍物就会被<strong>损毁</strong>。</p>

<p>给定终点坐标<code>(x, y)</code>，返回机器人能否<strong>完好</strong>地到达终点。如果能，返回<code>true</code>；否则返回<code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>command = &quot;URR&quot;, obstacles = [], x = 3, y = 2
<strong>输出：</strong>true
<strong>解释：</strong>U(0, 1) -&gt; R(1, 1) -&gt; R(2, 1) -&gt; U(2, 2) -&gt; R(3, 2)。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>command = &quot;URR&quot;, obstacles = [[2, 2]], x = 3, y = 2
<strong>输出：</strong>false
<strong>解释：</strong>机器人在到达终点前会碰到(2, 2)的障碍物。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>command = &quot;URR&quot;, obstacles = [[4, 2]], x = 3, y = 2
<strong>输出：</strong>true
<strong>解释：</strong>到达终点后，再碰到障碍物也不影响返回结果。</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ol>
	<li><code>2 &lt;= command的长度 &lt;= 1000</code></li>
	<li><code>command</code>由<code>U，R</code>构成，且至少有一个<code>U</code>，至少有一个<code>R</code></li>
	<li><code>0 &lt;= x &lt;= 1e9, 0 &lt;= y &lt;= 1e9</code></li>
	<li><code>0 &lt;= obstacles的长度 &lt;= 1000</code></li>
	<li><code>obstacles[i]</code>不为原点或者终点</li>
</ol>

## 解法

### 方法一：哈希表

我们用哈希表 $vis$ 记录机器人在一轮指令执行完毕后所能到达的所有位置。初始时，机器人位于原点 $(0, 0)$，因此 $vis$ 中只包含一个元素 $(0, 0)$。随后我们遍历指令 $command$ 中的每个字符 $c$，更新机器人的位置，加入哈希表 $vis$ 中。记第一轮执行完毕后，机器人所在的位置为 $(i, j)$。

如果机器人重复执行多轮指令，那么实际上机器人的位置是在 $vis$ 中的所有位置的线性组合。我们将 $(x, y)$ 分别减去 $k$ 倍 $(i, j)$ 的偏移量，其中 $k = \min(\lfloor x / i \rfloor, \lfloor y / j \rfloor)$，如果 $(x, y)$ 不在 $vis$ 中， 说明机器人不可能到达 $(x, y)$，返回 `false`。

接下来，我们只需要判断机器人有没有经过障碍点即可。

我们遍历所有障碍点 $(a, b)$，如果 $a \gt x$ 或者 $b \gt y$，说明机器人不会经过该障碍点，跳过即可。否则，我们将 $(x, y)$ 分别减去 $k$ 倍 $(i, j)$ 的偏移量，其中 $k = \min(\lfloor a / i \rfloor, \lfloor b / j \rfloor)$，如果 $(a, b)$ 在 $vis$ 中，说明机器人会经过该障碍点，返回 `false`。

否则，遍历结束后，返回 `true`。

时间复杂度 $O(m + n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是指令 $command$ 和障碍数组 $obstacles$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def robot(self, command: str, obstacles: List[List[int]], x: int, y: int) -> bool:
        vis = {(0, 0)}
        i = j = 0
        for c in command:
            match c:
                case "U":
                    j += 1
                case "R":
                    i += 1
            vis.add((i, j))
        k = min(x // i, y // j)
        if (x - k * i, y - k * j) not in vis:
            return False
        for a, b in obstacles:
            if a > x or b > y:
                continue
            k = min(a // i, b // j)
            if (a - k * i, b - k * j) in vis:
                return False
        return True
```

```java
class Solution {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        Set<List<Integer>> vis = new HashSet<>();
        int i = 0, j = 0;
        vis.add(List.of(i, j));
        for (char c : command.toCharArray()) {
            if (c == 'U') {
                ++j;
            } else {
                ++i;
            }
            vis.add(List.of(i, j));
        }
        int k = Math.min(x / i, y / j);
        if (!vis.contains(List.of(x - k * i, y - k * j))) {
            return false;
        }
        for (int[] ob : obstacles) {
            if (ob[0] > x || ob[1] > y) {
                continue;
            }
            k = Math.min(ob[0] / i, ob[1] / j);
            if (vis.contains(List.of(ob[0] - k * i, ob[1] - k * j))) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool robot(string command, vector<vector<int>>& obstacles, int x, int y) {
        set<pair<int, int>> vis;
        int i = 0, j = 0;
        vis.insert({i, j});
        for (char c : command) {
            if (c == 'U') {
                ++j;
            } else {
                ++i;
            }
            vis.insert({i, j});
        }
        int k = min(x / i, y / j);
        if (!vis.count({x - k * i, y - k * j})) {
            return false;
        }
        for (auto& ob : obstacles) {
            if (ob[0] > x || ob[1] > y) {
                continue;
            }
            k = min(ob[0] / i, ob[1] / j);
            if (vis.count({ob[0] - k * i, ob[1] - k * j})) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func robot(command string, obstacles [][]int, x int, y int) bool {
	type pair struct{ i, j int }
	vis := map[pair]bool{}
	i, j := 0, 0
	vis[pair{0, 0}] = true
	for _, c := range command {
		if c == 'U' {
			j++
		} else {
			i++
		}
		vis[pair{i, j}] = true
	}
	k := min(x/i, y/j)
	if !vis[pair{x - k*i, y - k*j}] {
		return false
	}
	for _, ob := range obstacles {
		if ob[0] > x || ob[1] > y {
			continue
		}
		k := min(ob[0]/i, ob[1]/j)
		if vis[pair{ob[0] - k*i, ob[1] - k*j}] {
			return false
		}
	}
	return true
}
```

```ts
function robot(command: string, obstacles: number[][], x: number, y: number): boolean {
    const f = (i: number, j: number): number => {
        return i * (10 ** 9 + 1) + j;
    };
    const vis: Set<number> = new Set();
    vis.add(f(0, 0));
    let [i, j] = [0, 0];
    for (const c of command) {
        if (c === 'U') {
            ++j;
        } else {
            ++i;
        }
        vis.add(f(i, j));
    }
    const k = Math.min(Math.floor(x / i), Math.floor(y / j));
    if (!vis.has(f(x - k * i, y - k * j))) {
        return false;
    }
    for (const [a, b] of obstacles) {
        if (a > x || b > y) {
            continue;
        }
        const k = Math.min(Math.floor(a / i), Math.floor(b / j));
        if (vis.has(f(a - k * i, b - k * j))) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
