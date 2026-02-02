class Solution:
    def alternatingXOR(self, nums: List[int], target1: int, target2: int) -> int:
        cnt1 = defaultdict(int)
        cnt2 = defaultdict(int)
        cnt2[0] = 1
        ans = pre = 0
        mod = 10**9 + 7
        for x in nums:
            pre ^= x
            a = cnt2[pre ^ target1]
            b = cnt1[pre ^ target2]
            ans = (a + b) % mod
            cnt1[pre] = (cnt1[pre] + a) % mod
            cnt2[pre] = (cnt2[pre] + b) % mod
        return ans
