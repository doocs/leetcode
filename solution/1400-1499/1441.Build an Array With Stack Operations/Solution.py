class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 1, []
        for t in target:
            for i in range(cur, n + 1):
                ans.append('Push')
                if t == i:
                    cur = i + 1
                    break
                ans.append('Pop')
        return ans
