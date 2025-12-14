class Solution:
    def reverseWords(self, s: str) -> str:
        def calc(w: str) -> int:
            return sum(c in "aeiou" for c in w)

        words = s.split()
        cnt = calc(words[0])
        ans = [words[0]]
        for w in words[1:]:
            if calc(w) == cnt:
                ans.append(w[::-1])
            else:
                ans.append(w)
        return " ".join(ans)
