---
comments: true
difficulty: Medium
tags:
    - Stack
    - Tree
    - Design
    - Binary Search Tree
    - Binary Tree
    - Iterator
---

<!-- problem:start -->

# [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator)

[中文文档](/solution/0100-0199/0173.Binary%20Search%20Tree%20Iterator/README.md)

## Mô tả

<!-- description:start -->

<p>Hãy triển khai lớp <code>BSTIterator</code> biểu diễn một iterator trên <strong><a href="https://en.wikipedia.org/wiki/Tree_traversal#In-order_(LNR)" target="_blank">phép duyệt theo thứ tự giữa</a></strong> của một cây tìm kiếm nhị phân (BST):</p>

<ul>
	<li><code>BSTIterator(TreeNode root)</code> Khởi tạo một đối tượng thuộc lớp <code>BSTIterator</code>. <code>root</code> của BST được cung cấp như một phần của hàm khởi tạo. Con trỏ phải được khởi tạo thành một số không tồn tại nhỏ hơn mọi phần tử trong BST.</li>
	<li><code>boolean hasNext()</code> Trả về <code>true</code> nếu có một số nằm bên phải con trỏ trong phép duyệt, ngược lại trả về <code>false</code>.</li>
	<li><code>int next()</code> Di chuyển con trỏ sang phải, sau đó trả về số tại vị trí của con trỏ.</li>
</ul>

<p>Lưu ý rằng bằng cách khởi tạo con trỏ thành một số nhỏ nhất không tồn tại, lần gọi <code>next()</code> đầu tiên sẽ trả về phần tử nhỏ nhất trong BST.</p>

<p>Có thể giả sử rằng các lần gọi <code>next()</code> luôn hợp lệ. Nghĩa là, sẽ luôn có ít nhất một số tiếp theo trong phép duyệt theo thứ tự giữa khi gọi <code>next()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0173.Binary%20Search%20Tree%20Iterator/images/bst-tree.png" style="width: 189px; height: 178px;" />
<pre>
<strong>Đầu vào</strong>
[&quot;BSTIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
<strong>Đầu ra</strong>
[null, 3, 7, true, 9, true, 15, true, 20, false]

<strong>Giải thích</strong>
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next(); // trả về 3
bSTIterator.next(); // trả về 7
bSTIterator.hasNext(); // trả về True
bSTIterator.next(); // trả về 9
bSTIterator.hasNext(); // trả về True
bSTIterator.next(); // trả về 15
bSTIterator.hasNext(); // trả về True
bSTIterator.next(); // trả về 20
bSTIterator.hasNext(); // trả về False
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li>Có nhiều nhất <code>10<sup>5</sup></code> lần gọi đến <code>hasNext</code> và <code>next</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong></p>

<ul>
	<li>Bạn có thể triển khai <code>next()</code> và <code>hasNext()</code> để chạy trong thời gian trung bình <code>O(1)</code> và sử dụng bộ nhớ <code>O(h)</code>, trong đó <code>h</code> là chiều cao của cây không?</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Phép duyệt theo thứ tự giữa trên BST có thứ tự đã sắp xếp. Với tối đa $10^5$ nút và số lần gọi cũng nhiều như vậy, lưu toàn bộ phép duyệt theo thứ tự giữa vào một mảng khiến việc gọi $\textit{next}/\textit{hasNext}$ chỉ là di chuyển con trỏ — có độ phức tạp phân bổ $O(1)$ — nhưng cần $O(n)$ bộ nhớ. Câu hỏi mở rộng yêu cầu bộ nhớ $O(h)$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa cho một nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:
    def __init__(self, root: TreeNode):
        def inorder(root):
            if root:
                inorder(root.left)
                self.vals.append(root.val)
                inorder(root.right)

        self.cur = 0
        self.vals = []
        inorder(root)

    def next(self) -> int:
        res = self.vals[self.cur]
        self.cur += 1
        return res

    def hasNext(self) -> bool:
        return self.cur < len(self.vals)


# Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()
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
class BSTIterator {
    private int cur = 0;
    private List<Integer> vals = new ArrayList<>();

    public BSTIterator(TreeNode root) {
        inorder(root);
    }

    public int next() {
        return vals.get(cur++);
    }

    public boolean hasNext() {
        return cur < vals.size();
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            vals.add(root.val);
            inorder(root.right);
        }
    }
}

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
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
class BSTIterator {
public:
    vector<int> vals;
    int cur;
    BSTIterator(TreeNode* root) {
        cur = 0;
        inorder(root);
    }

    int next() {
        return vals[cur++];
    }

    bool hasNext() {
        return cur < vals.size();
    }

    void inorder(TreeNode* root) {
        if (root) {
            inorder(root->left);
            vals.push_back(root->val);
            inorder(root->right);
        }
    }
};

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
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
type BSTIterator struct {
	stack []*TreeNode
}

func Constructor(root *TreeNode) BSTIterator {
	var stack []*TreeNode
	for ; root != nil; root = root.Left {
		stack = append(stack, root)
	}
	return BSTIterator{
		stack: stack,
	}
}

func (this *BSTIterator) Next() int {
	cur := this.stack[len(this.stack)-1]
	this.stack = this.stack[:len(this.stack)-1]
	for node := cur.Right; node != nil; node = node.Left {
		this.stack = append(this.stack, node)
	}
	return cur.Val
}

