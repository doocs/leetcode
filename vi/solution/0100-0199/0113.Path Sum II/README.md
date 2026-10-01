---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Backtracking
    - Binary Tree
---

<!-- problem:start -->

# [113. Path Sum II](https://leetcode.com/problems/path-sum-ii)

[中文文档](/solution/0100-0199/0113.Path%20Sum%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>root</code> của một cây nhị phân và một số nguyên <code>targetSum</code>, hãy trả về <em>tất cả các đường đi <strong>từ gốc đến lá</strong> mà tổng giá trị của các nút trên đường đi bằng </em><code>targetSum</code><em>. Mỗi đường đi phải được trả về dưới dạng danh sách các <strong>giá trị</strong> của nút, không phải tham chiếu đến nút</em>.</p>

<p>Đường đi <strong>từ gốc đến lá</strong> là đường đi bắt đầu từ gốc và kết thúc tại bất kỳ nút lá nào. <strong>Nút lá</strong> là nút không có nút con.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsumii1.jpg" style="width: 500px; height: 356px;" />
<pre>
<strong>Đầu vào:</strong> root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>Đầu ra:</strong> [[5,4,11,2],[5,8,4,5]]
<strong>Giải thích:</strong> Có hai đường đi có tổng bằng targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsum2.jpg" style="width: 212px; height: 181px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3], targetSum = 5
<strong>Đầu ra:</strong> []
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [1,2], targetSum = 0
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 5000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Khác với Path Sum, chúng ta phải liệt kê mọi đường đi từ gốc đến lá có tổng bằng $\textit{targetSum}$. $n \le 5000$; việc sao chép mọi đường đi vào đáp án có thể tốn $O(n^2)$.
>
> DFS thêm nút vào một đường đi dùng chung và cộng giá trị của nó. Khi gặp một nút lá khớp, chúng ta sao chép đường đi; lúc quay lui thì lấy nút ra, nên cả hai nút con đều dùng lại cùng một bộ đệm.

<!-- thinking:end -->

Bắt đầu từ nút gốc, chúng ta đệ quy duyệt tất cả các đường đi từ nút gốc đến các nút lá và ghi lại tổng đường đi. Khi duyệt đến một nút lá, nếu tổng đường đi hiện tại bằng `targetSum`, chúng ta thêm đường đi này vào đáp án.

Độ phức tạp thời gian là $O(n^2)$, trong đó $n$ là số nút trong cây nhị phân. Độ phức tạp không gian là $O(n)$.

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
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        def dfs(root, s):
            if root is None:
                return
            s += root.val
            t.append(root.val)
            if root.left is None and root.right is None and s == targetSum:
                ans.append(t[:])
            dfs(root.left, s)
            dfs(root.right, s)
            t.pop()

        ans = []
        t = []
        dfs(root, 0)
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
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ans;
    }

    private void dfs(TreeNode root, int s) {
        if (root == null) {
            return;
        }
        s -= root.val;
        t.add(root.val);
        if (root.left == null && root.right == null && s == 0) {
            ans.add(new ArrayList<>(t));
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.remove(t.size() - 1);
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
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int s) {
            if (!root) return;
            s -= root->val;
            t.emplace_back(root->val);
            if (!root->left && !root->right && s == 0) ans.emplace_back(t);
            dfs(root->left, s);
            dfs(root->right, s);
            t.pop_back();
        };
        dfs(root, targetSum);
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
func pathSum(root *TreeNode, targetSum int) (ans [][]int) {
	t := []int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, s int) {
		if root == nil {
			return
		}
		s -= root.Val
		t = append(t, root.Val)
		if root.Left == nil && root.Right == nil && s == 0 {
			ans = append(ans, slices.Clone(t))
		}
		dfs(root.Left, s)
		dfs(root.Right, s)
		t = t[:len(t)-1]
	}
	dfs(root, targetSum)
	return
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
    fn dfs(
        root: Option<Rc<RefCell<TreeNode>>>,
        paths: &mut Vec<i32>,
        mut target_sum: i32,
        res: &mut Vec<Vec<i32>>,
    ) {
        if let Some(node) = root {
            let mut node = node.borrow_mut();
            target_sum -= node.val;
            paths.push(node.val);
            if node.left.is_none() && node.right.is_none() {
                if target_sum == 0 {
                    res.push(paths.clone());
                }
            } else {
                if node.left.is_some() {
                    Self::dfs(node.left.take(), paths, target_sum, res);
                }
                if node.right.is_some() {
                    Self::dfs(node.right.take(), paths, target_sum, res);
                }
            }
            paths.pop();
        }
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target_sum: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        let mut paths = vec![];
        Self::dfs(root, &mut paths, target_sum, &mut res);
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
 * @param {number} targetSum
 * @return {number[][]}
 */
var pathSum = function (root, targetSum) {
    const ans = [];
    const t = [];
    function dfs(root, s) {
        if (!root) return;
        s -= root.val;
        t.push(root.val);
        if (!root.left && !root.right && s == 0) ans.push([...t]);
        dfs(root.left, s);
        dfs(root.right, s);
        t.pop();
    }
    dfs(root, targetSum);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
