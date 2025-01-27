---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2064.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%81%AF%E9%A5%B0/README.md
---

<!-- problem:start -->

# [LCP 64. 二叉树灯饰](https://leetcode.cn/problems/U7WvvU)

## 题目描述

<!-- description:start -->

<p>「力扣嘉年华」的中心广场放置了一个巨型的二叉树形状的装饰树。每个节点上均有一盏灯和三个开关。节点值为&nbsp;<code>0</code> 表示灯处于「关闭」状态，节点值为 <code>1</code>&nbsp;表示灯处于「开启」状态。每个节点上的三个开关各自功能如下：</p>

<ul>
	<li>开关 <code>1</code>：切换当前节点的灯的状态；</li>
	<li>开关 <code>2</code>：切换 <strong>以当前节点为根</strong>&nbsp;的子树中，所有节点上的灯的状态；</li>
	<li>开关 <code>3</code>：切换 <strong>当前节点及其左右子节点</strong>（若存在的话） 上的灯的状态；</li>
</ul>

<p>给定该装饰的初始状态 <code>root</code>，请返回最少需要操作多少次开关，可以关闭所有节点的灯。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [1,1,0,null,null,null,1]
<strong>输出：</strong>2
<strong>解释：</strong>以下是最佳的方案之一，如图所示
<img alt="" src="https://pic.leetcode-cn.com/1629357030-GSbzpY-b71b95bf405e3b223e00b2820a062ba4.gif" style="width: 300px; height: 225px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1,1,1,1,null,null,1]
<strong>输出：</strong>1
<strong>解释：</strong>以下是最佳的方案，如图所示
<img alt="" src="https://pic.leetcode-cn.com/1629356950-HZsKZC-a4091b6448a0089b4d9e8f0390ff9ac6.gif" style="width: 300px; height: 225px;" />
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0,null,0]
<strong>输出：</strong>0
<strong>解释：</strong>无需操作开关，当前所有节点上的灯均已关闭
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= 节点个数 &lt;= 10^5</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们注意到，三个开关只能影响当前节点及其左右子节点，因此我们可以将当前节点的状态分为四种：

-   全灭：当前节点及其左右子节点的灯均处于关闭状态；
-   全亮：当前节点及其左右子节点的灯均处于开启状态；
-   当前灯亮：当前节点的灯处于开启状态，其余节点的灯均处于关闭状态；
-   当前灯灭：当前节点的灯处于关闭状态，其余节点的灯均处于开启状态；

我们用 $t_1$, $t_2$, $t_3$, $t_4$ 分别表示四种状态下，需要操作的开关次数。我们可以发现，对于当前节点的状态，我们先递归计算其左右子节点的状态 $l_1$, $l_2$, $l_3$, $l_4$ 和 $r_1$, $r_2$, $r_3$, $r_4$，然后根据当前节点的灯的状态，可以得到四种状态下的最小操作次数：

如果当前节点的灯处于开启状态，那么：

-   全灭 $t_1 = min(l_1 + r_1 + 1, l_2 + r_2 + 1, l_3 + r_3 + 1, l_4 + r_4 + 3)$
-   全亮 $t_2 = min(l_1 + r_1 + 2, l_2 + r_2, l_3 + r_3 + 2, l_4 + r_4 + 2)$
-   当前灯亮 $t_3 = min(l_1 + r_1, l_2 + r_2 + 2, l_3 + r_3 + 2, l_4 + r_4 + 2)$
-   当前灯灭 $t_4 = min(l_1 + r_1 + 1, l_2 + r_2 + 1, l_3 + r_3 + 3, l_4 + r_4 + 1)$

如果当前节点的灯处于关闭状态，那么：

-   全灭 $t_1 = min(l_1 + r_1, l_2 + r_2 + 2, l_3 + r_3 + 2, l_4 + r_4 + 2)$
-   全亮 $t_2 = min(l_1 + r_1 + 1, l_2 + r_2 + 1, l_3 + r_3 + 3, l_4 + r_4 + 1)$
-   当前灯亮 $t_3 = min(l_1 + r_1 + 1, l_2 + r_2 + 1, l_3 + r_3 + 1, l_4 + r_4 + 3)$
-   当前灯灭 $t_4 = min(l_1 + r_1 + 2, l_2 + r_2, l_3 + r_3 + 2, l_4 + r_4 + 2)$

最后，我们返回四种状态下的最小操作次数即可。

