# [面试题 22. 链表中倒数第 k 个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

## 题目描述

输入一个链表，输出该链表中倒数第 k 个节点。为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾节点是倒数第 1 个节点。例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

**示例：**

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

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

### **...**

```

```

<!-- tabs:end -->
