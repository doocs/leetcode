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

# [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal)

[中文文档](/solution/0100-0199/0145.Binary%20Tree%20Postorder%20Traversal/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân, hãy trả về <em>phép duyệt hậu tự của các giá trị nút</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,null,2,3]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[3,2,1]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0145.Binary%20Tree%20Postorder%20Traversal/images/screenshot-2024-08-29-202743.png" style="width: 200px; height: 264px;" /></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,2,3,4,5,null,8,null,null,6,7,9]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[4,6,7,5,2,9,8,3,1]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0145.Binary%20Tree%20Postorder%20Traversal/images/tree_2.png" style="width: 350px; height: 286px;" /></p>
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
<strong>Câu hỏi mở rộng:</strong> Lời giải đệ quy là hiển nhiên, bạn có thể thực hiện bằng cách lặp không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Phép duyệt hậu tự có thứ tự là trái, phải, gốc. Gọi đệ quy trên cả hai nút con, sau đó ghi nhận nút gốc. $n\le 100$, nên đệ quy là phù hợp. Câu hỏi mở rộng yêu cầu thực hiện bằng phép lặp.

<!-- thinking:end -->

Trước tiên, chúng ta duyệt đệ quy cây con trái và cây con phải, sau đó thăm nút gốc.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào không gian ngăn xếp được sử dụng cho các lời gọi đệ quy.

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
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            dfs(root.right)
            ans.append(root.val)

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

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        ans.add(root.val);
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
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> ans;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            dfs(root->left);
            dfs(root->right);
            ans.push_back(root->val);
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
func postorderTraversal(root *TreeNode) (ans []int) {
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		dfs(root.Right)
		ans = append(ans, root.Val)
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

function postorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        ans.push(root.val);
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
        Self::dfs(&node.left, ans);
        Self::dfs(&node.right, ans);
        ans.push(node.val);
    }

    pub fn postorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = vec![];
        Self::dfs(&root, &mut ans);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Cài đặt ngăn xếp cho phép duyệt hậu tự

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng ngăn xếp lời gọi hàm. Gốc-phải-trái là phép duyệt tiền tự với các nút con được đổi chỗ; đảo ngược thứ tự đó sẽ thu được trái-phải-gốc. Chúng ta duyệt theo thứ tự này bằng một ngăn xếp rồi đảo ngược kết quả, không cần cờ “thăm lần hai”.

<!-- thinking:end -->

Thứ tự của phép duyệt tiền tự là: gốc, trái, phải. Nếu thay đổi thứ tự của các nút con trái và phải, thứ tự sẽ trở thành: gốc, phải, trái. Cuối cùng, đảo ngược kết quả sẽ cho ta kết quả của phép duyệt hậu tự.

Do đó, ý tưởng sử dụng ngăn xếp để thực hiện phép duyệt không đệ quy như sau:

