class Solution:
    def mergeAdjacent(self, nums: List[int]) -> List[int]:
        stk = []
        for x in nums:
            stk.append(x)
            while len(stk) > 1 and stk[-1] == stk[-2]:
                stk.append(stk.pop() + stk.pop())
        return stk
