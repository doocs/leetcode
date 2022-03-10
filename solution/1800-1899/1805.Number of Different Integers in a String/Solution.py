import re


class Solution:
    def numDifferentIntegers(self, word: str) -> int:
        nums = re.split(r'[a-z]+', word)
        return len({int(num) for num in nums if num != ''})
