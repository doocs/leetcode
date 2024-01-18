class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        cnt = [0] * 1001
        for a, b in zip(target, arr):
            cnt[a] += 1
            cnt[b] -= 1
        return all(v == 0 for v in cnt)
