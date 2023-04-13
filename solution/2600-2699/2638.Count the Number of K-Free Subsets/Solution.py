class Solution:
    def countTheNumOfKFreeSubsets(self, nums: List[int], k: int) -> int:
        nums.sort()
        g = defaultdict(list)
        for x in nums:
            g[x % k].append(x)
        ans = 1
        for arr in g.values():
            m = len(arr)
            f = [0] * (m + 1)
            f[0] = 1
            f[1] = 2
            for i in range(2, m + 1):
                if arr[i - 1] - arr[i - 2] == k:
                    f[i] = f[i - 1] + f[i - 2]
                else:
                    f[i] = f[i - 1] * 2
            ans *= f[m]
        return ans
