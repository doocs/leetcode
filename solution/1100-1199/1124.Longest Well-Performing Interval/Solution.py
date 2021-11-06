class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        pre_sum, res = 0, 0
        mp = {}
        for i in range(len(hours)):
            temp = 1 if hours[i] > 8 else -1
            pre_sum += temp
            if pre_sum > 0:
                res = i + 1
            else:
                if pre_sum not in mp:
                    mp[pre_sum] = i
                if (pre_sum - 1) in mp:
                    res = max(res, i - mp[pre_sum - 1])
        return res