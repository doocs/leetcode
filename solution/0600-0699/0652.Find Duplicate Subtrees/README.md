# [652. 寻找重复的子树](https://leetcode.cn/problems/find-duplicate-subtrees)

[English Version](/solution/0600-0699/0652.Find%20Duplicate%20Subtrees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，返回所有 <strong>重复的子树 </strong>。</p>

<p>对于同一类的重复子树，你只需要返回其中任意 <strong>一棵 </strong>的根结点即可。</p>

<p>如果两棵树具有<strong> 相同的结构</strong> 和 <strong>相同的结点值 </strong>，则认为二者是 <strong>重复 </strong>的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0652.Find%20Duplicate%20Subtrees/images/e1.jpg" style="height: 236px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4,null,2,4,null,null,4]
<strong>输出：</strong>[[2,4],[4]]</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0652.Find%20Duplicate%20Subtrees/images/e2.jpg" style="height: 125px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>root = [2,1,1]
<strong>输出：</strong>[[1]]</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0652.Find%20Duplicate%20Subtrees/images/e33.jpg" style="height: 202px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [2,2,2,3,null,3,null]
<strong>输出：</strong>[[2,3],[3]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的结点数在 <code>[1, 5000]</code> 范围内。</li>
	<li><code>-200 &lt;= Node.val &lt;= 200</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：后序遍历**

后序遍历，序列化每个子树，用哈希表判断序列化的字符串出现次数是否等于 `2`，若是，说明这棵子树重复。

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
    def findDuplicateSubtrees(
        self, root: Optional[TreeNode]
    ) -> List[Optional[TreeNode]]:
        def dfs(root):
            if root is None:
                return '#'
            v = f'{root.val},{dfs(root.left)},{dfs(root.right)}'
            counter[v] += 1
            if counter[v] == 2:
                ans.append(root)
            return v

        ans = []
        counter = Counter()
        dfs(root)
        return ans
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
    private Map<String, Integer> counter;
    private List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        counter = new HashMap<>();
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String v = root.val + "," + dfs(root.left) + "," + dfs(root.right);
        counter.put(v, counter.getOrDefault(v, 0) + 1);
        if (counter.get(v) == 2) {
            ans.add(root);
        }
        return v;
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
    unordered_map<string, int> counter;
    vector<TreeNode*> ans;

    vector<TreeNode*> findDuplicateSubtrees(TreeNode* root) {
        dfs(root);
        return ans;
    }

    string dfs(TreeNode* root) {
        if (!root) return "#";
        string v = to_string(root->val) + "," + dfs(root->left) + "," + dfs(root->right);
        ++counter[v];
        if (counter[v] == 2) ans.push_back(root);
        return v;
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
func findDuplicateSubtrees(root *TreeNode) []*TreeNode {
	var ans []*TreeNode
	counter := make(map[string]int)
	var dfs func(root *TreeNode) string
	dfs = func(root *TreeNode) string {
		if root == nil {
			return "#"
		}
		v := strconv.Itoa(root.Val) + "," + dfs(root.Left) + "," + dfs(root.Right)
		counter[v]++
		if counter[v] == 2 {
			ans = append(ans, root)
		}
		return v
	}
	dfs(root)
	return ans
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

function findDuplicateSubtrees(root: TreeNode | null): Array<TreeNode | null> {
    const map = new Map<string, number>();
    const res = [];
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return '#';
        }
        const { val, left, right } = root;
        const s = `${val},${dfs(left)},${dfs(right)}`;
        map.set(s, (map.get(s) ?? 0) + 1);
        if (map.get(s) === 2) {
            res.push(root);
        }
        return s;
    };
    dfs(root);
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
use std::collections::HashMap;
impl Solution {
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        map: &mut HashMap<String, i32>,
        res: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
    ) -> String {
        if root.is_none() {
            return String::from('#');
        }

        let s = {
            let root = root.as_ref().unwrap().as_ref().borrow();
            format!(
                "{},{},{}",
                root.val.to_string(),
                Self::dfs(&root.left, map, res),
                Self::dfs(&root.right, map, res)
            )
        };
        *map.entry(s.clone()).or_insert(0) += 1;
        if *map.get(&s).unwrap() == 2 {
            res.push(root.clone());
        }
        return s;
    }

    pub fn find_duplicate_subtrees(
        root: Option<Rc<RefCell<TreeNode>>>,
    ) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut map = HashMap::new();
        let mut res = Vec::new();
        Self::dfs(&root, &mut map, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
