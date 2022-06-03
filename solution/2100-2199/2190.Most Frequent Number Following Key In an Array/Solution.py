class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        mx = ans = 0
        for i, v in enumerate(nums[:-1]):
            if v == key:
                target = nums[i + 1]
                cnt[target] += 1
                if mx < cnt[target]:
                    mx = cnt[target]
                    ans = nums[i + 1]
        return ans
