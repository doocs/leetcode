class Solution:
    def countKthRoots(self, l: int, r: int, k: int) -> int:
        if k == 1:
            return r - l + 1
        ans = 0
        for x in count():
            y = x**k
            if y > r:
                break
            if l <= y <= r:
                ans += 1
        return ans
