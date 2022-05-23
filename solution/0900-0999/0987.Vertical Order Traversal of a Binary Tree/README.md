# [987. 二叉树的垂序遍历](https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree)

[English Version](/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你二叉树的根结点 <code>root</code> ，请你设计算法计算二叉树的<em> </em><strong>垂序遍历</strong> 序列。</p>

<p>对位于 <code>(row, col)</code> 的每个结点而言，其左右子结点分别位于 <code>(row + 1, col - 1)</code> 和 <code>(row + 1, col + 1)</code> 。树的根结点位于 <code>(0, 0)</code> 。</p>

<p>二叉树的 <strong>垂序遍历</strong> 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。</p>

<p>返回二叉树的 <strong>垂序遍历</strong> 序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree1.jpg" style="width: 431px; height: 304px;" />
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>[[9],[3,15],[20],[7]]
<strong>解释：</strong>
列 -1 ：只有结点 9 在此列中。
列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
列  1 ：只有结点 20 在此列中。
列  2 ：只有结点 7 在此列中。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree2.jpg" style="width: 512px; height: 304px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6,7]
<strong>输出：</strong>[[4],[2],[1,5,6],[3],[7]]
<strong>解释：</strong>
列 -2 ：只有结点 4 在此列中。
列 -1 ：只有结点 2 在此列中。
列  0 ：结点 1 、5 和 6 都在此列中。
          1 在上面，所以它出现在前面。
          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
列  1 ：只有结点 3 在此列中。
列  2 ：只有结点 7 在此列中。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/images/vtree3.jpg" style="width: 512px; height: 304px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,6,5,7]
<strong>输出：</strong>[[4],[2],[1,5,6],[3],[7]]
<strong>解释：</strong>
这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中结点数目总数在范围 <code>[1, 1000]</code> 内</li>
	<li><code>0 <= Node.val <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        list.add(new int[]{x, y, root.val});
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
    // 优先依据i=2排序， 然后依据i=1排序
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
