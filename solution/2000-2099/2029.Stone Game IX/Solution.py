class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(cnt: List[int]) -> bool:
            if cnt[1] == 0:
                return False
            cnt[1] -= 1
            r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0]
            if cnt[1] > cnt[2]:
                cnt[1] -= 1
                r += 1
            return r % 2 == 1 and cnt[1] != cnt[2]

        c1 = [0] * 3
        for x in stones:
            c1[x % 3] += 1
        c2 = [c1[0], c1[2], c1[1]]
        return check(c1) or check(c2)
