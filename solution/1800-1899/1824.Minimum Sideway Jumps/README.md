# [1824. 最少侧跳次数](https://leetcode.cn/problems/minimum-sideway-jumps)

[English Version](/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的 <strong>3 跑道道路</strong> ，它总共包含 <code>n + 1</code> 个 <strong>点</strong> ，编号为 <code>0</code> 到 <code>n</code> 。一只青蛙从 <code>0</code> 号点第二条跑道 <strong>出发</strong> ，它想要跳到点 <code>n</code> 处。然而道路上可能有一些障碍。</p>

<p>给你一个长度为 <code>n + 1</code> 的数组 <code>obstacles</code> ，其中 <code>obstacles[i]</code> （<b>取值范围从 0 到 3</b>）表示在点 <code>i</code> 处的 <code>obstacles[i]</code> 跑道上有一个障碍。如果 <code>obstacles[i] == 0</code> ，那么点 <code>i</code> 处没有障碍。任何一个点的三条跑道中 <strong>最多有一个</strong> 障碍。</p>

<ul>
	<li>比方说，如果 <code>obstacles[2] == 1</code> ，那么说明在点 2 处跑道 1 有障碍。</li>
</ul>

<p>这只青蛙从点 <code>i</code> 跳到点 <code>i + 1</code> 且跑道不变的前提是点 <code>i + 1</code> 的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在 <strong>同一个</strong> 点处 <strong>侧跳</strong> 到 <strong>另外一条</strong> 跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。</p>

<ul>
	<li>比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。</li>
</ul>

<p>这只青蛙从点 0 处跑道 <code>2</code> 出发，并想到达点 <code>n</code> 处的 <strong>任一跑道</strong> ，请你返回 <strong>最少侧跳次数</strong> 。</p>

<p><strong>注意</strong>：点 <code>0</code> 处和点 <code>n</code> 处的任一跑道都不会有障碍。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex1.png" style="width: 500px; height: 244px;" />
<pre>
<b>输入：</b>obstacles = [0,1,2,3,0]
<b>输出：</b>2 
<b>解释：</b>最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。
注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点 2 处所示）。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex2.png" style="width: 500px; height: 196px;" />
<pre>
<b>输入：</b>obstacles = [0,1,1,3,3,0]
<b>输出：</b>0
<b>解释：</b>跑道 2 没有任何障碍，所以不需要任何侧跳。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex3.png" style="width: 500px; height: 196px;" />
<pre>
<b>输入：</b>obstacles = [0,2,1,0,3,0]
<b>输出：</b>2
<b>解释：</b>最优方案如上图所示。总共有 2 次侧跳。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obstacles.length == n + 1</code></li>
	<li><code>1 <= n <= 5 * 10<sup>5</sup></code></li>
	<li><code>0 <= obstacles[i] <= 3</code></li>
	<li><code>obstacles[0] == obstacles[n] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们用 $f[i][j]$ 表示从起点跳到第 $i$ 个点，且当前在第 $j$ 条跑道的最少侧跳次数。注意到每个位置 $i$ 只依赖于 $i - 1$ 位置，因此我们可以使用滚动数组优化空间复杂度。

定义 $f[j]$ 表示从起点跳到第 $i$ 个点，且当前在第 $j$ 条跑道的最少侧跳次数。答案即为 $min(f[0], f[1], f[2])$。

我们可以枚举当前点的三条跑道，如果当前跑道没有障碍，那么 $f[j]$ 不变，否则 $f[j]$ 为其它两条跑道中的最小值加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `obstacles` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSideJumps(self, obstacles: List[int]) -> int:
        f = [1, 0, 1]
        for v in obstacles[1:]:
            g = [inf] * 3
            for j in range(3):
                if v != j + 1:
                    g[j] = f[j]
            if v != 1:
                g[0] = min(g[0], min(g[1], g[2]) + 1)
            if v != 2:
                g[1] = min(g[1], min(g[0], g[2]) + 1)
            if v != 3:
                g[2] = min(g[2], min(g[0], g[1]) + 1)
            f = g
        return min(f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int inf = 0x3f3f3f3f;

    public int minSideJumps(int[] obstacles) {
        int[] f = new int[] {1, 0, 1};
        for (int i = 1; i < obstacles.length; ++i) {
            int v = obstacles[i];
            int[] g = new int[] {inf, inf, inf};
            for (int j = 0; j < 3; ++j) {
                if (v != j + 1) {
                    g[j] = f[j];
                }
            }
            if (v != 1) {
                g[0] = Math.min(g[0], Math.min(g[1], g[2]) + 1);
            }
            if (v != 2) {
                g[1] = Math.min(g[1], Math.min(g[0], g[2]) + 1);
            }
            if (v != 3) {
                g[2] = Math.min(g[2], Math.min(g[0], g[1]) + 1);
            }
            f = g;
        }
        return Math.min(f[0], Math.min(f[1], f[2]));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int inf = 0x3f3f3f3f;

    int minSideJumps(vector<int>& obstacles) {
        vector<int> f = {1, 0, 1};
        for (int i = 1; i < obstacles.size(); ++i) {
            int v = obstacles[i];
            vector<int> g(3, inf);
            for (int j = 0; j < 3; ++j) if (v != j + 1) g[j] = f[j];
            if (v != 1) g[0] = min(g[0], min(g[1], g[2]) + 1);
            if (v != 2) g[1] = min(g[1], min(g[0], g[2]) + 1);
            if (v != 3) g[2] = min(g[2], min(g[0], g[1]) + 1);
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func minSideJumps(obstacles []int) int {
	inf := 0x3f3f3f3f
	f := [3]int{1, 0, 1}
	for _, v := range obstacles[1:] {
		g := [3]int{inf, inf, inf}
		for j := 0; j < 3; j++ {
			if v != j+1 {
				g[j] = f[j]
			}
		}
		if v != 1 {
			g[0] = min(g[0], min(g[1], g[2])+1)
		}
		if v != 2 {
			g[1] = min(g[1], min(g[0], g[2])+1)
		}
		if v != 3 {
			g[2] = min(g[2], min(g[0], g[1])+1)
		}
		f = g
	}
	return min(f[0], min(f[1], f[2]))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
