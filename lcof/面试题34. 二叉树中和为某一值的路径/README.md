# [面试题 34. 二叉树中和为某一值的路径](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你二叉树的根节点 <code>root</code> 和一个整数目标和 <code>targetSum</code> ，找出所有 <strong>从根节点到叶子节点</strong> 路径总和等于给定目标和的路径。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9834.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E4%B8%AD%E5%92%8C%E4%B8%BA%E6%9F%90%E4%B8%80%E5%80%BC%E7%9A%84%E8%B7%AF%E5%BE%84/images/pathsumii1.jpg" /></p>

<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>[[5,4,11,2],[5,8,4,5]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9834.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E4%B8%AD%E5%92%8C%E4%B8%BA%E6%9F%90%E4%B8%80%E5%80%BC%E7%9A%84%E8%B7%AF%E5%BE%84/images/pathsum2.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2], targetSum = 0
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点总数在范围 <code>[0, 5000]</code> 内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
</ul>

<p>注意：本题与主站 113&nbsp;题相同：<a href="https://leetcode.cn/problems/path-sum-ii/">https://leetcode.cn/problems/path-sum-ii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先序遍历+路径记录。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> List[List[int]]:
        def dfs(root, sum):
            if root is None:
                return
            path.append(root.val)
            if root.val == sum and root.left is None and root.right is None:
                res.append(path.copy())
            dfs(root.left, sum - root.val)
            dfs(root.right, sum - root.val)
            path.pop()

        if not root:
            return []
        res = []
        path = []
        dfs(root, sum)
        return res
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private List<List<Integer>> res;
    private List<Integer> path;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return Collections.emptyList();
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(root, sum);
        return res;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, sum - root.val);
        dfs(root.right, sum - root.val);
        path.remove(path.size() - 1);
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} sum
 * @return {number[][]}
 */
var pathSum = function (root, sum) {
    if (!root) return [];
    let res = [];
    function dfs(node, sum, arr) {
        if (!node) return;
        arr = [...arr, node.val];
        if (node.val === sum && !node.left && !node.right) {
            res.push(arr);
            return;
        }
        dfs(node.left, sum - node.val, arr);
        dfs(node.right, sum - node.val, arr);
    }
    dfs(root, sum, []);
    return res;
};
```

### **Go**

```go
var res [][]int
func pathSum(root *TreeNode, sum int) [][]int {
    res = [][]int{}
    if root == nil {
        return res
    }
    helper(root, sum, []int{})
    return res
}

func helper(node *TreeNode, target int, ans []int) {
    if node == nil {
        return
    }
    ans = append(ans,node.Val)
    target -= node.Val
    if target == 0 && node.Left == nil && node.Right == nil {
        tmp := make([]int,len(ans))
        copy(tmp,ans)
        res = append(res,tmp)
    } else {
        helper(node.Left, target, ans)
        helper(node.Right, target, ans)
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> pathSum(TreeNode* root, int target) {
        vector<vector<int>> ans;
        vector<int> path;
        dfs(root, ans, path, target);
        return ans;
    }

    void dfs(TreeNode* root, vector<vector<int>>& ans, vector<int>& path, int target) {
        if (root == NULL) {
            return;
        }
        target -= root->val;
        path.push_back(root->val);
        if (root->left == NULL && root->right == NULL) {
            if (target == 0) {
                ans.push_back(vector<int>(path));
            }
        }
        dfs(root->left, ans, path, target);
        dfs(root->right, ans, path, target);
        path.pop_back();
    }
};
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

function pathSum(root: TreeNode | null, target: number): number[][] {
    const res: number[][] = [];
    if (root == null) {
        return res;
    }
    const paths: number[] = [];
    const dfs = ({ val, right, left }: TreeNode, target: number) => {
        paths.push(val);
        target -= val;
        if (left == null && right == null) {
            if (target === 0) {
                res.push([...paths]);
            }
        } else {
            left && dfs(left, target);
            right && dfs(right, target);
        }
        paths.pop();
    };
    dfs(root, target);
    return res;
}
```

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        mut target: i32,
        paths: &mut Vec<i32>,
        res: &mut Vec<Vec<i32>>,
    ) {
        if let Some(node) = root.as_ref() {
            let node = node.borrow();
            paths.push(node.val);
            target -= node.val;
            if node.left.is_none() && node.right.is_none() && target == 0 {
                res.push(paths.clone());
            }
            Self::dfs(&node.left, target, paths, res);
            Self::dfs(&node.right, target, paths, res);
            paths.pop();
        }
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(&root, target, &mut vec![], &mut res);
        res
    }
}
```

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        mut target: i32,
        paths: &mut Vec<i32>,
    ) -> Vec<Vec<i32>> {
        let node = root.as_ref().unwrap().borrow();
        paths.push(node.val);
        target -= node.val;
        let mut res = vec![];
        // 确定叶结点身份
        if node.left.is_none() && node.right.is_none() {
            if target == 0 {
                res.push(paths.clone());
            }
        } else {
            if node.left.is_some() {
                let res_l = Self::dfs(&node.left, target, paths);
                if !res_l.is_empty() {
                    res = [res, res_l].concat();
                }
            }
            if node.right.is_some() {
                let res_r = Self::dfs(&node.right, target, paths);
                if !res_r.is_empty() {
                    res = [res, res_r].concat();
                }
            }
        }
        paths.pop();
        res
    }
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target: i32) -> Vec<Vec<i32>> {
        if root.is_none() {
            return vec![];
        }
        Self::dfs(&root, target, &mut vec![])
    }
}
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    List<IList<int>> res;
    List<int> path;

    public IList<IList<int>> PathSum(TreeNode root, int target) {
        res = new List<IList<int>>();
        path = new List<int>();
        if (root == null) {
            return res;
        }
        dfs(root, target);
        return res;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.Add(root.val);
        if (root.val == target && root.left is null && root.right is null) {
            res.Add(new List<int>(path));
        }
        dfs(root.left, target - root.val);
        dfs(root.right, target - root.val);
        path.RemoveAt(path.Count - 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
