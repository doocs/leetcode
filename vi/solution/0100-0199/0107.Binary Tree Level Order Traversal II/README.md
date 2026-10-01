---
comments: true
difficulty: Medium
tags:
    - Tree
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [107. Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii)

[中文文档](/solution/0100-0199/0107.Binary%20Tree%20Level%20Order%20Traversal%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>root</code> của một cây nhị phân, hãy trả về <em>phép duyệt các giá trị của các nút theo thứ tự mức từ dưới lên</em> (tức là từ trái sang phải, theo từng mức từ lá đến gốc).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0107.Binary%20Tree%20Level%20Order%20Traversal%20II/images/tree1.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [3,9,20,null,null,15,7]
<strong>Đầu ra:</strong> [[15,7],[9,20],[3]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [1]
<strong>Đầu ra:</strong> [[1]]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số nút trong cây nằm trong khoảng <code>[0, 2000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Đây là phép duyệt theo thứ tự mức thông thường, chỉ khác là các mức phải được liệt kê từ dưới lên. Hãy thu thập các mức như thường lệ rồi đảo ngược đáp án; không cần chèn vào đầu trong khi duyệt. $n \le 2000$, vì vậy BFS theo mức và đảo ngược cuối cùng là đủ.

<!-- thinking:end -->

Chúng ta có thể sử dụng phương pháp BFS (Breadth-First Search) để giải bài toán này. Trước tiên, đưa nút gốc vào hàng đợi, sau đó liên tục thực hiện các thao tác sau cho đến khi hàng đợi rỗng:

- Duyệt qua tất cả các nút trong hàng đợi hiện tại, lưu giá trị của chúng vào một mảng tạm thời $t$, rồi đưa các nút con của chúng vào hàng đợi.
- Lưu mảng tạm thời $t$ vào mảng kết quả.

Cuối cùng, trả về mảng kết quả sau khi đảo ngược.

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
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        ans = []
        if root is None:
            return ans
        q = deque([root])
        while q:
            t = []
            for _ in range(len(q)):
                node = q.popleft()
                t.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            ans.append(t)
        return ans[::-1]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                t.add(node.val);
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            ans.addFirst(t);
        }
        return ans;
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
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> ans;
        if (!root) {
            return ans;
        }
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            vector<int> t;
            for (int n = q.size(); n; --n) {
                auto node = q.front();
                q.pop();
                t.push_back(node->val);
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            ans.push_back(t);
        }
        reverse(ans.begin(), ans.end());
        return ans;
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
func levelOrderBottom(root *TreeNode) (ans [][]int) {
	if root == nil {
		return
	}
	q := []*TreeNode{root}
	for len(q) > 0 {
		t := []int{}
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			t = append(t, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, t)
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

function levelOrderBottom(root: TreeNode | null): number[][] {
    const ans: number[][] = [];
    if (!root) {
        return ans;
    }
    const q: TreeNode[] = [root];
    while (q.length) {
        const t: number[] = [];
        const qq: TreeNode[] = [];
        for (const { val, left, right } of q) {
            t.push(val);
            left && qq.push(left);
            right && qq.push(right);
        }
        ans.push(t);
        q.splice(0, q.length, ...qq);
    }
    return ans.reverse();
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
use std::{cell::RefCell, collections::VecDeque, rc::Rc};
impl Solution {
    pub fn level_order_bottom(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        if let Some(root_node) = root {
            let mut q = VecDeque::new();
            q.push_back(root_node);
            while !q.is_empty() {
                let mut t = Vec::new();
                for _ in 0..q.len() {
                    if let Some(node) = q.pop_front() {
                        let node_ref = node.borrow();
                        t.push(node_ref.val);
                        if let Some(ref left) = node_ref.left {
                            q.push_back(Rc::clone(left));
                        }
                        if let Some(ref right) = node_ref.right {
                            q.push_back(Rc::clone(right));
                        }
                    }
                }
                ans.push(t);
            }
        }
        ans.reverse();
        ans
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
 * @return {number[][]}
 */
var levelOrderBottom = function (root) {
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    while (q.length) {
        const t = [];
        const qq = [];
        for (const { val, left, right } of q) {
            t.push(val);
            left && qq.push(left);
            right && qq.push(right);
        }
        ans.push(t);
        q.splice(0, q.length, ...qq);
    }
    return ans.reverse();
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
