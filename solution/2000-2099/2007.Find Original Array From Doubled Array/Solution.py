class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        changed.sort()
        cnt = Counter(changed)
        ans = []
        for x in changed:
            if cnt[x] == 0:
                continue
            cnt[x] -= 1
            if cnt[x << 1] <= 0:
                return []
            cnt[x << 1] -= 1
            ans.append(x)
        return ans
