class Solution:
    def splitWordsBySeparator(self, words: List[str], separator: str) -> List[str]:
        return [s for w in words for s in w.split(separator) if s]
