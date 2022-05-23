# [314. Binary Tree Vertical Order Traversal](https://leetcode.com/problems/binary-tree-vertical-order-traversal)

[中文文档](/solution/0300-0399/0314.Binary%20Tree%20Vertical%20Order%20Traversal/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em><strong>the vertical order traversal</strong> of its nodes&#39; values</em>. (i.e., from top to bottom, column by column).</p>

<p>If two nodes are in the same row and column, the order should be from <strong>left to right</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0314.Binary%20Tree%20Vertical%20Order%20Traversal/images/vtree1.jpg" style="width: 282px; height: 301px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [[9],[3,15],[20],[7]]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0314.Binary%20Tree%20Vertical%20Order%20Traversal/images/vtree2-1.jpg" style="width: 462px; height: 222px;" />
<pre>
<strong>Input:</strong> root = [3,9,8,4,0,1,7]
<strong>Output:</strong> [[4],[9],[3,0,1],[8],[7]]
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0314.Binary%20Tree%20Vertical%20Order%20Traversal/images/vtree2.jpg" style="width: 462px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,8,4,0,1,7,null,null,null,2,5]
<strong>Output:</strong> [[4],[9,5],[3,0,1],[8,2],[7]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
    def verticalOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = deque([(root, 0)])
        offset_vals = defaultdict(list)
        while q:
            node, offset = q.popleft()
            offset_vals[offset].append(node.val)
            if node.left:
                q.append((node.left, offset - 1))
            if node.right:
                q.append((node.right, offset + 1))
        res = []
        for _, vals in sorted(offset_vals.items(), key=lambda x: x[0]):
            res.append(vals)
        return res
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> offsetVals = new TreeMap<>();
        Map<TreeNode, Integer> nodeOffsets = new HashMap<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        nodeOffsets.put(root, 0);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int offset = nodeOffsets.get(node);
            offsetVals.computeIfAbsent(offset, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                q.offer(node.left);
                nodeOffsets.put(node.left, offset - 1);
            }
            if (node.right != null) {
                q.offer(node.right);
                nodeOffsets.put(node.right, offset + 1);
            }
        }
        return new ArrayList<>(offsetVals.values());
    }
}
```

### **...**

```

```

<!-- tabs:end -->
