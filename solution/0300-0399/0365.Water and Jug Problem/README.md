---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0365.Water%20and%20Jug%20Problem/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 数学
---

<!-- problem:start -->

# [365. 水壶问题](https://leetcode.cn/problems/water-and-jug-problem)

[English Version](/solution/0300-0399/0365.Water%20and%20Jug%20Problem/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有两个水壶，容量分别为&nbsp;<code>x</code>&nbsp;和 <code>y</code> 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到&nbsp;<code>target</code>&nbsp;升。</p>

<p>你可以：</p>

<ul>
	<li>装满任意一个水壶</li>
	<li>清空任意一个水壶</li>
	<li>将水从一个水壶倒入另一个水壶，直到接水壶已满，或倒水壶已空。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong>&nbsp;</p>

<pre>
<strong>输入:</strong> x = 3,y = 5,target = 4
<strong>输出:</strong> true
<strong>解释：
</strong>按照以下步骤操作，以达到总共 4 升水：
1. 装满 5 升的水壶(0, 5)。
2. 把 5 升的水壶倒进 3 升的水壶，留下 2 升(3, 2)。
3. 倒空 3 升的水壶(0, 2)。
4. 把 2 升水从 5 升的水壶转移到 3 升的水壶(2, 0)。
5. 再次加满 5 升的水壶(2, 5)。
6. 从 5 升的水壶向 3 升的水壶倒水直到 3 升的水壶倒满。5 升的水壶里留下了 4 升水(3, 4)。
7. 倒空 3 升的水壶。现在，5 升的水壶里正好有 4 升水(0, 4)。
参考：来自著名的&nbsp;<a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg"><em>"Die Hard"</em></a></pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> x = 2, y = 6, target = 5
<strong>输出:</strong> false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> x = 1, y = 2, target = 3
<strong>输出:</strong> true
<b>解释：</b>同时倒满两个水壶。现在两个水壶中水的总量等于 3。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= x, y, target &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们不妨记 $\textit{jug1Capacity}$ 为 $x$, $\textit{jug2Capacity}$ 为 $y$, $\textit{targetCapacity}$ 为 $z$。

接下来，我们设计一个函数 $dfs(i, j)$，表示当前 $jug1$ 中有 $i$ 升水，$jug2$ 中有 $j$ 升水，是否可以得到 $z$ 升水。

函数 $dfs(i, j)$ 的执行过程如下：

- 如果 $(i, j)$ 已经被访问过，返回 $false$。
- 如果 $i = z$ 或者 $j = z$ 或者 $i + j = z$，返回 $true$。
- 如果我们给 $jug1$ 倒满水，或者给 $jug2$ 倒满水，或者将 $jug1$ 清空，或者将 $jug2$ 清空，可以得到 $z$ 升水，返回 $true$。
- 如果我们将 $jug1$ 中的水倒入 $jug2$，或者将 $jug2$ 中的水倒入 $jug1$，可以得到 $z$ 升水，返回 $true$。

答案即为 $dfs(0, 0)$。

时间复杂度 $O(x + y)$，空间复杂度 $O(x + y)$。其中 $x$ 和 $y$ 分别为 $\textit{jug1Capacity}$ 和 $\textit{jug2Capacity}$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMeasureWater(self, x: int, y: int, z: int) -> bool:
        def dfs(i: int, j: int) -> bool:
            if (i, j) in vis:
                return False
            vis.add((i, j))
            if i == z or j == z or i + j == z:
                return True
            if dfs(x, j) or dfs(i, y) or dfs(0, j) or dfs(i, 0):
                return True
            a = min(i, y - j)
            b = min(j, x - i)
            return dfs(i - a, j + a) or dfs(i + b, j - b)

        vis = set()
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Set<Long> vis = new HashSet<>();
    private int x, y, z;

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        x = jug1Capacity;
        y = jug2Capacity;
        z = targetCapacity;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        long st = f(i, j);
        if (!vis.add(st)) {
            return false;
        }
        if (i == z || j == z || i + j == z) {
            return true;
        }
        if (dfs(x, j) || dfs(i, y) || dfs(0, j) || dfs(i, 0)) {
            return true;
        }
        int a = Math.min(i, y - j);
        int b = Math.min(j, x - i);
        return dfs(i - a, j + a) || dfs(i + b, j - b);
    }

    private long f(int i, int j) {
        return i * 1000000L + j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canMeasureWater(int x, int y, int z) {
        using pii = pair<int, int>;
        stack<pii> stk;
        stk.emplace(0, 0);
        auto hash_function = [](const pii& o) { return hash<int>()(o.first) ^ hash<int>()(o.second); };
        unordered_set<pii, decltype(hash_function)> vis(0, hash_function);
        while (stk.size()) {
            auto st = stk.top();
            stk.pop();
            if (vis.count(st)) {
                continue;
            }
            vis.emplace(st);
            auto [i, j] = st;
            if (i == z || j == z || i + j == z) {
                return true;
            }
            stk.emplace(x, j);
            stk.emplace(i, y);
            stk.emplace(0, j);
            stk.emplace(i, 0);
            int a = min(i, y - j);
            int b = min(j, x - i);
            stk.emplace(i - a, j + a);
            stk.emplace(i + b, j - b);
        }
        return false;
    }
};
```

#### Go

```go
func canMeasureWater(x int, y int, z int) bool {
	type pair struct{ x, y int }
	vis := map[pair]bool{}
	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		st := pair{i, j}
		if vis[st] {
			return false
		}
		vis[st] = true
		if i == z || j == z || i+j == z {
			return true
		}
		if dfs(x, j) || dfs(i, y) || dfs(0, j) || dfs(i, 0) {
			return true
		}
		a := min(i, y-j)
		b := min(j, x-i)
		return dfs(i-a, j+a) || dfs(i+b, j-b)
	}
	return dfs(0, 0)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
