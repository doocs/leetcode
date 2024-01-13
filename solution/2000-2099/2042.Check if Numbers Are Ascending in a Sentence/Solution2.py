class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        pre = i = 0
        n = len(s)
        while i < n:
            if s[i].isdigit():
                cur = 0
                while i < n and s[i].isdigit():
                    cur = cur * 10 + int(s[i])
                    i += 1
                if pre >= cur:
                    return False
                pre = cur
            else:
                i += 1
        return True
