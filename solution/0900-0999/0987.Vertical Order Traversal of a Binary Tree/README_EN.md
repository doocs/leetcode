---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
    - Sorting
---

<!-- problem:start -->

# [987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree)

[中文文档](/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a binary tree, calculate the <strong>vertical order traversal</strong> of the binary tree.</p>

<p>For each node at position <code>(row, col)</code>, its left and right children will be at positions <code>(row + 1, col - 1)</code> and <code>(row + 1, col + 1)</code> respectively. The root of the tree is at <code>(0, 0)</code>.</p>

<p>The <strong>vertical order traversal</strong> of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.</p>

<p>Return <em>the <strong>vertical order traversal</strong> of the binary tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree1.jpg" style="width: 431px; height: 304px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [[9],[3,15],[20],[7]]
<strong>Explanation:</strong>
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree2.jpg" style="width: 512px; height: 304px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7]
<strong>Output:</strong> [[4],[2],[1,5,6],[3],[7]]
<strong>Explanation:</strong>
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree3.jpg" style="width: 512px; height: 304px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,6,5,7]
<strong>Output:</strong> [[4],[2],[1,5,6],[3],[7]]
<strong>Explanation:</strong>
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Sorting

We design a function $dfs(root, i, j)$, where $i$ and $j$ represent the row and column of the current node. We can record the row and column information of the nodes through depth-first search, store it in an array or list $nodes$, and then sort $nodes$ in the order of column, row, and value.

Next, we traverse $nodes$, putting the values of nodes in the same column into the same list, and finally return these lists.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        def dfs(root: Optional[TreeNode], i: int, j: int):
            if root is None:
                return
            nodes.append((j, i, root.val))
            dfs(root.left, i + 1, j - 1)
            dfs(root.right, i + 1, j + 1)

        nodes = []
        dfs(root, 0, 0)
        nodes.sort()
        ans = []
        prev = -2000
        for j, _, val in nodes:
            if prev != j:
                ans.append([])
                prev = j
            ans[-1].append(val)
        return ans
```

#### Java

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
    private List<int[]> nodes = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        Collections.sort(nodes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });
        List<List<Integer>> ans = new ArrayList<>();
        int prev = -2000;
        for (int[] node : nodes) {
            int j = node[0], val = node[2];
            if (prev != j) {
                ans.add(new ArrayList<>());
                prev = j;
            }
            ans.get(ans.size() - 1).add(val);
        }

        return ans;
    }

    private void dfs(TreeNode root, int i, int j) {
        if (root == null) {
            return;
        }
        nodes.add(new int[] {j, i, root.val});
        dfs(root.left, i + 1, j - 1);
        dfs(root.right, i + 1, j + 1);
    }
}
```

