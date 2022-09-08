class Solution:
    def constructArray(self, n: int, k: int) -> List[int]:
        l, r = 1, n
        ans = []
        for i in range(k):
            if i % 2 == 0:
                ans.append(l)
                l += 1
            else:
                ans.append(r)
                r -= 1
        for i in range(k, n):
            if k % 2 == 0:
                ans.append(r)
                r -= 1
            else:
                ans.append(l)
                l += 1
        return ans
