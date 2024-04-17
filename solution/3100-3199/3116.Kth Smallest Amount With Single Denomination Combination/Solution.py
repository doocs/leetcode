class Solution:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        def check(mx: int) -> bool:
            cnt = 0
            for i in range(1, 1 << len(coins)):
                v = 1
                for j, x in enumerate(coins):
                    if i >> j & 1:
                        v = lcm(v, x)
                        if v > mx:
                            break
                m = i.bit_count()
                if m & 1:
                    cnt += mx // v
                else:
                    cnt -= mx // v
            return cnt >= k

        return bisect_left(range(10**11), True, key=check)
