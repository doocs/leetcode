## 回文链表
### 题目描述


请判断一个链表是否为回文链表。

**示例 1:**
```
输入: 1->2
输出: false
```

**示例 2:**
```
输入: 1->2->2->1
输出: true
```

**进阶：**

你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

### 解法
利用快慢指针，找到链表的中间节点，之后对右部分链表进行逆置，然后左右两部分链表依次遍历，若出现对应元素不相等的情况，返回 `false`；否则返回 `true`。


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
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        
        ListNode rightPre = new ListNode(-1);
        while (slow != null) {
            ListNode t = slow.next;
            slow.next = rightPre.next;
            rightPre.next = slow;
            slow = t;
        }
        
        ListNode p1 = rightPre.next;
        ListNode p2 = head;
        while (p1 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
        
    }
}
```