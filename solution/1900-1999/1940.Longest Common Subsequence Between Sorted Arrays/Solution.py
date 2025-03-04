class Solution:
    def longestCommonSubsequence(self, arrays: List[List[int]]) -> List[int]:
        cnt = [0] * 101
        for row in arrays:
            for x in row:
                cnt[x] += 1
        return [x for x, v in enumerate(cnt) if v == len(arrays)]
