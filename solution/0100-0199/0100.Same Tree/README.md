# [100. 相同的树](https://leetcode.cn/problems/same-tree)

[English Version](/solution/0100-0199/0100.Same%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两棵二叉树的根节点 <code>p</code> 和 <code>q</code> ，编写一个函数来检验这两棵树是否相同。</p>

<p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex1.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,3], q = [1,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex2.jpg" style="width: 382px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2], q = [1,null,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex3.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,1], q = [1,1,2]
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两棵树上的节点数目都在范围 <code>[0, 100]</code> 内</li>
	<li><code>-10<sup>4</sup> <= Node.val <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们可以使用 DFS 递归的方法来解决这个问题。

首先判断两个二叉树的根节点是否相同，如果两个根节点都为空，则两个二叉树相同，如果两个根节点中有且只有一个为空，则两个二叉树一定不同。如果两个根节点都不为空，则判断它们的值是否相同，如果不相同则两个二叉树一定不同，如果相同，则分别判断两个二叉树的左子树是否相同以及右子树是否相同。当以上所有条件都满足时，两个二叉树才相同。

时间复杂度 $O(\min(m, n))$，空间复杂度 $O(\min(m, n))$。其中 $m$ 和 $n$ 分别是两个二叉树的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的节点个数。

**方法二：BFS**

我们也可以使用 BFS 迭代的方法来解决这个问题。

首先将两个二叉树的根节点分别加入两个队列。每次从两个队列各取出一个节点，进行如下比较操作。如果两个节点的值不相同，则两个二叉树的结构一定不同，如果两个节点的值相同，则判断两个节点的子节点是否为空，如果只有一个节点的左子节点为空，则两个二叉树的结构一定不同，如果只有一个节点的右子节点为空，则两个二叉树的结构一定不同，如果左右子节点的结构相同，则将两个节点的左子节点和右子节点分别加入两个队列，对于下一次迭代，将从两个队列各取出一个节点进行比较。当两个队列同时为空时，说明我们已经比较完了所有节点，两个二叉树的结构完全相同。

