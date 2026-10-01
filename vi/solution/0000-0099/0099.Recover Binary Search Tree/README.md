---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Binary Search Tree
    - Binary Tree
---

<!-- problem:start -->

# [99. Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree)

[中文文档](/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp <code>root</code> của một cây tìm kiếm nhị phân (BST), trong đó giá trị của <strong>chính xác</strong> hai nút trong cây đã bị hoán đổi do nhầm lẫn. <em>Khôi phục cây mà không thay đổi cấu trúc của nó</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/images/recover1.jpg" style="width: 422px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,3,null,null,2]
<strong>Đầu ra:</strong> [3,1,null,null,2]
<strong>Giải thích:</strong> 3 không thể là con trái của 1 vì 3 &gt; 1. Hoán đổi 1 và 3 sẽ làm cho BST hợp lệ.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/images/recover2.jpg" style="width: 581px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> root = [3,1,4,null,null,2]
<strong>Đầu ra:</strong> [2,1,4,null,null,3]
<strong>Giải thích:</strong> 2 không thể nằm trong cây con phải của 3 vì 2 &lt; 3. Hoán đổi 2 và 3 sẽ làm cho BST hợp lệ.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[2, 1000]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Lời giải sử dụng không gian <code>O(n)</code> khá đơn giản. Bạn có thể xây dựng lời giải sử dụng không gian hằng số <code>O(1)</code> không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt trung thứ tự

<!-- thinking:start -->

> **Tư duy**
>
> Duyệt trung thứ tự của BST là một dãy tăng nghiêm ngặt. Sau khi hoán đổi hai nút, dãy có một hoặc hai nghịch thế: hoán đổi hai nút liền kề tạo ra một nghịch thế; hoán đổi hai nút không liền kề tạo ra hai nghịch thế (nút sớm hơn của nghịch thế thứ nhất và nút muộn hơn của nghịch thế thứ hai).
>
> Duyệt trung thứ tự, ghi lại hai nút đó và hoán đổi giá trị của chúng. Không cần xây dựng lại cây. Lời giải này sử dụng phép duyệt trung thứ tự đệ quy (ngăn xếp $O(n)$); Morris sẽ đáp ứng câu hỏi mở rộng $O(1)$. Mục đích của phương pháp này là xác định cặp nút đã bị hoán đổi.

<!-- thinking:end -->

Duyệt trung thứ tự của cây tìm kiếm nhị phân cho kết quả là một dãy tăng dần. Nếu giá trị của hai nút bị hoán đổi do nhầm lẫn, chắc chắn sẽ có hai cặp đảo ngược trong dãy thu được từ phép duyệt trung thứ tự. Chúng ta dùng `first` và `second` để ghi lại các giá trị nhỏ hơn và lớn hơn của hai cặp đảo ngược này, tương ứng. Cuối cùng, hoán đổi giá trị của hai nút này sẽ sửa lỗi.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây tìm kiếm nhị phân.

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
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """

        def dfs(root):
            if root is None:
                return
            nonlocal prev, first, second
            dfs(root.left)
            if prev and prev.val > root.val:
                if first is None:
                    first = prev
                second = root
            prev = root
            dfs(root.right)

        prev = first = second = None
        dfs(root)
        first.val, second.val = second.val, first.val
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
    private TreeNode prev;
    private TreeNode first;
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        dfs(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        dfs(root.right);
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
    void recoverTree(TreeNode* root) {
        TreeNode* prev = nullptr;
        TreeNode* first = nullptr;
        TreeNode* second = nullptr;
        function<void(TreeNode * root)> dfs = [&](TreeNode* root) {
            if (!root) return;
            dfs(root->left);
            if (prev && prev->val > root->val) {
                if (!first) first = prev;
                second = root;
            }
            prev = root;
            dfs(root->right);
        };
        dfs(root);
        swap(first->val, second->val);
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
func recoverTree(root *TreeNode) {
	var prev, first, second *TreeNode
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev != nil && prev.Val > root.Val {
			if first == nil {
				first = prev
			}
			second = root
		}
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	first.Val, second.Val = second.Val, first.Val
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
 * @return {void} Do not return anything, modify root in-place instead.
 */
var recoverTree = function (root) {
    let prev = null;
    let first = null;
    let second = null;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.left);
        if (prev && prev.val > root.val) {
            if (!first) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        dfs(root.right);
    }
    dfs(root);
    const t = first.val;
    first.val = second.val;
    second.val = t;
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
    private TreeNode prev, first, second;

    public void RecoverTree(TreeNode root) {
        dfs(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        dfs(root.right);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
