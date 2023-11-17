class Solution:
    def generateAbbreviations(self, word: str) -> List[str]:
        n = len(word)
        ans = []
        for i in range(1 << n):
            cnt = 0
            s = []
            for j in range(n):
                if i >> j & 1:
                    cnt += 1
                else:
                    if cnt:
                        s.append(str(cnt))
                        cnt = 0
                    s.append(word[j])
            if cnt:
                s.append(str(cnt))
            ans.append("".join(s))
        return ans
