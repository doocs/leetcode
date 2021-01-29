class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        mapper = {}
        for ch in s:
            mapper[ch] = mapper.get(ch, 0) + 1
        cnt = 0
        for _, v in mapper.items():
            if v % 2 != 0:
                cnt += 1
        return cnt <= 1
