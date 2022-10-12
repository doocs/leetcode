# [987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree)

[中文文档](/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/README.md)

## Description

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
    def verticalTraversal(self, root: TreeNode) -> List[List[int]]:
        def dfs(root, i, j):
            if root is None:
                return
            nodes.append((i, j, root.val))
            dfs(root.left, i + 1, j - 1)
            dfs(root.right, i + 1, j + 1)

        nodes = []
        dfs(root, 0, 0)
        nodes.sort(key=lambda x: (x[1], x[0], x[2]))
        ans = []
        prev = -2000
        for i, j, v in nodes:
            if prev != j:
                ans.append([])
                prev = j
            ans[-1].append(v)
        return ans
```

### **Java**

```java
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> list = new ArrayList<>();
        dfs(root, 0, 0, list);
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                if (o1[1] != o2[1]) return Integer.compare(o2[1], o1[1]);
                return Integer.compare(o1[2], o2[2]);
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        int preX = 1;
        for (int[] cur : list) {
            if (preX != cur[0]) {
                res.add(new ArrayList<>());
                preX = cur[0];
            }
            res.get(res.size() - 1).add(cur[2]);
        }
        return res;
    }

    private void dfs(TreeNode root, int x, int y, List<int[]> list) {
        if (root == null) {
            return;
        }
        list.add(new int[] {x, y, root.val});
        dfs(root.left, x - 1, y - 1, list);
        dfs(root.right, x + 1, y - 1, list);
    }
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

function verticalTraversal(root: TreeNode | null): number[][] {
    let solution = [];
    dfs(root, 0, 0, solution);
    solution.sort(compare);
    let ans = [];
    let pre = Number.MIN_SAFE_INTEGER;
    for (let node of solution) {
        const [val, , idx] = node;
        if (idx != pre) {
            ans.push([]);
            pre = idx;
        }
        ans[ans.length - 1].push(val);
    }
    return ans;
}

function compare(a: Array<number>, b: Array<number>) {
    const [a0, a1, a2] = a,
        [b0, b1, b2] = b;
    if (a2 == b2) {
        if (a1 == b1) {
            return a0 - b0;
        }
        return a1 - b1;
    }
    return a2 - b2;
}

function dfs(
    root: TreeNode | null,
    depth: number,
    idx: number,
    solution: Array<Array<number>>,
) {
    if (!root) return;
    solution.push([root.val, depth, idx]);
    dfs(root.left, depth + 1, idx - 1, solution);
    dfs(root.right, depth + 1, idx + 1, solution);
}
```

### **...**

```

```

<!-- tabs:end -->
