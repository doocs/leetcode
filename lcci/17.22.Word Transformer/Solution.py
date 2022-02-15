class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[str]:
        def check(a, b):
            return sum(a[i] != b[i] for i in range(len(a))) == 1

        def dfs(begin, end, t):
            nonlocal ans
            if ans:
                return
            if begin == end:
                ans = t.copy()
                return
            for word in wordList:
                if word in visited or not check(begin, word):
                    continue
                visited.add(word)
                t.append(word)
                dfs(word, end, t)
                t.pop()

        ans = []
        visited = set()
        dfs(beginWord, endWord, [beginWord])
        return ans
