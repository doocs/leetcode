class Solution:
    def findString(self, words: List[str], s: str) -> int:
        left, right = 0, len(words) - 1
        while left < right:
            mid = (left + right) >> 1
            while left < mid and words[mid] == '':
                mid -= 1
            if s <= words[mid]:
                right = mid
            else:
                left = mid + 1
        return -1 if words[left] != s else left
