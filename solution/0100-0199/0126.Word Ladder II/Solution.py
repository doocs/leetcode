class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[List[str]]:
        def dfs(path, cur):
            if cur == beginWord:
                ans.append(path[::-1])
                return
            for precursor in prev[cur]:
                path.append(precursor)
                dfs(path, precursor)
                path.pop()

        ans = []
        words = set(wordList)
        if endWord not in words:
            return ans
        words.discard(beginWord)
        dist = {beginWord: 0}
        prev = defaultdict(set)
        q = deque([beginWord])
        found = False
        step = 0
        while q and not found:
            step += 1
            for i in range(len(q), 0, -1):
                p = q.popleft()
                s = list(p)
                for i in range(len(s)):
                    ch = s[i]
                    for j in range(26):
                        s[i] = chr(ord('a') + j)
                        t = ''.join(s)
                        if dist.get(t, 0) == step:
                            prev[t].add(p)
                        if t not in words:
                            continue
                        prev[t].add(p)
                        words.discard(t)
                        q.append(t)
                        dist[t] = step
                        if endWord == t:
                            found = True
                    s[i] = ch
        if found:
            path = [endWord]
            dfs(path, endWord)
        return ans