#### C++

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
    vector<vector<int>> verticalTraversal(TreeNode* root) {
        vector<tuple<int, int, int>> nodes;
        function<void(TreeNode*, int, int)> dfs = [&](TreeNode* root, int i, int j) {
            if (!root) {
                return;
            }
            nodes.emplace_back(j, i, root->val);
            dfs(root->left, i + 1, j - 1);
            dfs(root->right, i + 1, j + 1);
        };
        dfs(root, 0, 0);
        sort(nodes.begin(), nodes.end());
        vector<vector<int>> ans;
        int prev = -2000;
        for (auto [j, _, val] : nodes) {
            if (j != prev) {
                prev = j;
                ans.emplace_back();
            }
            ans.back().push_back(val);
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func verticalTraversal(root *TreeNode) (ans [][]int) {
	nodes := [][3]int{}
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, i, j int) {
		if root == nil {
			return
		}
		nodes = append(nodes, [3]int{j, i, root.Val})
		dfs(root.Left, i+1, j-1)
		dfs(root.Right, i+1, j+1)
	}
	dfs(root, 0, 0)
	sort.Slice(nodes, func(i, j int) bool {
		a, b := nodes[i], nodes[j]
		return a[0] < b[0] || a[0] == b[0] && (a[1] < b[1] || a[1] == b[1] && a[2] < b[2])
	})
	prev := -2000
	for _, node := range nodes {
		j, val := node[0], node[2]
		if j != prev {
			ans = append(ans, nil)
			prev = j
		}
		ans[len(ans)-1] = append(ans[len(ans)-1], val)
	}
	return
}
```

#### TypeScript

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

function verticalTraversal(root: TreeNode | null): number[][] {
    const nodes: [number, number, number][] = [];
    const dfs = (root: TreeNode | null, i: number, j: number) => {
        if (!root) {
            return;
        }
        nodes.push([j, i, root.val]);
        dfs(root.left, i + 1, j - 1);
        dfs(root.right, i + 1, j + 1);
    };
    dfs(root, 0, 0);
    nodes.sort((a, b) => a[0] - b[0] || a[1] - b[1] || a[2] - b[2]);
    const ans: number[][] = [];
    let prev = -2000;
    for (const [j, _, val] of nodes) {
        if (j !== prev) {
            prev = j;
            ans.push([]);
        }
        ans.at(-1)!.push(val);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2：BFS + Sorting

We perform a breadth-first search (BFS) on the tree.

Since our final answer must be returned from leftmost column to rightmost column, we maintain:
- `leftmostCol`: smallest column index currently stored.
- `rightmostCol`: largest column index currently stored.

We also use a deque `columnsValues`, where each element represents
a column and stores all node values belonging to that column.

When a newly visited node belongs to a column outside the current range:

- If its column index $<$ `leftmostCol`, we put a new column container at the front of deque.
- If its column index $>$ `rightmostCol`, we append a new column container to the back of deque.

- For any column index `col`, its corresponding position in `columnsValues` can be computed as:
- 
$$
col - `leftmostCol`
$$

This allows us to locate target column in constant time.

After BFS finishes, each column contains all node values belonging to that column.

Since BFS already visits nodes level by level, we only need to sort values within each column
in ascending order to satisfy ordering requirements of the problem.

Finally, we output all columns from left to right.

#### Complexity Analysis

Assume binary tree contains $n$ nodes.

- Time Complexity: $O(n \log n)$
  
  BFS visits every node exactly once, which takes $O(n)$ time. Sorting values is $O(n \log n)$ time in worst case.
  Therefore, overall time complexity is $O(n \log n)$.

- Space Complexity: $O(n)$

  BFS queue, deque structure, and result container may collectively store all nodes, resulting in $O(n)$ space.

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> list[list[int]]:
        # Format: (tree node, row, column).
        queue: deque[tuple[TreeNode, int, int]] = deque([(root, 0, 0)])

        # Deque append left speeds up indexing.
        # Each tuple format: (row, value).
        columns_values: deque[list[tuple[int, int]]] = deque([[]])

        leftmost_col, rightmost_col = 0, 0

        while queue:
            node, row, column = queue.popleft()

            if column < leftmost_col:
                leftmost_col = column
                columns_values.appendleft([])

            if column > rightmost_col:
                rightmost_col = column
                columns_values.append([])

            columns_values[column - leftmost_col].append((row, node.val))

            if node.left:
                queue.append((node.left, row + 1, column - 1))
            if node.right:
                queue.append((node.right, row + 1, column + 1))

        vertical_traversal: list[list[int]] = []

        for column_values in columns_values:
            vertical_traversal.append([])

            column_values.sort()
            for _, value in column_values:
                vertical_traversal[-1].append(value)

        return vertical_traversal
```

#### C++

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
    vector<vector<int>> verticalTraversal(TreeNode* root) {
        // Each tuple format: {tree node, row, column}.
        deque<tuple<TreeNode*, int, int>> queue = {{root, 0, 0}};

        // Each tuple format: {row, value}.
        deque<vector<tuple<int, int>>> columnsValues = {{}};

        int leftmostCol = 0, rightmostCol = 0;

        while (!queue.empty()) {
            auto [node, row, column] = queue.front();
            queue.pop_front();

            if (column < leftmostCol) {
                leftmostCol = column;
                columnsValues.push_front({});
            }

            if (column > rightmostCol) {
                rightmostCol = column;
                columnsValues.push_back({});
            }

            columnsValues[column - leftmostCol].push_back({row, node->val});

            if (node->left != nullptr)
                queue.push_back({node->left, row + 1, column - 1});

            if (node->right != nullptr)
                queue.push_back({node->right, row + 1, column + 1});
        }

        vector<vector<int>> verticalTraversal = {};

        for (auto columnValues : columnsValues) { // Need to sort so no const.
            verticalTraversal.push_back({});

            sort(columnValues.begin(), columnValues.end());
            for (const auto& [row, value] : columnValues)
                verticalTraversal.back().push_back(value);
        }

        return verticalTraversal;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
