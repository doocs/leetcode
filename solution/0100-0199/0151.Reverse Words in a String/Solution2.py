class Solution:
    def reverseWords(self, s: str) -> str:
        ans = []
        i, n = 0, len(s)
        while i < n:
            while i < n and s[i] == ' ':
                i += 1
            if i < n:
                j = i
                while j < n and s[j] != ' ':
                    j += 1
                ans.append(s[i:j])
                i = j
        return ' '.join(ans[::-1])
