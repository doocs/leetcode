class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = len(s) - 10
        cnt = Counter()
        ans = []
        for i in range(n + 1):
            sub = s[i : i + 10]
            cnt[sub] += 1
            if cnt[sub] == 2:
                ans.append(sub)
        return ans
