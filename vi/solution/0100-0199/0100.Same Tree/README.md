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

# [100. Same Tree](https://leetcode.com/problems/same-tree)

[中文文档](/solution/0100-0199/0100.Same%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước gốc của hai cây nhị phân <code>p</code> và <code>q</code>, hãy viết một hàm để kiểm tra xem chúng có giống nhau hay không.</p>

<p>Hai cây nhị phân được coi là giống nhau nếu chúng có cấu trúc giống hệt nhau và các nút có cùng giá trị.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex1.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>Đầu vào:</strong> p = [1,2,3], q = [1,2,3]
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex2.jpg" style="width: 382px; height: 182px;" />
<pre>
<strong>Đầu vào:</strong> p = [1,2], q = [1,null,2]
<strong>Đầu ra:</strong> false
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex3.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>Đầu vào:</strong> p = [1,2,1], q = [1,1,2]
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cả hai cây nằm trong khoảng <code>[0, 100]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Việc xác định hai cây có giống nhau hay không phụ thuộc vào việc khớp cấu trúc và giá trị tại mọi vị trí tương ứng. Việc thu thập cả hai phép duyệt rồi so sánh các dãy vẫn cần một cách mã hóa dùng sentinel cho các nút con bị thiếu. $n \le 100$, vì vậy đệ quy là phù hợp.
>
> Một cây được xác định bởi nút gốc và hai cây con, vì vậy ta chỉ cần so sánh cặp nút hiện tại: cả hai đều null nghĩa là bằng nhau; một nút là null hoặc các giá trị không bằng nhau nghĩa là không bằng nhau; nếu không thì đệ quy trên các nút con bên trái và bên phải.

<!-- thinking:end -->

Chúng ta có thể sử dụng phương pháp đệ quy DFS để giải bài toán này.

Trước tiên, xác định xem các nút gốc của hai cây nhị phân có giống nhau hay không. Nếu cả hai nút gốc đều là null, thì hai cây nhị phân giống nhau. Nếu chỉ một trong hai nút gốc là null, thì chắc chắn hai cây nhị phân khác nhau. Nếu cả hai nút gốc đều không phải null, thì xác định xem giá trị của chúng có giống nhau hay không. Nếu chúng không giống nhau, thì chắc chắn hai cây nhị phân khác nhau. Nếu chúng giống nhau, thì xác định xem các cây con bên trái của hai cây nhị phân có giống nhau hay không và các cây con bên phải có giống nhau hay không. Hai cây nhị phân chỉ giống nhau khi tất cả các điều kiện trên đều được thỏa mãn.

Độ phức tạp thời gian là $O(\min(m, n))$, và độ phức tạp không gian là $O(\min(m, n))$. Ở đây, $m$ và $n$ lần lượt là số lượng nút trong hai cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào số lớp của các lời gọi đệ quy, không vượt quá số nút trong cây nhị phân nhỏ hơn.

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
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if p == q:
            return True
        if p is None or q is None or p.val != q.val:
            return False
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (p == q) return true;
        if (!p || !q || p->val != q->val) return false;
        return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
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
func isSameTree(p *TreeNode, q *TreeNode) bool {
	if p == q {
		return true
	}
	if p == nil || q == nil || p.Val != q.Val {
		return false
	}
	return isSameTree(p.Left, q.Left) && isSameTree(p.Right, q.Right)
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

function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    if (p == null && q == null) {
        return true;
    }
    if (p == null || q == null || p.val !== q.val) {
        return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
    fn dfs(p: &Option<Rc<RefCell<TreeNode>>>, q: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if p.is_none() && q.is_none() {
            return true;
        }
        if p.is_none() || q.is_none() {
            return false;
        }
        let r1 = p.as_ref().unwrap().borrow();
        let r2 = q.as_ref().unwrap().borrow();
        r1.val == r2.val && Self::dfs(&r1.left, &r2.left) && Self::dfs(&r1.right, &r2.right)
    }

    pub fn is_same_tree(
        p: Option<Rc<RefCell<TreeNode>>>,
        q: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::dfs(&p, &q)
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
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {boolean}
 */
var isSameTree = function (p, q) {
    if (!p && !q) return true;
    if (p && q) {
        return p.val === q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    return false;
};
```

#### PHP

```php
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     public $val = null;
 *     public $left = null;
 *     public $right = null;
 *     function __construct($val = 0, $left = null, $right = null) {
 *         $this->val = $val;
 *         $this->left = $left;
 *         $this->right = $right;
 *     }
 * }
 */
class Solution {
    /**
     * @param TreeNode $p
     * @param TreeNode $q
     * @return Boolean
     */
    function isSameTree($p, $q) {
        if ($p == null && $q == null) {
            return true;
        }
        if ($p == null || $q == null) {
            return false;
        }
        if ($p->val != $q->val) {
            return false;
        }
        return $this->isSameTree($p->left, $q->left) && $this->isSameTree($p->right, $q->right);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã cho ra đáp án, nhưng phải trả giá bằng ngăn xếp lời gọi. Phiên bản lặp phải duyệt đồng thời hai cây theo cùng nhịp. Một cặp hàng đợi BFS có thể lấy các nút ra cùng nhau, kiểm tra giá trị và sự tồn tại của nút con, sau đó đưa các nút con tương ứng vào hàng đợi, nhờ đó chúng ta không còn phải đệ quy nữa.

<!-- thinking:end -->

Chúng ta cũng có thể sử dụng phương pháp lặp BFS để giải bài toán này.

Trước tiên, thêm các nút gốc của hai cây nhị phân vào hai hàng đợi. Mỗi lần, lấy một nút ra khỏi mỗi hàng đợi và thực hiện các thao tác so sánh sau. Nếu giá trị của hai nút không giống nhau, thì chắc chắn cấu trúc của hai cây nhị phân khác nhau. Nếu giá trị của chúng giống nhau, thì xác định xem các nút con của hai nút có phải là null hay không. Nếu chỉ nút con bên trái của một nút là null, thì chắc chắn cấu trúc của hai cây nhị phân khác nhau. Nếu chỉ nút con bên phải của một nút là null, thì chắc chắn cấu trúc của hai cây nhị phân khác nhau. Nếu cấu trúc của các nút con bên trái và bên phải giống nhau, thì lần lượt thêm các nút con bên trái và bên phải của hai nút vào hai hàng đợi. Ở lần lặp tiếp theo, lấy một nút ra khỏi mỗi hàng đợi để so sánh. Khi cả hai hàng đợi đồng thời rỗng, điều đó có nghĩa là chúng ta đã so sánh tất cả các nút, và cấu trúc của hai cây nhị phân hoàn toàn giống nhau.

Độ phức tạp thời gian là $O(\min(m, n))$, và độ phức tạp không gian là $O(\min(m, n))$. Ở đây, $m$ và $n$ lần lượt là số lượng nút trong hai cây nhị phân. Độ phức tạp không gian chủ yếu phụ thuộc vào số lượng phần tử trong hàng đợi, không vượt quá số nút trong cây nhị phân nhỏ hơn.

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
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if p == q:
            return True
        if p is None or q is None:
            return False
        q1, q2 = deque([p]), deque([q])
        while q1 and q2:
            a, b = q1.popleft(), q2.popleft()
            if a.val != b.val:
                return False
            la, ra = a.left, a.right
            lb, rb = b.left, b.right
            if (la and not lb) or (lb and not la):
                return False
            if (ra and not rb) or (rb and not ra):
                return False
            if la:
                q1.append(la)
                q2.append(lb)
            if ra:
                q1.append(ra)
                q2.append(rb)
        return True
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            p = q1.poll();
            q = q2.poll();
            if (p.val != q.val) {
                return false;
            }
            TreeNode la = p.left, ra = p.right;
            TreeNode lb = q.left, rb = q.right;
            if ((la != null && lb == null) || (lb != null && la == null)) {
                return false;
            }
            if ((ra != null && rb == null) || (rb != null && ra == null)) {
                return false;
            }
            if (la != null) {
                q1.offer(la);
                q2.offer(lb);
            }
            if (ra != null) {
                q1.offer(ra);
                q2.offer(rb);
            }
        }
        return true;
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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (p == q) return true;
        if (!p || !q) return false;
        queue<TreeNode*> q1{{p}};
        queue<TreeNode*> q2{{q}};
        while (!q1.empty() && !q2.empty()) {
            p = q1.front();
            q = q2.front();
            if (p->val != q->val) return false;
            q1.pop();
            q2.pop();
            TreeNode *la = p->left, *ra = p->right;
            TreeNode *lb = q->left, *rb = q->right;
            if ((la && !lb) || (lb && !la)) return false;
            if ((ra && !rb) || (rb && !ra)) return false;
            if (la) {
                q1.push(la);
                q2.push(lb);
            }
            if (ra) {
                q1.push(ra);
                q2.push(rb);
            }
        }
        return true;
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
func isSameTree(p *TreeNode, q *TreeNode) bool {
	if p == q {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	q1 := []*TreeNode{p}
	q2 := []*TreeNode{q}
	for len(q1) > 0 && len(q2) > 0 {
		p, q = q1[0], q2[0]
		if p.Val != q.Val {
			return false
		}
		q1, q2 = q1[1:], q2[1:]
		la, ra := p.Left, p.Right
		lb, rb := q.Left, q.Right
		if (la != nil && lb == nil) || (lb != nil && la == nil) {
			return false
		}
		if (ra != nil && rb == nil) || (rb != nil && ra == nil) {
			return false
		}
		if la != nil {
			q1 = append(q1, la)
			q2 = append(q2, lb)
		}
		if ra != nil {
			q1 = append(q1, ra)
			q2 = append(q2, rb)
		}
	}
	return true
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

function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    const queue = [];
    p && queue.push(p);
    q && queue.push(q);
    if (queue.length === 1) {
        return false;
    }
    while (queue.length !== 0) {
        const node1 = queue.shift();
        const node2 = queue.shift();
        if (node1.val !== node2.val) {
            return false;
        }
        if (
            (node1.left == null && node2.left != null) ||
            (node1.left != null && node2.left == null)
        ) {
            return false;
        }
        if (
            (node1.right == null && node2.right != null) ||
            (node1.right != null && node2.right == null)
        ) {
            return false;
        }

        if (node1.left != null) {
            queue.push(node1.left);
            queue.push(node2.left);
        }
        if (node1.right != null) {
            queue.push(node1.right);
            queue.push(node2.right);
        }
    }
    return true;
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
use std::collections::VecDeque;
use std::rc::Rc;
impl Solution {
    pub fn is_same_tree(
        mut p: Option<Rc<RefCell<TreeNode>>>,
        mut q: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut queue = VecDeque::new();
        if p.is_some() {
            queue.push_back(p.take());
        }
        if q.is_some() {
            queue.push_back(q.take());
        }
        if queue.len() == 1 {
            return false;
        }
        while queue.len() != 0 {
            if let (Some(mut node1), Some(mut node2)) = (queue.pop_front(), queue.pop_front()) {
                let mut node1 = node1.as_mut().unwrap().borrow_mut();
                let mut node2 = node2.as_mut().unwrap().borrow_mut();
                if node1.val != node2.val {
                    return false;
                }
                match (node1.left.is_some(), node2.left.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.left.take());
                        queue.push_back(node2.left.take());
                    }
                    (_, _) => {
                        return false;
                    }
                }
                match (node1.right.is_some(), node2.right.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.right.take());
                        queue.push_back(node2.right.take());
                    }
                    (_, _) => {
                        return false;
                    }
                }
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
