class Solution:
    def minSumSquareDiff(
        self, nums1: List[int], nums2: List[int], k1: int, k2: int
    ) -> int:
        k = k1 + k2
        s = mx = 0
        cnt = [0] * 100001
        for a, b in zip(nums1, nums2):
            v = abs(a - b)
            cnt[v] += 1
            s += v
            mx = max(mx, v)
        if s <= k:
            return 0
        for v in range(mx, 0, -1):
            if cnt[v] == 0:
                continue
            take = min(cnt[v], k)
            k -= take
            cnt[v] -= take
            cnt[v - 1] += take
            if k == 0:
                break
        return sum(v * v * cnt[v] for v in range(mx + 1))
