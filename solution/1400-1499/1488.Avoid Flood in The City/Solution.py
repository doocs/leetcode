class Solution:
    def avoidFlood(self, rains: List[int]) -> List[int]:
        n = len(rains)
        ans = [-1] * n
        sunny = []
        rainy = {}
        for i, v in enumerate(rains):
            if v:
                if v in rainy:
                    idx = bisect_right(sunny, rainy[v])
                    if idx == len(sunny):
                        return []
                    ans[sunny.pop(idx)] = v
                rainy[v] = i
            else:
                sunny.append(i)
                ans[i] = 1
        return ans
