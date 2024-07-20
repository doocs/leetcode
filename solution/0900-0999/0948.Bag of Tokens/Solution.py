class Solution:
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        tokens.sort()
        ans = score = 0
        i, j = 0, len(tokens) - 1
        while i <= j:
            if power >= tokens[i]:
                power -= tokens[i]
                score, i = score + 1, i + 1
                ans = max(ans, score)
            elif score:
                power += tokens[j]
                score, j = score - 1, j - 1
            else:
                break
        return ans
