---
comments: true
difficulty: Easy
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree)

[中文文档](/solution/0100-0199/0110.Balanced%20Binary%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước một cây nhị phân, hãy xác định xem cây đó có <span data-keyword="height-balanced"><strong>cân bằng theo chiều cao</strong></span> hay không.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0110.Balanced%20Binary%20Tree/images/balance_1.jpg" style="width: 342px; height: 221px;" />
<pre>
<strong>Đầu vào:</strong> root = [3,9,20,null,null,15,7]
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0110.Balanced%20Binary%20Tree/images/balance_2.jpg" style="width: 452px; height: 301px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,2,3,3,null,null,4,4]
<strong>Đầu ra:</strong> false
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 5000]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy từ dưới lên

<!-- thinking:start -->

> **Tư duy**
>
> Việc tính chiều cao bên trái và bên phải từ đầu tại mỗi nút sẽ duyệt lại các cây con giống nhau và có thể đạt $O(n^2)$; $n \le 5000$ là khá lớn.
>
> Ta có thể quyết định tính cân bằng ngay trong khi tính chiều cao. Một lượt duyệt từ dưới lên trả về chiều cao hoặc một sentinel báo lỗi nếu cây con đã mất cân bằng. Một lần duyệt hậu thứ tự trả lời cả hai câu hỏi.

<!-- thinking:end -->

Chúng ta định nghĩa một hàm $height(root)$ để tính chiều cao của cây nhị phân, với logic như sau:

- Nếu cây nhị phân $root$ là null, trả về $0$.
- Ngược lại, tính đệ quy chiều cao của các cây con bên trái và bên phải, lần lượt ký hiệu là $l$ và $r$. Nếu $l$ hoặc $r$ bằng $-1$, hoặc độ chênh lệch tuyệt đối giữa $l$ và $r$ lớn hơn $1$, thì trả về $-1$. Nếu không, trả về $max(l, r) + 1$.

Do đó, nếu hàm $height(root)$ trả về $-1$, điều đó có nghĩa là cây nhị phân $root$ không cân bằng. Ngược lại, cây đó cân bằng.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân.

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
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        def height(root):
            if root is None:
                return 0
            l, r = height(root.left), height(root.right)
            if l == -1 or r == -1 or abs(l - r) > 1:
                return -1
            return 1 + max(l, r)

        return height(root) >= 0
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
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = height(root.left);
        int r = height(root.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
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
    bool isBalanced(TreeNode* root) {
        function<int(TreeNode*)> height = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int l = height(root->left);
            int r = height(root->right);
            if (l == -1 || r == -1 || abs(l - r) > 1) {
                return -1;
            }
            return 1 + max(l, r);
        };
        return height(root) >= 0;
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
func isBalanced(root *TreeNode) bool {
	var height func(*TreeNode) int
	height = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := height(root.Left), height(root.Right)
		if l == -1 || r == -1 || abs(l-r) > 1 {
			return -1
		}
		if l > r {
			return 1 + l
		}
		return 1 + r
	}
	return height(root) >= 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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

function isBalanced(root: TreeNode | null): boolean {
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return 0;
        }
        const left = dfs(root.left);
        const right = dfs(root.right);
        if (left === -1 || right === -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    };
    return dfs(root) > -1;
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
    pub fn is_balanced(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root) > -1
    }

    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        let left = Self::dfs(&node.left);
        let right = Self::dfs(&node.right);
        if left == -1 || right == -1 || (left - right).abs() > 1 {
            return -1;
        }
        1 + left.max(right)
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
 * @return {boolean}
 */
var isBalanced = function (root) {
    const height = root => {
        if (!root) {
            return 0;
        }
        const l = height(root.left);
        const r = height(root.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    };
    return height(root) >= 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
