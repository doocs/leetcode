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
