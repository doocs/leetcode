# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicatesUnsorted(self, head: ListNode) -> ListNode:
        cur = head
        counter = Counter()
        while cur:
            counter[cur.val] += 1
            cur = cur.next

        dummy = ListNode(0, head)
        pre, cur = dummy, head
        while cur:
            if counter[cur.val] > 1:
                pre.next = cur.next
            else:
                pre = cur
            cur = cur.next
        return dummy.next
