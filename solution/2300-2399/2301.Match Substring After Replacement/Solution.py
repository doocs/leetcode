class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = defaultdict(set)
        for a, b in mappings:
            d[a].add(b)
        n, k = len(s), len(sub)
        for i in range(n - k + 1):
            flag = True
            for j in range(k):
                a, b = s[i + j], sub[j]
                if a == b or a in d[b]:
                    continue
                else:
                    flag = False
                    break
            if flag:
                return True
        return False
