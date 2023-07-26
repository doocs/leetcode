# Definition for a category handler.
# class CategoryHandler:
#     def haveSameCategory(self, a: int, b: int) -> bool:
#         pass
class Solution:
    def numberOfCategories(
        self, n: int, categoryHandler: Optional['CategoryHandler']
    ) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for a in range(n):
            for b in range(a + 1, n):
                if categoryHandler.haveSameCategory(a, b):
                    p[find(a)] = find(b)
        return sum(i == x for i, x in enumerate(p))
