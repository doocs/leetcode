class Solution:
    def findAllRecipes(
        self, recipes: List[str], ingredients: List[List[str]], supplies: List[str]
    ) -> List[str]:
        g = defaultdict(list)
        indeg = defaultdict(int)
        for a, b in zip(recipes, ingredients):
            for v in b:
                g[v].append(a)
            indeg[a] += len(b)
        q = deque(supplies)
        ans = []
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                for j in g[i]:
                    indeg[j] -= 1
                    if indeg[j] == 0:
                        ans.append(j)
                        q.append(j)
        return ans
