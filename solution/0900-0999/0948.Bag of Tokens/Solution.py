class Solution:
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        tokens.sort()
        i, j = 0, len(tokens) - 1
        ans = t = 0
        while i <= j:
            if power >= tokens[i]:
                power -= tokens[i]
                i, t = i + 1, t + 1
                ans = max(ans, t)
            elif t:
                power += tokens[j]
                j, t = j - 1, t - 1
            else:
                break
        return ans
