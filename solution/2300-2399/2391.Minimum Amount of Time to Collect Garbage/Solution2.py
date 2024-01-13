class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        def f(x: str) -> int:
            ans = 0
            st = 0
            for i, s in enumerate(garbage):
                if t := s.count(x):
                    ans += t + st
                    st = 0
                if i < len(travel):
                    st += travel[i]
            return ans

        return f('M') + f('P') + f('G')
