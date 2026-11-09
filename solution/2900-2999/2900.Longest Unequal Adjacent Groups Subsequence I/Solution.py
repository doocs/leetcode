class Solution:
    def getLongestSubsequence(self, words: list[str], groups: list[int]) -> list[str]:
        return [words[i] for i, x in enumerate(groups) if i == 0 or x != groups[i - 1]]
