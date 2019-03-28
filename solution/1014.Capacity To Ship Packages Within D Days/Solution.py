class Solution:

    def shipWithinDays(self, weights: List[int], D: int) -> int:
        ans = max(max(weights), sum(weights) // D)

        # 判断某一个运载量能否支撑运载
        def can_trans(ans, weights, D):
            # print('input : ',ans)
            tmp = 0
            while D >= 0 and weights:
                w = weights.pop()
                if tmp + w <= ans:
                    tmp += w
                else:
                    # print(D,tmp,weights+[w])
                    D -= 1
                    tmp = w
            if D > 1:
                return True
            elif D > 0 and tmp <= ans:
                return True
            return False

        while not can_trans(ans, weights[:], D):
            ans += 1
        return ans
