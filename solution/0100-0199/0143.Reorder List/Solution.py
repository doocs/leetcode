# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if head is None or head.next is None:
            return

        # 快慢指针找到链表中点
        slow, fast = head, head.next
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next

        # cur 指向右半部分链表
        cur = slow.next
        slow.next = None

        # 反转右半部分链表
        pre = None
        while cur:
            t = cur.next
            cur.next = pre
            pre, cur = cur, t
        cur = head
        # 此时 cur, pre 分别指向链表左右两半的第一个节点

        while pre:
            t = pre.next
            pre.next = cur.next
            cur.next = pre
            cur, pre = pre.next, t
