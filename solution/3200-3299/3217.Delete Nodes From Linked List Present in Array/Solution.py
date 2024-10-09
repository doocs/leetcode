# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def modifiedList(
        self, nums: List[int], head: Optional[ListNode]
    ) -> Optional[ListNode]:
        s = set(nums)
        pre = dummy = ListNode(next=head)
        while pre.next:
            if pre.next.val in s:
                pre.next = pre.next.next
            else:
                pre = pre.next
        return dummy.next
