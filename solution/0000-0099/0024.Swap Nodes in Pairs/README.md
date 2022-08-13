# [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs)

[English Version](/solution/0000-0099/0024.Swap%20Nodes%20in%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0024.Swap%20Nodes%20in%20Pairs/images/swap_ex1.jpg" style="width: 422px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4]
<strong>输出：</strong>[2,1,4,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [1]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目在范围 <code>[0, 100]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

设置虚拟头节点 dummy，pre 指针初始指向 dummy，遍历链表，每次交换 pre 后面的两个节点即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        dummy = ListNode(next=head)
        pre, cur = dummy, head
        while cur and cur.next:
            t = cur.next
            cur.next = t.next
            t.next = cur
            pre.next = t
            pre, cur = cur, cur.next
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = head;
        while (cur != null && cur.next != null) {
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = cur;
            pre.next = t;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var swapPairs = function (head) {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    let cur = head;
    while (cur && cur.next) {
        const t = cur.next;
        cur.next = t.next;
        t.next = cur;
        pre.next = t;
        pre = cur;
        cur = cur.next;
    }
    return dummy.next;
};
```

### **C++**

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
class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode *pre = dummy, *cur = head;
        while (cur != nullptr && cur->next != nullptr) {
            ListNode* t = cur->next;
            cur->next = t->next;
            t->next = cur;
            pre->next = t;
            pre = cur;
            cur = cur->next;
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
func swapPairs(head *ListNode) *ListNode {
    dummy := &ListNode{0, head}
    pre, cur := dummy, head
    for cur != nil && cur.Next != nil {
        t := cur.Next
        cur.Next = t.Next
        t.Next = cur
        pre.Next = t
        pre = cur
        cur = cur.Next
    }
    return dummy.Next
}
```

### **Ruby**

```rb
# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} head
# @return {ListNode}
def swap_pairs(head)
    dummy = ListNode.new(0, head)
    pre = dummy
    cur = head
    while !cur.nil? && !cur.next.nil?
        t = cur.next
        cur.next = t.next
        t.next = cur
        pre.next = t
        pre = cur
        cur = cur.next
    end
    dummy.next
end
```

### **TypeScript**

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

function swapPairs(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let cur = dummy;
    while (cur.next != null && cur.next.next != null) {
        const a = cur.next;
        const b = cur.next.next;
        [a.next, b.next, cur.next] = [b.next, a, b];
        cur = cur.next.next;
    }
    return dummy.next;
}
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
    pub fn swap_pairs(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut cur = dummy.as_mut().unwrap();
        while cur.next.is_some() && cur.next.as_ref().unwrap().next.is_some() {
            cur.next = {
                let mut b = cur.next.as_mut().unwrap().next.take();
                cur.next.as_mut().unwrap().next = b.as_mut().unwrap().next.take();
                let a = cur.next.take();
                b.as_mut().unwrap().next = a;
                b
            };
            cur = cur.next.as_mut().unwrap().next.as_mut().unwrap();
        }
        dummy.unwrap().next
    }
}
```

### **...**

```

```

<!-- tabs:end -->
