class Solution:
    def longestBeautifulSubstring(self, word: str) -> int:
        arr = []
        n = len(word)
        i = 0
        while i < n:
            j = i
            while j < n and word[j] == word[i]:
                j += 1
            arr.append((word[i], j - i))
            i = j
        ans = 0
        for i in range(len(arr) - 4):
            a, b, c, d, e = arr[i : i + 5]
            if a[0] + b[0] + c[0] + d[0] + e[0] == "aeiou":
                ans = max(ans, a[1] + b[1] + c[1] + d[1] + e[1])
        return ans
