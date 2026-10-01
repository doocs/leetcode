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

# [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree)

[中文文档](/solution/0100-0199/0111.Minimum%20Depth%20of%20Binary%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một cây nhị phân, hãy tìm độ sâu nhỏ nhất của nó.</p>

<p>Độ sâu nhỏ nhất là số lượng nút trên đường đi ngắn nhất từ nút gốc xuống nút lá gần nhất.</p>

<p><strong>Chú ý:</strong>&nbsp;Nút lá là một nút không có nút con.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0111.Minimum%20Depth%20of%20Binary%20Tree/images/ex_depth.jpg" style="width: 432px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [3,9,20,null,null,15,7]
<strong>Đầu ra:</strong> 2
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [2,null,3,null,4,null,5,null,6]
<strong>Đầu ra:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 10<sup>5</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Độ sâu nhỏ nhất là số nút trên đường đi ngắn nhất từ gốc đến lá. Một nút chỉ có một nút con không phải là nút lá, vì vậy chúng ta phải tiếp tục đi xuống phía đang tồn tại; coi nút con bị thiếu có độ sâu $0$ là sai. $n \le 10^5$.
>
> Đệ quy: cây rỗng có độ sâu là $0$; thiếu cây con trái nghĩa là $1$ cộng với độ sâu nhỏ nhất của cây con phải; trường hợp thiếu cây con phải là đối xứng; cả hai cây con đều tồn tại thì là $1$ cộng với giá trị nhỏ hơn của hai độ sâu nhỏ nhất.

<!-- thinking:end -->

Điều kiện kết thúc của đệ quy là khi nút hiện tại là null, tại đó trả về $0$. Nếu cây con trái hoặc phải của nút hiện tại là null, trả về độ sâu nhỏ nhất của cây con khác null cộng với $1$. Nếu cả cây con trái và phải của nút hiện tại đều không null, trả về giá trị nhỏ hơn của độ sâu nhỏ nhất của hai cây con cộng với $1$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân.

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
    def minDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        if root.left is None:
            return 1 + self.minDepth(root.right)
        if root.right is None:
            return 1 + self.minDepth(root.left)
        return 1 + min(self.minDepth(root.left), self.minDepth(root.right))
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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
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
    int minDepth(TreeNode* root) {
        if (!root) {
            return 0;
        }
        if (!root->left) {
            return 1 + minDepth(root->right);
        }
        if (!root->right) {
            return 1 + minDepth(root->left);
        }
        return 1 + min(minDepth(root->left), minDepth(root->right));
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
func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	if root.Left == nil {
		return 1 + minDepth(root.Right)
	}
	if root.Right == nil {
		return 1 + minDepth(root.Left)
	}
	return 1 + min(minDepth(root.Left), minDepth(root.Right))
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

function minDepth(root: TreeNode | null): number {
    if (root == null) {
        return 0;
    }
    const { left, right } = root;
    if (left == null) {
        return 1 + minDepth(right);
    }
    if (right == null) {
        return 1 + minDepth(left);
    }
    return 1 + Math.min(minDepth(left), minDepth(right));
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        if node.left.is_none() {
            return 1 + Self::dfs(&node.right);
        }
        if node.right.is_none() {
            return 1 + Self::dfs(&node.left);
        }
        1 + Self::dfs(&node.left).min(Self::dfs(&node.right))
    }

    pub fn min_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root)
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
var minDepth = function (root) {
    if (!root) {
        return 0;
    }
    if (!root.left) {
        return 1 + minDepth(root.right);
    }
    if (!root.right) {
        return 1 + minDepth(root.left);
    }
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
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

#define min(a, b) (((a) < (b)) ? (a) : (b))

int minDepth(struct TreeNode* root) {
    if (!root) {
        return 0;
    }
    if (!root->left) {
        return 1 + minDepth(root->right);
    }
    if (!root->right) {
        return 1 + minDepth(root->left);
    }
    int left = minDepth(root->left);
    int right = minDepth(root->right);
    return 1 + min(left, right);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 có thể duyệt toàn bộ một nhánh sâu trước khi thấy nút lá gần nhất. Độ sâu nhỏ nhất là một đường đi ngắn nhất, vì vậy BFS có thể dừng ở nút lá đầu tiên. Cách này nhanh hơn trong thực tế trên các cây lệch.

<!-- thinking:end -->

Dùng một hàng đợi để triển khai BFS, ban đầu thêm nút gốc vào hàng đợi. Mỗi lần, lấy một nút ra khỏi hàng đợi. Nếu nút này là nút lá, trực tiếp trả về độ sâu hiện tại. Nếu nút này không phải là nút lá, thêm tất cả các nút con khác null của nó vào hàng đợi. Tiếp tục tìm kiếm lớp nút tiếp theo cho đến khi tìm thấy một nút lá.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân.

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
    def minDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        q = deque([root])
        ans = 0
        while 1:
            ans += 1
            for _ in range(len(q)):
                node = q.popleft()
                if node.left is None and node.right is None:
                    return ans
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        while (true) {
            ++ans;
            for (int n = q.size(); n > 0; n--) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) {
                    return ans;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
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
    int minDepth(TreeNode* root) {
        if (!root) {
            return 0;
        }
        queue<TreeNode*> q{{root}};
        int ans = 0;
        while (1) {
            ++ans;
            for (int n = q.size(); n; --n) {
                auto node = q.front();
                q.pop();
                if (!node->left && !node->right) {
                    return ans;
                }
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
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
func minDepth(root *TreeNode) (ans int) {
	if root == nil {
		return 0
	}
	q := []*TreeNode{root}
	for {
		ans++
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			if node.Left == nil && node.Right == nil {
				return
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
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

function minDepth(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    const q = [root];
    let ans = 0;
    while (1) {
        ++ans;
        for (let n = q.length; n; --n) {
            const node = q.shift();
            if (!node.left && !node.right) {
                return ans;
            }
            if (node.left) {
                q.push(node.left);
            }
            if (node.right) {
                q.push(node.right);
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
 * @return {number}
 */
var minDepth = function (root) {
    if (!root) {
        return 0;
    }
    const q = [root];
    let ans = 0;
    while (1) {
        ++ans;
        for (let n = q.length; n; --n) {
            const node = q.shift();
            if (!node.left && !node.right) {
                return ans;
            }
            if (node.left) {
                q.push(node.left);
            }
            if (node.right) {
                q.push(node.right);
            }
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
