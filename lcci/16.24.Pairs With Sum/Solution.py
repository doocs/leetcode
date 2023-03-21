class Solution:
    def pairSums(self, nums: List[int], target: int) -> List[List[int]]:
        cnt = Counter()
        ans = []
        for x in nums:
            y = target - x
            if cnt[y]:
                cnt[y] -= 1
                ans.append([x, y])
            else:
                cnt[x] += 1
        return ans
