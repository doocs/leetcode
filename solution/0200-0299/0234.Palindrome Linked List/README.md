# [234. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list)

[English Version](/solution/0200-0299/0234.Palindrome%20Linked%20List/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>请判断一个链表是否为回文链表。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2
<strong>输出:</strong> false</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;2-&gt;1
<strong>输出:</strong> true
</pre>

<p><strong>进阶：</strong><br>
你能否用&nbsp;O(n) 时间复杂度和 O(1) 空间复杂度解决此题？</p>



## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if not head:
            return True
        mid = self.find_mid_node(head)
        second_half_list = self.reverse_list(mid.next)
        result = True
        p, q = head, second_half_list
        while result and q:
            if p.val != q.val:
                result = False
            else:
                p, q = p.next, q.next
        mid.next = self.reverse_list(second_half_list)
        return result
        
    def reverse_list(self, head):
        pre, p = None, head
        while p:
            q = p.next
            p.next = pre
            pre = p
            p = q
        return pre
    
    def find_mid_node(self, head):
        slow = fast = head
        while fast.next and fast.next.next:
            slow, fast = slow.next, fast.next.next
        return slow

```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode mid = findMidNode(head);
        ListNode secondHalfList = reverseList(mid.next);
        boolean result = true;
        ListNode p = head, q = secondHalfList;
        while (result && q != null) {
            if (p.val != q.val) {
                result = false;
            } else {
                p = p.next;
                q = q.next;
            }
        }
        mid.next = reverseList(secondHalfList);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }

    private ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```

### **...**
```

```

<!-- tabs:end -->