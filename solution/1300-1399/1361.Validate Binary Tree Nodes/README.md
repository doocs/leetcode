---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/README.md
rating: 1464
source: 第 177 场周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
    - 二叉树
---

<!-- problem:start -->

# [1361. 验证二叉树](https://leetcode.cn/problems/validate-binary-tree-nodes)

[English Version](/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>二叉树上有 <code>n</code>&nbsp;个节点，按从&nbsp;<code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;编号，其中节点&nbsp;<code>i</code>&nbsp;的两个子节点分别是&nbsp;<code>leftChild[i]</code>&nbsp;和&nbsp;<code>rightChild[i]</code>。</p>

<p>只有 <strong>所有</strong> 节点能够形成且 <strong>只</strong> 形成 <strong>一颗</strong>&nbsp;有效的二叉树时，返回&nbsp;<code>true</code>；否则返回 <code>false</code>。</p>

<p>如果节点&nbsp;<code>i</code>&nbsp;没有左子节点，那么&nbsp;<code>leftChild[i]</code>&nbsp;就等于&nbsp;<code>-1</code>。右子节点也符合该规则。</p>

<p>注意：节点没有值，本问题中仅仅使用节点编号。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex1.png" style="height: 287px; width: 195px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex2.png" style="height: 272px; width: 183px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex3.png" style="height: 174px; width: 82px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 2, leftChild = [1,0], rightChild = [-1,-1]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == leftChild.length == rightChild.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-1 &lt;= leftChild[i], rightChild[i] &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集

我们可以遍历每个节点 $i$ 以及对应的左右孩子 $l$, $r$，用 $vis$ 数组记录节点是否有父节点：

- 若孩子节点已存在父节点，说明有多个父亲，不满足条件，直接返回 `false`。
- 若孩子节点与父节点已经处于同一个连通分量，说明会形成环，不满足条件，直接返回 `false`。
- 否则，进行合并，并且将 $vis$ 数组对应位置置为 `true`，同时将连通分量个数减去 $1$。

遍历结束，判断并查集中连通分量个数是否为 $1$，若是返回 `true`，否则返回 `false`。

时间复杂度 $O(n \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 为节点个数，而 $\alpha(n)$ 为阿克曼函数的反函数，即反阿克曼函数，其值小于 $5$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        vis = [False] * n
        for i, (a, b) in enumerate(zip(leftChild, rightChild)):
            for j in (a, b):
                if j != -1:
                    if vis[j] or find(i) == find(j):
                        return False
                    p[find(i)] = find(j)
                    vis[j] = True
                    n -= 1
        return n == 1
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        boolean[] vis = new boolean[n];
        for (int i = 0, m = n; i < m; ++i) {
            for (int j : new int[] {leftChild[i], rightChild[i]}) {
                if (j != -1) {
                    if (vis[j] || find(i) == find(j)) {
                        return false;
                    }
                    p[find(i)] = find(j);
                    vis[j] = true;
                    --n;
                }
            }
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        int p[n];
        iota(p, p + n, 0);
        bool vis[n];
        memset(vis, 0, sizeof(vis));
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        for (int i = 0, m = n; i < m; ++i) {
            for (int j : {leftChild[i], rightChild[i]}) {
                if (j != -1) {
                    if (vis[j] || find(i) == find(j)) {
                        return false;
                    }
                    p[find(i)] = find(j);
                    vis[j] = true;
                    --n;
                }
            }
        }
        return n == 1;
    }
};
```

#### Go

```go
func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	vis := make([]bool, n)
	for i, a := range leftChild {
		for _, j := range []int{a, rightChild[i]} {
			if j != -1 {
				if vis[j] || find(i) == find(j) {
					return false
				}
				p[find(i)] = find(j)
				vis[j] = true
				n--
			}
		}
	}
	return n == 1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：入度统计 + BFS

我们可以先统计每个节点的入度，即有多少个父节点指向它。若不存在入度为 $0$ 的节点，说明图中存在环，直接返回 `false`；否则，该节点即为根节点。

接下来，从根节点出发进行广度优先搜索。遍历过程中，若某个子节点已被访问过，说明该节点有多个父节点或图中存在环，直接返回 `false`。

遍历结束后，判断访问过的节点数是否等于 $n$，若是则说明所有节点构成且仅构成一棵有效的二叉树，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        indeg = [0] * n
        for c in chain(leftChild, rightChild):
            if c != -1:
                indeg[c] += 1
        root = next((i for i, x in enumerate(indeg) if x == 0), -1)
        if root == -1:
            return False
        q = deque([root])
        vis = {root}
        while q:
            i = q.popleft()
            for j in (leftChild[i], rightChild[i]):
                if j != -1:
                    if j in vis:
                        return False
                    vis.add(j)
                    q.append(j)
        return len(vis) == n
```

#### Java

```java
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indeg = new int[n];
        for (int c : leftChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }
        for (int c : rightChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            return false;
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.add(root);
        Set<Integer> vis = new HashSet<>();
        vis.add(root);

        while (!q.isEmpty()) {
            int i = q.poll();
            int j = leftChild[i];
            if (j != -1) {
                if (vis.contains(j)) {
                    return false;
                }
                vis.add(j);
                q.add(j);
            }

            j = rightChild[i];
            if (j != -1) {
                if (vis.contains(j)) {
                    return false;
                }
                vis.add(j);
                q.add(j);
            }
        }

        return vis.size() == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        vector<int> indeg(n, 0);
        for (int c : leftChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }
        for (int c : rightChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            return false;
        }

        queue<int> q;
        unordered_set<int> vis;

        q.push(root);
        vis.insert(root);

        while (!q.empty()) {
            int i = q.front();
            q.pop();

            int j = leftChild[i];
            if (j != -1) {
                if (vis.count(j)) {
                    return false;
                }
                vis.insert(j);
                q.push(j);
            }

            j = rightChild[i];
            if (j != -1) {
                if (vis.count(j)) {
                    return false;
                }
                vis.insert(j);
                q.push(j);
            }
        }

        return vis.size() == n;
    }
};
```

#### Go

```go
func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	indeg := make([]int, n)

	for _, c := range leftChild {
		if c != -1 {
			indeg[c]++
		}
	}
	for _, c := range rightChild {
		if c != -1 {
			indeg[c]++
		}
	}

	root := -1
	for i, x := range indeg {
		if x == 0 {
			root = i
			break
		}
	}
	if root == -1 {
		return false
	}

	q := []int{root}
	vis := map[int]bool{root: true}

	for len(q) > 0 {
		i := q[0]
		q = q[1:]

		j := leftChild[i]
		if j != -1 {
			if vis[j] {
				return false
			}
			vis[j] = true
			q = append(q, j)
		}

		j = rightChild[i]
		if j != -1 {
			if vis[j] {
				return false
			}
			vis[j] = true
			q = append(q, j)
		}
	}

	return len(vis) == n
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
