# [2385. Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected)

[中文文档](/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/README.md)

## Description

<p>You are given the <code>root</code> of a binary tree with <strong>unique</strong> values, and an integer <code>start</code>. At minute <code>0</code>, an <strong>infection</strong> starts from the node with value <code>start</code>.</p>

<p>Each minute, a node becomes infected if:</p>

<ul>
	<li>The node is currently uninfected.</li>
	<li>The node is adjacent to an infected node.</li>
</ul>

<p>Return <em>the number of minutes needed for the entire tree to be infected.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231744-1.png" style="width: 400px; height: 306px;" />
<pre>
<strong>Input:</strong> root = [1,5,3,null,4,10,6,9,2], start = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231812-2.png" style="width: 75px; height: 66px;" />
<pre>
<strong>Input:</strong> root = [1], start = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> At minute 0, the only node in the tree is infected so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>Each node has a <strong>unique</strong> value.</li>
	<li>A node with a value of <code>start</code> exists in the tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        def dfs(root):
            if root is None:
                return
            if root.left:
                g[root.val].append(root.left.val)
                g[root.left.val].append(root.val)
            if root.right:
                g[root.val].append(root.right.val)
                g[root.right.val].append(root.val)
            dfs(root.left)
            dfs(root.right)

        g = defaultdict(list)
        dfs(root)
        vis = set()
        q = deque([start])
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i = q.popleft()
                vis.add(i)
                for j in g[i]:
                    if j not in vis:
                        q.append(j)
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        def dfs(root):
            if root is None:
                return
            if root.left:
                g[root.val].append(root.left.val)
                g[root.left.val].append(root.val)
            if root.right:
                g[root.val].append(root.right.val)
                g[root.right.val].append(root.val)
            dfs(root.left)
            dfs(root.right)

        def dfs2(i, fa):
            ans = 0
            for j in g[i]:
                if j != fa:
                    ans = max(ans, 1 + dfs2(j, i))
            return ans

        g = defaultdict(list)
        dfs(root)
        return dfs2(start, -1)
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        q.offer(start);
        int ans = -1;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                int i = q.pollFirst();
                vis.add(i);
                if (g.containsKey(i)) {
                    for (int j : g.get(i)) {
                        if (!vis.contains(j)) {
                            q.offer(j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.left.val);
            g.computeIfAbsent(root.left.val, k -> new ArrayList<>()).add(root.val);
        }
        if (root.right != null) {
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.right.val);
            g.computeIfAbsent(root.right.val, k -> new ArrayList<>()).add(root.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        return dfs(start, -1);
    }

    private int dfs(int i, int fa) {
        int ans = 0;
        for (int j : g.getOrDefault(i, Collections.emptyList())) {
            if (j != fa) {
                ans = Math.max(ans, 1 + dfs(j, i));
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            g.computeIfAbsent(root.left.val, k -> new ArrayList<>()).add(root.val);
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.left.val);
        }
        if (root.right != null) {
            g.computeIfAbsent(root.right.val, k -> new ArrayList<>()).add(root.val);
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.right.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    unordered_map<int, vector<int>> g;

    int amountOfTime(TreeNode* root, int start) {
        dfs(root);
        queue<int> q {{start}};
        unordered_set<int> vis;
        int ans = -1;
        while (q.size()) {
            ++ans;
            for (int n = q.size(); n; --n) {
                int i = q.front();
                q.pop();
                vis.insert(i);
                for (int j : g[i]) {
                    if (!vis.count(j)) {
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            g[root->val].push_back(root->left->val);
            g[root->left->val].push_back(root->val);
        }
        if (root->right) {
            g[root->val].push_back(root->right->val);
            g[root->right->val].push_back(root->val);
        }
        dfs(root->left);
        dfs(root->right);
    }
};
```

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    unordered_map<int, vector<int>> g;

    int amountOfTime(TreeNode* root, int start) {
        dfs(root);
        return dfs(start, -1);
    }

    int dfs(int i, int fa) {
        int ans = 0;
        for (int& j : g[i]) {
            if (j != fa) {
                ans = max(ans, 1 + dfs(j, i));
            }
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            g[root->val].push_back(root->left->val);
            g[root->left->val].push_back(root->val);
        }
        if (root->right) {
            g[root->val].push_back(root->right->val);
            g[root->right->val].push_back(root->val);
        }
        dfs(root->left);
        dfs(root->right);
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			g[root.Val] = append(g[root.Val], root.Left.Val)
			g[root.Left.Val] = append(g[root.Left.Val], root.Val)
		}
		if root.Right != nil {
			g[root.Val] = append(g[root.Val], root.Right.Val)
			g[root.Right.Val] = append(g[root.Right.Val], root.Val)
		}
		dfs(root.Left)
		dfs(root.Right)
	}

	dfs(root)
	q := []int{start}
	ans := -1
	vis := map[int]bool{}
	for len(q) > 0 {
		ans++
		for n := len(q); n > 0; n-- {
			i := q[0]
			q = q[1:]
			vis[i] = true
			for _, j := range g[i] {
				if !vis[j] {
					q = append(q, j)
				}
			}
		}
	}
	return ans
}
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			g[root.Val] = append(g[root.Val], root.Left.Val)
			g[root.Left.Val] = append(g[root.Left.Val], root.Val)
		}
		if root.Right != nil {
			g[root.Val] = append(g[root.Val], root.Right.Val)
			g[root.Right.Val] = append(g[root.Right.Val], root.Val)
		}
		dfs(root.Left)
		dfs(root.Right)
	}

	var dfs2 func(int, int) int
	dfs2 = func(i, fa int) int {
		ans := 0
		for _, j := range g[i] {
			if j != fa {
				ans = max(ans, 1+dfs2(j, i))
			}
		}
		return ans
	}

	dfs(root)
	return dfs2(start, -1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function amountOfTime(root: TreeNode | null, start: number): number {
    const map = new Map<number, number[]>();
    const create = ({ val, left, right }: TreeNode) => {
        if (left != null) {
            map.set(val, [...(map.get(val) ?? []), left.val]);
            map.set(left.val, [...(map.get(left.val) ?? []), val]);
            create(left);
        }
        if (right != null) {
            map.set(val, [...(map.get(val) ?? []), right.val]);
            map.set(right.val, [...(map.get(right.val) ?? []), val]);
            create(right);
        }
    };
    create(root);
    const dfs = (st: number, fa: number) => {
        let res = 0;
        for (const v of map.get(st) ?? []) {
            if (v !== fa) {
                res = Math.max(res, dfs(v, st) + 1);
            }
        }
        return res;
    };
    return dfs(start, -1);
}
```

### **...**

```


```

<!-- tabs:end -->
