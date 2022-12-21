class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for r in responses:
            ws = set(r.split())
            for s in ws:
                cnt[s] += 1
        return sorted(features, key=lambda x: -cnt[x])
