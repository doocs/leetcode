---
comments: true
difficulty: Medium
tags:
    - Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal)

[中文文档](/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai mảng số nguyên <code>inorder</code> và <code>postorder</code>, trong đó <code>inorder</code> là phép duyệt inorder của một cây nhị phân còn <code>postorder</code> là phép duyệt postorder của cùng cây đó, hãy xây dựng và trả về <em>cây nhị phân</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/images/tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
<strong>Đầu ra:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> inorder = [-1], postorder = [-1]
<strong>Đầu ra:</strong> [-1]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= inorder.length &lt;= 3000</code></li>
	<li><code>postorder.length == inorder.length</code></li>
	<li><code>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</code></li>
	<li><code>inorder</code> và <code>postorder</code> gồm các giá trị <strong>duy nhất</strong>.</li>
	<li>Mỗi giá trị của <code>postorder</code> cũng xuất hiện trong <code>inorder</code>.</li>
	<li><code>inorder</code> được <strong>đảm bảo</strong> là phép duyệt inorder của cây.</li>
	<li><code>postorder</code> được <strong>đảm bảo</strong> là phép duyệt postorder của cây.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm + Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Giá trị cuối cùng trong postorder là gốc; inorder một lần nữa chia cây thành cây con trái và phải. Việc tìm kiếm tuyến tính gốc ở mỗi lần có thể có độ phức tạp $O(n^2)$. Các giá trị là duy nhất, nên trước tiên chúng ta lập chỉ mục cho inorder. Giá trị cuối cùng của đoạn postorder hiện tại là gốc; số lượng giá trị inorder ở bên trái nó là kích thước cây con trái, từ đó chia phần postorder còn lại thành hai phạm vi đệ quy.

<!-- thinking:end -->

Nút cuối cùng trong phép duyệt postorder là nút gốc. Chúng ta có thể tìm vị trí của nút gốc trong phép duyệt inorder, sau đó đệ quy xây dựng cây con trái và cây con phải.

Cụ thể, trước tiên chúng ta dùng một bảng băm $d$ để lưu vị trí của mỗi nút trong phép duyệt inorder. Sau đó, chúng ta thiết kế một hàm đệ quy $dfs(i, j, n)$, trong đó $i$ và $j$ lần lượt biểu thị vị trí bắt đầu của phép duyệt inorder và postorder, còn $n$ biểu thị số nút trong cây con. Logic của hàm như sau:

- Nếu $n \leq 0$, điều đó có nghĩa là cây con rỗng, trả về một nút null.
- Nếu không, lấy nút cuối cùng $v$ của phép duyệt postorder, sau đó dùng bảng băm $d$ để tìm vị trí $k$ của $v$ trong phép duyệt inorder. Khi đó, số nút trong cây con trái là $k - i$, còn số nút trong cây con phải là $n - k + i - 1$.
- Đệ quy xây dựng cây con trái $dfs(i, j, k - i)$ và cây con phải $dfs(k + 1, j + k - i, n - k + i - 1)$, nối chúng với nút gốc, rồi cuối cùng trả về nút gốc.

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
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        def dfs(i: int, j: int, n: int) -> Optional[TreeNode]:
            if n <= 0:
                return None
            v = postorder[j + n - 1]
            k = d[v]
            l = dfs(i, j, k - i)
            r = dfs(k + 1, j + k - i, n - k + i - 1)
            return TreeNode(v, l, r)

        d = {v: i for i, v in enumerate(inorder)}
        return dfs(0, 0, len(inorder))
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
    private Map<Integer, Integer> d = new HashMap<>();
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = postorder[j + n - 1];
        int k = d.get(v);
        TreeNode l = dfs(i, j, k - i);
        TreeNode r = dfs(k + 1, j + k - i, n - k + i - 1);
        return new TreeNode(v, l, r);
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
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        unordered_map<int, int> d;
        int n = inorder.size();
        for (int i = 0; i < n; ++i) {
            d[inorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n <= 0) {
                return nullptr;
            }
            int v = postorder[j + n - 1];
            int k = d[v];
            auto l = dfs(i, j, k - i);
            auto r = dfs(k + 1, j + k - i, n - k + i - 1);
            return new TreeNode(v, l, r);
        };
        return dfs(0, 0, n);
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
func buildTree(inorder []int, postorder []int) *TreeNode {
	d := map[int]int{}
	for i, v := range inorder {
		d[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := postorder[j+n-1]
		k := d[v]
		l := dfs(i, j, k-i)
		r := dfs(k+1, j+k-i, n-k+i-1)
		return &TreeNode{v, l, r}
	}
	return dfs(0, 0, len(inorder))
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

function buildTree(inorder: number[], postorder: number[]): TreeNode | null {
    const n = inorder.length;
    const d: Record<number, number> = {};
    for (let i = 0; i < n; i++) {
        d[inorder[i]] = i;
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const v = postorder[j + n - 1];
        const k = d[v];
        const l = dfs(i, j, k - i);
        const r = dfs(k + 1, j + k - i, n - 1 - (k - i));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
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
use std::collections::HashMap;
use std::rc::Rc;
impl Solution {
    pub fn build_tree(inorder: Vec<i32>, postorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = inorder.len();
        let mut d: HashMap<i32, usize> = HashMap::new();
        for i in 0..n {
            d.insert(inorder[i], i);
        }
        fn dfs(
            postorder: &[i32],
            d: &HashMap<i32, usize>,
            i: usize,
            j: usize,
            n: usize,
        ) -> Option<Rc<RefCell<TreeNode>>> {
            if n <= 0 {
                return None;
            }
            let val = postorder[j + n - 1];
            let k = *d.get(&val).unwrap();
            let left = dfs(postorder, d, i, j, k - i);
            let right = dfs(postorder, d, k + 1, j + k - i, n - 1 - (k - i));
            Some(Rc::new(RefCell::new(TreeNode { val, left, right })))
        }
        dfs(&postorder, &d, 0, 0, n)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
