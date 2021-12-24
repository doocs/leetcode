class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        feature_set = set(features)
        counter = Counter()
        for resp in responses:
            for feat in set(resp.split(' ')):
                if feat in feature_set:
                    counter[feat] += 1
        order = {feat: i for i, feat in enumerate(features)}
        return sorted(features, key=lambda feat: (-counter[feat], order[feat]))
