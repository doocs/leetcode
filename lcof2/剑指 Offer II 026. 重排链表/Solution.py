# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: ListNode) -> None:
        mid = self.middleNode(head)
        tmp = mid.next
        mid.next = None
        tmp = self.reverseList(tmp)
        head = self.mergeTwoLists(head, tmp)

    def middleNode(self, head: ListNode) -> ListNode:
        slow, fast = head, head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        return slow

    def reverseList(self, head: ListNode) -> ListNode:
        pre, cur = None, head
        while cur:
            tmp = cur.next
            cur.next = pre
            pre = cur
            cur = tmp
        return pre

    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode()
        cur = dummy
        while l1 and l2:
            cur.next = l1
            l1 = l1.next
            cur = cur.next
            cur.next = l2
            l2 = l2.next
            cur = cur.next
        cur.next = l1 or l2
        return dummy.next
