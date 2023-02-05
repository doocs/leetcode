class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        t = [i for i, w in enumerate(words) if w[0] in "aeiou" and w[-1] in "aeiou"]
        return [bisect_left(t, r + 1) - bisect_left(t, l) for l, r in queries]
