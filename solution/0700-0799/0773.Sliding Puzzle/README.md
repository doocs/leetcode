# [773. 滑动谜题](https://leetcode.cn/problems/sliding-puzzle)

[English Version](/solution/0700-0799/0773.Sliding%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 <code>2 x 3</code> 的板上（<code>board</code>）有 5 块砖瓦，用数字 <code>1~5</code> 来表示, 以及一块空缺用&nbsp;<code>0</code>&nbsp;来表示。一次 <strong>移动</strong> 定义为选择&nbsp;<code>0</code>&nbsp;与一个相邻的数字（上下左右）进行交换.</p>

<p>最终当板&nbsp;<code>board</code>&nbsp;的结果是&nbsp;<code>[[1,2,3],[4,5,0]]</code>&nbsp;谜板被解开。</p>

<p>给出一个谜板的初始状态&nbsp;<code>board</code>&nbsp;，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0773.Sliding%20Puzzle/images/slide1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>board = [[1,2,3],[4,0,5]]
<strong>输出：</strong>1
<strong>解释：</strong>交换 0 和 5 ，1 步完成
</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0773.Sliding%20Puzzle/images/slide2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>board = [[1,2,3],[5,4,0]]
<strong>输出：</strong>-1
<strong>解释：</strong>没有办法完成谜板
</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0773.Sliding%20Puzzle/images/slide3-grid.jpg" /></p>

<pre>
<strong>输入：</strong>board = [[4,1,2],[5,0,3]]
<strong>输出：</strong>5
<strong>解释：</strong>
最少完成谜板的最少移动次数是 5 ，
一种移动路径:
尚未移动: [[4,1,2],[5,0,3]]
移动 1 次: [[4,1,2],[0,5,3]]
移动 2 次: [[0,1,2],[4,5,3]]
移动 3 次: [[1,0,2],[4,5,3]]
移动 4 次: [[1,2,0],[4,5,3]]
移动 5 次: [[1,2,3],[4,5,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board.length == 2</code></li>
	<li><code>board[i].length == 3</code></li>
	<li><code>0 &lt;= board[i][j] &lt;= 5</code></li>
	<li><code>board[i][j]</code>&nbsp;中每个值都 <strong>不同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 最小步数模型。可以使用朴素 BFS 直接搜索，也可以使用 A\* 算法优化搜索。

A\* 算法主要思想如下：

1. 将 BFS 队列转换为优先队列（小根堆）；
1. 队列中的每个元素为 `(dist[state] + f(state), state)`，`dist[state]` 表示从起点到当前 state 的距离，`f(state)` 表示从当前 state 到终点的估计距离，这两个距离之和作为堆排序的依据；
1. 当终点第一次出队时，说明找到了从起点到终点的最短路径，直接返回对应的 step；
1. `f(state)` 是估价函数，并且估价函数要满足 `f(state) <= g(state)`，其中 `g(state)` 表示 state 到终点的真实距离；
1. A\* 算法只能保证终点第一次出队时，即找到了一条从起点到终点的最小路径，不能保证其他点出队时也是从起点到当前点的最短路径。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def slidingPuzzle(self, board: List[List[int]]) -> int:
        t = [None] * 6

        def gets():
            for i in range(2):
                for j in range(3):
                    t[i * 3 + j] = str(board[i][j])
            return ''.join(t)

        def setb(s):
            for i in range(2):
                for j in range(3):
                    board[i][j] = int(s[i * 3 + j])

        def f():
            res = []
            i, j = next((i, j) for i in range(2) for j in range(3) if board[i][j] == 0)
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < 2 and 0 <= y < 3:
                    board[i][j], board[x][y] = board[x][y], board[i][j]
                    res.append(gets())
                    board[i][j], board[x][y] = board[x][y], board[i][j]
            return res

        start = gets()
        end = "123450"
        if start == end:
            return 0
        vis = {start}
        q = deque([(start)])
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                x = q.popleft()
                setb(x)
                for y in f():
                    if y == end:
                        return ans
                    if y not in vis:
                        vis.add(y)
                        q.append(y)
        return -1
```

A\* 算法：

```python
class Solution:
    def slidingPuzzle(self, board: List[List[int]]) -> int:
        m, n = 2, 3
        seq = []
        start, end = '', '123450'
        for i in range(m):
            for j in range(n):
                if board[i][j] != 0:
                    seq.append(board[i][j])
                start += str(board[i][j])

        def check(seq):
            n = len(seq)
            cnt = sum(seq[i] > seq[j] for i in range(n) for j in range(i, n))
            return cnt % 2 == 0

        def f(s):
            ans = 0
            for i in range(m * n):
                if s[i] != '0':
                    num = ord(s[i]) - ord('1')
                    ans += abs(i // n - num // n) + abs(i % n - num % n)
            return ans


        if not check(seq):
            return -1
        q = [(f(start), start)]
        dist = {start: 0}
        while q:
            _, state = heappop(q)
            if state == end:
                return dist[state]
            p1 = state.index('0')
            i, j = p1 // n, p1 % n
            s = list(state)
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    p2 = x * n + y
                    s[p1], s[p2] = s[p2], s[p1]
                    next = ''.join(s)
                    s[p1], s[p2] = s[p2], s[p1]
                    if next not in dist or dist[next] > dist[state] + 1:
                        dist[next] = dist[state] + 1
                        heappush(q, (dist[next] + f(next), next))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String[] t = new String[6];
    private int[][] board;

    public int slidingPuzzle(int[][] board) {
        this.board = board;
        String start = gets();
        String end = "123450";
        if (end.equals(start)) {
            return 0;
        }
        Set<String> vis = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        q.offer(start);
        vis.add(start);
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                String x = q.poll();
                setb(x);
                for (String y : next()) {
                    if (y.equals(end)) {
                        return ans;
                    }
                    if (!vis.contains(y)) {
                        vis.add(y);
                        q.offer(y);
                    }
                }
            }
        }
        return -1;
    }

    private String gets() {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                t[i * 3 + j] = String.valueOf(board[i][j]);
            }
        }
        return String.join("", t);
    }

    private void setb(String s) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                board[i][j] = s.charAt(i * 3 + j) - '0';
            }
        }
    }

    private int[] find0() {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    private List<String> next() {
        int[] p = find0();
        int i = p[0], j = p[1];
        int[] dirs = {-1, 0, 1, 0, -1};
        List<String> res = new ArrayList<>();
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < 2 && y >= 0 && y < 3) {
                swap(i, j, x, y);
                res.add(gets());
                swap(i, j, x, y);
            }
        }
        return res;
    }

    private void swap(int i, int j, int x, int y) {
        int t = board[i][j];
        board[i][j] = board[x][y];
        board[x][y] = t;
    }
}
```

A\* 算法：

```java
class Solution {
    private int m = 2;
    private int n = 3;

