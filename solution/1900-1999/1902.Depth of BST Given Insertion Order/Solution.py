from sortedcontainers import SortedDict


class Solution:
    def maxDepthBST(self, order: List[int]) -> int:
        sd = SortedDict({0: 0, inf: 0, order[0]: 1})
        ans = 1
        for v in order[1:]:
            lower = sd.bisect_left(v) - 1
            higher = lower + 1
            depth = 1 + max(sd.values()[lower], sd.values()[higher])
            ans = max(ans, depth)
            sd[v] = depth
        return ans
