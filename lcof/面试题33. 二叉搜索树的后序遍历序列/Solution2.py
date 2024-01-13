class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        mx = inf
        stk = []
        for x in postorder[::-1]:
            if x > mx:
                return False
            while stk and stk[-1] > x:
                mx = stk.pop()
            stk.append(x)
        return True
