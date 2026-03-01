class Solution:
    def countSubarrays(self, nums: list[int], k: int, m: int) -> int:
        def f(lim: int) -> int:
            cnt = Counter()
            t = 0
            ans = l = 0
            for x in nums:
                cnt[x] += 1
                if cnt[x] == m:
                    t += 1
                while len(cnt) >= lim and t >= k:
                    y = nums[l]
                    cnt[y] -= 1
                    if cnt[y] == m - 1:
                        t -= 1
                    if cnt[y] == 0:
                        cnt.pop(y)
                    l += 1
                ans += l
            return ans

        return f(k) - f(k + 1)
