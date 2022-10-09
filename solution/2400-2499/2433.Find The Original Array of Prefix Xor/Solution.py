class Solution:
    def findArray(self, pref: List[int]) -> List[int]:
        ans = [pref[0]]
        for a, b in pairwise(pref):
            ans.append(a ^ b)
        return ans
