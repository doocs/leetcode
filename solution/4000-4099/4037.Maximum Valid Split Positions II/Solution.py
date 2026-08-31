class Solution:
    def maxValidSplits(self, nums: List[int]) -> int:
        n = len(nums)

        def calc(arr):
            m = len(arr)
            pre = [0] * m
            suf = [0] * m

            pre[0] = arr[0]
            for i in range(1, m):
                pre[i] = gcd(pre[i - 1], arr[i])

            suf[-1] = arr[-1]
            for i in range(m - 2, -1, -1):
                suf[i] = gcd(suf[i + 1], arr[i])

            ans = 0
            for i in range(m - 1):
                if pre[i] == suf[i + 1]:
                    ans += 1

            return ans

        def mark(arr):
            pos = [False] * n
            pos[0] = True
            g = arr[0]

            for i in range(1, n):
                ng = gcd(g, arr[i])
                pos[i] = ng != g
                g = ng

            return pos

        pos1 = mark(nums)
        pos2 = mark(nums[::-1])

        ans = calc(nums)

        for i in range(n):
            if pos1[i] or pos2[n - 1 - i]:
                arr = nums[:i] + nums[i + 1 :]
                ans = max(ans, calc(arr))

        return ans
