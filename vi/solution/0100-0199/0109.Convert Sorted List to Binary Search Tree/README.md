---
comments: true
difficulty: Medium
tags:
    - Tree
    - Binary Search Tree
    - Linked List
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [109. Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree)

[中文文档](/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>head</code> của một danh sách liên kết đơn có các phần tử được sắp xếp theo thứ tự <strong>tăng dần</strong>, hãy chuyển đổi <em>nó thành một </em><span data-keyword="height-balanced"><strong><em>cây tìm kiếm nhị phân cân bằng chiều cao</em></strong></span><em></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/images/linked.jpg" style="width: 500px; height: 388px;" />
<pre>
<strong>Đầu vào:</strong> head = [-10,-3,0,5,9]
<strong>Đầu ra:</strong> [0,-3,9,-10,null,5]
<strong>Giải thích:</strong> Một đáp án có thể là [0,-3,9,-10,null,5], biểu diễn cây BST cân bằng chiều cao được hiển thị.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> head = []
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong <code>head</code> nằm trong khoảng <code>[0, 2 * 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Một danh sách không thể truy cập phần tử giữa trong $O(1)$. Việc tìm nó bằng hai con trỏ ở mỗi bước đệ quy tốn $O(n\log n)$. Có tối đa $2\times 10^4$ nút.
>
> Một danh sách đã sắp xếp vẫn là một dãy inorder. Chuyển nó thành một mảng, sau đó xây dựng một BST cân bằng như với một mảng đã sắp xếp: chọn phần tử giữa làm gốc, đệ quy trên hai phía. Đổi lại, $O(n)$ không gian bổ sung cho phép truy cập phần tử giữa trong $O(1)$.

<!-- thinking:end -->

Trước tiên, chúng ta chuyển đổi danh sách liên kết thành một mảng $\textit{nums}$, sau đó sử dụng duyệt theo chiều sâu để xây dựng cây tìm kiếm nhị phân.

Chúng ta định nghĩa một hàm $\textit{dfs}(i, j)$, trong đó $i$ và $j$ biểu diễn khoảng hiện tại $[i, j]$. Mỗi lần, chúng ta chọn số ở vị trí giữa $\textit{mid}$ của khoảng làm nút gốc, đệ quy xây dựng cây con trái cho khoảng $[i, \textit{mid} - 1]$, và cây con phải cho khoảng $[\textit{mid} + 1, j]$. Cuối cùng, chúng ta trả về nút tương ứng với $\textit{mid}$ làm nút gốc của cây con hiện tại.

Trong hàm chính, chúng ta chỉ cần gọi $\textit{dfs}(0, n - 1)$ và trả về kết quả.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là độ dài của danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        def dfs(i: int, j: int) -> Optional[TreeNode]:
            if i > j:
                return None
            mid = (i + j) >> 1
            l, r = dfs(i, mid - 1), dfs(mid + 1, j)
            return TreeNode(nums[mid], l, r)

        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        return dfs(0, len(nums) - 1)
```

#### Java

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    private List<Integer> nums = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        return dfs(0, nums.size() - 1);
    }

    private TreeNode dfs(int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode left = dfs(i, mid - 1);
        TreeNode right = dfs(mid + 1, j);
        return new TreeNode(nums.get(mid), left, right);
    }
}
```

#### C++

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
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
    TreeNode* sortedListToBST(ListNode* head) {
        vector<int> nums;
        for (; head; head = head->next) {
            nums.push_back(head->val);
        }
        auto dfs = [&](this auto&& dfs, int i, int j) -> TreeNode* {
            if (i > j) {
                return nullptr;
            }
            int mid = (i + j) >> 1;
            TreeNode* left = dfs(i, mid - 1);
            TreeNode* right = dfs(mid + 1, j);
            return new TreeNode(nums[mid], left, right);
        };
        return dfs(0, nums.size() - 1);
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sortedListToBST(head *ListNode) *TreeNode {
	nums := []int{}
	for ; head != nil; head = head.Next {
		nums = append(nums, head.Val)
	}
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		mid := (i + j) >> 1
		left := dfs(i, mid-1)
		right := dfs(mid+1, j)
		return &TreeNode{nums[mid], left, right}
	}
	return dfs(0, len(nums)-1)
}
```

#### TypeScript

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

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

function sortedListToBST(head: ListNode | null): TreeNode | null {
    const nums: number[] = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const dfs = (i: number, j: number): TreeNode | null => {
        if (i > j) {
            return null;
        }
        const mid = (i + j) >> 1;
        const left = dfs(i, mid - 1);
        const right = dfs(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    return dfs(0, nums.length - 1);
}
```

#### Rust

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
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
    pub fn sorted_list_to_bst(head: Option<Box<ListNode>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut nums = Vec::new();
        let mut current = head;
        while let Some(node) = current {
            nums.push(node.val);
            current = node.next;
        }

        fn dfs(nums: &[i32]) -> Option<Rc<RefCell<TreeNode>>> {
            if nums.is_empty() {
                return None;
            }
            let mid = nums.len() / 2;
            Some(Rc::new(RefCell::new(TreeNode {
                val: nums[mid],
                left: dfs(&nums[..mid]),
                right: dfs(&nums[mid + 1..]),
            })))
        }

        dfs(&nums)
    }
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {ListNode} head
 * @return {TreeNode}
 */
var sortedListToBST = function (head) {
    const nums = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const dfs = (i, j) => {
        if (i > j) {
            return null;
        }
        const mid = (i + j) >> 1;
        const left = dfs(i, mid - 1);
        const right = dfs(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    return dfs(0, nums.length - 1);
};
```

#### C

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* dfs(int* nums, int i, int j) {
    if (i > j) {
        return NULL;
    }
    int mid = (i + j) >> 1;
    struct TreeNode* left = dfs(nums, i, mid - 1);
    struct TreeNode* right = dfs(nums, mid + 1, j);
    struct TreeNode* root = (struct TreeNode*) malloc(sizeof(struct TreeNode));
    root->val = nums[mid];
    root->left = left;
    root->right = right;
    return root;
}

struct TreeNode* sortedListToBST(struct ListNode* head) {
    int size = 0;
    struct ListNode* temp = head;
    while (temp) {
        size++;
        temp = temp->next;
    }

    int* nums = (int*) malloc(size * sizeof(int));
    temp = head;
    for (int i = 0; i < size; i++) {
        nums[i] = temp->val;
        temp = temp->next;
    }

    struct TreeNode* root = dfs(nums, 0, size - 1);
    free(nums);
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
