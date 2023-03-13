class Solution:
    def vowelStrings(self, words: List[str], left: int, right: int) -> int:
        return sum(
            w[0] in 'aeiou' and w[-1] in 'aeiou' for w in words[left : right + 1]
        )
