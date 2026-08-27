mx = 100001
primes = [[] for _ in range(mx)]
for i in range(2, mx):
    if not primes[i]:
        for j in range(i, mx, i):
            primes[j].append(i)


class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = l = 0
        for r, x in enumerate(nums):
            for y in primes[x]:
                cnt[y] += 1
            while len(cnt) > k:
                for y in primes[nums[l]]:
                    cnt[y] -= 1
                    if cnt[y] == 0:
                        cnt.pop(y)
                l += 1
            ans = max(ans, r - l + 1)
        return ans
