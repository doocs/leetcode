class Solution:
    def maximumEvenSplit(self, finalSum: int) -> List[int]:
        if finalSum & 1:
            return []
        ans = []
        i = 2
        while i <= finalSum:
            finalSum -= i
            ans.append(i)
            i += 2
        ans[-1] += finalSum
        return ans
