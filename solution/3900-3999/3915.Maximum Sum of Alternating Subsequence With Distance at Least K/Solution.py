class FenwickTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0]*(n+1)

    def update(self, index: int, val: int) -> None:
        while index <= self.n:
            self.tree[index] = max(self.tree[index], val)
            index += (index&(-index)) # 往后更新

    def preSum(self, pos):
        # 按照预期的方式求前缀最大值
        ans = 0
        while pos >= 1:
            ans = max(ans, self.tree[pos])
            pos -= (pos&(-pos))
        return ans

class Solution:
    def maxAlternatingSum(self, nums: list[int], k: int) -> int:
        stl = sorted(set(nums)) # 将nums中不同的数字进行排序
        rank = {v:i+1 for i,v in enumerate(stl)} # 将nums中的值快速转换成stl中的索引
        fwt0 = FenwickTree(len(stl))
        fwt1 = FenwickTree(len(stl))
        
        n = len(nums)
        dp = [[0,0] for _ in range(n)]
        res = nums[0]
        for i in range(n):
            dp[i][0] = dp[i][1] = nums[i]
            if i >= k:
                indx = rank[nums[i]] # 找到nums[i]在stl中的索引
                dp[i][1] = max(dp[i][1], fwt0.preSum(indx-1)+nums[i]) # indx-1即表示小于nums[i]的部分
                dp[i][0] = max(dp[i][0], fwt1.preSum(len(stl)-indx)+nums[i]) # len(stl)-indx即表示在倒序列表中大于nums[i]的部分

            if i-k+1 >= 0:
                indx = rank[nums[i-k+1]]
                fwt0.update(indx, dp[i-k+1][0]) # 在正序列表中更新i-k+1位置的值
                fwt1.update(len(stl)-indx+1, dp[i-k+1][1]) # 在倒序列表中更新i-k+1位置的值
                
            res = max(res, dp[i][0], dp[i][1]) # 更新答案

        return res