---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Tree
    - Binary Search
    - Binary Tree
---

<!-- problem:start -->

# [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes)

[中文文档](/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân <strong>hoàn chỉnh</strong>, hãy trả về số lượng nút trong cây.</p>

<p>Theo <strong><a href="http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees" target="_blank">Wikipedia</a></strong>, trong một cây nhị phân hoàn chỉnh, mọi tầng, ngoại trừ tầng cuối cùng (nếu có), đều được lấp đầy hoàn toàn, và tất cả các nút ở tầng cuối cùng nằm xa về bên trái nhất có thể. Ở tầng cuối cùng <code>h</code>, số nút có thể nằm trong khoảng từ <code>1</code> đến <code>2<sup>h</sup></code>, bao gồm cả hai đầu mút.</p>

<p>Hãy thiết kế một thuật toán có độ phức tạp thời gian nhỏ hơn&nbsp;<code data-stringify-type="code">O(n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/images/complete.jpg" style="width: 372px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3,4,5,6]
<strong>Đầu ra:</strong> 6
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> 0
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [1]
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 5 * 10<sup>4</sup></code></li>
	<li>Cây được đảm bảo là <strong>hoàn chỉnh</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Một cây hoàn chỉnh vẫn có thể được đếm bằng đệ quy thông thường: kích thước bằng $1$ cộng với kích thước của hai cây con, bằng cách duyệt qua mỗi nút đúng một lần.

<!-- thinking:end -->

Chúng ta duyệt đệ quy toàn bộ cây và đếm số lượng nút.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là số lượng nút trong cây.

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa cho một nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        return 1 + self.countNodes(root.left) + self.countNodes(root.right)
```

#### Java

```java
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
```

#### C++

```cpp
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    int countNodes(TreeNode* root) {
        if (!root) {
            return 0;
        }
        return 1 + countNodes(root->left) + countNodes(root->right);
    }
};
```

#### Go

```go
/**
 * Định nghĩa cho một nút cây nhị phân.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return 1 + countNodes(root.Left) + countNodes(root.Right)
}
```

#### Rust

```rust
use std::cell::RefCell;
use std::rc::Rc;

impl Solution {
    pub fn count_nodes(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(node) = root {
            let node = node.borrow();
            let left = Self::depth(&node.left);
            let right = Self::depth(&node.right);
            if left == right {
                Self::count_nodes(node.right.clone()) + (1 << left)
            } else {
                Self::count_nodes(node.left.clone()) + (1 << right)
            }
        } else {
            0
        }
    }

    fn depth(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(node) = root {
            Self::depth(&node.borrow().left) + 1
        } else {
            0
        }
    }
}
```

#### JavaScript

```js
/**
 * Định nghĩa cho một nút cây nhị phân.
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
var countNodes = function (root) {
    if (!root) {
        return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
};
```

#### C#

```cs
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    public int CountNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + CountNodes(root.left) + CountNodes(root.right);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Việc duyệt tuyến tính bỏ qua đặc điểm tầng cuối cùng được lấp đầy từ trái sang phải. Nếu chiều cao bên trái và bên phải bằng nhau, cây con bên trái là cây nhị phân đầy đủ và đóng góp $2^{left}$ nút (bao gồm cả nút gốc), vì vậy chúng ta chỉ đệ quy trên bên phải; nếu không, cây con bên phải là cây nhị phân đầy đủ và chúng ta đệ quy trên bên trái.
>
> Mỗi lần duyệt chiều cao mất $O(\log n)$ và độ sâu đệ quy là $O(\log n)$, nên thời gian là $O(\log^2 n)$.

<!-- thinking:end -->

Trong bài toán này, chúng ta cũng có thể tận dụng các đặc điểm của cây nhị phân hoàn chỉnh để thiết kế một thuật toán nhanh hơn.

Đặc điểm của cây nhị phân hoàn chỉnh: các nút lá chỉ có thể xuất hiện ở tầng dưới cùng và tầng ngay trên tầng dưới cùng, còn các nút lá ở tầng dưới cùng tập trung về phía bên trái của cây. Cần lưu ý rằng cây nhị phân đầy đủ chắc chắn là cây nhị phân hoàn chỉnh, nhưng cây nhị phân hoàn chỉnh không nhất thiết là cây nhị phân đầy đủ.

Nếu số tầng trong một cây nhị phân đầy đủ là $h$, thì tổng số nút là $2^h - 1$.

Trước tiên, chúng ta đếm chiều cao của các cây con trái và phải của $root$, lần lượt ký hiệu là $left$ và $right$.

1. Nếu $left = right$, điều đó có nghĩa là cây con trái là một cây nhị phân đầy đủ, nên tổng số nút trong cây con trái là $2^{left} - 1$. Cộng thêm nút $root$, ta được $2^{left}$. Sau đó, chúng ta đệ quy để đếm cây con phải.
1. Nếu $left > right$, điều đó có nghĩa là cây con phải là một cây nhị phân đầy đủ, nên tổng số nút trong cây con phải là $2^{right} - 1$. Cộng thêm nút $root$, ta được $2^{right}$. Sau đó, chúng ta đệ quy để đếm cây con trái.

Độ phức tạp thời gian là $O(\log^2 n)$.

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa cho một nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        def depth(root):
            d = 0
            while root:
                d += 1
                root = root.left
            return d

        if root is None:
            return 0
        left, right = depth(root.left), depth(root.right)
        if left == right:
            return (1 << left) + self.countNodes(root.right)
        return (1 << right) + self.countNodes(root.left)
```

#### Java

```java
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == right) {
            return (1 << left) + countNodes(root.right);
        }
        return (1 << right) + countNodes(root.left);
    }

    private int depth(TreeNode root) {
        int d = 0;
        for (; root != null; root = root.left) {
            ++d;
        }
        return d;
    }
}
```

#### C++

```cpp
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    int countNodes(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int left = depth(root->left);
        int right = depth(root->right);
        if (left == right) {
            return (1 << left) + countNodes(root->right);
        }
        return (1 << right) + countNodes(root->left);
    }

    int depth(TreeNode* root) {
        int d = 0;
        for (; root; root = root->left) {
            ++d;
        }
        return d;
    }
};
```

#### Go

```go
/**
 * Định nghĩa cho một nút cây nhị phân.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left, right := depth(root.Left), depth(root.Right)
	if left == right {
		return (1 << left) + countNodes(root.Right)
	}
	return (1 << right) + countNodes(root.Left)
}

func depth(root *TreeNode) (d int) {
	for ; root != nil; root = root.Left {
		d++
	}
	return
}
```

#### JavaScript

```js
/**
 * Định nghĩa cho một nút cây nhị phân.
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
var countNodes = function (root) {
    const depth = root => {
        let d = 0;
        for (; root; root = root.left) {
            ++d;
        }
        return d;
    };
    if (!root) {
        return 0;
    }
    const left = depth(root.left);
    const right = depth(root.right);
    if (left == right) {
        return (1 << left) + countNodes(root.right);
    }
    return (1 << right) + countNodes(root.left);
};
```

#### C#

```cs
/**
 * Định nghĩa cho một nút cây nhị phân.
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
    public int CountNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == right) {
            return (1 << left) + CountNodes(root.right);
        }
        return (1 << right) + CountNodes(root.left);
    }

    private int depth(TreeNode root) {
        int d = 0;
        for (; root != null; root = root.left) {
            ++d;
        }
        return d;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
