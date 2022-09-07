class Solution:
    def distinctNumbers(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        cnt = Counter(nums[:k])
        ans = [len(cnt)]
        for i in range(k, n):
            u = nums[i - k]
            cnt[u] -= 1
            if cnt[u] == 0:
                cnt.pop(u)

            cnt[nums[i]] += 1
            ans.append(len(cnt))
        return ans
