class Solution:
    def isUnique(self, astr: str) -> bool:
        sets = set(astr)
        return len(sets) == len(astr)