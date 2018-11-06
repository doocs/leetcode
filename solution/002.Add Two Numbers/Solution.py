# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        ans=ListNode(-1)
        mn=ans
        tmp1=[]
        while l1:
            tmp1.append(l1.val)
            l1=l1.next
        tmp1.reverse()
        l11=''
        for i in tmp1:
            l11+=str(i)
        l11=int(l11)
        tmp2=[]
        while l2:
            tmp2.append(l2.val)
            l2=l2.next
        tmp2.reverse()
        l22=''
        for i in tmp2:
            l22+=str(i)
        l22=int(l22)
        tmp=l11+l22
        tmp=str(tmp)
        tmp3=[]
        for i in tmp:
            tmp3.append(i)
        tmp3.reverse()
        for j in tmp3:
            ans.next=ListNode(int(j))
            ans=ans.next
        return mn.next