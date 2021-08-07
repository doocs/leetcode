class Solution:
    def uniqueOccurrences(self, arr: List[int]) -> bool:
        counter = collections.Counter(arr)
        s = set()
        for num in counter.values():
            if num in s:
                return False
            s.add(num)
        return True
