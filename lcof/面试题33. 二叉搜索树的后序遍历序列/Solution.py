class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        stk = [(0, len(postorder) - 1)]
        while stk:
            l, r = stk.pop()
            if l >= r:
                continue
            v = postorder[r]
            i = l
            while i < r and postorder[i] < v:
                i += 1
            if any(x < v for x in postorder[i:r]):
                return False
            stk.append((i, r - 1))
            stk.append((l, i - 1))
        return True
