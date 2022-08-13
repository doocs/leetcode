# [面试题 32 - III. 从上到下打印二叉树 III](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)

## 题目描述

<p>请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。</p>

<p>&nbsp;</p>

<p>例如:<br>
给定二叉树:&nbsp;<code>[3,9,20,null,null,15,7]</code>,</p>

<pre>    3
   / \
  9  20
    /  \
   15   7
</pre>

<p>返回其层次遍历结果：</p>

<pre>[
  [3],
  [20,9],
  [15,7]
]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>节点总数 &lt;= 1000</code></li>
</ol>

## 解法

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = deque()
        res = []
        q.append(root)
        while q:
            size = len(q)
            t = []
            for _ in range(size):
                node = q.popleft()
                t.append(node.val)
                if node.left is not None:
                    q.append(node.left)
                if node.right is not None:
                    q.append(node.right)
            res.append(t if len(res) & 1 == 0 else t[::-1])
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> q = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> t = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            if ((res.size() & 1) == 1) Collections.reverse(t);
            res.add(t);
        }
        return res;
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
 * @return {number[][]}
 */
var levelOrder = function (root) {
    if (!root) return [];
    let queue = [root];
    let res = [];
    let depth = 0;
    let dir = true;
    while (queue.length) {
        let len = queue.length;
        for (let i = 0; i < len; i++) {
            let node = queue.shift();
            if (!node) continue;
            if (!res[depth]) res[depth] = [];
            if (dir) {
                res[depth].push(node.val);
            } else {
                res[depth].unshift(node.val);
            }
            queue.push(node.left, node.right);
        }
        depth++;
        dir = !dir;
    }
    return res;
};
```

### **Go**

```go
func levelOrder(root *TreeNode) [][]int {
    if root == nil {
        return nil
    }
    res := [][]int{}
    queue := []*TreeNode{}
    queue = append(queue,root)
    level := 0
    for len(queue) != 0 {
        size := len(queue)
        ans := []int{}
        //size记录每层大小,level记录层数
        for size > 0 {
            cur := queue[0]
            if level & 1 == 0 {
                ans = append(ans, cur.Val)
            } else {
                ans = append([]int{cur.Val},ans...)
            }

            queue = queue[1:]
            size--
            if cur.Left != nil {
                queue = append(queue, cur.Left)
            }
            if cur.Right != nil {
                queue = append(queue, cur.Right)
            }
        }
        level++
        res = append(res, ans)
    }
    return res
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ans;
        if (root == NULL) return ans;
        queue<TreeNode*> q;
        q.push(root);
        bool flag = false;
        while (!q.empty()) {
            int n = q.size();
            vector<int> v;
            for (int i = 0; i < n; ++i) {
                TreeNode* node = q.front();
                q.pop();
                v.emplace_back(node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            if (flag) reverse(v.begin(), v.end());
            flag = !flag;
            ans.emplace_back(v);
        }
        return ans;
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

function levelOrder(root: TreeNode | null): number[][] {
    const res = [];
    if (root == null) {
        return res;
    }
    let isEven = false;
    const queue = [root];
    while (queue.length !== 0) {
        const n = queue.length;
        const vals = new Array(n);
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            vals[i] = val;
            left && queue.push(left);
            right && queue.push(right);
        }
        res.push(isEven ? vals.reverse() : vals);
        isEven = !isEven;
    }
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
use std::collections::VecDeque;
impl Solution {
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        if root.is_none() {
            return res;
        }
        let mut queue = VecDeque::new();
        queue.push_back(root);
        let mut is_even = false;
        while !queue.is_empty() {
            let n = queue.len();
            let mut vals = Vec::with_capacity(n);
            for _ in 0..n {
                let mut node = queue.pop_front().unwrap();
                let mut node = node.as_mut().unwrap().borrow_mut();
                vals.push(node.val);
                if node.left.is_some() {
                    queue.push_back(node.left.take());
                }
                if node.right.is_some() {
                    queue.push_back(node.right.take());
                }
            }
            if is_even {
                vals.reverse();
            }
            res.push(vals);
            is_even = !is_even;
        }
        res
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
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public IList<IList<int>> LevelOrder(TreeNode root) {
        var ans = new List<IList<int>>();
        if (root == null) {
            return ans;
        }
        var q = new Queue<TreeNode>();
        q.Enqueue(root);
        int i = 0;
        while (q.Count > 0) {
            var tmp = new List<int>();
            int x = q.Count;
            for (int j = 0; j < x; j++) {
                var node = q.Dequeue();
                tmp.Add(node.val);
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
            if (i % 2 == 1) {
                tmp.Reverse();
            }
            ans.Add(tmp);
            i += 1;
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
