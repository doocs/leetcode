class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        def f(i: int) -> Tuple[int, int, int, int]:
            k1 = k2 = 0
            cnt = Counter(nums[i::2])
            for k, v in cnt.items():
                if cnt[k1] < v:
                    k2, k1 = k1, k
                elif cnt[k2] < v:
                    k2 = k
            return k1, cnt[k1], k2, cnt[k2]

        a, b = f(0), f(1)
        n = len(nums)
        if a[0] != b[0]:
            return n - (a[1] + b[1])
        return n - max(a[1] + b[3], a[3] + b[1])