1. Định nghĩa một ngăn xếp $stk$, trước tiên đẩy nút gốc vào ngăn xếp.
2. Nếu ngăn xếp không rỗng, mỗi lần lấy một nút ra khỏi ngăn xếp.
3. Xử lý nút đó.
4. Trước tiên đẩy nút con trái của nút đó vào ngăn xếp, sau đó đẩy nút con phải vào ngăn xếp (nếu có nút con).
5. Lặp lại các bước 2-4.
6. Đảo ngược kết quả để thu được kết quả của phép duyệt hậu tự.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào không gian ngăn xếp.

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
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        if root is None:
            return ans
        stk = [root]
        while stk:
            node = stk.pop()
            ans.append(node.val)
            if node.left:
                stk.append(node.left)
            if node.right:
                stk.append(node.right)
        return ans[::-1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            ans.addFirst(node.val);
            if (node.left != null) {
                stk.push(node.left);
            }
            if (node.right != null) {
                stk.push(node.right);
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
    vector<int> postorderTraversal(TreeNode* root) {
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
            if (node->left) {
                stk.push(node->left);
            }
            if (node->right) {
                stk.push(node->right);
            }
        }
        reverse(ans.begin(), ans.end());
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
func postorderTraversal(root *TreeNode) (ans []int) {
	if root == nil {
		return
	}
	stk := []*TreeNode{root}
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append(ans, node.Val)
		if node.Left != nil {
			stk = append(stk, node.Left)
		}
		if node.Right != nil {
			stk = append(stk, node.Right)
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
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

function postorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const stk: TreeNode[] = [root];
    while (stk.length) {
        const { left, right, val } = stk.pop();
        ans.push(val);
        left && stk.push(left);
        right && stk.push(right);
    }
    ans.reverse();
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Cài đặt Morris cho phép duyệt hậu tự

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 2 vẫn cần một ngăn xếp $O(n)$ và thao tác đảo ngược. Morris tạo các liên kết tạm thời cho cây theo thứ tự gốc-phải-trái rồi đảo ngược kết quả, nhờ đó không gian phụ trở thành $O(1)$.

<!-- thinking:end -->

Phép duyệt Morris không yêu cầu ngăn xếp và có độ phức tạp không gian là $O(1)$. Ý tưởng cốt lõi là:

Duyệt qua các nút của cây nhị phân,

1. Nếu cây con phải của nút hiện tại `root` là rỗng, thêm giá trị của nút hiện tại vào danh sách kết quả $ans$, rồi cập nhật nút hiện tại thành `root.left`.
1. Nếu cây con phải của nút hiện tại `root` không rỗng, tìm nút ngoài cùng bên trái `next` của cây con phải (đó là nút kế nhiệm của nút `root` trong phép duyệt trung tự):
    - Nếu cây con trái của nút kế nhiệm `next` là rỗng, thêm giá trị của nút hiện tại vào danh sách kết quả $ans$, sau đó trỏ cây con trái của nút kế nhiệm tới nút hiện tại `root`, rồi cập nhật nút hiện tại thành `root.right`.
    - Nếu cây con trái của nút kế nhiệm `next` không rỗng, trỏ cây con trái của nút kế nhiệm tới null (tức là ngắt liên kết giữa `next` và `root`), rồi cập nhật nút hiện tại thành `root.left`.
1. Lặp lại các bước trên cho đến khi nút cây nhị phân là null, khi đó phép duyệt kết thúc.
1. Cuối cùng, trả về danh sách kết quả đã đảo ngược.

> Ý tưởng của phép duyệt hậu tự Morris nhất quán với phép duyệt tiền tự Morris; chỉ cần đổi thứ tự “gốc-trái-phải” của phép duyệt tiền tự thành “gốc-phải-trái”, rồi cuối cùng đảo ngược kết quả để trở thành “trái-phải-gốc”.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng nút trong cây nhị phân. Độ phức tạp không gian là $O(1)$.

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
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        while root:
            if root.right is None:
                ans.append(root.val)
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left != root:
                    ans.append(root.val)
                    next.left = root
                    root = root.right
                else:
                    next.left = None
                    root = root.left
        return ans[::-1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        while (root != null) {
            if (root.right == null) {
                ans.addFirst(root.val);
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    ans.addFirst(root.val);
                    next.left = root;
                    root = root.right;
                } else {
                    next.left = null;
                    root = root.left;
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
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> ans;
        while (root) {
            if (!root->right) {
                ans.push_back(root->val);
                root = root->left;
            } else {
                TreeNode* next = root->right;
                while (next->left && next->left != root) {
                    next = next->left;
                }
                if (next->left != root) {
                    ans.push_back(root->val);
                    next->left = root;
                    root = root->right;
                } else {
                    next->left = nullptr;
                    root = root->left;
                }
            }
        }
        reverse(ans.begin(), ans.end());
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
func postorderTraversal(root *TreeNode) (ans []int) {
	for root != nil {
		if root.Right == nil {
			ans = append([]int{root.Val}, ans...)
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				ans = append([]int{root.Val}, ans...)
				next.Left = root
				root = root.Right
			} else {
				next.Left = nil
				root = root.Left
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

function postorderTraversal(root: TreeNode | null): number[] {
    const ans: number[] = [];
    while (root !== null) {
        const { val, left, right } = root;
        if (right === null) {
            ans.push(val);
            root = left;
        } else {
            let next = right;
            while (next.left !== null && next.left !== root) {
                next = next.left;
            }
            if (next.left === null) {
                ans.push(val);
                next.left = root;
                root = right;
            } else {
                next.left = null;
                root = left;
            }
        }
    }
    return ans.reverse();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
