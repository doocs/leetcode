# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def listOfDepth(self, tree: TreeNode) -> List[ListNode]:
        q = [tree]
        ans = []
        while q:
            n = len(q)
            head = ListNode(-1)
            tail = head
            for i in range(n):
                front = q.pop(0)
                node = ListNode(front.val)
                tail.next = node
                tail = node
                if front.left:
                    q.append(front.left)
                if front.right:
                    q.append(front.right)
            ans.append(head.next)
        return ans
