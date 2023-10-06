class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        def f(x: int) -> int:
            cnt1 = cnt2 = 0
            for a, b in zip(tops, bottoms):
                if x not in (a, b):
                    return inf
                cnt1 += a == x
                cnt2 += b == x
            return len(tops) - max(cnt1, cnt2)

        ans = min(f(tops[0]), f(bottoms[0]))
        return -1 if ans == inf else ans
