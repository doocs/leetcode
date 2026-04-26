class Solution:
    def sortVowels(self, s: str) -> str:
        st = set("aeiou")
        vowels = []
        cnt = Counter()
        for c in s:
            if c not in st:
                continue
            if c not in cnt:
                vowels.append(c)
            cnt[c] += 1
        vowels.sort(key=lambda c: -cnt[c])
        ans = list(s)
        i = 0
        for k, c in enumerate(s):
            if c not in st:
                continue
            ans[k] = c = vowels[i]
            cnt[c] -= 1
            if cnt[c] == 0:
                i += 1
        return "".join(ans)
