class Solution:
    def peopleIndexes(self, favoriteCompanies: List[List[str]]) -> List[int]:
        d = {}
        idx = 0
        t = []
        for v in favoriteCompanies:
            for c in v:
                if c not in d:
                    d[c] = idx
                    idx += 1
            t.append({d[c] for c in v})
        ans = []
        for i, nums1 in enumerate(t):
            ok = True
            for j, nums2 in enumerate(t):
                if i == j:
                    continue
                if not (nums1 - nums2):
                    ok = False
                    break
            if ok:
                ans.append(i)
        return ans
