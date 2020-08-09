# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if not head:
            return True
        mid = self.find_mid_node(head)
        second_half_list = self.reverse_list(mid.next)
        result = True
        p, q = head, second_half_list
        while result and q:
            if p.val != q.val:
                result = False
            else:
                p, q = p.next, q.next
        mid.next = self.reverse_list(second_half_list)
        return result
        
    def reverse_list(self, head):
        pre, p = None, head
        while p:
            q = p.next
            p.next = pre
            pre = p
            p = q
        return pre
    
    def find_mid_node(self, head):
        slow = fast = head
        while fast.next and fast.next.next:
            slow, fast = slow.next, fast.next.next
        return slow
