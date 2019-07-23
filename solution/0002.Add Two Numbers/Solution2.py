class Solution:
    """
    This solution is from: https://leetcode.com/problems/add-two-numbers/discuss/1102/Python-for-the-win
    A very Pythonic solution, coooooool!
    Runtime: 76 ms, faster than 84.66% of Python3 online submissions for Add Two Numbers.
    Memory Usage: 13.3 MB, less than 38.91% of Python3 online submissions for Add Two Numbers.
    """
    def addTwoNumbers(self, l1, l2):
        def toint(node):
            return node.val + 10 * toint(node.next) if node else 0
        def tolist(n):
            node = ListNode(n % 10)
            if n > 9:
                node.next = tolist(n // 10)
            return node
        return tolist(toint(l1) + toint(l2))
