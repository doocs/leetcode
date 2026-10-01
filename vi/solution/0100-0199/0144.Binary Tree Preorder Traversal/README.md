---
comments: true
difficulty: Easy
tags:
    - Stack
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal)

[中文文档](/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân, hãy trả về <em>phép duyệt preorder các giá trị của các nút</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,null,2,3]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1,2,3]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/images/screenshot-2024-08-29-202743.png" style="width: 200px; height: 264px;" /></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,2,3,4,5,null,8,null,null,6,7,9]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1,2,4,5,6,7,3,8,9]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/images/tree_2.png" style="width: 350px; height: 286px;" /></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = []</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[]</span></p>
</div>

<p><strong class="example">Ví dụ 4:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Lời giải đệ quy là hiển nhiên, bạn có thể thực hiện bằng cách lặp không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Duyệt preorder có thứ tự là gốc, trái, phải. Cây có tính đệ quy, vì vậy hãy ghi nhận nút gốc rồi đệ quy trên cả hai nút con. $n\le 100$, nên ngăn xếp lời gọi là đủ. Câu hỏi mở rộng yêu cầu dùng phép lặp.

<!-- thinking:end -->

Trước tiên, chúng ta thăm nút gốc, sau đó đệ quy duyệt cây con bên trái và bên phải.

