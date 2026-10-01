---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers)

[中文文档](/solution/0100-0199/0129.Sum%20Root%20to%20Leaf%20Numbers/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho <code>root</code> của một cây nhị phân chỉ chứa các chữ số từ <code>0</code> đến <code>9</code>.</p>

<p>Mỗi đường đi từ nút gốc đến nút lá trong cây biểu diễn một số.</p>

<ul>
	<li>Ví dụ, đường đi từ nút gốc đến nút lá <code>1 -&gt; 2 -&gt; 3</code> biểu diễn số <code>123</code>.</li>
</ul>

<p>Trả về <em>tổng của tất cả các số trên đường đi từ nút gốc đến nút lá</em>. Các trường hợp kiểm thử được tạo sao cho đáp án vừa với số nguyên <strong>32-bit</strong>.</p>

<p>Một nút <strong>lá</strong> là nút không có nút con.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0129.Sum%20Root%20to%20Leaf%20Numbers/images/num1tree.jpg" style="width: 212px; height: 182px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3]
<strong>Đầu ra:</strong> 25
<strong>Giải thích:</strong>
Đường đi từ nút gốc đến nút lá <code>1-&gt;2</code> biểu diễn số <code>12</code>.
Đường đi từ nút gốc đến nút lá <code>1-&gt;3</code> biểu diễn số <code>13</code>.
Do đó, tổng = 12 + 13 = <code>25</code>.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0129.Sum%20Root%20to%20Leaf%20Numbers/images/num2tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [4,9,0,5,1]
<strong>Đầu ra:</strong> 1026
<strong>Giải thích:</strong>
Đường đi từ nút gốc đến nút lá <code>4-&gt;9-&gt;5</code> biểu diễn số 495.
Đường đi từ nút gốc đến nút lá <code>4-&gt;9-&gt;1</code> biểu diễn số 491.
Đường đi từ nút gốc đến nút lá <code>4-&gt;0</code> biểu diễn số 40.
Do đó, tổng = 495 + 491 + 40 = <code>1026</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[1, 1000]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 9</code></li>
	<li>Độ sâu của cây không vượt quá <code>10</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Mỗi đường đi từ nút gốc đến nút lá là một số nguyên; chúng ta muốn tính tổng của các số đó. Độ sâu nhiều nhất là $10$, nên số lượng đường đi không lớn, nhưng chúng ta không cần xây dựng các chuỗi. Khi đi xuống, ta tích lũy bằng công thức $s \times 10 + \textit{val}$. Tại nút lá, số đó là phần đóng góp; cộng kết quả của hai cây con.

<!-- thinking:end -->

Chúng ta có thể thiết kế một hàm $dfs(root, s)$, biểu diễn tổng của tất cả các số trên đường đi từ nút hiện tại $root$ đến các nút lá, với số của đường đi hiện tại là $s$. Đáp án là $dfs(root, 0)$.

Cách tính hàm $dfs(root, s)$ như sau:

- Nếu nút hiện tại $root$ là null, trả về $0$.
- Nếu không, cộng giá trị của nút hiện tại vào $s$, tức là $s = s \times 10 + root.val$.
- Nếu nút hiện tại là một nút lá, trả về $s$.
- Nếu không, trả về $dfs(root.left, s) + dfs(root.right, s)$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(\log n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân.

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
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        def dfs(root, s):
            if root is None:
                return 0
            s = s * 10 + root.val
            if root.left is None and root.right is None:
                return s
            return dfs(root.left, s) + dfs(root.right, s)

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
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int s) {
        if (root == null) {
            return 0;
        }
        s = s * 10 + root.val;
        if (root.left == null && root.right == null) {
            return s;
        }
        return dfs(root.left, s) + dfs(root.right, s);
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
    int sumNumbers(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int s) -> int {
            if (!root) return 0;
            s = s * 10 + root->val;
            if (!root->left && !root->right) return s;
            return dfs(root->left, s) + dfs(root->right, s);
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
func sumNumbers(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, s int) int {
		if root == nil {
			return 0
		}
		s = s*10 + root.Val
		if root.Left == nil && root.Right == nil {
			return s
		}
		return dfs(root.Left, s) + dfs(root.Right, s)
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

function sumNumbers(root: TreeNode | null): number {
    function dfs(root: TreeNode | null, s: number): number {
        if (!root) return 0;
        s = s * 10 + root.val;
        if (!root.left && !root.right) return s;
        return dfs(root.left, s) + dfs(root.right, s);
    }
    return dfs(root, 0);
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
    fn dfs(node: &Option<Rc<RefCell<TreeNode>>>, mut num: i32) -> i32 {
        if node.is_none() {
            return 0;
        }
        let node = node.as_ref().unwrap().borrow();
        num = num * 10 + node.val;
        if node.left.is_none() && node.right.is_none() {
            return num;
        }
        Self::dfs(&node.left, num) + Self::dfs(&node.right, num)
    }

    pub fn sum_numbers(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root, 0)
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
 * @return {number}
 */
var sumNumbers = function (root) {
    function dfs(root, s) {
        if (!root) return 0;
        s = s * 10 + root.val;
        if (!root.left && !root.right) return s;
        return dfs(root.left, s) + dfs(root.right, s);
    }
    return dfs(root, 0);
};
```

#### C

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int dfs(struct TreeNode* root, int num) {
    if (!root) {
        return 0;
    }
    num = num * 10 + root->val;
    if (!root->left && !root->right) {
        return num;
    }
    return dfs(root->left, num) + dfs(root->right, num);
}

int sumNumbers(struct TreeNode* root) {
    return dfs(root, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
