class Solution:
    def reverseWords(self, s: str) -> str:
        if s is None:
            return s
        return ' '.join(list(filter(lambda x: x != '', s.strip(' ').split(' ')))[::-1])
