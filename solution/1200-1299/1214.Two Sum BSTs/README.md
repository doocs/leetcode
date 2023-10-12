# [1214. 查找两棵二叉搜索树之和](https://leetcode.cn/problems/two-sum-bsts)

[English Version](/solution/1200-1299/1214.Two%20Sum%20BSTs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出两棵二叉搜索树的根节点&nbsp;<meta charset="UTF-8" /><code>root1</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>root2</code>&nbsp;，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值&nbsp;<code>Target</code>。</p>

<p>如果可以找到返回&nbsp;<code>True</code>，否则返回&nbsp;<code>False</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex1.png" style="height: 169px; width: 369px;" /></p>

<pre>
<strong>输入：</strong>root1 = [2,1,4], root2 = [1,0,3], target = 5
<strong>输出：</strong>true
<strong>解释：</strong>2 加 3 和为 5 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex2.png" style="height: 290px; width: 453px;" /></p>

<pre>
<strong>输入：</strong>root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树上节点数在<meta charset="UTF-8" />&nbsp;<code>[1, 5000]</code>&nbsp;范围内。<meta charset="UTF-8" /></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= Node.val, target &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：中序遍历 + 双指针**

我们分别对两棵树进行中序遍历，得到两个有序数组 $nums[0]$ 和 $nums[1]$，然后使用双指针的方法判断是否存在两个数的和为目标值。双指针判断方法如下：

初始化两个指针 $i$ 和 $j$，分别指向数组 $nums[0]$ 的左边界和数组 $nums[1]$ 的右边界；

每次比较 $x = nums[0][i] + nums[1][j]$ 与目标值的大小。如果 $x = target$，则返回 `true`；否则，如果 $x \lt target$，则 $i$ 右移一位；否则，如果 $x \gt target$，则 $j$ 左移一位。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为两棵树的节点数。

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
    def twoSumBSTs(
        self, root1: Optional[TreeNode], root2: Optional[TreeNode], target: int
    ) -> bool:
        def dfs(root: Optional[TreeNode], i: int):
            if root is None:
                return
            dfs(root.left, i)
            nums[i].append(root.val)
            dfs(root.right, i)

        nums = [[], []]
        dfs(root1, 0)
        dfs(root2, 1)
        i, j = 0, len(nums[1]) - 1
        while i < len(nums[0]) and ~j:
            x = nums[0][i] + nums[1][j]
            if x == target:
                return True
            if x < target:
                i += 1
            else:
                j -= 1
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    private List<Integer>[] nums = new List[2];

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Arrays.setAll(nums, k -> new ArrayList<>());
        dfs(root1, 0);
        dfs(root2, 1);
        int i = 0, j = nums[1].size() - 1;
        while (i < nums[0].size() && j >= 0) {
            int x = nums[0].get(i) + nums[1].get(j);
            if (x == target) {
                return true;
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        dfs(root.left, i);
        nums[i].add(root.val);
        dfs(root.right, i);
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
    bool twoSumBSTs(TreeNode* root1, TreeNode* root2, int target) {
        vector<int> nums[2];
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int i) {
            if (!root) {
                return;
            }
            dfs(root->left, i);
            nums[i].push_back(root->val);
            dfs(root->right, i);
        };
        dfs(root1, 0);
        dfs(root2, 1);
        int i = 0, j = nums[1].size() - 1;
        while (i < nums[0].size() && j >= 0) {
            int x = nums[0][i] + nums[1][j];
            if (x == target) {
                return true;
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
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
func twoSumBSTs(root1 *TreeNode, root2 *TreeNode, target int) bool {
	nums := [2][]int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, i int) {
		if root == nil {
			return
		}
		dfs(root.Left, i)
		nums[i] = append(nums[i], root.Val)
		dfs(root.Right, i)
	}
	dfs(root1, 0)
	dfs(root2, 1)
	i, j := 0, len(nums[1])-1
	for i < len(nums[0]) && j >= 0 {
		x := nums[0][i] + nums[1][j]
		if x == target {
			return true
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
	return false
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

function twoSumBSTs(root1: TreeNode | null, root2: TreeNode | null, target: number): boolean {
    const nums: number[][] = Array(2)
        .fill(0)
        .map(() => []);
    const dfs = (root: TreeNode | null, i: number) => {
        if (!root) {
            return;
        }
        dfs(root.left, i);
        nums[i].push(root.val);
        dfs(root.right, i);
    };
    dfs(root1, 0);
    dfs(root2, 1);
    let i = 0;
    let j = nums[1].length - 1;
    while (i < nums[0].length && j >= 0) {
        const x = nums[0][i] + nums[1][j];
        if (x === target) {
            return true;
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
