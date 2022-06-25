class Solution:
    def repeatLimitedString(self, s: str, repeatLimit: int) -> str:
        cnt = [0] * 26
        for c in s:
            cnt[ord(c) - ord('a')] += 1
        ans = []
        for i in range(25, -1, -1):
            j = i - 1
            while 1:
                for _ in range(min(repeatLimit, cnt[i])):
                    cnt[i] -= 1
                    ans.append(chr(ord('a') + i))
                if cnt[i] == 0:
                    break
                while j >= 0 and cnt[j] == 0:
                    j -= 1
                if j < 0:
                    break
                cnt[j] -= 1
                ans.append(chr(ord('a') + j))
        return ''.join(ans)
