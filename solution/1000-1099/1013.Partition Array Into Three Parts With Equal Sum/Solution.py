class Solution:
    def canThreePartsEqualSum(self, arr: List[int]) -> bool:
        s, mod = divmod(sum(arr), 3)
        if mod:
            return False
        cnt = t = 0
        for x in arr:
            t += x
            if t == s:
                cnt += 1
                t = 0
        return cnt >= 3
