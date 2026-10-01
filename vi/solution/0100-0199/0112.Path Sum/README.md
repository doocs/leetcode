---
comments: true
difficulty: Easy
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [112. Path Sum](https://leetcode.com/problems/path-sum)

[中文文档](/solution/0100-0199/0112.Path%20Sum/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>root</code> của một cây nhị phân và một số nguyên <code>targetSum</code>, hãy trả về <code>true</code> nếu cây có một đường đi <strong>từ gốc đến lá</strong> sao cho tổng tất cả các giá trị trên đường đi bằng <code>targetSum</code>.</p>

<p><strong>Lá</strong> là một nút không có nút con.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0112.Path%20Sum/images/pathsum1.jpg" style="width: 500px; height: 356px;" />
<pre>
<strong>Đầu vào:</strong> root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Đường đi từ gốc đến lá có tổng bằng targetSum được minh họa trong hình.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0112.Path%20Sum/images/pathsum2.jpg" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3], targetSum = 5
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Có hai đường đi từ gốc đến lá trong cây:
(1 --&gt; 2): Tổng là 3.
(1 --&gt; 3): Tổng là 4.
Không có đường đi từ gốc đến lá nào có tổng = 5.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [], targetSum = 0
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Vì cây rỗng nên không có đường đi nào từ gốc đến lá.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 5000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta cần một đường đi từ gốc đến lá có tổng các giá trị bằng target. Việc liệt kê mọi đường đi là khả thi với $n \le 5000$, nhưng sao chép các đường đi sẽ tạo thêm công việc.
>
> Hãy tích lũy tổng đang chạy khi đi xuống và chỉ kiểm tra tổng đó tại một nút lá. Chỉ cần một trong hai cây con cho kết quả đúng; không cần lưu toàn bộ đường đi.

<!-- thinking:end -->

Bắt đầu từ nút gốc, đệ quy duyệt cây và cập nhật giá trị của nút thành tổng đường đi từ nút gốc đến nút đó. Khi duyệt đến một nút lá, hãy xác định xem tổng đường đi này có bằng giá trị mục tiêu hay không. Nếu bằng, trả về `true`, ngược lại trả về `false`.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng nút trong cây nhị phân. Mỗi nút được duyệt đúng một lần.

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
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        def dfs(root, s):
            if root is None:
                return False
            s += root.val
            if root.left is None and root.right is None and s == targetSum:
                return True
            return dfs(root.left, s) or dfs(root.right, s)

        return dfs(root, 0)
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int s) {
        if (root == null) {
            return false;
        }
        s -= root.val;
        if (root.left == null && root.right == null && s == 0) {
            return true;
        }
        return dfs(root.left, s) || dfs(root.right, s);
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
    bool hasPathSum(TreeNode* root, int targetSum) {
        function<bool(TreeNode*, int)> dfs = [&](TreeNode* root, int s) -> int {
            if (!root) return false;
            s += root->val;
            if (!root->left && !root->right && s == targetSum) return true;
            return dfs(root->left, s) || dfs(root->right, s);
        };
        return dfs(root, 0);
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
func hasPathSum(root *TreeNode, targetSum int) bool {
	var dfs func(*TreeNode, int) bool
	dfs = func(root *TreeNode, s int) bool {
		if root == nil {
			return false
		}
		s += root.Val
		if root.Left == nil && root.Right == nil && s == targetSum {
			return true
		}
		return dfs(root.Left, s) || dfs(root.Right, s)
	}
	return dfs(root, 0)
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

function hasPathSum(root: TreeNode | null, targetSum: number): boolean {
    if (root === null) {
        return false;
    }
    const { val, left, right } = root;
    if (left === null && right === null) {
        return targetSum - val === 0;
    }
    return hasPathSum(left, targetSum - val) || hasPathSum(right, targetSum - val);
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
    pub fn has_path_sum(root: Option<Rc<RefCell<TreeNode>>>, target_sum: i32) -> bool {
        match root {
            None => false,
            Some(node) => {
                let mut node = node.borrow_mut();
                // 确定叶结点身份
                if node.left.is_none() && node.right.is_none() {
                    return target_sum - node.val == 0;
                }
                let val = node.val;
                Self::has_path_sum(node.left.take(), target_sum - val)
                    || Self::has_path_sum(node.right.take(), target_sum - val)
            }
        }
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
 * @param {number} targetSum
 * @return {boolean}
 */
var hasPathSum = function (root, targetSum) {
    function dfs(root, s) {
        if (!root) return false;
        s += root.val;
        if (!root.left && !root.right && s == targetSum) return true;
        return dfs(root.left, s) || dfs(root.right, s);
    }
    return dfs(root, 0);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
