---
comments: true
difficulty: Easy
tags:
    - Tree
    - Binary Search Tree
    - Array
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [108. Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree)

[中文文档](/solution/0100-0199/0108.Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> có các phần tử được sắp xếp theo <strong>thứ tự tăng dần</strong>, hãy chuyển <em>nó thành một </em><span data-keyword="height-balanced"><strong><em>cân bằng chiều cao</em></strong></span> <em>cây tìm kiếm nhị phân</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0108.Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree/images/btree1.jpg" style="width: 302px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> nums = [-10,-3,0,5,9]
<strong>Đầu ra:</strong> [0,-3,9,-10,null,5]
<strong>Giải thích:</strong> [0,-10,5,null,-3,null,9] cũng được chấp nhận:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0108.Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree/images/btree2.jpg" style="width: 302px; height: 222px;" />
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0108.Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree/images/btree.jpg" style="width: 342px; height: 142px;" />
<pre>
<strong>Đầu vào:</strong> nums = [1,3]
<strong>Đầu ra:</strong> [3,1]
<strong>Giải thích:</strong> [1,null,3] và [3,1] đều là các BST cân bằng chiều cao.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> được sắp xếp theo <strong>thứ tự tăng nghiêm ngặt</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân + Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Một mảng đã sắp xếp vốn là dãy inorder của một BST. Nếu luôn chọn giá trị ngoài cùng bên trái hoặc bên phải làm gốc, ta sẽ tạo thành một chuỗi có chiều cao $n$, không cân bằng theo chiều cao. $n \le 10^4$.
>
> Trung điểm của một khoảng khiến hai phía chênh nhau nhiều nhất một phần tử, nhờ đó chiều cao vẫn cân bằng. Chọn mid của $[l,r]$ làm gốc rồi đệ quy trên hai nửa.

<!-- thinking:end -->

Chúng ta thiết kế một hàm $\textit{dfs}(l, r)$, biểu thị rằng các giá trị của những nút cần xây dựng trong cây tìm kiếm nhị phân hiện tại nằm trong khoảng chỉ số $[l, r]$ của mảng $\textit{nums}$. Hàm này trả về nút gốc của cây tìm kiếm nhị phân được xây dựng.

Quá trình thực thi hàm $\textit{dfs}(l, r)$ như sau:

1. Nếu $l > r$, điều đó có nghĩa là mảng hiện tại rỗng, nên trả về `null`.
2. Nếu $l \leq r$, lấy phần tử tại chỉ số $\textit{mid} = \lfloor \frac{l + r}{2} \rfloor$ của mảng làm nút gốc của cây tìm kiếm nhị phân hiện tại, trong đó $\lfloor x \rfloor$ biểu thị hàm floor của $x$.
3. Đệ quy xây dựng cây con bên trái của cây tìm kiếm nhị phân hiện tại, với giá trị của nút gốc là phần tử tại chỉ số $\textit{mid} - 1$ của mảng. Các giá trị của những nút trong cây con bên trái nằm trong khoảng chỉ số $[l, \textit{mid} - 1]$ của mảng.
4. Đệ quy xây dựng cây con bên phải của cây tìm kiếm nhị phân hiện tại, với giá trị của nút gốc là phần tử tại chỉ số $\textit{mid} + 1$ của mảng. Các giá trị của những nút trong cây con bên phải nằm trong khoảng chỉ số $[\textit{mid} + 1, r]$ của mảng.
5. Trả về nút gốc của cây tìm kiếm nhị phân hiện tại.

Đáp án là giá trị trả về của hàm $\textit{dfs}(0, n - 1)$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(\log n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

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
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        def dfs(l: int, r: int) -> Optional[TreeNode]:
            if l > r:
                return None
            mid = (l + r) >> 1
            return TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r))

        return dfs(0, len(nums) - 1)
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
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        auto dfs = [&](this auto&& dfs, int l, int r) -> TreeNode* {
            if (l > r) {
                return nullptr;
            }
            int mid = (l + r) >> 1;
            return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
        };
        return dfs(0, nums.size() - 1);
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
func sortedArrayToBST(nums []int) *TreeNode {
	var dfs func(int, int) *TreeNode
	dfs = func(l, r int) *TreeNode {
		if l > r {
			return nil
		}
		mid := (l + r) >> 1
		return &TreeNode{nums[mid], dfs(l, mid-1), dfs(mid+1, r)}
	}
	return dfs(0, len(nums)-1)
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

function sortedArrayToBST(nums: number[]): TreeNode | null {
    const dfs = (l: number, r: number): TreeNode | null => {
        if (l > r) {
            return null;
        }
        const mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    };
    return dfs(0, nums.length - 1);
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
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    pub fn sorted_array_to_bst(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        fn dfs(nums: &Vec<i32>, l: usize, r: usize) -> Option<Rc<RefCell<TreeNode>>> {
            if l > r {
                return None;
            }
            let mid = (l + r) / 2;
            if mid >= nums.len() {
                return None;
            }
            let mut node = Rc::new(RefCell::new(TreeNode::new(nums[mid])));
            node.borrow_mut().left = dfs(nums, l, mid - 1);
            node.borrow_mut().right = dfs(nums, mid + 1, r);
            Some(node)
        }
        dfs(&nums, 0, nums.len() - 1)
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
 * @param {number[]} nums
 * @return {TreeNode}
 */
var sortedArrayToBST = function (nums) {
    const dfs = (l, r) => {
        if (l > r) {
            return null;
        }
        const mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    };
    return dfs(0, nums.length - 1);
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
    private int[] nums;

    public TreeNode SortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.Length - 1);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
