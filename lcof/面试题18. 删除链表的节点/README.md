# [面试题 18. 删除链表的节点](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

## 题目描述

<p>给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。</p>

<p>返回删除后的链表的头节点。</p>

<p><strong>注意：</strong>此题对比原题有改动</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> head = [4,5,1,9], val = 5
<strong>输出:</strong> [4,1,9]
<strong>解释: </strong>给定你链表中值为&nbsp;5&nbsp;的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 1 -&gt; 9.
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> head = [4,5,1,9], val = 1
<strong>输出:</strong> [4,5,9]
<strong>解释: </strong>给定你链表中值为&nbsp;1&nbsp;的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 5 -&gt; 9.
</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong></p>

<ul>
	<li>题目保证链表中节点的值互不相同</li>
	<li>若使用 C 或 C++ 语言，你不需要 <code>free</code> 或 <code>delete</code> 被删除的节点</li>
</ul>

## 解法

定义一个虚拟头节点 `dummy` 指向 `head`，`pre` 指针初始指向 `dummy`。

循环遍历链表，`pre` 往后移动。当指针 `pre.next` 指向的节点的值等于 `val` 时退出循环，将 `pre.next` 指向 `pre.next.next`，然后返回 `dummy.next`。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        pre = dummy = ListNode(next=head)
        while pre.next and pre.next.val != val:
            pre = pre.next
        pre.next = None if not pre.next else pre.next.next
        return dummy.next
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (; pre.next != null && pre.next.val != val; pre = pre.next);
        pre.next = pre.next == null ? null : pre.next.next;
        return dummy.next;
    }
}

```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var deleteNode = function (head, val) {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    for (; pre.next && pre.next.val != val; pre = pre.next);
    pre.next = pre.next?.next;
    return dummy.next;
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNode(head *ListNode, val int) *ListNode {
	dummy := &ListNode{0, head}
	pre := dummy
	for ; pre.Next != nil && pre.Next.Val != val; pre = pre.Next {
	}
	if pre.Next != nil {
		pre.Next = pre.Next.Next
	}
	return dummy.Next
}
```

### **C++**

```cpp
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
    ListNode* deleteNode(ListNode* head, int val) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* pre = dummy;
        for (; pre->next && pre->next->val != val; pre = pre->next)
            ;
        pre->next = pre->next ? pre->next->next : nullptr;
        return dummy->next;
    }
};
```

### **Rust**

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
impl Solution {
    pub fn delete_node(mut head: Option<Box<ListNode>>, val: i32) -> Option<Box<ListNode>> {
        let mut cur = &mut head;
        while let Some(node) = cur {
            if node.val == val {
                *cur = node.next.take();
                break;
            }
            cur = &mut cur.as_mut().unwrap().next;
        }
        head
    }
}
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */

 public class Solution {
     public ListNode DeleteNode(ListNode head, int val) {
         if (head == null) {
             return null;
         }
         if (head.val == val) {
             return head.next;
         }
         ListNode p = head;
         while (p.next != null && p.next.val != val) {
             p = p.next;
         }
         p.next = p.next == null ? null : p.next.next;
         return head;
     }
 }
```

### **...**

```

```

<!-- tabs:end -->
