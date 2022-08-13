# [2092. 找出知晓秘密的所有专家](https://leetcode.cn/problems/find-all-people-with-secret)

[English Version](/solution/2000-2099/2092.Find%20All%20People%20With%20Secret/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，表示有 <code>n</code> 个专家从 <code>0</code> 到 <code>n - 1</code> 编号。另外给你一个下标从 0 开始的二维整数数组 <code>meetings</code> ，其中 <code>meetings[i] = [x<sub>i</sub>, y<sub>i</sub>, time<sub>i</sub>]</code> 表示专家 <code>x<sub>i</sub></code> 和专家 <code>y<sub>i</sub></code> 在时间 <code>time<sub>i</sub></code> 要开一场会。一个专家可以同时参加 <strong>多场会议</strong> 。最后，给你一个整数 <code>firstPerson</code> 。</p>

<p>专家 <code>0</code> 有一个 <strong>秘密</strong> ，最初，他在时间&nbsp;<code>0</code> 将这个秘密分享给了专家 <code>firstPerson</code> 。接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。更正式的表达是，每次会议，如果专家 <code>x<sub>i</sub></code> 在时间 <code>time<sub>i</sub></code> 时知晓这个秘密，那么他将会与专家 <code>y<sub>i</sub></code> 分享这个秘密，反之亦然。</p>

<p>秘密共享是 <strong>瞬时发生</strong> 的。也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。</p>

<p>在所有会议都结束之后，返回所有知晓这个秘密的专家列表。你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
<strong>输出：</strong>[0,1,2,3,5]
<strong>解释：
</strong>时间 0 ，专家 0 将秘密与专家 1 共享。
时间 5 ，专家 1 将秘密与专家 2 共享。
时间 8 ，专家 2 将秘密与专家 3 共享。
时间 10 ，专家 1 将秘密与专家 5 共享。
因此，在所有会议结束后，专家 0、1、2、3 和 5 都将知晓这个秘密。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
<strong>输出：</strong>[0,1,3]
<strong>解释：</strong>
时间 0 ，专家 0 将秘密与专家 3 共享。
时间 2 ，专家 1 与专家 2 都不知晓这个秘密。
时间 3 ，专家 3 将秘密与专家 0 和专家 1 共享。
因此，在所有会议结束后，专家 0、1 和 3 都将知晓这个秘密。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
<strong>输出：</strong>[0,1,2,3,4]
<strong>解释：</strong>
时间 0 ，专家 0 将秘密与专家 1 共享。
时间 1 ，专家 1 将秘密与专家 2 共享，专家 2 将秘密与专家 3 共享。
注意，专家 2 可以在收到秘密的同一时间分享此秘密。
时间 2 ，专家 3 将秘密与专家 4 共享。
因此，在所有会议结束后，专家 0、1、2、3 和 4 都将知晓这个秘密。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= meetings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>meetings[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i </sub>&lt;= n - 1</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= firstPerson &lt;= n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findAllPeople(
        self, n: int, meetings: List[List[int]], firstPerson: int
    ) -> List[int]:
        vis = [False] * n
        vis[0] = vis[firstPerson] = True
        meetings.sort(key=lambda x: x[2])
        i, m = 0, len(meetings)
        while i < m:
            j = i
            while j + 1 < m and meetings[j + 1][2] == meetings[i][2]:
                j += 1
            s = set()
            g = defaultdict(list)
            for x, y, _ in meetings[i : j + 1]:
                g[x].append(y)
                g[y].append(x)
                s.update([x, y])
            q = deque([u for u in s if vis[u]])
            while q:
                u = q.popleft()
                for v in g[u]:
                    if not vis[v]:
                        vis[v] = True
                        q.append(v)
            i = j + 1
        return [i for i, v in enumerate(vis) if v]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean[] vis = new boolean[n];
        vis[0] = true;
        vis[firstPerson] = true;
        int m = meetings.length;
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < m;) {
            int j = i;
            for (; j + 1 < m && meetings[j + 1][2] == meetings[i][2]; ++j);
            Map<Integer, List<Integer>> g = new HashMap<>();
            Set<Integer> s = new HashSet<>();
            for (int k = i; k <= j; ++k) {
                int x = meetings[k][0], y = meetings[k][1];
                g.computeIfAbsent(x, key -> new ArrayList<>()).add(y);
                g.computeIfAbsent(y, key -> new ArrayList<>()).add(x);
                s.add(x);
                s.add(y);
            }
            Deque<Integer> q = new ArrayDeque<>();
            for (int u : s) {
                if (vis[u]) {
                    q.offer(u);
                }
            }
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : g.getOrDefault(u, Collections.emptyList())) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
            }
            i = j + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                ans.add(i);
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
    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {
        vector<bool> vis(n);
        vis[0] = vis[firstPerson] = true;
        sort(meetings.begin(), meetings.end(), [&](const auto& x, const auto& y) {
            return x[2] < y[2];
        });
        for (int i = 0, m = meetings.size(); i < m;) {
            int j = i;
            for (; j + 1 < m && meetings[j + 1][2] == meetings[i][2]; ++j)
                ;
            unordered_map<int, vector<int>> g;
            unordered_set<int> s;
            for (int k = i; k <= j; ++k) {
                int x = meetings[k][0], y = meetings[k][1];
                g[x].push_back(y);
                g[y].push_back(x);
                s.insert(x);
                s.insert(y);
            }
            queue<int> q;
            for (int u : s)
                if (vis[u])
                    q.push(u);
            while (!q.empty()) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.push(v);
                    }
                }
            }
            i = j + 1;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (vis[i])
                ans.push_back(i);
        return ans;
    }
};
```

### **Go**

```go
func findAllPeople(n int, meetings [][]int, firstPerson int) []int {
	vis := make([]bool, n)
	vis[0], vis[firstPerson] = true, true
	sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][2] < meetings[j][2]
	})
	for i, j, m := 0, 0, len(meetings); i < m; i = j + 1 {
		j = i
		for j+1 < m && meetings[j+1][2] == meetings[i][2] {
			j++
		}
		g := map[int][]int{}
		s := map[int]bool{}
		for _, e := range meetings[i : j+1] {
			x, y := e[0], e[1]
			g[x] = append(g[x], y)
			g[y] = append(g[y], x)
			s[x], s[y] = true, true
		}
		q := []int{}
		for u := range s {
			if vis[u] {
				q = append(q, u)
			}
		}
		for len(q) > 0 {
			u := q[0]
			q = q[1:]
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					q = append(q, v)
				}
			}
		}
	}
	var ans []int
	for i, v := range vis {
		if v {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findAllPeople(
    n: number,
    meetings: number[][],
    firstPerson: number,
): number[] {
    let parent: Array<number> = Array.from({ length: n + 1 }, (v, i) => i);
    parent[firstPerson] = 0;

    function findParent(index: number): number {
        if (parent[index] != index) parent[index] = findParent(parent[index]);
        return parent[index];
    }

    let map = new Map<number, Array<Array<number>>>();
    for (let meeting of meetings) {
        const time = meeting[2];
        let members: Array<Array<number>> = map.get(time) || new Array();
        members.push(meeting);
        map.set(time, members);
    }
    const times = [...map.keys()].sort((a, b) => a - b);
    for (let time of times) {
        // round 1
        for (let meeting of map.get(time)) {
            let [a, b] = meeting;
            if (!parent[findParent(a)] || !parent[findParent(b)]) {
                parent[findParent(a)] = 0;
                parent[findParent(b)] = 0;
            }
            parent[findParent(a)] = parent[findParent(b)];
        }
        // round 2
        for (let meeting of map.get(time)) {
            let [a, b] = meeting;
            if (!parent[findParent(a)] || !parent[findParent(b)]) {
                parent[findParent(a)] = 0;
                parent[findParent(b)] = 0;
            } else {
                parent[a] = a;
                parent[b] = b;
            }
        }
    }

    let ans = new Array<number>();
    for (let i = 0; i <= n; i++) {
        if (!parent[findParent(i)]) {
            ans.push(i);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
