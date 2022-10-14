class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 0, []
        for v in target:
            cur += 1
            while cur < v:
                ans.extend(['Push', 'Pop'])
                cur += 1
            ans.append('Push')
        return ans
