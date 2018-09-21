## 链表的中间结点
### 题目描述

给定一个带有头结点 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。


示例 1：
```
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
```

示例 2：
```
输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
```

提示：

- 给定链表的结点数介于 1 和 100 之间。

### 解法
快指针 fast 和慢指针 slow 初始指向 head。循环开始，fast 每次走两步，slow 每次走一步，当快指针为 null 或者 快指针下一个结点为 null（简单抠一下边界可以了），退出循环。

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
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            // 快指针每次循环走两步，慢指针走一步
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return slow;
            }
        }
        return  null;
    }
}
```