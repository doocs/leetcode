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

**方法一：模拟**

我们先创建一个虚拟头节点 `dummy`，令 `dummy.next = head`，然后创建一个指针 `cur` 指向 `dummy`。

遍历链表，当 `cur.next.val == val` 时，将 `cur.next` 指向 `cur.next.next`，然后跳出循环。

最后返回 `dummy.next` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表的长度。

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
        dummy = cur = ListNode(0, head)
        while cur.next:
            if cur.next.val == val:
                cur.next = cur.next.next
                break
            cur = cur.next
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
        for (ListNode cur = dummy; cur.next != null; cur = cur.next) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            }
        }
        return dummy.next;
    }
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
        for (ListNode* cur = dummy; cur->next; cur = cur->next) {
            if (cur->next->val == val) {
                cur->next = cur->next->next;
                break;
            }
        }
        return dummy->next;
    }
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
	for cur := dummy; cur.Next != nil; cur = cur.Next {
		if cur.Next.Val == val {
			cur.Next = cur.Next.Next
			break
		}
	}
	return dummy.Next
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
    for (let cur = dummy; cur.next; cur = cur.next) {
        if (cur.next.val == val) {
            cur.next = cur.next.next;
            break;
        }
    }
    return dummy.next;
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
        ListNode dummy = new ListNode(0, head);
        for (ListNode cur = dummy; cur.next != null; cur = cur.next) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            }
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
