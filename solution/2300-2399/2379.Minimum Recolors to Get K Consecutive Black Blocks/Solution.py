class Solution:
    def minimumRecolors(self, blocks: str, k: int) -> int:
        cnt = blocks[:k].count('W')
        ans = cnt
        i, n = k, len(blocks)
        while i < n:
            cnt += blocks[i] == 'W'
            cnt -= blocks[i - k] == 'W'
            ans = min(ans, cnt)
            i += 1
        return ans
