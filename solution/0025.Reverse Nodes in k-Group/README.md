## k个一组翻转链表
### 题目描述

给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换

### 解法
1. 在 head 节点前增加一个头节点 reNode 使所有的翻转操作情况一致。
2. 维护一个 num 计数，指针 pNode 从 head 节点开始，每经过 k 个节点，进行一次 k 个节点的翻转
3. 将翻转后的 k 个节点与前后组的节点相连

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k < 2) {
            return head;
        }
        int num = 0;
        ListNode pNode = head;
        ListNode lastNode = new ListNode(0);
        ListNode reNode = lastNode;
        lastNode.next = head;
        while (pNode != null) {
            num++;
            if(num >= k) {
                num = 0;
                ListNode tempNode = pNode.next;
                reverse(lastNode.next, k);
				// k 个节点的尾节点指向下一组的头节点
                lastNode.next.next = tempNode;	
				// 上一组的尾节点指向当前 k 个节点的头节点				
                tempNode = lastNode.next;				
                lastNode.next = pNode;
				
                lastNode = tempNode;
                pNode = lastNode.next;
            }
            else {
                pNode = pNode.next;
            }
        }
        return reNode.next;
    }

    private ListNode reverse(ListNode node, int i) {
        if(i <= 1 || node.next == null) {
            return node;
        }
        ListNode lastNode = reverse(node.next, i - 1);
        lastNode.next = node;
        return node;
    }
}
```