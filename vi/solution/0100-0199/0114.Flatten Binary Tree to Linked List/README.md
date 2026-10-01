---
comments: true
difficulty: Medium
tags:
    - Stack
    - Tree
    - Depth-First Search
    - Linked List
    - Binary Tree
---

<!-- problem:start -->

# [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list)

[中文文档](/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân, hãy làm phẳng cây thành một &quot;danh sách liên kết&quot;:</p>

<ul>
    <li>&quot;Danh sách liên kết&quot; phải sử dụng cùng lớp <code>TreeNode</code>, trong đó con trỏ con <code>right</code> trỏ đến nút tiếp theo trong danh sách và con trỏ con <code>left</code> luôn là <code>null</code>.</li>
    <li>&quot;Danh sách liên kết&quot; phải có cùng thứ tự với một <a href="https://en.wikipedia.org/wiki/Tree_traversal#Pre-order,_NLR" target="_blank"><strong>phép duyệt</strong><strong> theo thứ tự trước</strong></a> của cây nhị phân.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/images/flaten.jpg" style="width: 500px; height: 226px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,5,3,4,null,6]
<strong>Đầu ra:</strong> [1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> []
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [0]
<strong>Đầu ra:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
    <li>Số lượng nút trong cây nằm trong khoảng <code>[0, 2000]</code>.</li>
    <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể làm phẳng cây tại chỗ (với không gian bổ sung <code>O(1)</code>) không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm nút tiền nhiệm

<!-- thinking:start -->

> **Tư duy**
>
> Có thể tạo một danh sách mới theo thứ tự preorder bằng đệ quy hoặc ngăn xếp, nhưng câu hỏi mở rộng yêu cầu thực hiện tại chỗ với $O(1)$ không gian bổ sung. $n \le 2000$.
>
> Sau cây con trái trong thứ tự preorder là cây con phải ban đầu. Nút ngoài cùng bên phải của cây con trái là nút tiền nhiệm đó: nối cây con phải cũ vào nó, chuyển cây con trái sang `right`, và xóa `left`. Duyệt theo nhánh phải; không cần ngăn xếp đệ quy.

<!-- thinking:end -->

Thứ tự thăm của phép duyệt theo thứ tự trước là &quot;gốc, cây con trái, cây con phải&quot;. Sau khi thăm nút cuối cùng của cây con trái, nút của cây con phải của nút gốc sẽ được thăm tiếp theo.

Do đó, với nút hiện tại, nếu nút con trái của nó không phải là null, chúng ta tìm nút ngoài cùng bên phải của cây con trái làm nút tiền nhiệm, sau đó gán nút con phải của nút hiện tại cho nút con phải của nút tiền nhiệm. Tiếp theo, gán nút con trái của nút hiện tại cho nút con phải của nút hiện tại, rồi đặt nút con trái của nút hiện tại thành null. Sau đó lấy nút con phải của nút hiện tại làm nút tiếp theo và tiếp tục xử lý cho đến khi mọi nút được xử lý.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng nút trong cây. Độ phức tạp không gian là $O(1)$.

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
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        while root:
            if root.left:
                pre = root.left
                while pre.right:
                    pre = pre.right
                pre.right = root.right
                root.right = root.left
                root.left = None
            root = root.right
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
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 找到当前节点左子树的最右节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将左子树的最右节点指向原来的右子树
                pre.right = root.right;

                // 将当前节点指向左子树
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
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
    void flatten(TreeNode* root) {
        while (root) {
            if (root->left) {
                TreeNode* pre = root->left;
                while (pre->right) {
                    pre = pre->right;
                }
                pre->right = root->right;
                root->right = root->left;
                root->left = nullptr;
            }
            root = root->right;
        }
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
func flatten(root *TreeNode) {
	for root != nil {
		if root.Left != nil {
			pre := root.Left
			for pre.Right != nil {
				pre = pre.Right
			}
			pre.Right = root.Right
			root.Right = root.Left
			root.Left = nil
		}
		root = root.Right
	}
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

/**
 Do not return anything, modify root in-place instead.
 */
function flatten(root: TreeNode | null): void {
    while (root !== null) {
        if (root.left !== null) {
            let pre = root.left;
            while (pre.right !== null) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        root = root.right;
    }
}
```

#### Rust

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
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    #[allow(dead_code)]
    pub fn flatten(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        let mut v: Vec<Option<Rc<RefCell<TreeNode>>>> = Vec::new();
        // Initialize the vector
        Self::pre_order_traverse(&mut v, root);
        // Traverse the vector
        let n = v.len();
        for i in 0..n - 1 {
            v[i].as_ref().unwrap().borrow_mut().left = None;
            v[i].as_ref().unwrap().borrow_mut().right = v[i + 1].clone();
        }
    }

    #[allow(dead_code)]
    fn pre_order_traverse(
        v: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
        root: &Option<Rc<RefCell<TreeNode>>>,
    ) {
        if root.is_none() {
            return;
        }
        v.push(root.clone());
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::pre_order_traverse(v, &left);
        Self::pre_order_traverse(v, &right);
    }
}
```

#### JavaScript

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
 * @param {TreeNode} root
 * @return {void} Do not return anything, modify root in-place instead.
 */
var flatten = function (root) {
    while (root) {
        if (root.left) {
            let pre = root.left;
            while (pre.right) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        root = root.right;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã nối các nút tiền nhiệm với không gian $O(1)$. Đây là cùng một ý tưởng với phần trình bày khác: lưu cả hai nút con, đặt cây con trái vào bên phải, nối cây con phải cũ vào nút ngoài cùng bên phải của nó, rồi di chuyển theo `right`.

<!-- thinking:end -->

<!-- tabs:start -->

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
func flatten(root *TreeNode) {
	for root != nil {
		left, right := root.Left, root.Right
		root.Left = nil
		if left != nil {
			root.Right = left
			for left.Right != nil {
				left = left.Right
			}
			left.Right = right
		}
		root = root.Right
	}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
