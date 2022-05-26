class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        def verify(p1, p2):
            if p1 > p2:
                return True
            pos = p1
            while pos < p2 and postorder[pos] < postorder[p2]:
                pos += 1
            p = pos
            while pos < p2:
                if postorder[pos] < postorder[p2]:
                    return False
                pos += 1
            return verify(p1, p - 1) and verify(p, p2 - 1)
        if not postorder:
            return True
        return verify(0, len(postorder) - 1)
