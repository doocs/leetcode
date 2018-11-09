# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

#经典的merge，每次都比较两个链表的第一个数字
#递归的方法
class Solution(object):
    def mergeTwoLists(self, l1, l2):
        if not l1 or not l2:
            return l1 or l2
        if l1.val < l2.val:   #输出较小的一个
            l1.next = self.mergeTwoLists(l1.next, l2)
            return l1
        else :
            l2.next = self.mergeTwoLists(l1, l2.next)
            return l2

