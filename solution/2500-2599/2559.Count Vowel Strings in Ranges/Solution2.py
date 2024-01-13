class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        vowels = set("aeiou")
        s = list(
            accumulate(
                (int(w[0] in vowels and w[-1] in vowels) for w in words), initial=0
            )
        )
        return [s[r + 1] - s[l] for l, r in queries]