最终答案为 $t_1$，因为我们需要将所有节点的灯都关闭。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def closeLampInTree(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0, 0, 0, 0
            l1, l2, l3, l4 = dfs(root.left)
            r1, r2, r3, r4 = dfs(root.right)
            t1 = t2 = t3 = t4 = inf
            if root.val:
                t1 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
                t2 = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
                t3 = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
                t4 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
            else:
                t1 = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
                t2 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
                t3 = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
                t4 = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
            return t1, t2, t3, t4

        return dfs(root)[0]
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int closeLampInTree(TreeNode root) {
        return dfs(root)[0];
    }

    private int[] dfs(TreeNode root) {
        int[] ans = new int[4];
        if (root == null) {
            return ans;
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int l1 = left[0], l2 = left[1], l3 = left[2], l4 = left[3];
        int r1 = right[0], r2 = right[1], r3 = right[2], r4 = right[3];
        if (root.val != 0) {
            ans[0] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3);
            ans[1] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2);
            ans[2] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2);
            ans[3] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1);
        } else {
            ans[0] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2);
            ans[1] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1);
            ans[2] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3);
            ans[3] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2);
        }
        return ans;
    }

    private int min(int... nums) {
        int ans = 1 << 30;
        for (int num : nums) {
            ans = Math.min(ans, num);
        }
        return ans;
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int closeLampInTree(TreeNode* root) {
        return dfs(root)[0];
    }

    vector<int> dfs(TreeNode* root) {
        vector<int> ans(4);
        if (!root) {
            return ans;
        }
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        int l1 = left[0], l2 = left[1], l3 = left[2], l4 = left[3];
        int r1 = right[0], r2 = right[1], r3 = right[2], r4 = right[3];
        if (root->val) {
            ans[0] = min({l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3});
            ans[1] = min({l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2});
            ans[2] = min({l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2});
            ans[3] = min({l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1});
        } else {
            ans[0] = min({l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2});
            ans[1] = min({l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1});
            ans[2] = min({l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3});
            ans[3] = min({l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2});
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
func closeLampInTree(root *TreeNode) (ans int) {
	const inf = 1 << 30
	var dfs func(*TreeNode) (int, int, int, int)
	dfs = func(root *TreeNode) (int, int, int, int) {
		if root == nil {
			return 0, 0, 0, 0
		}
		l1, l2, l3, l4 := dfs(root.Left)
		r1, r2, r3, r4 := dfs(root.Right)
		t1, t2, t3, t4 := inf, inf, inf, inf
		if root.Val == 1 {
			t1 = min(l1+r1+1, l2+r2+1, l3+r3+1, l4+r4+3)
			t2 = min(l1+r1+2, l2+r2, l3+r3+2, l4+r4+2)
			t3 = min(l1+r1, l2+r2+2, l3+r3+2, l4+r4+2)
			t4 = min(l1+r1+1, l2+r2+1, l3+r3+3, l4+r4+1)
		} else {
			t1 = min(l1+r1, l2+r2+2, l3+r3+2, l4+r4+2)
			t2 = min(l1+r1+1, l2+r2+1, l3+r3+3, l4+r4+1)
			t3 = min(l1+r1+1, l2+r2+1, l3+r3+1, l4+r4+3)
			t4 = min(l1+r1+2, l2+r2, l3+r3+2, l4+r4+2)
		}
		return t1, t2, t3, t4
	}
	ans, _, _, _ = dfs(root)
	return
}
```

#### Swift

```swift
/* public class TreeNode {
*     public var val: Int
*     public var left: TreeNode?
*     public var right: TreeNode?
*     public init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    func closeLampInTree(_ root: TreeNode?) -> Int {
        return dfs(root)[0]
    }

    private func dfs(_ root: TreeNode?) -> [Int] {
        var ans = [Int](repeating: 0, count: 4)
        guard let root = root else {
            return ans
        }

        let left = dfs(root.left)
        let right = dfs(root.right)

        let l1 = left[0], l2 = left[1], l3 = left[2], l4 = left[3]
        let r1 = right[0], r2 = right[1], r3 = right[2], r4 = right[3]

        if root.val != 0 {
            ans[0] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
            ans[1] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
            ans[2] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
            ans[3] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
        } else {
            ans[0] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
            ans[1] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
            ans[2] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
            ans[3] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
        }

        return ans
    }

    private func min(_ nums: Int...) -> Int {
        return nums.min() ?? Int.max
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
