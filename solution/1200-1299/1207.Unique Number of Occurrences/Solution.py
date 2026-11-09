class Solution:
    def uniqueOccurrences(self, arr: list[int]) -> bool:
        cnt = Counter(arr)
        return len(set(cnt.values())) == len(cnt)
