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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        s = set(forbidden)
        q = deque([(0, 0)])
        vis = {(0, 1), (0, -1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, dir = q.popleft()
                if i == x:
                    return ans
                nxt = [(i + a, 1)]
                if dir != -1:
                    nxt.append((i - b, -1))
                for j, dir in nxt:
                    if 0 <= j <= 6000 and j not in s and (j, dir) not in vis:
                        vis.add((j, dir))
                        q.append((j, dir))
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int N = 6010;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> s = new HashSet<>();
        for (int v : forbidden) {
            s.add(v);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 2});
        boolean[][] vis = new boolean[N][2];
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0], dir = p[1];
                if (i == x) {
                    return ans;
                }
                List<int[]> nxt = new ArrayList<>();
                nxt.add(new int[]{i + a, 1});
                if (dir != 0) {
                    nxt.add(new int[]{i - b, 0});
                }
                for (int[] e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.contains(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.offer(new int[]{j, dir});
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
    const int N = 6010;

    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<vector<int>> q;
        q.push({0, 0});
        vector<vector<bool>> vis(N, vector<bool>(2));
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto p = q.front();
                q.pop();
                int i = p[0], dir = p[1];
                if (i == x) return ans;
                vector<vector<int>> nxt;
                nxt.push_back({i + a, 1});
                if (dir) nxt.push_back({i - b, 0});
                for (auto& e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.count(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.push({j, dir});
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
func minimumJumps(forbidden []int, a int, b int, x int) int {
	n := 6010
	s := make(map[int]bool)
	for _, v := range forbidden {
		s[v] = true
	}
	q := [][]int{[]int{0, 0}}
	vis := make([][]bool, n)
	for i := range vis {
		vis[i] = make([]bool, 2)
	}
	vis[0][0] = true
	vis[0][1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, dir := p[0], p[1]
			if i == x {
				return ans
			}
			nxt := [][]int{[]int{i + a, 1}}
			if dir != 0 {
				nxt = append(nxt, []int{i - b, 0})
			}
			for _, e := range nxt {
				j := e[0]
				dir = e[1]
				if j >= 0 && j < n && !s[j] && !vis[j][dir] {
					vis[j][dir] = true
					q = append(q, []int{j, dir})
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
