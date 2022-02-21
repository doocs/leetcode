# [773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle)

[中文文档](/solution/0700-0799/0773.Sliding%20Puzzle/README.md)

## Description

<p>On a 2x3 <code>board</code>, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.</p>

<p>A move consists of choosing <code>0</code>&nbsp;and a 4-directionally adjacent number and swapping it.</p>

<p>The state of the board is <em>solved</em> if and only if the <code>board</code> is <code>[[1,2,3],[4,5,0]].</code></p>

<p>Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.</p>

<p><strong>Examples:</strong></p>

<pre>

<strong>Input:</strong> board = [[1,2,3],[4,0,5]]

<strong>Output:</strong> 1

<strong>Explanation:</strong> Swap the 0 and the 5 in one move.

</pre>

<pre>

<strong>Input:</strong> board = [[1,2,3],[5,4,0]]

<strong>Output:</strong> -1

<strong>Explanation:</strong> No number of moves will make the board solved.

</pre>

<pre>

<strong>Input:</strong> board = [[4,1,2],[5,0,3]]

<strong>Output:</strong> 5

<strong>Explanation:</strong> 5 is the smallest number of moves that solves the board.

An example path:

After move 0: [[4,1,2],[5,0,3]]

After move 1: [[4,1,2],[0,5,3]]

After move 2: [[0,1,2],[4,5,3]]

After move 3: [[1,0,2],[4,5,3]]

After move 4: [[1,2,0],[4,5,3]]

After move 5: [[1,2,3],[4,5,0]]

</pre>

<pre>

<strong>Input:</strong> board = [[3,2,4],[1,5,0]]

<strong>Output:</strong> 14

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>board</code> will be a 2 x 3 array as described above.</li>
	<li><code>board[i][j]</code> will be a permutation of <code>[0, 1, 2, 3, 4, 5]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

        def next():
            def find0():
                for i in range(2):
                    for j in range(3):
                        if board[i][j] == 0:
                            return (i, j)
                return (0, 0)

            res = []
            i, j = find0()
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
        vis = set([(start)])
        q = deque([(start)])
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q), 0, -1):
                x = q.popleft()
                setb(x)
                for y in next():
                    if y == end:
                        return ans
                    if y not in vis:
                        vis.add(y)
                        q.append(y)
        return -1
```

### **Java**

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
        queue<string> q{{start}};
        int ans = 0;
        while (!q.empty())
        {
            ++ans;
            for (int n = q.size(); n > 0; --n)
            {
                string x = q.front();
                q.pop();
                setb(x, board);
                for (string y : next(board))
                {
                    if (y == end) return ans;
                    if (!vis.count(y))
                    {
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
        for (int k = 0; k < 4; ++k)
        {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < 2 && y >= 0 && y < 3)
            {
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

### **...**

```

```

<!-- tabs:end -->
