class Solution:
    def getWordsInLongestSubsequence(
        self, n: int, words: List[str], groups: List[int]
    ) -> List[str]:
        def check(s: str, t: str) -> bool:
            return len(s) == len(t) and sum(a != b for a, b in zip(s, t)) == 1

        f = [1] * n
        g = [-1] * n
        mx = 1
        for i, x in enumerate(groups):
            for j, y in enumerate(groups[:i]):
                if x != y and f[i] < f[j] + 1 and check(words[i], words[j]):
                    f[i] = f[j] + 1
                    g[i] = j
                    mx = max(mx, f[i])
        ans = []
        for i in range(n):
            if f[i] == mx:
                j = i
                while j >= 0:
                    ans.append(words[j])
                    j = g[j]
                break
        return ans[::-1]
