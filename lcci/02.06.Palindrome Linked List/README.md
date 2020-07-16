# [面试题 02.06. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list-lcci)

[English Version](/lcci/02.06.Palindrome%20Linked%20List/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>编写一个函数，检查输入的链表是否是回文的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入： </strong>1-&gt;2
<strong>输出：</strong> false 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入： </strong>1-&gt;2-&gt;2-&gt;1
<strong>输出：</strong> true 
</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong><br>
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？</p>


## 解法
<!-- 这里可写通用的实现逻辑 -->
先利用快慢指针找到链表中点，之后将后半部分链表利用头插法逆序，再比对前后两段链表得出结果。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None or head.next is None:
            return True
        slow, fast = head, head.next
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        cur = slow.next
        slow.next = None
        while cur:
            t = cur.next
            cur.next = slow.next
            slow.next = cur
            cur = t
        t = slow.next
        while t:
            if head.val != t.val:
                return False
            t = t.next
            head = head.next
        return True
```

### Java
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
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = slow.next;
            slow.next = cur;
            cur = t;
        }
        ListNode t = slow.next;
        while (t != null) {
            if (head.val != t.val) {
                return false;
            }
            head = head.next;
            t = t.next;
        }
        return true;
    }
}
```

### ...
```

```
