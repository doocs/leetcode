# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        def reverse(head):
            dummy = ListNode()
            cur = head
            while cur:
                next = cur.next
                cur.next = dummy.next
                dummy.next = cur
                cur = next
            return dummy.next

        head = reverse(head)
        dummy = cur = ListNode()
        mul, carry = 2, 0
        while head:
            x = head.val * mul + carry
            carry = x // 10
            cur.next = ListNode(x % 10)
            cur = cur.next
            head = head.next
        if carry:
            cur.next = ListNode(carry)
        return reverse(dummy.next)