func (this *BSTIterator) HasNext() bool {
	return len(this.stack) > 0
}

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
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

class BSTIterator {
    private data: number[];
    private index: number;

    constructor(root: TreeNode | null) {
        this.index = 0;
        this.data = [];
        const dfs = (root: TreeNode | null) => {
            if (root == null) {
                return;
            }
            const { val, left, right } = root;
            dfs(left);
            this.data.push(val);
            dfs(right);
        };
        dfs(root);
    }

    next(): number {
        return this.data[this.index++];
    }

    hasNext(): boolean {
        return this.index < this.data.length;
    }
}

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
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
struct BSTIterator {
    vals: Vec<i32>,
    index: usize,
}

use std::cell::RefCell;
use std::rc::Rc;
/**
 * `&self` nghĩa là phương thức nhận một tham chiếu bất biến.
 * Nếu cần một tham chiếu khả biến, hãy đổi nó thành `&mut self`.
 */
impl BSTIterator {
    fn inorder(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.as_ref().borrow();
            Self::inorder(&node.left, res);
            res.push(node.val);
            Self::inorder(&node.right, res);
        }
    }

    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut vals = vec![];
        Self::inorder(&root, &mut vals);
        BSTIterator { vals, index: 0 }
    }

    fn next(&mut self) -> i32 {
        self.index += 1;
        self.vals[self.index - 1]
    }

    fn has_next(&self) -> bool {
        self.index != self.vals.len()
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
 */
var BSTIterator = function (root) {
    this.stack = [];
    for (; root != null; root = root.left) {
        this.stack.push(root);
    }
};

/**
 * @return {number}
 */
BSTIterator.prototype.next = function () {
    let cur = this.stack.pop();
    let node = cur.right;
    for (; node != null; node = node.left) {
        this.stack.push(node);
    }
    return cur.val;
};

/**
 * @return {boolean}
 */
BSTIterator.prototype.hasNext = function () {
    return this.stack.length > 0;
};

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 làm phẳng toàn bộ cây. Một ngăn xếp tường minh mô phỏng phép duyệt theo thứ tự giữa: đẩy nhánh trái vào ngăn xếp lúc khởi tạo; $\textit{next}$ lấy phần tử trên cùng, sau đó đẩy nhánh trái của nút con phải vào ngăn xếp. Có nhiều nhất $h$ nút nằm trên ngăn xếp, và mỗi nút được đẩy vào rồi lấy ra đúng một lần.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa cho một nút cây nhị phân.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:
    def __init__(self, root: TreeNode):
        self.stack = []
        while root:
            self.stack.append(root)
            root = root.left

    def next(self) -> int:
        cur = self.stack.pop()
        node = cur.right
        while node:
            self.stack.append(node)
            node = node.left
        return cur.val

    def hasNext(self) -> bool:
        return len(self.stack) > 0


# Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()
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
class BSTIterator {
    private Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        for (; root != null; root = root.left) {
            stack.offerLast(root);
        }
    }

    public int next() {
        TreeNode cur = stack.pollLast();
        for (TreeNode node = cur.right; node != null; node = node.left) {
            stack.offerLast(node);
        }
        return cur.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
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
class BSTIterator {
public:
    stack<TreeNode*> stack;
    BSTIterator(TreeNode* root) {
        for (; root != nullptr; root = root->left) {
            stack.push(root);
        }
    }

    int next() {
        TreeNode* cur = stack.top();
        stack.pop();
        TreeNode* node = cur->right;
        for (; node != nullptr; node = node->left) {
            stack.push(node);
        }
        return cur->val;
    }

    bool hasNext() {
        return !stack.empty();
    }
};

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
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

class BSTIterator {
    private stack: TreeNode[];

    constructor(root: TreeNode | null) {
        this.stack = [];
        const dfs = (root: TreeNode | null) => {
            if (root == null) {
                return;
            }
            this.stack.push(root);
            dfs(root.left);
        };
        dfs(root);
    }

    next(): number {
        const { val, right } = this.stack.pop();
        if (right) {
            let cur = right;
            while (cur != null) {
                this.stack.push(cur);
                cur = cur.left;
            }
        }
        return val;
    }

    hasNext(): boolean {
        return this.stack.length !== 0;
    }
}

/**
 * Đối tượng BSTIterator của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
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
struct BSTIterator {
    stack: Vec<Option<Rc<RefCell<TreeNode>>>>,
}

use std::cell::RefCell;
use std::rc::Rc;
/**
 * `&self` nghĩa là phương thức nhận một tham chiếu bất biến.
 * Nếu cần một tham chiếu khả biến, hãy đổi nó thành `&mut self`.
 */
impl BSTIterator {
    fn dfs(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        stack: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
    ) {
        if root.is_some() {
            let left = root.as_mut().unwrap().borrow_mut().left.take();
            stack.push(root);
            Self::dfs(left, stack);
        }
    }

    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut stack = vec![];
        Self::dfs(root, &mut stack);
        BSTIterator { stack }
    }

    fn next(&mut self) -> i32 {
        let node = self.stack.pop().unwrap().unwrap();
        let mut node = node.borrow_mut();
        if node.right.is_some() {
            Self::dfs(node.right.take(), &mut self.stack);
        }
        node.val
    }

    fn has_next(&self) -> bool {
        self.stack.len() != 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
