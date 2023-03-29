class Solution:
    def kthSmallestPath(self, destination: List[int], k: int) -> str:
        v, h = destination
        ans = []
        for _ in range(h + v):
            if h == 0:
                ans.append("V")
            else:
                x = comb(h + v - 1, h - 1)
                if k > x:
                    ans.append("V")
                    v -= 1
                    k -= x
                else:
                    ans.append("H")
                    h -= 1
        return "".join(ans)
