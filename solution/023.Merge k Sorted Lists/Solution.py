# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        list0=[]
        for i in lists:
            while i:
                list0.append(i.val)
                i=i.next
        if list0==[]:
            return []
        list0.sort(reverse=True)
        ln=ListNode(0)
        tmp=ln
        while list0:
            tmp1=list0.pop()
            ln.next=ListNode(tmp1)
            ln=ln.next
        return tmp.next