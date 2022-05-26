class Solution:
    def modifyString(self, s: str) -> str:
        s = list(s)
        for i in range(len(s)):
            if s[i] == '?':
                ahead = ' ' if i == 0 else s[i - 1]
                behind = ' ' if i == len(s) - 1 else s[i + 1]
                for c in string.ascii_lowercase:
                    if c != ahead and c != behind:
                        s[i] = c
                        break
        return "".join(s)