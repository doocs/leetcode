# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def numComponents(self, head, G):
        """
        :type head: ListNode
        :type G: List[int]
        :rtype: int
        """
        dic = set(G)
        ans = 0
        flag = 0
        while head:
            if head.val not in dic:
                if flag == 1:
                    ans += 1
                    flag = 0
            else:
                flag = 1
            head = head.next
        else:
            if flag == 1:
                ans += 1
        return ans
