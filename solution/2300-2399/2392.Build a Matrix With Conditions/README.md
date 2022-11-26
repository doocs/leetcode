# [2392. 给定条件下构造矩阵](https://leetcode.cn/problems/build-a-matrix-with-conditions)

[English Version](/solution/2300-2399/2392.Build%20a%20Matrix%20With%20Conditions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;，同时给你：</p>

<ul>
	<li>一个大小为 <code>n</code>&nbsp;的二维整数数组&nbsp;<code>rowConditions</code>&nbsp;，其中&nbsp;<code>rowConditions[i] = [above<sub>i</sub>, below<sub>i</sub>]</code>&nbsp;和</li>
	<li>一个大小为 <code>m</code>&nbsp;的二维整数数组&nbsp;<code>colConditions</code>&nbsp;，其中&nbsp;<code>colConditions[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;。</li>
</ul>

<p>两个数组里的整数都是&nbsp;<code>1</code>&nbsp;到&nbsp;<code>k</code>&nbsp;之间的数字。</p>

<p>你需要构造一个&nbsp;<code>k x k</code>&nbsp;的矩阵，<code>1</code>&nbsp;到&nbsp;<code>k</code>&nbsp;每个数字需要&nbsp;<strong>恰好出现一次</strong>&nbsp;。剩余的数字都是<b>&nbsp;</b><code>0</code>&nbsp;。</p>

<p>矩阵还需要满足以下条件：</p>

<ul>
	<li>对于所有 <code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;之间的下标&nbsp;<code>i</code>&nbsp;，数字&nbsp;<code>above<sub>i</sub></code>&nbsp;所在的 <strong>行</strong>&nbsp;必须在数字&nbsp;<code>below<sub>i</sub></code>&nbsp;所在行的上面。</li>
	<li>对于所有 <code>0</code>&nbsp;到 <code>m - 1</code>&nbsp;之间的下标&nbsp;<code>i</code>&nbsp;，数字&nbsp;<code>left<sub>i</sub></code>&nbsp;所在的 <b>列</b>&nbsp;必须在数字&nbsp;<code>right<sub>i</sub></code>&nbsp;所在列的左边。</li>
</ul>

<p>返回满足上述要求的 <strong>任意</strong>&nbsp;矩阵。如果不存在答案，返回一个空的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2392.Build%20a%20Matrix%20With%20Conditions/images/gridosdrawio.png" style="width: 211px; height: 211px;"></p>

<pre><b>输入：</b>k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
<b>输出：</b>[[3,0,0],[0,0,1],[0,2,0]]
<b>解释：</b>上图为一个符合所有条件的矩阵。
行要求如下：
- 数字 1 在第 <strong>1</strong> 行，数字 2 在第 <strong>2</strong>&nbsp;行，1 在 2 的上面。
- 数字 3 在第 <strong>0</strong>&nbsp;行，数字 2 在第 <strong>2</strong>&nbsp;行，3 在 2 的上面。
列要求如下：
- 数字 2 在第 <strong>1</strong>&nbsp;列，数字 1 在第 <strong>2</strong>&nbsp;列，2 在 1 的左边。
- 数字 3 在第 <strong>0</strong>&nbsp;列，数字 2 在第 <strong>1</strong>&nbsp;列，3 在 2 的左边。
注意，可能有多种正确的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
<b>输出：</b>[]
<b>解释：</b>由前两个条件可以得到 3 在 1 的下面，但第三个条件是 3 在 1 的上面。
没有符合条件的矩阵存在，所以我们返回空矩阵。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 400</code></li>
	<li><code>1 &lt;= rowConditions.length, colConditions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>rowConditions[i].length == colConditions[i].length == 2</code></li>
	<li><code>1 &lt;= above<sub>i</sub>, below<sub>i</sub>, left<sub>i</sub>, right<sub>i</sub> &lt;= k</code></li>
	<li><code>above<sub>i</sub> != below<sub>i</sub></code></li>
	<li><code>left<sub>i</sub> != right<sub>i</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序**

利用拓扑排序，找到一个合法的 `row` 序列和 `col` 序列，然后根据这两个序列构造出矩阵。

时间复杂度 $O(m+n+k)$。其中 $m$ 和 $n$ 分别为 `rowConditions` 和 `colConditions` 的长度，而 $k$ 为题目中给定的正整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def buildMatrix(self, k: int, rowConditions: List[List[int]], colConditions: List[List[int]]) -> List[List[int]]:
        def f(cond):
            g = defaultdict(list)
            indeg = [0] * (k + 1)
            for a, b in cond:
                g[a].append(b)
                indeg[b] += 1
            q = deque([i for i, v in enumerate(indeg[1:], 1) if v == 0])
            res = []
            while q:
                for _ in range(len(q)):
                    i = q.popleft()
                    res.append(i)
                    for j in g[i]:
                        indeg[j] -= 1
                        if indeg[j] == 0:
                            q.append(j)
            return None if len(res) != k else res

        row = f(rowConditions)
        col = f(colConditions)
        if row is None or col is None:
            return []
        ans = [[0] * k for _ in range(k)]
        m = [0] * (k + 1)
        for i, v in enumerate(col):
            m[v] = i
        for i, v in enumerate(row):
            ans[i][m[v]] = v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int k;

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        this.k = k;
        List<Integer> row = f(rowConditions);
        List<Integer> col = f(colConditions);
        if (row == null || col == null) {
            return new int[0][0];
        }
        int[][] ans = new int[k][k];
        int[] m = new int[k + 1];
        for (int i = 0; i < k; ++i) {
            m[col.get(i)] = i;
        }
        for (int i = 0; i < k; ++i) {
            ans[i][m[row.get(i)]] = row.get(i);
        }
        return ans;
    }

    private List<Integer> f(int[][] cond) {
        List<Integer>[] g = new List[k + 1];
        Arrays.setAll(g, key -> new ArrayList<>());
        int[] indeg = new int[k + 1];
        for (var e : cond) {
            int a = e[0], b = e[1];
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < indeg.length; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                int i = q.pollFirst();
                res.add(i);
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }
        return res.size() == k ? res : null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int k;

    vector<vector<int>> buildMatrix(int k, vector<vector<int>>& rowConditions, vector<vector<int>>& colConditions) {
        this->k = k;
        auto row = f(rowConditions);
        auto col = f(colConditions);
        if (row.empty() || col.empty()) return {};
        vector<vector<int>> ans(k, vector<int>(k));
        vector<int> m(k + 1);
        for (int i = 0; i < k; ++i) {
            m[col[i]] = i;
        }
        for (int i = 0; i < k; ++i) {
            ans[i][m[row[i]]] = row[i];
        }
        return ans;
    }

    vector<int> f(vector<vector<int>>& cond) {
        vector<vector<int>> g(k + 1);
        vector<int> indeg(k + 1);
        for (auto& e : cond) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        for (int i = 1; i < k + 1; ++i) {
            if (!indeg[i]) {
                q.push(i);
            }
        }
        vector<int> res;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                int i = q.front();
                res.push_back(i);
                q.pop();
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.push(j);
                    }
                }
            }
        }
        return res.size() == k ? res : vector<int>();
    }
};
```

### **Go**

```go
func buildMatrix(k int, rowConditions [][]int, colConditions [][]int) [][]int {
	f := func(cond [][]int) []int {
		g := make([][]int, k+1)
		indeg := make([]int, k+1)
		for _, e := range cond {
			a, b := e[0], e[1]
			g[a] = append(g[a], b)
			indeg[b]++
		}
		q := []int{}
		for i, v := range indeg[1:] {
			if v == 0 {
				q = append(q, i+1)
			}
		}
		res := []int{}
		for len(q) > 0 {
			for n := len(q); n > 0; n-- {
				i := q[0]
				q = q[1:]
				res = append(res, i)
				for _, j := range g[i] {
					indeg[j]--
					if indeg[j] == 0 {
						q = append(q, j)
					}
				}
			}
		}
		if len(res) == k {
			return res
		}
		return []int{}
	}

	row := f(rowConditions)
	col := f(colConditions)
	if len(row) == 0 || len(col) == 0 {
		return [][]int{}
	}
	m := make([]int, k+1)
	for i, v := range col {
		m[v] = i
	}
	ans := make([][]int, k)
	for i := range ans {
		ans[i] = make([]int, k)
	}
	for i, v := range row {
		ans[i][m[v]] = v
	}
	return ans
}
```

### **TypeScript**

```ts
function buildMatrix(
    k: number,
    rowConditions: number[][],
    colConditions: number[][],
): number[][] {
    function f(cond) {
        const g = Array.from({ length: k + 1 }, () => []);
        const indeg = new Array(k + 1).fill(0);
        for (const [a, b] of cond) {
            g[a].push(b);
            ++indeg[b];
        }
        const q = [];
        for (let i = 1; i < indeg.length; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        const res = [];
        while (q.length) {
            for (let n = q.length; n; --n) {
                const i = q.shift();
                res.push(i);
                for (const j of g[i]) {
                    if (--indeg[j] == 0) {
                        q.push(j);
                    }
                }
            }
        }
        return res.length == k ? res : [];
    }

    const row = f(rowConditions);
    const col = f(colConditions);
    if (!row.length || !col.length) return [];
    const ans = Array.from({ length: k }, () => new Array(k).fill(0));
    const m = new Array(k + 1).fill(0);
    for (let i = 0; i < k; ++i) {
        m[col[i]] = i;
    }
    for (let i = 0; i < k; ++i) {
        ans[i][m[row[i]]] = row[i];
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
