class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        def dfs():
            ans = 0
            for i in range(26):
                if cnt[i]:
                    ans += 1
                    cnt[i] -= 1
                    ans += dfs()
                    cnt[i] += 1
            return ans

        cnt = [0] * 26
        for t in tiles:
            cnt[ord(t) - ord('A')] += 1
        return dfs()
