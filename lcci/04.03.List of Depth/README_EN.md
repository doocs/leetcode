# [04.03. List of Depth](https://leetcode.cn/problems/list-of-depth-lcci)

[中文文档](/lcci/04.03.List%20of%20Depth/README.md)

## Description

<p>Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you&#39;ll have D linked lists). Return a array containing all the linked lists.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>[1,2,3,4,5,null,7,8]



        1

       /  \

      2    3

     / \    \

    4   5    7

   /

  8



<strong>Output: </strong>[[1],[2,3],[4,5,7],[8]]

</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def listOfDepth(self, tree: TreeNode) -> List[ListNode]:
        ans = []
        q = deque([tree])
        while q:
            dummy = cur = ListNode(0)
            for _ in range(len(q)):
                node = q.popleft()
                cur.next = ListNode(node.val)
                cur = cur.next
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            ans.append(dummy.next)
        return ans
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(tree);
        while (!q.isEmpty()) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            for (int k = q.size(); k > 0; --k) {
                TreeNode node = q.poll();
                cur.next = new ListNode(node.val);
                cur = cur.next;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(dummy.next);
        }
        return ans.toArray(new ListNode[0]);
    }
}
```

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    vector<ListNode*> listOfDepth(TreeNode* tree) {
        vector<ListNode*> ans;
        queue<TreeNode*> q{{tree}};
        while (!q.empty()) {
            ListNode* dummy = new ListNode(0);
            ListNode* cur = dummy;
            for (int k = q.size(); k; --k) {
                TreeNode* node = q.front();
                q.pop();
                cur->next = new ListNode(node->val);
                cur = cur->next;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            ans.push_back(dummy->next);
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func listOfDepth(tree *TreeNode) (ans []*ListNode) {
	q := []*TreeNode{tree}
	for len(q) > 0 {
		dummy := &ListNode{}
		cur := dummy
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			cur.Next = &ListNode{Val: node.Val}
			cur = cur.Next
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, dummy.Next)
	}
	return
}
```

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

function listOfDepth(tree: TreeNode | null): Array<ListNode | null> {
    const ans: Array<ListNode | null> = [];
    const q: Array<TreeNode | null> = [tree];
    while (q.length) {
        const dummy = new ListNode();
        let cur = dummy;
        for (let k = q.length; k; --k) {
            const { val, left, right } = q.shift()!;
            cur.next = new ListNode(val);
            cur = cur.next;
            left && q.push(left);
            right && q.push(right);
        }
        ans.push(dummy.next);
    }
    return ans;
}
```

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
use std::rc::Rc;
use std::cell::RefCell;
use std::collections::VecDeque;
impl Solution {
    pub fn list_of_depth(tree: Option<Rc<RefCell<TreeNode>>>) -> Vec<Option<Box<ListNode>>> {
        let mut res = vec![];
        if tree.is_none() {
            return res;
        }
        let mut q = VecDeque::new();
        q.push_back(tree);
        while !q.is_empty() {
            let n = q.len();
            let mut demmy = Some(Box::new(ListNode::new(0)));
            let mut cur = &mut demmy;
            for _ in 0..n {
                if let Some(node) = q.pop_front().unwrap() {
                    let mut node = node.borrow_mut();
                    if node.left.is_some() {
                        q.push_back(node.left.take());
                    }
                    if node.right.is_some() {
                        q.push_back(node.right.take());
                    }
                    cur.as_mut().unwrap().next = Some(Box::new(ListNode::new(node.val)));
                    cur = &mut cur.as_mut().unwrap().next;
                }
            }
            res.push(demmy.as_mut().unwrap().next.take());
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- end -->