Độ phức tạp thời gian là $O(n)$, còn độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào không gian ngăn xếp được sử dụng cho các lời gọi đệ quy.

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
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root):
            if root is None:
                return
            ans.append(root.val)
            dfs(root.left)
            dfs(root.right)

        ans = []
        dfs(root)
        return ans
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
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        dfs(root.left);
        dfs(root.right);
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
    vector<int> preorderTraversal(TreeNode* root) {
        vector<int> ans;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            ans.push_back(root->val);
            dfs(root->left);
            dfs(root->right);
        };
        dfs(root);
        return ans;
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
func preorderTraversal(root *TreeNode) (ans []int) {
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		ans = append(ans, root.Val)
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return
}
```

#### TypeScript

```ts
/**
 * Định nghĩa cho một nút cây nhị phân.
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

function preorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        ans.push(root.val);
        dfs(root.left);
        dfs(root.right);
    };
    dfs(root);
    return ans;
}
```

#### Rust

```rust
// Định nghĩa cho một nút cây nhị phân.
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, ans: &mut Vec<i32>) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        ans.push(node.val);
        Self::dfs(&node.left, ans);
        Self::dfs(&node.right, ans);
    }

    pub fn preorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = vec![];
        Self::dfs(&root, &mut ans);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Cài đặt ngăn xếp cho duyệt không đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã đúng; câu hỏi mở rộng loại bỏ ngăn xếp lời gọi. Một ngăn xếp tường minh đẩy nút phải trước rồi nút trái, vì vậy khi lấy ra sẽ thăm nút gốc trước. Thứ tự vẫn giống nhau, với không gian $O(n)$.

<!-- thinking:end -->

Ý tưởng sử dụng một ngăn xếp để thực hiện phép duyệt không đệ quy như sau:

1. Khai báo một ngăn xếp $stk$, trước tiên đẩy nút gốc vào ngăn xếp.
2. Nếu ngăn xếp không rỗng, mỗi lần lấy một nút ra khỏi ngăn xếp.
3. Xử lý nút đó.
4. Trước tiên đẩy nút con bên phải của nút đó vào ngăn xếp, sau đó đẩy nút con bên trái vào ngăn xếp (nếu có nút con).
5. Lặp lại các bước 2-4.
6. Trả về kết quả.

Độ phức tạp thời gian là $O(n)$, còn độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào không gian ngăn xếp.

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
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        if root is None:
            return ans
        stk = [root]
        while stk:
            node = stk.pop()
            ans.append(node.val)
            if node.right:
                stk.append(node.right)
            if node.left:
                stk.append(node.left)
        return ans
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            ans.add(node.val);
            if (node.right != null) {
                stk.push(node.right);
            }
            if (node.left != null) {
                stk.push(node.left);
            }
        }
        return ans;
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
    vector<int> preorderTraversal(TreeNode* root) {
        vector<int> ans;
        if (!root) {
            return ans;
        }
        stack<TreeNode*> stk;
        stk.push(root);
        while (stk.size()) {
            auto node = stk.top();
            stk.pop();
            ans.push_back(node->val);
            if (node->right) {
                stk.push(node->right);
            }
            if (node->left) {
                stk.push(node->left);
            }
        }
        return ans;
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
func preorderTraversal(root *TreeNode) (ans []int) {
	if root == nil {
		return
	}
	stk := []*TreeNode{root}
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append(ans, node.Val)
		if node.Right != nil {
			stk = append(stk, node.Right)
		}
		if node.Left != nil {
			stk = append(stk, node.Left)
		}
	}
	return
}
```

#### TypeScript

```ts
/**
 * Định nghĩa cho một nút cây nhị phân.
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

function preorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const stk: TreeNode[] = [root];
    while (stk.length) {
        const { left, right, val } = stk.pop();
        ans.push(val);
        right && stk.push(right);
        left && stk.push(left);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Duyệt preorder Morris

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 2 vẫn sử dụng không gian $O(h)$. Morris tạm thời nối nút ngoài cùng bên phải của cây con trái với gốc hiện tại, xuất gốc ở lần thăm đầu tiên, rồi xoá liên kết khi quay lại. Các con trỏ null của cây đóng vai trò như ngăn xếp, nên không gian là $O(1)$.

<!-- thinking:end -->

Duyệt Morris không cần ngăn xếp và có độ phức tạp không gian là $O(1)$. Ý tưởng cốt lõi là:

Duyệt các nút của cây nhị phân,

1. Nếu cây con trái của nút hiện tại `root` rỗng, thêm giá trị của nút hiện tại vào danh sách kết quả $ans$, rồi cập nhật nút hiện tại thành `root.right`.
1. Nếu cây con trái của nút hiện tại `root` không rỗng, tìm nút ngoài cùng bên phải `pre` của cây con trái (đây là nút predecessor của nút `root` trong phép duyệt inorder):
    - Nếu cây con phải của nút predecessor `pre` rỗng, thêm giá trị của nút hiện tại vào danh sách kết quả $ans$, sau đó trỏ cây con phải của nút predecessor tới nút hiện tại `root`, rồi cập nhật nút hiện tại thành `root.left`.
    - Nếu cây con phải của nút predecessor `pre` không rỗng, trỏ cây con phải của nút predecessor tới null (tức là ngắt kết nối `pre` và `root`), rồi cập nhật nút hiện tại thành `root.right`.
1. Lặp lại các bước trên cho đến khi nút của cây nhị phân là null và phép duyệt kết thúc.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số nút trong cây nhị phân. Độ phức tạp không gian là $O(1)$.

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
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        while root:
            if root.left is None:
                ans.append(root.val)
                root = root.right
            else:
                prev = root.left
                while prev.right and prev.right != root:
                    prev = prev.right
                if prev.right is None:
                    ans.append(root.val)
                    prev.right = root
                    root = root.left
                else:
                    prev.right = None
                    root = root.right
        return ans
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    ans.add(root.val);
                    prev.right = root;
                    root = root.left;
                } else {
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
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
    vector<int> preorderTraversal(TreeNode* root) {
        vector<int> ans;
        while (root) {
            if (!root->left) {
                ans.push_back(root->val);
                root = root->right;
            } else {
                TreeNode* prev = root->left;
                while (prev->right && prev->right != root) {
                    prev = prev->right;
                }
                if (!prev->right) {
                    ans.push_back(root->val);
                    prev->right = root;
                    root = root->left;
                } else {
                    prev->right = nullptr;
                    root = root->right;
                }
            }
        }
        return ans;
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
func preorderTraversal(root *TreeNode) (ans []int) {
	for root != nil {
		if root.Left == nil {
			ans = append(ans, root.Val)
			root = root.Right
		} else {
			prev := root.Left
			for prev.Right != nil && prev.Right != root {
				prev = prev.Right
			}
			if prev.Right == nil {
				ans = append(ans, root.Val)
				prev.Right = root
				root = root.Left
			} else {
				prev.Right = nil
				root = root.Right
			}
		}
	}
	return
}
```

#### TypeScript

```ts
/**
 * Định nghĩa cho một nút cây nhị phân.
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

function preorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    while (root) {
        const { left, right, val } = root;
        if (!left) {
            ans.push(val);
            root = right;
        } else {
            let prev = left;
            while (prev.right && prev.right != root) {
                prev = prev.right;
            }
            if (!prev.right) {
                ans.push(val);
                prev.right = root;
                root = root.left;
            } else {
                prev.right = null;
                root = root.right;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
