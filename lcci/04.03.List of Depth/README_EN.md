# [04.03. List of Depth](https://leetcode-cn.com/problems/list-of-depth-lcci)

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

### **Go**

```go
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

### **...**

```

```

<!-- tabs:end -->
