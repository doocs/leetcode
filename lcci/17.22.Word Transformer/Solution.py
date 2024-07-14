class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[str]:
        def check(s: str, t: str) -> bool:
            return len(s) == len(t) and sum(a != b for a, b in zip(s, t)) == 1

        def dfs(s: str) -> bool:
            if s == endWord:
                return True
            for i, t in enumerate(wordList):
                if not vis[i] and check(s, t):
                    vis[i] = True
                    ans.append(t)
                    if dfs(t):
                        return True
                    ans.pop()
            return False

        ans = [beginWord]
        vis = [False] * len(wordList)
        return ans if dfs(beginWord) else []