    public int slidingPuzzle(int[][] board) {
        String start = "";
        String end = "123450";
        String seq = "";
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                start += board[i][j];
                if (board[i][j] != 0) {
                    seq += board[i][j];
                }
            }
        }
        if (!check(seq)) {
            return -1;
        }
        PriorityQueue<Pair<Integer, String>> q
            = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        q.offer(new Pair<>(f(start), start));
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            String state = q.poll().getValue();
            int step = dist.get(state);
            if (end.equals(state)) {
                return step;
            }
            int p1 = state.indexOf("0");
            int i = p1 / n, j = p1 % n;
            char[] s = state.toCharArray();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int p2 = x * n + y;
                    swap(s, p1, p2);
                    String next = String.valueOf(s);
                    if (!dist.containsKey(next) || dist.get(next) > step + 1) {
                        dist.put(next, step + 1);
                        q.offer(new Pair<>(step + 1 + f(next), next));
                    }
                    swap(s, p1, p2);
                }
            }
        }
        return -1;
    }

    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private int f(String s) {
        int ans = 0;
        for (int i = 0; i < m * n; ++i) {
            if (s.charAt(i) != '0') {
                int num = s.charAt(i) - '1';
                ans += Math.abs(i / n - num / n) + Math.abs(i % n - num % n);
            }
        }
        return ans;
    }

    private boolean check(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) > s.charAt(j)) {
                    ++cnt;
                }
            }
        }
        return cnt % 2 == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        string start = gets(board);
        string end = "123450";
        if (start == end) return 0;
        unordered_set<string> vis;
        vis.insert(start);
        queue<string> q {{start}};
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                string x = q.front();
                q.pop();
                setb(x, board);
                for (string y : next(board)) {
                    if (y == end) return ans;
                    if (!vis.count(y)) {
                        vis.insert(y);
                        q.push(y);
                    }
                }
            }
        }
        return -1;
    }

    string gets(vector<vector<int>>& board) {
        string s = "";
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 3; ++j)
                s.push_back('0' + board[i][j]);
        return s;
    }

    void setb(string s, vector<vector<int>>& board) {
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 3; ++j)
                board[i][j] = s[i * 3 + j] - '0';
    }

    vector<string> next(vector<vector<int>>& board) {
        vector<string> res;
        auto p = find0(board);
        int i = p.first, j = p.second;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < 2 && y >= 0 && y < 3) {
                swap(i, j, x, y, board);
                res.push_back(gets(board));
                swap(i, j, x, y, board);
            }
        }
        return res;
    }

    void swap(int i, int j, int x, int y, vector<vector<int>>& board) {
        int t = board[i][j];
        board[i][j] = board[x][y];
        board[x][y] = t;
    }

    pair<int, int> find0(vector<vector<int>>& board) {
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 3; ++j)
                if (board[i][j] == 0)
                    return {i, j};
        return {0, 0};
    }
};
```

A\* 算法：

```cpp
class Solution {
public:
    int m = 2;
    int n = 3;

    int slidingPuzzle(vector<vector<int>>& board) {
        string start, seq;
        string end = "123450";
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                start += char(board[i][j] + '0');
                if (board[i][j] != 0) seq += char(board[i][j] + '0');
            }
        }
        if (!check(seq)) return -1;
        typedef pair<int , string> PIS;
        priority_queue<PIS, vector<PIS>, greater<PIS>> q;
        unordered_map<string, int> dist;
        dist[start] = 0;
        q.push({f(start), start});
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty())
        {
            PIS t = q.top();
            q.pop();
            string state = t.second;
            int step = dist[state];
            if (state == end) return step;
            int p1 = state.find('0');
            int i = p1 / n, j = p1 % n;
            for (int k = 0; k < 4; ++k)
            {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                int p2 = x * n + y;
                swap(state[p1], state[p2]);
                if (!dist.count(state) || dist[state] > step + 1)
                {
                    dist[state] = step + 1;
                    q.push({step + 1 + f(state), state});
                }
                swap(state[p1], state[p2]);
            }
        }
        return -1;
    }

    bool check(string s) {
        int n = s.size();
        int cnt = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i; j < n; ++j)
                if (s[i] > s[j])
                    ++cnt;
        return cnt % 2 == 0;
    }

    int f(string s) {
        int ans = 0;
        for (int i = 0; i < m * n; ++i)
        {
            if (s[i] == '0') continue;
            int num = s[i] - '1';
            ans += abs(num / n - i / n) + abs(num % n - i % n);
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
