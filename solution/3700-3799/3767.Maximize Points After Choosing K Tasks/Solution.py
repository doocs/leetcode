class Solution:
    def maxPoints(self, technique1: List[int], technique2: List[int], k: int) -> int:
        n = len(technique1)
        idx = sorted(range(n), key=lambda i: -(technique1[i] - technique2[i]))
        ans = sum(technique2)
        for i in idx[:k]:
            ans -= technique2[i]
            ans += technique1[i]
        for i in idx[k:]:
            if technique1[i] >= technique2[i]:
                ans -= technique2[i]
                ans += technique1[i]
        return ans
