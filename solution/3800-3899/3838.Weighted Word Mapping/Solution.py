class Solution:
    def mapWordWeights(self, words: List[str], weights: List[int]) -> str:
        ans = []
        for w in words:
            s = sum(weights[ord(c) - ord('a')] for c in w)
            ans.append(ascii_lowercase[25 - s % 26])
        return ''.join(ans)
