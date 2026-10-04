---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉搜索树
    - 二叉树
---

<!-- problem:start -->

# [3831. 二叉搜索树某一层的中位数 🔒](https://leetcode.cn/problems/median-of-a-binary-search-tree-level)

[English Version](/solution/3800-3899/3831.Median%20of%20a%20Binary%20Search%20Tree%20Level/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵 <strong>二叉搜索树（BST）</strong>的根结点&nbsp;<code>root</code>&nbsp;和一个整数&nbsp;<code>level</code>。</p>

<p>根节点位于第 0 层。每一层代表与根节点的距离。</p>

<p>返回给定&nbsp;<code>level</code>&nbsp;中所有节点值的中位数。如果该层不存在或没有节点，则返回 -1。</p>

<p><strong>中位数</strong> 定义为将该层的值按 <strong>非降序</strong> 排序后中间的元素。如果该层的值的数量为偶数，则返回 <b>向上</b>&nbsp;中位数（排序后两个中间元素中较大的那个）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3831.Median%20of%20a%20Binary%20Search%20Tree%20Level/images/screenshot-2026-01-27-at-20801pm.png" style="width: 180px; height: 182px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [4,null,5,null,7], level = 2</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><b>解释：</b></p>

<p>位于&nbsp;<code>level = 2</code>&nbsp;的节点是&nbsp;<code>[7]</code>。中位数是 7。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3831.Median%20of%20a%20Binary%20Search%20Tree%20Level/images/screenshot-2026-01-27-at-20926pm.png" style="width: 200px; height: 169px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [6,3,8], level = 1</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<p>位于&nbsp;<code>level = 1</code>&nbsp;的节点是&nbsp;<code>[3, 8]</code>。有两个可能的中位数，因此较大的那个 8 是答案。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><strong class="example">​​​​​​​</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3831.Median%20of%20a%20Binary%20Search%20Tree%20Level/images/screenshot-2026-01-27-at-21001pm.png" style="width: 150px; height: 193px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [2,1], level = 2</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<p>在&nbsp;<code>level = 2</code>​​​​​​​ 没有节点，所以答案是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量在 <code>[1, 2 * 10<sup>5</sup>]</code>&nbsp;范围内。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= level &lt;= 2 * 10<sup>​​​​​​​5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 求 BST 指定层节点值的中位数；偶数个取较大的中间元。层上节点可达 $O(n)$， $n \le 2 \times 10^5$。
>
> 若先收集再排序，BST 的中序遍历已按值有序，指定层在中序中的出现次序即该层有序序列。
>
> 中序 DFS，仅当深度等于 $\textit{level}$ 时写入列表，得到的已是非降序。
>
> 中位数为 $\textit{nums}[\lfloor |\textit{nums}|/2 \rfloor]$；该层为空则返回 $-1$。

<!-- thinking:end -->

我们注意到，题目要求我们找到二叉搜索树中某一层的节点值的中位数。由于中位数的定义是将节点值排序后取中间的值，而二叉搜索树的中序遍历本身就是有序的，因此我们可以通过中序遍历来收集指定层级的节点值。

我们定义一个辅助函数 $\text{dfs}(root, i)$，其中 $root$ 是当前节点，而 $i$ 是当前节点的层级。在函数中，如果当前节点为空，则直接返回。否则，我们递归地遍历左子树，检查当前节点的层级是否等于目标层级，如果是，则将当前节点的值加入结果列表中，最后递归地遍历右子树。

我们初始化一个空列表 $\text{nums}$ 来存储指定层级的节点值，并调用 $\text{dfs}(root, 0)$ 来开始遍历。最后，我们检查 $\text{nums}$ 是否为空，如果为空则返回 -1，否则返回 $\text{nums}$ 中间位置的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是树中节点的数量。

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
    def levelMedian(self, root: Optional[TreeNode], level: int) -> int:
        def dfs(root: Optional[TreeNode], i: int):
            if root is None:
                return
            dfs(root.left, i + 1)
            if i == level:
                nums.append(root.val)
            dfs(root.right, i + 1)

        nums = []
        dfs(root, 0)
        return nums[len(nums) // 2] if nums else -1
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
    private List<Integer> nums = new ArrayList<>();
    private int level;

    public int levelMedian(TreeNode root, int level) {
        this.level = level;
        dfs(root, 0);
        return nums.isEmpty() ? -1 : nums.get(nums.size() / 2);
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        dfs(root.left, i + 1);
        if (i == level) {
            nums.add(root.val);
        }
        dfs(root.right, i + 1);
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
    int levelMedian(TreeNode* root, int level) {
        vector<int> nums;

        auto dfs = [&](this auto&& dfs, TreeNode* node, int i) -> void {
            if (!node) {
                return;
            }
            dfs(node->left, i + 1);
            if (i == level) {
                nums.push_back(node->val);
            }
            dfs(node->right, i + 1);
        };

        dfs(root, 0);
        return nums.empty() ? -1 : nums[nums.size() / 2];
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
func levelMedian(root *TreeNode, level int) int {
	nums := make([]int, 0)

	var dfs func(*TreeNode, int)
	dfs = func(node *TreeNode, i int) {
		if node == nil {
			return
		}
		dfs(node.Left, i+1)
		if i == level {
			nums = append(nums, node.Val)
		}
		dfs(node.Right, i+1)
	}

	dfs(root, 0)
	if len(nums) == 0 {
		return -1
	}
	return nums[len(nums)/2]
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
function levelMedian(root: TreeNode | null, level: number): number {
    const nums: number[] = [];

    const dfs = (node: TreeNode | null, i: number): void => {
        if (node === null) {
            return;
        }
        dfs(node.left, i + 1);
        if (i === level) {
            nums.push(node.val);
        }
        dfs(node.right, i + 1);
    };

    dfs(root, 0);
    if (nums.length === 0) {
        return -1;
    }
    return nums[nums.length >> 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈中序遍历

<!-- thinking:start -->

> **思考**
>
> 求二叉搜索树指定层节点值的中位数，偶数个取较大的中间元。先把该层节点收集出来再排序，在 $n \le 2 \times 10^5$ 时是对的。
>
> 中序遍历会先走进左孩子。一条长度为 $2 \times 10^5$ 的链按结点个数递归，调用栈会溢出。
>
> 二叉搜索树的中序次序就是结点值的非降序，指定层在这次遍历中的出现次序已经排好，不必再排序。每个结点只需要自己的深度，不需要等子树返回。
>
> 因此用显式栈做中序遍历。进入结点时先压入退出标记和左孩子；退出时，若深度等于 $\textit{level}$ 就把值写入列表，再压入右孩子。中位数是 $\textit{nums}[\lfloor |\textit{nums}|/2 \rfloor]$，该层为空则返回 $-1$。

<!-- thinking:end -->

题目要求二叉搜索树中某一层结点值的中位数。中位数是排序后的中间值，而二叉搜索树的中序遍历本身有序，所以按中序收集指定层的结点值即可。偶数个结点时，下标 $\lfloor |\textit{nums}|/2 \rfloor$ 恰好是较大的那个中间元。

我们用显式栈完成这次中序遍历。栈里保存当前结点、它的深度，以及是否已经展开过左子树。进入结点时压入退出标记和左孩子；退出时，若深度等于 $\textit{level}$，就把结点值加入 $\textit{nums}$，然后压入右孩子。

$\textit{nums}$ 为空时返回 $-1$，否则返回中间位置的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是树中节点的数量。

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
    def levelMedian(self, root: Optional[TreeNode], level: int) -> int:
        nums = []
        stk = [(root, 0, 0)]
        while stk:
            node, i, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, i, 1))
                stk.append((node.left, i + 1, 0))
                continue
            if i == level:
                nums.append(node.val)
            stk.append((node.right, i + 1, 0))
        return nums[len(nums) // 2] if nums else -1
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
    private static class Frame {
        TreeNode node;
        int i;
        int state;

        Frame(TreeNode node, int i, int state) {
            this.node = node;
            this.i = i;
            this.state = state;
        }
    }

    public int levelMedian(TreeNode root, int level) {
        List<Integer> nums = new ArrayList<>();
        if (root == null) {
            return -1;
        }
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, cur.i, 1));
                if (node.left != null) {
                    stk.push(new Frame(node.left, cur.i + 1, 0));
                }
                continue;
            }
            if (cur.i == level) {
                nums.add(node.val);
            }
            if (node.right != null) {
                stk.push(new Frame(node.right, cur.i + 1, 0));
            }
        }
        return nums.isEmpty() ? -1 : nums.get(nums.size() / 2);
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
    int levelMedian(TreeNode* root, int level) {
        vector<int> nums;
        vector<tuple<TreeNode*, int, int>> stk{{root, 0, 0}};
        while (!stk.empty()) {
            auto [node, i, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, i, 1);
                stk.emplace_back(node->left, i + 1, 0);
                continue;
            }
            if (i == level) {
                nums.push_back(node->val);
            }
            stk.emplace_back(node->right, i + 1, 0);
        }
        return nums.empty() ? -1 : nums[nums.size() / 2];
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
func levelMedian(root *TreeNode, level int) int {
	nums := make([]int, 0)
	type frame struct {
		node  *TreeNode
		i     int
		state int
	}
	stk := []frame{{root, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, i, state := cur.node, cur.i, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, i, 1}, frame{node.Left, i + 1, 0})
			continue
		}
		if i == level {
			nums = append(nums, node.Val)
		}
		stk = append(stk, frame{node.Right, i + 1, 0})
	}
	if len(nums) == 0 {
		return -1
	}
	return nums[len(nums)/2]
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
function levelMedian(root: TreeNode | null, level: number): number {
    const nums: number[] = [];
    const stk: [TreeNode | null, number, number][] = [[root, 0, 0]];
    while (stk.length) {
        const [node, i, state] = stk.pop()!;
        if (node === null) {
            continue;
        }
        if (state === 0) {
            stk.push([node, i, 1]);
            stk.push([node.left, i + 1, 0]);
            continue;
        }
        if (i === level) {
            nums.push(node.val);
        }
        stk.push([node.right, i + 1, 0]);
    }
    if (nums.length === 0) {
        return -1;
    }
    return nums[nums.length >> 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
