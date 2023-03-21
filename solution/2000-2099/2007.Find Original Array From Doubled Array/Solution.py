class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        n = len(changed)
        if n & 1:
            return []
        cnt = Counter(changed)
        changed.sort()
        ans = []
        for x in changed:
            if cnt[x] == 0:
                continue
            if cnt[x * 2] <= 0:
                return []
            ans.append(x)
            cnt[x] -= 1
            cnt[x * 2] -= 1
        return ans if len(ans) == n // 2 else []
