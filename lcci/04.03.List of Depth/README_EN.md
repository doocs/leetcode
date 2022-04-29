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

Level order traversal.

<!-- tabs:start -->

### **Python3**

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
        q = [tree]
        ans = []
        while q:
            n = len(q)
            head = ListNode(-1)
            tail = head
            for i in range(n):
                front = q.pop(0)
                node = ListNode(front.val)
                tail.next = node
                tail = node
                if front.left:
                    q.append(front.left)
                if front.right:
                    q.append(front.right)
            ans.append(head.next)
        return ans
```

### **Java**

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
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        List<ListNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int n = queue.size();
            ListNode head = new ListNode(-1);
            ListNode tail = head;
            for (int i = 0; i < n; i++) {
                TreeNode front = queue.poll();
                ListNode node = new ListNode(front.val);
                tail.next = node;
                tail = node;
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            ans.add(head.next);
        }
        return ans.toArray(new ListNode[0]);
    }
}
```

### **C++**

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
        if (tree == nullptr) {
            return ans;
        }
        queue<TreeNode*> q;
        q.push(tree);
        while (!q.empty()) {
            int n = q.size();
            ListNode* head = new ListNode(-1);
            ListNode* tail = head;
            for (int i = 0; i < n; ++i) {
                TreeNode* front = q.front();
                q.pop();
                ListNode* node = new ListNode(front->val);
                tail->next = node;
                tail = node;
                if (front->left != nullptr) {
                    q.push(front->left);
                }
                if (front->right != nullptr) {
                    q.push(front->right);
                }
            }
            ans.push_back(head->next);
        }
        return ans;
    }
};
```

### **Go**

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
func listOfDepth(tree *TreeNode) []*ListNode {
	queue := make([]*TreeNode, 0)
	queue = append(queue, tree)
	ans := make([]*ListNode, 0)
	for len(queue) > 0 {
		n := len(queue)
		head := new(ListNode)
		tail := head
		for i := 0; i < n; i++ {
			front := queue[0]
			queue = queue[1:]
			node := &ListNode{Val: front.Val}
			tail.Next = node
			tail = node
			if front.Left != nil {
				queue = append(queue, front.Left)
			}
			if front.Right != nil {
				queue = append(queue, front.Right)
			}
		}
		ans = append(ans, head.Next)
	}
	return ans
}
```

### **TypeScript**

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
    const res = [];
    if (tree == null) {
        return res;
    }
    const queue = [tree];
    while (queue.length !== 0) {
        const n = queue.length;
        const dummy = new ListNode();
        let cur = dummy;
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        res.push(dummy.next);
    }
    return res;
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
