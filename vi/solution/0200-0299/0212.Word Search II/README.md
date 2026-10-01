---
comments: true
difficulty: Hard
tags:
    - Trie
    - Array
    - String
    - Backtracking
    - Matrix
---

<!-- problem:start -->

# [212. Word Search II](https://leetcode.com/problems/word-search-ii)

[中文文档](/solution/0200-0299/0212.Word%20Search%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một <code>board</code> ký tự kích thước <code>m x n</code> và một danh sách các chuỗi <code>words</code>, hãy trả về <em>tất cả các từ xuất hiện trên board</em>.</p>

<p>Mỗi từ phải được tạo thành từ các chữ cái trong những ô kề nhau liên tiếp, trong đó <strong>các ô kề nhau</strong> là các ô nằm ngang hoặc dọc cạnh nhau. Không được sử dụng cùng một ô chữ cái quá một lần trong một từ.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search1.jpg" style="width: 322px; height: 322px;" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;o&quot;,&quot;a&quot;,&quot;a&quot;,&quot;n&quot;],[&quot;e&quot;,&quot;t&quot;,&quot;a&quot;,&quot;e&quot;],[&quot;i&quot;,&quot;h&quot;,&quot;k&quot;,&quot;r&quot;],[&quot;i&quot;,&quot;f&quot;,&quot;l&quot;,&quot;v&quot;]], words = [&quot;oath&quot;,&quot;pea&quot;,&quot;eat&quot;,&quot;rain&quot;]
<strong>Đầu ra:</strong> [&quot;eat&quot;,&quot;oath&quot;]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;]], words = [&quot;abcb&quot;]
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>board[i][j]</code> là một chữ cái tiếng Anh viết thường.</li>
	<li><code>1 &lt;= words.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li>Tất cả các chuỗi trong <code>words</code> đều khác nhau.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Thực hiện một DFS riêng cho từng từ sẽ lặp lại công việc trên các tiền tố dùng chung. Xây dựng một trie từ $words$ cho phép quá trình tìm kiếm trên board đi theo cây và loại bỏ các nhánh không thể tiếp tục.
>
> Từ mỗi ô, chúng ta mở rộng đến các ô lân cận có cạnh tương ứng tồn tại trong trie. Khi đến một nút biểu diễn từ, chúng ta ghi nhận đáp án và đặt $ref$ thành $-1$ để từ đó không được ghi nhận lại.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.ref: int = -1

    def insert(self, w: str, ref: int):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.ref = ref


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        def dfs(node: Trie, i: int, j: int):
            idx = ord(board[i][j]) - ord('a')
            if node.children[idx] is None:
                return
            node = node.children[idx]
            if node.ref >= 0:
                ans.append(words[node.ref])
                node.ref = -1
            c = board[i][j]
            board[i][j] = '#'
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and board[x][y] != '#':
                    dfs(node, x, y)
            board[i][j] = c

        tree = Trie()
        for i, w in enumerate(words):
            tree.insert(w, i)
        m, n = len(board), len(board[0])
        ans = []
        for i in range(m):
            for j in range(n):
                dfs(tree, i, j)
        return ans
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    int ref = -1;

    public void insert(String w, int ref) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
        }
        node.ref = ref;
    }
}

class Solution {
    private char[][] board;
    private String[] words;
    private List<String> ans = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
        Trie tree = new Trie();
        for (int i = 0; i < words.length; ++i) {
            tree.insert(words[i], i);
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(tree, i, j);
            }
        }
        return ans;
    }

    private void dfs(Trie node, int i, int j) {
        int idx = board[i][j] - 'a';
        if (node.children[idx] == null) {
            return;
        }
        node = node.children[idx];
        if (node.ref != -1) {
            ans.add(words[node.ref]);
            node.ref = -1;
        }
        char c = board[i][j];
        board[i][j] = '#';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != '#') {
                dfs(node, x, y);
            }
        }
        board[i][j] = c;
    }
}
```

#### C++

```cpp
class Trie {
public:
    vector<Trie*> children;
    int ref;

    Trie()
        : children(26, nullptr)
        , ref(-1) {}

    void insert(const string& w, int ref) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
        }
        node->ref = ref;
    }
};

class Solution {
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        Trie* tree = new Trie();
        for (int i = 0; i < words.size(); ++i) {
            tree->insert(words[i], i);
        }
        vector<string> ans;
        int m = board.size(), n = board[0].size();

        function<void(Trie*, int, int)> dfs = [&](Trie* node, int i, int j) {
            int idx = board[i][j] - 'a';
            if (!node->children[idx]) {
                return;
            }
            node = node->children[idx];
            if (node->ref != -1) {
                ans.emplace_back(words[node->ref]);
                node->ref = -1;
            }
            int dirs[5] = {-1, 0, 1, 0, -1};
            char c = board[i][j];
            board[i][j] = '#';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
                    dfs(node, x, y);
                }
            }
            board[i][j] = c;
        };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(tree, i, j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	ref      int
}

func newTrie() *Trie {
	return &Trie{ref: -1}
}
func (this *Trie) insert(w string, ref int) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.ref = ref
}

func findWords(board [][]byte, words []string) (ans []string) {
	trie := newTrie()
	for i, w := range words {
		trie.insert(w, i)
	}
	m, n := len(board), len(board[0])
	var dfs func(*Trie, int, int)
	dfs = func(node *Trie, i, j int) {
		idx := board[i][j] - 'a'
		if node.children[idx] == nil {
			return
		}
		node = node.children[idx]
		if node.ref != -1 {
			ans = append(ans, words[node.ref])
			node.ref = -1
		}
		c := board[i][j]
		board[i][j] = '#'
		dirs := [5]int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#' {
				dfs(node, x, y)
			}
		}
		board[i][j] = c
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			dfs(trie, i, j)
		}
	}
	return
}
```

#### TypeScript

```ts
class Trie {
    children: Trie[];
    ref: number;

    constructor() {
        this.children = new Array(26);
        this.ref = -1;
    }

    insert(w: string, ref: number): void {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const c = w.charCodeAt(i) - 97;
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.ref = ref;
    }
}

function findWords(board: string[][], words: string[]): string[] {
    const tree = new Trie();
    for (let i = 0; i < words.length; ++i) {
        tree.insert(words[i], i);
    }
    const m = board.length;
    const n = board[0].length;
    const ans: string[] = [];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (node: Trie, i: number, j: number) => {
        const idx = board[i][j].charCodeAt(0) - 97;
        if (node.children[idx] == null) {
            return;
        }
        node = node.children[idx];
        if (node.ref != -1) {
            ans.push(words[node.ref]);
            node.ref = -1;
        }
        const c = board[i][j];
        board[i][j] = '#';
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
                dfs(node, x, y);
            }
        }
        board[i][j] = c;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            dfs(tree, i, j);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
