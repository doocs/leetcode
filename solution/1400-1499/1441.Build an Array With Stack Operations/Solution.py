class Solution:
    def buildArray(self, target: list[int], n: int) -> list[str]:
        ans = []
        cur = 1
        for x in target:
            while cur < x:
                ans.extend(["Push", "Pop"])
                cur += 1
            ans.append("Push")
            cur += 1
        return ans
