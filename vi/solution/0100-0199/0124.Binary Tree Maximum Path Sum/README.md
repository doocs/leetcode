---
comments: true
difficulty: Hard
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
    - Tree DP
---

<!-- problem:start -->

# [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum)

[中文文档](/solution/0100-0199/0124.Binary%20Tree%20Maximum%20Path%20Sum/README.md)

## Mô tả

<!-- description:start -->

<p>Một <strong>đường đi</strong> trong cây nhị phân là một dãy các nút, trong đó mỗi cặp nút liền kề trong dãy được nối với nhau bằng một cạnh. Một nút chỉ có thể xuất hiện trong dãy <strong>nhiều nhất một lần</strong>. Lưu ý rằng đường đi không nhất thiết phải đi qua nút gốc.</p>

<p><strong>Tổng đường đi</strong> của một đường đi là tổng các giá trị của các nút trên đường đi đó.</p>

<p>Cho <code>root</code> của một cây nhị phân, hãy trả về <em><strong>tổng đường đi</strong> lớn nhất của bất kỳ đường đi <strong>không rỗng</strong> nào</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0124.Binary%20Tree%20Maximum%20Path%20Sum/images/exx1.jpg" style="width: 322px; height: 182px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Đường đi tối ưu là 2 -&gt; 1 -&gt; 3 với tổng đường đi là 2 + 1 + 3 = 6.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0124.Binary%20Tree%20Maximum%20Path%20Sum/images/exx2.jpg" />
<pre>
<strong>Đầu vào:</strong> root = [-10,9,20,null,null,15,7]
<strong>Đầu ra:</strong> 42
<strong>Giải thích:</strong> Đường đi tối ưu là 15 -&gt; 20 -&gt; 7 với tổng đường đi là 15 + 20 + 7 = 42.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[1, 3 * 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Một đường đi có thể bắt đầu ở bất kỳ đâu, không cần đi qua nút gốc và có thể uốn tại một nút để lấy cả hai nút con. Việc liệt kê các đường đi là không thể với $n \le 3\times 10^4$.
>
> Giá trị mà một nút có thể truyền lên là giá trị của nó cộng với chuỗi nút con không âm tốt hơn. Tổng sử dụng cả hai nút con chỉ cập nhật đáp án toàn cục và không thể truyền tiếp. Các đóng góp âm được loại bỏ bằng cách lấy $\max(0,\cdot)$ trước khi trả về.

<!-- thinking:end -->

Khi suy nghĩ về quy trình kinh điển của các bài toán đệ quy trên cây nhị phân, chúng ta xem xét:

1. Điều kiện kết thúc (khi nào kết thúc đệ quy)
2. Xử lý đệ quy cây con trái và cây con phải
3. Gộp kết quả tính toán của các cây con trái và phải

Đối với bài toán này, chúng ta thiết kế một hàm $dfs(root)$, trả về tổng đường đi lớn nhất của cây nhị phân với $root$ là nút gốc.

Logic thực thi của hàm $dfs(root)$ như sau:

Nếu $root$ không tồn tại, thì $dfs(root)$ trả về $0$;

Nếu không, chúng ta đệ quy tính tổng đường đi lớn nhất của cây con trái và cây con phải của $root$, lần lượt ký hiệu là $left$ và $right$. Nếu $left$ nhỏ hơn $0$, chúng ta đặt nó bằng $0$; tương tự, nếu $right$ nhỏ hơn $0$, chúng ta đặt nó bằng $0$.

Sau đó, chúng ta cập nhật đáp án bằng $root.val + left + right$. Cuối cùng, hàm trả về $root.val + \max(left, right)$.

Trong hàm chính, chúng ta gọi $dfs(root)$ để lấy tổng đường đi lớn nhất tại mỗi nút, và giá trị lớn nhất trong số đó là đáp án.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là số lượng nút trong cây nhị phân.

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
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            left = max(0, dfs(root.left))
            right = max(0, dfs(root.right))
            nonlocal ans
            ans = max(ans, root.val + left + right)
            return root.val + max(left, right)

        ans = -inf
        dfs(root)
        return ans
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
    private int ans = -1001;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        ans = Math.max(ans, root.val + left + right);
        return root.val + Math.max(left, right);
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
    int maxPathSum(TreeNode* root) {
        int ans = -1001;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int left = max(0, dfs(root->left));
            int right = max(0, dfs(root->right));
            ans = max(ans, left + right + root->val);
            return root->val + max(left, right);
        };
        dfs(root);
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
func maxPathSum(root *TreeNode) int {
	ans := -1001
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		left := max(0, dfs(root.Left))
		right := max(0, dfs(root.Right))
		ans = max(ans, left+right+root.Val)
		return max(left, right) + root.Val
	}
	dfs(root)
	return ans
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

function maxPathSum(root: TreeNode | null): number {
    let ans = -1001;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const left = Math.max(0, dfs(root.left));
        const right = Math.max(0, dfs(root.right));
        ans = Math.max(ans, left + right + root.val);
        return Math.max(left, right) + root.val;
    };
    dfs(root);
    return ans;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut i32) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        let left = (0).max(Self::dfs(&node.left, res));
        let right = (0).max(Self::dfs(&node.right, res));
        *res = (node.val + left + right).max(*res);
        node.val + left.max(right)
    }

    pub fn max_path_sum(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut res = -1000;
        Self::dfs(&root, &mut res);
        res
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
var maxPathSum = function (root) {
    let ans = -1001;
    const dfs = root => {
        if (!root) {
            return 0;
        }
        const left = Math.max(0, dfs(root.left));
        const right = Math.max(0, dfs(root.right));
        ans = Math.max(ans, left + right + root.val);
        return Math.max(left, right) + root.val;
    };
    dfs(root);
    return ans;
};
```

#### C#

```cs
/**
 * Definition for a binary tree node.
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
    private int ans = -1001;

    public int MaxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.Max(0, dfs(root.left));
        int right = Math.Max(0, dfs(root.right));
        ans = Math.Max(ans, left + right + root.val);
        return root.val + Math.Max(left, right);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
