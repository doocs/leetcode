# [1654. 到家的最少跳跃次数](https://leetcode.cn/problems/minimum-jumps-to-reach-home)

[English Version](/solution/1600-1699/1654.Minimum%20Jumps%20to%20Reach%20Home/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一只跳蚤的家在数轴上的位置 <code>x</code> 处。请你帮助它从位置 <code>0</code> 出发，到达它的家。</p>

<p>跳蚤跳跃的规则如下：</p>

<ul>
	<li>它可以 <strong>往前</strong> 跳恰好 <code>a</code> 个位置（即往右跳）。</li>
	<li>它可以 <strong>往后</strong> 跳恰好 <code>b</code> 个位置（即往左跳）。</li>
	<li>它不能 <strong>连续</strong> 往后跳 <code>2</code> 次。</li>
	<li>它不能跳到任何 <code>forbidden</code> 数组中的位置。</li>
</ul>

<p>跳蚤可以往前跳 <strong>超过</strong> 它的家的位置，但是它 <strong>不能跳到负整数</strong> 的位置。</p>

<p>给你一个整数数组 <code>forbidden</code> ，其中 <code>forbidden[i]</code> 是跳蚤不能跳到的位置，同时给你整数 <code>a</code>， <code>b</code> 和 <code>x</code> ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 <code>x</code> 的可行方案，请你返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
<b>输出：</b>3
<b>解释：</b>往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
<b>输出：</b>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
<b>输出：</b>2
<b>解释：</b>往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= forbidden.length <= 1000</code></li>
	<li><code>1 <= a, b, forbidden[i] <= 2000</code></li>
	<li><code>0 <= x <= 2000</code></li>
	<li><code>forbidden</code> 中所有位置互不相同。</li>
	<li>位置 <code>x</code> 不在 <code>forbidden</code> 中。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

我们可以将跳蚤的位置和跳跃方向作为状态，使用 BFS 搜索最短路径。本题比较关键的地方在于确定右边界，即跳蚤最远能跳到哪里。

如果 $a \geq b$，即往前跳的距离大于往后跳的距离，那么如果跳蚤在位置大于 $x+b$ 的地方，它就不能再往前跳了，因为跳蚤不能连续往后跳两次，如果继续往前跳，那么永远无法跳到 $x$ 的位置。因此，如果 $a \geq b$，那么右边界可以是 $x+b$。

如果 $a \lt b$，即往前跳的距离小于往后跳的距离，那么如果跳蚤所在的位置减去 $b$ 超过 $2000$，此时选择往后跳，否则往前跳。因此，如果 $a \lt b$，那么右边界不超过 $6000$。

综上，我们可以将右边界设置为 $6000$。

接下来，我们使用 BFS 搜索最短路径。我们使用一个队列，初始时将跳蚤的位置和跳跃方向作为状态加入队列。每次从队列中取出一个状态，如果该状态的位置等于 $x$，那么我们就找到了一条从初始状态到达目标状态的路径，返回当前的步数即可。否则，我们将当前状态的下一个状态加入队列，下一个状态有两种情况：

-   往前跳，跳跃方向为 $1$；
-   当前跳跃方向为 $1$ 时，往后跳，跳跃方向为 $0$。

注意，我们需要判断下一个状态是否合法，即下一个状态的位置不超过右边界，且不在禁止的位置中，且未被访问过。

如果队列为空，说明无法到达目标位置，返回 $-1$。

时间复杂度 $O(M)$，空间复杂度 $O(M)$。其中 $M$ 是右边界，本题中 $M \leq 6000$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        s = set(forbidden)
        q = deque([(0, 1)])
        vis = {(0, 1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, k = q.popleft()
                if i == x:
                    return ans
                nxt = [(i + a, 1)]
                if k & 1:
                    nxt.append((i - b, 0))
                for j, k in nxt:
                    if 0 <= j < 6000 and j not in s and (j, k) not in vis:
                        q.append((j, k))
                        vis.add((j, k))
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> s = new HashSet<>();
        for (int v : forbidden) {
            s.add(v);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 1});
        final int n = 6000;
        boolean[][] vis = new boolean[n][2];
        vis[0][1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                int i = p[0], k = p[1];
                if (i == x) {
                    return ans;
                }
                List<int[]> nxt = new ArrayList<>();
                nxt.add(new int[] {i + a, 1});
                if ((k & 1) == 1) {
                    nxt.add(new int[] {i - b, 0});
                }
                for (var e : nxt) {
                    int j = e[0];
                    k = e[1];
                    if (j >= 0 && j < n && !s.contains(j) && !vis[j][k]) {
                        q.offer(new int[] {j, k});
                        vis[j][k] = true;
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<pair<int, int>> q;
        q.emplace(0, 1);
        const int n = 6000;
        bool vis[n][2];
        memset(vis, false, sizeof(vis));
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto [i, k] = q.front();
                q.pop();
                if (i == x) {
                    return ans;
                }
                vector<pair<int, int>> nxts = {{i + a, 1}};
                if (k & 1) {
                    nxts.emplace_back(i - b, 0);
                }
                for (auto [j, l] : nxts) {
                    if (j >= 0 && j < n && !s.count(j) && !vis[j][l]) {
                        vis[j][l] = true;
                        q.emplace(j, l);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func minimumJumps(forbidden []int, a int, b int, x int) (ans int) {
	s := map[int]bool{}
	for _, v := range forbidden {
		s[v] = true
	}
	q := [][2]int{[2]int{0, 1}}
	const n = 6000
	vis := make([][2]bool, n)
	vis[0][1] = true
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, k := p[0], p[1]
			if i == x {
				return
			}
			nxt := [][2]int{[2]int{i + a, 1}}
			if k&1 == 1 {
				nxt = append(nxt, [2]int{i - b, 0})
			}
			for _, e := range nxt {
				j, l := e[0], e[1]
				if j >= 0 && j < n && !s[j] && !vis[j][l] {
					q = append(q, [2]int{j, l})
					vis[j][l] = true
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
