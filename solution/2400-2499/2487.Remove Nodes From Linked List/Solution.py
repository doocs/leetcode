# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        stk = []
        for v in nums:
            while stk and stk[-1] < v:
                stk.pop()
            stk.append(v)
        dummy = ListNode()
        head = dummy
        for v in stk:
            head.next = ListNode(v)
            head = head.next
        return dummy.next
