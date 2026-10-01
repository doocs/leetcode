---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view)

[中文文档](/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân, hãy tưởng tượng bạn đang đứng ở <strong>phía bên phải</strong> của cây, trả về <em>các giá trị của những nút mà bạn có thể nhìn thấy theo thứ tự từ trên xuống dưới</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,2,3,null,5,null,4]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1,3,4]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/images/tmpd5jn43fs-1.png" style="width: 400px; height: 207px;" /></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,2,3,4,null,null,null,5]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1,3,4,5]</span></p>

<p><strong>Giải thích:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/images/tmpkpe40xeh-1.png" style="width: 400px; height: 214px;" /></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = [1,null,3]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[1,3]</span></p>
</div>

<p><strong class="example">Ví dụ 4:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">root = []</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số nút trong cây nằm trong khoảng <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Nhìn từ bên phải là nút ngoài cùng bên phải của mỗi level. $n\le 100$. Đưa nút con phải vào hàng đợi trước để phần tử đầu hàng đợi là nút ngoài cùng bên phải của level đó; ghi nhận nó, sau đó lấy hết các nút còn lại trong level. Không cần lấy phần tử cuối cùng sau đó.

<!-- thinking:end -->

Chúng ta có thể sử dụng tìm kiếm theo chiều rộng (BFS) và định nghĩa một hàng đợi $\textit{q}$ để lưu các nút. Chúng ta bắt đầu bằng cách đưa nút gốc vào hàng đợi. Mỗi lần, chúng ta lấy tất cả các nút của level hiện tại ra khỏi hàng đợi. Với nút hiện tại, trước tiên chúng ta kiểm tra xem cây con phải có tồn tại hay không; nếu có, chúng ta đưa cây con phải vào hàng đợi. Sau đó, chúng ta kiểm tra xem cây con trái có tồn tại hay không; nếu có, chúng ta đưa cây con trái vào hàng đợi. Bằng cách này, nút đầu tiên được lấy ra khỏi hàng đợi mỗi lần là nút ngoài cùng bên phải của level đó.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân.

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        if root is None:
            return ans
        q = deque([root])
        while q:
            ans.append(q[0].val)
            for _ in range(len(q)):
                node = q.popleft()
                if node.right:
                    q.append(node.right)
                if node.left:
                    q.append(node.left)
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            ans.add(q.peekFirst().val);
            for (int k = q.size(); k > 0; --k) {
                TreeNode node = q.poll();
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (node.left != null) {
                    q.offer(node.left);
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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> ans;
        if (!root) {
            return ans;
        }
        queue<TreeNode*> q{{root}};
        while (q.size()) {
            ans.push_back(q.front()->val);
            for (int k = q.size(); k; --k) {
                auto node = q.front();
                q.pop();
                if (node->right) {
                    q.push(node->right);
                }
                if (node->left) {
                    q.push(node->left);
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
func rightSideView(root *TreeNode) (ans []int) {
	if root == nil {
		return
	}
	q := []*TreeNode{root}
	for len(q) > 0 {
		ans = append(ans, q[0].Val)
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			if node.Right != nil {
				q = append(q, node.Right)
			}
			if node.Left != nil {
				q = append(q, node.Left)
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

function rightSideView(root: TreeNode | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const q: TreeNode[] = [root];
    while (q.length > 0) {
        ans.push(q[0].val);
        const nq: TreeNode[] = [];
        for (const { left, right } of q) {
            if (right) {
                nq.push(right);
            }
            if (left) {
                nq.push(left);
            }
        }
        q.length = 0;
        q.push(...nq);
    }
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
use std::collections::VecDeque;
use std::rc::Rc;
impl Solution {
    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = vec![];
        if root.is_none() {
            return ans;
        }
        let mut q = VecDeque::new();
        q.push_back(root);
        while !q.is_empty() {
            let k = q.len();
            ans.push(q[0].as_ref().unwrap().borrow().val);
            for _ in 0..k {
                if let Some(node) = q.pop_front().unwrap() {
                    let mut node = node.borrow_mut();
                    if node.right.is_some() {
                        q.push_back(node.right.take());
                    }
                    if node.left.is_some() {
                        q.push_back(node.left.take());
                    }
                }
            }
        }
        ans
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
 * @return {number[]}
 */
var rightSideView = function (root) {
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    while (q.length > 0) {
        ans.push(q[0].val);
        const nq = [];
        for (const { left, right } of q) {
            if (right) {
                nq.push(right);
            }
            if (left) {
                nq.push(left);
            }
        }
        q.length = 0;
        q.push(...nq);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng một hàng đợi. Đệ quy sang phải rồi sang trái cùng với độ sâu: lần thăm đầu tiên ở độ sâu đó là nút ngoài cùng bên phải. Không gian phụ thuộc vào chiều cao thay vì chiều rộng.

<!-- thinking:end -->

Sử dụng DFS (tìm kiếm theo chiều sâu) để duyệt cây nhị phân. Mỗi lần, duyệt cây con phải trước, sau đó là cây con trái. Bằng cách này, nút đầu tiên được thăm ở mỗi level là nút ngoài cùng bên phải của level đó.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân.

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root: Optional[TreeNode], depth: int) -> None:
            if root is None:
                return
            if len(ans) == depth:
                ans.append(root.val)
            dfs(root.right, depth + 1)
            dfs(root.left, depth + 1)

        ans = []
        dfs(root, 0)
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

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (ans.size() == depth) {
            ans.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> ans;
        auto dfs = [&](this auto&& dfs, TreeNode* root, int depth) -> void {
            if (!root) {
                return;
            }
            if (ans.size() == depth) {
                ans.push_back(root->val);
            }
            dfs(root->right, depth + 1);
            dfs(root->left, depth + 1);
        };
        dfs(root, 0);
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
func rightSideView(root *TreeNode) (ans []int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if len(ans) == depth {
			ans = append(ans, root.Val)
		}
		dfs(root.Right, depth+1)
		dfs(root.Left, depth+1)
	}
	dfs(root, 0)
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

function rightSideView(root: TreeNode | null): number[] {
    const ans = [];
    const dfs = (root: TreeNode | null, depth: number) => {
        if (!root) {
            return;
        }
        if (ans.length == depth) {
            ans.push(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    };
    dfs(root, 0);
    return ans;
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
 * @return {number[]}
 */
var rightSideView = function (root) {
    const ans = [];
    const dfs = (root, depth) => {
        if (!root) {
            return;
        }
        if (ans.length == depth) {
            ans.push(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    };
    dfs(root, 0);
    return ans;
};
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
    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = Vec::new();
        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, depth: usize, ans: &mut Vec<i32>) {
            if let Some(node_ref) = node {
                let node = node_ref.borrow();
                if ans.len() == depth {
                    ans.push(node.val);
                }
                dfs(node.right.clone(), depth + 1, ans);
                dfs(node.left.clone(), depth + 1, ans);
            }
        }
        dfs(root, 0, &mut ans);
        ans
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
 * @return {number[]}
 */
var rightSideView = function (root) {
    const ans = [];
    const dfs = (root, depth) => {
        if (!root) {
            return;
        }
        if (ans.length == depth) {
            ans.push(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    };
    dfs(root, 0);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
