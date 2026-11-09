class Solution:
    def nextGreatestLetter(self, letters: list[str], target: str) -> str:
        i = bisect_right(letters, ord(target), key=lambda c: ord(c))
        return letters[i % len(letters)]
