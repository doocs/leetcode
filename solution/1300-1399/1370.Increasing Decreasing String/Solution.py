class Solution:
    def sortString(self, s: str) -> str:
        counter = [0] * 26
        for c in s:
            counter[ord(c) - ord('a')] += 1
        ans = []
        while len(ans) < len(s):
            for i in range(26):
                if counter[i]:
                    ans.append(chr(i + ord('a')))
                    counter[i] -= 1
            for i in range(25, -1, -1):
                if counter[i]:
                    ans.append(chr(i + ord('a')))
                    counter[i] -= 1
        return ''.join(ans)
