class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        cnt = Counter(nums)
        for v in sorted(nums):
            if cnt[v]:
                for x in range(v, v + k):
                    if cnt[x] == 0:
                        return False
                    cnt[x] -= 1
                    if cnt[x] == 0:
                        cnt.pop(x)
        return True
