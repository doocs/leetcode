# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head == None:
            return None
        dict0=dict()
        i=head
        c=0
        while i:
            if i.val not in dict0.values():
                dict0[c]=i.val
                c+=1
            i=i.next
        new_listnode=ListNode(0)
        j=new_listnode
        for v in dict0.values():
            new_listnode.next=ListNode(v)
            new_listnode=new_listnode.next
        return j.next
        