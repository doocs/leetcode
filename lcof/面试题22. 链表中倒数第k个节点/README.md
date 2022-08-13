# [面试题 22. 链表中倒数第 k 个节点](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

## 题目描述

<p>输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。</p>

<p>例如，一个链表有 <code>6</code> 个节点，从头节点开始，它们的值依次是 <code>1、2、3、4、5、6</code>。这个链表的倒数第 <code>3</code> 个节点是值为 <code>4</code> 的节点。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
给定一个链表: <strong>1->2->3->4->5</strong>, 和 <em>k </em><strong>= 2</strong>.

返回链表 4<strong>->5</strong>.</pre>

## 解法

定义快慢指针 `slow`、`fast`，初始指向 `head`。

`fast` 先向前走 `k` 步，接着 `slow`、`fast` 同时向前走，当 `fast` 指向 `null` 时，`slow` 指向的节点即为链表的倒数第 `k` 个节点。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def getKthFromEnd(self, head: ListNode, k: int) -> ListNode:
        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast:
            slow = slow.next
            fast = fast.next
        return slow
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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```

### **JavaScript**

```js
/**
 * @param {ListNode} head
 * @param {number} k
 * @return {ListNode}
 */
var getKthFromEnd = function (head, k) {
    // 递归
    // let cnt = 1
    // function func(node) {
    //     if(!node || !node.next) return node
    //     let newNode = func(node.next)
    //     if(cnt === k) return newNode
    //     else cnt++
    //     return node
    // }
    // return func(head)

    // 快慢指针
    let slow = head;
    let fast = head;
    while (k) {
        fast = fast.next;
        k--;
    }
    while (fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
};
```

### **Go**

```go
func getKthFromEnd(head *ListNode, k int) *ListNode {
    tmp := head
    for tmp != nil && k > 0{
        tmp = tmp.Next
        k--
    }
    slow := head
    fast := tmp
    for fast != nil {
        fast = fast.Next
        slow = slow.Next
    }
    return slow
}
```

### **C++**

```cpp
class Solution {
public:
    ListNode* getKthFromEnd(ListNode* head, int k) {
        ListNode *slow = head, *fast = head;
        while (k--) {
            fast = fast->next;
        }
        while (fast) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
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
    pub fn get_kth_from_end(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        let mut fast = &head;
        for _ in 0..k {
            fast = &fast.as_ref().unwrap().next;
        }
        let mut slow = &head;
        while let (Some(nf), Some(ns)) = (fast, slow) {
            fast = &nf.next;
            slow = &ns.next;
        }
        slow.to_owned()
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
    public ListNode GetKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next;
            k -= 1;
            if (k < 0) {
                slow = slow.next;
            }
        }
        return slow;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
