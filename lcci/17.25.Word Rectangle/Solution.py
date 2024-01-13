class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True


class Solution:
    def maxRectangle(self, words: List[str]) -> List[str]:
        def check(mat):
            m, n = len(mat), len(mat[0])
            ans = 1
            for j in range(n):
                node = trie
                for i in range(m):
                    idx = ord(mat[i][j]) - ord("a")
                    if node.children[idx] is None:
                        return 0
                    node = node.children[idx]
                if not node.is_end:
                    ans = 2
            return ans

        def dfs(ws):
            nonlocal ans, max_s, max_l
            if len(ws[0]) * max_l <= max_s or len(t) >= max_l:
                return

            for w in ws:
                t.append(w)
                st = check(t)
                if st == 0:
                    t.pop()
                    continue
                if st == 1 and max_s < len(t) * len(t[0]):
                    ans = t[:]
                    max_s = len(t) * len(t[0])
                dfs(ws)
                t.pop()

        d = defaultdict(list)
        trie = Trie()
        max_l = 0
        for w in words:
            trie.insert(w)
            max_l = max(max_l, len(w))
            d[len(w)].append(w)

        max_s = 0
        ans = []
        for ws in d.values():
            t = []
            dfs(ws)
        return ans
