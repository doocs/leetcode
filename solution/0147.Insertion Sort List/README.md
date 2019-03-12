## 对链表进行插入排序
### 题目描述

对链表进行插入排序。

插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

插入排序算法：
1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
3. 重复直到所有输入数据插入完为止。

示例1：
```
输入: 4->2->1->3
输出: 1->2->3->4
```

示例2：
```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```
### 解法
从链表头部开始遍历，记录当前已完成插入排序的最后一个节点。然后进行以下操作：
- 获得要插入排序的节点 curNode 、其上一个节点 perNode 、其下一个节点 nextNode;
- 判断 curNode 是否应插入在 perNode 之后，若否，将 curNode 从链表中移除准备插入，若是，无需进一步操作，此时已排序的最后一个节点为 curNode;
- 在链表头节点前增加一个节点，应对 curNode 插入位置在 头节点之前的情况;
- 从头节点开始遍历，找到curNode 的插入位置，进行插入;
- 此时已排序的最后一个节点仍为 perNode ，重复以上操作直至链表末尾。

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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        return insertionOneNode(head, head);
    }

    private ListNode insertionOneNode(ListNode head, ListNode node) {
        if (head == null || node == null || node.next == null) {
            return head;
        }

        ListNode perNode = node;
        ListNode curNode = node.next;
        ListNode nextNode = curNode.next;

        if (node.val <= curNode.val) {
            return insertionOneNode(head, curNode);
        } else {
            node.next = nextNode;
        }

        ListNode pNode = new ListNode(0);
        pNode.next = head;
        head = pNode;
        while (pNode.next.val <= curNode.val) {
            pNode = pNode.next;
        }
        ListNode nNode = pNode.next;
        pNode.next = curNode;
        curNode.next = nNode;

        return insertionOneNode(head.next, perNode);
    }
}
```