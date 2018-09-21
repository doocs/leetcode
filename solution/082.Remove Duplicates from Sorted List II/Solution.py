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
        list0=list()
        i=head
        while i:
            list0.append(i.val)
            i=i.next
        list1=list0.copy()
        for i in list0:
            c=list0.count(i)
            if c != 1:            
                while 1:
                    try:
                        list1.remove(i)
                    except:
                        break
        
        new_listnode=ListNode(0)
        j=new_listnode
        for v in list1:
            new_listnode.next=ListNode(v)
            new_listnode=new_listnode.next
        return j.next