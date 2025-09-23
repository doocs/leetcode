class Solution:
    def minSplitMerge(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        target = tuple(nums2)
        start = tuple(nums1)

        q = [start]
        vis = set()
        vis.add(start)

        for ans in count(0):
            t = q
            q = []
            for cur in t:
                if cur == target:
                    return ans
                for l in range(n):
                    for r in range(l, n):
                        remain = list(cur[:l]) + list(cur[r + 1 :])
                        sub = cur[l : r + 1]
                        for i in range(len(remain) + 1):
                            nxt = tuple(remain[:i] + list(sub) + remain[i:])
                            if nxt not in vis:
                                vis.add(nxt)
                                q.append(nxt)
