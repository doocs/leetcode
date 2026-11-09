class Solution:
    def vowelStrings(self, words: list[str], left: int, right: int) -> int:
        return sum(
            w[0] in 'aeiou' and w[-1] in 'aeiou' for w in words[left : right + 1]
        )
