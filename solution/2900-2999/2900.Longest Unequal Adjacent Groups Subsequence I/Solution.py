class Solution:
    def getWordsInLongestSubsequence(
        self, n: int, words: List[str], groups: List[int]
    ) -> List[str]:
        return [words[i] for i, x in enumerate(groups) if i == 0 or x != groups[i - 1]]
