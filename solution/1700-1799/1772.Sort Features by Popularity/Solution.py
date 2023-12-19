class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for s in responses:
            for w in set(s.split()):
                cnt[w] += 1
        return sorted(features, key=lambda w: -cnt[w])