时间复杂度 $O(\min(m, n))$，空间复杂度 $O(\min(m, n))$。其中 $m$ 和 $n$ 分别是两个二叉树的节点个数。空间复杂度主要取决于队列中的元素个数，队列中的元素个数不会超过较小的二叉树的节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if p == q:
            return True
        if p is None or q is None or p.val != q.val:
            return False
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
```

BFS：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if p == q:
            return True
        if p is None or q is None:
            return False
        q1, q2 = deque([p]), deque([q])
        while q1 and q2:
            a, b = q1.popleft(), q2.popleft()
            if a.val != b.val:
                return False
            la, ra = a.left, a.right
            lb, rb = b.left, b.right
            if (la and not lb) or (lb and not la):
                return False
            if (ra and not rb) or (rb and not ra):
                return False
            if la:
                q1.append(la)
                q2.append(lb)
            if ra:
                q1.append(ra)
                q2.append(rb)
        return True
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            p = q1.poll();
            q = q2.poll();
            if (p.val != q.val) {
                return false;
            }
            TreeNode la = p.left, ra = p.right;
            TreeNode lb = q.left, rb = q.right;
            if ((la != null && lb == null) || (lb != null && la == null)) {
                return false;
            }
            if ((ra != null && rb == null) || (rb != null && ra == null)) {
                return false;
            }
            if (la != null) {
                q1.offer(la);
                q2.offer(lb);
            }
            if (ra != null) {
                q1.offer(ra);
                q2.offer(rb);
            }
        }
        return true;
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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (p == q) return true;
        if (!p || !q || p->val != q->val) return false;
        return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
    }
};
```

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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (p == q) return true;
        if (!p || !q) return false;
        queue<TreeNode*> q1{{p}};
        queue<TreeNode*> q2{{q}};
        while (!q1.empty() && !q2.empty())
        {
            p = q1.front();
            q = q2.front();
            if (p->val != q->val) return false;
            q1.pop();
            q2.pop();
            TreeNode *la = p->left, *ra = p->right;
            TreeNode *lb = q->left, *rb = q->right;
            if ((la && !lb) || (lb && !la)) return false;
            if ((ra && !rb) || (rb && !ra)) return false;
            if (la)
            {
                q1.push(la);
                q2.push(lb);
            }
            if (ra)
            {
                q1.push(ra);
                q2.push(rb);
            }
        }
        return true;
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
func isSameTree(p *TreeNode, q *TreeNode) bool {
    if p == q {
        return true
    }
    if p == nil || q == nil || p.Val != q.Val {
        return false
    }
    return isSameTree(p.Left, q.Left) && isSameTree(p.Right, q.Right)
}
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isSameTree(p *TreeNode, q *TreeNode) bool {
	if p == q {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	q1 := []*TreeNode{p}
	q2 := []*TreeNode{q}
	for len(q1) > 0 && len(q2) > 0 {
		p, q = q1[0], q2[0]
		if p.Val != q.Val {
			return false
		}
		q1, q2 = q1[1:], q2[1:]
		la, ra := p.Left, p.Right
		lb, rb := q.Left, q.Right
		if (la != nil && lb == nil) || (lb != nil && la == nil) {
			return false
		}
		if (ra != nil && rb == nil) || (rb != nil && ra == nil) {
			return false
		}
		if la != nil {
			q1 = append(q1, la)
			q2 = append(q2, lb)
		}
		if ra != nil {
			q1 = append(q1, ra)
			q2 = append(q2, rb)
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {boolean}
 */
var isSameTree = function (p, q) {
    if (!p && !q) return true;
    if (p && q) {
        return (
            p.val === q.val &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right)
        );
    }
    return false;
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

function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    if (p == null && q == null) {
        return true;
    }
    if (p == null || q == null || p.val !== q.val) {
        return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

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

function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    const queue = [];
    p && queue.push(p);
    q && queue.push(q);
    if (queue.length === 1) {
        return false;
    }
    while (queue.length !== 0) {
        const node1 = queue.shift();
        const node2 = queue.shift();
        if (node1.val !== node2.val) {
            return false;
        }
        if (
            (node1.left == null && node2.left != null) ||
            (node1.left != null && node2.left == null)
        ) {
            return false;
        }
        if (
            (node1.right == null && node2.right != null) ||
            (node1.right != null && node2.right == null)
        ) {
            return false;
        }

        if (node1.left != null) {
            queue.push(node1.left);
            queue.push(node2.left);
        }
        if (node1.right != null) {
            queue.push(node1.right);
            queue.push(node2.right);
        }
    }
    return true;
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
    fn dfs(p: &Option<Rc<RefCell<TreeNode>>>, q: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if p.is_none() && q.is_none() {
            return true;
        }
        if p.is_none() || q.is_none() {
            return false;
        }
        let r1 = p.as_ref().unwrap().borrow();
        let r2 = q.as_ref().unwrap().borrow();
        r1.val == r2.val && Self::dfs(&r1.left, &r2.left) && Self::dfs(&r1.right, &r2.right)
    }

    pub fn is_same_tree(
        p: Option<Rc<RefCell<TreeNode>>>,
        q: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::dfs(&p, &q)
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
use std::collections::VecDeque;
impl Solution {
    pub fn is_same_tree(
        mut p: Option<Rc<RefCell<TreeNode>>>,
        mut q: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut queue = VecDeque::new();
        if p.is_some() {
            queue.push_back(p.take());
        }
        if q.is_some() {
            queue.push_back(q.take());
        }
        if queue.len() == 1 {
            return false;
        }
        while queue.len() != 0 {
            if let (Some(mut node1), Some(mut node2)) = (queue.pop_front(), queue.pop_front()) {
                let mut node1 = node1.as_mut().unwrap().borrow_mut();
                let mut node2 = node2.as_mut().unwrap().borrow_mut();
                if node1.val != node2.val {
                    return false;
                }
                match (node1.left.is_some(), node2.left.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.left.take());
                        queue.push_back(node2.left.take());
                    }
                    (_, _) => return false,
                }
                match (node1.right.is_some(), node2.right.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.right.take());
                        queue.push_back(node2.right.take());
                    }
                    (_, _) => return false,
                }
            }
        }
        true
    }
}
```

### **PHP**

```php
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     public $val = null;
 *     public $left = null;
 *     public $right = null;
 *     function __construct($val = 0, $left = null, $right = null) {
 *         $this->val = $val;
 *         $this->left = $left;
 *         $this->right = $right;
 *     }
 * }
 */
class Solution {
    /**
     * @param TreeNode $p
     * @param TreeNode $q
     * @return Boolean
     */
    function isSameTree($p, $q) {
        if ($p == Null && $q == Null) {
            return true;
        }
        if ($p == Null || $q == Null) {
            return false;
        }
        if ($p->val != $q->val) {
            return false;
        }
        return $this->isSameTree($p->left, $q->left) && $this->isSameTree($p->right, $q->right);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
