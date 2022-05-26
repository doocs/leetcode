class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        p = {}

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in synonyms:
            p[a] = a
            p[b] = b
        for a, b in synonyms:
            p[find(a)] = find(b)

        s = defaultdict(set)
        for a, b in synonyms:
            s[find(a)].add(a)
            s[find(b)].add(b)
        res = []
        for word in text.split(' '):
            if word not in p:
                if not res:
                    res.append([word])
                else:
                    for a in res:
                        a.append(word)
            else:
                words = sorted(s[find(word)])
                if not res:
                    for b in words:
                        res.append([b])
                else:
                    res = [a + [b] for a in res for b in words]
        return [' '.join(sentence) for sentence in res]
