class Solution:
    def getLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 1
        for l in range(n):
            cnt = Counter()
            freq = Counter()
            for r in range(l, n):
                x = nums[r]
                if freq[cnt[x]]:
                    freq[cnt[x]] -= 1
                    if freq[cnt[x]] == 0:
                        freq.pop(cnt[x])
                cnt[x] += 1
                freq[cnt[x]] += 1
                if (len(cnt) == 1) or (
                    len(freq) == 2
                    and (freq[cnt[x] * 2] or (cnt[x] % 2 == 0 and freq[cnt[x] // 2]))
                ):
                    ans = max(ans, r - l + 1)
        return ans
