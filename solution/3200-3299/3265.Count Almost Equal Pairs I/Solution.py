class Solution:
    def countPairs(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        cnt = defaultdict(int)
        for x in nums:
            vis = {x}
            s = list(str(x))
            for j in range(len(s)):
                for i in range(j):
                    s[i], s[j] = s[j], s[i]
                    vis.add(int("".join(s)))
                    s[i], s[j] = s[j], s[i]
            ans += sum(cnt[x] for x in vis)
            cnt[x] += 1
        return ans
