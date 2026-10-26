class Solution:
    def countStaircase(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            orders = {}
            cnt = defaultdict(int)
            ok = 0
            for j in range(i, n):
                x = nums[j]
                if x not in orders:
                    orders[x] = len(orders) + 1
                cnt[x] += 1
                if cnt[x] > orders[x]:
                    break
                if cnt[x] == orders[x]:
                    ok += 1
                    if ok == len(cnt):
                        ans += 1
        return ans
