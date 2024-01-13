class Solution:
    def distinctNumbers(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums[:k])
        ans = [len(cnt)]
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            ans.append(len(cnt))
        return ans
