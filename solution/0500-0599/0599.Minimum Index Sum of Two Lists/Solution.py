class Solution:
    def findRestaurant(self, list1: list[str], list2: list[str]) -> list[str]:
        d = {s: i for i, s in enumerate(list2)}
        ans = []
        mi = inf
        for i, s in enumerate(list1):
            if s in d:
                j = d[s]
                if i + j < mi:
                    mi = i + j
                    ans = [s]
                elif i + j == mi:
                    ans.append(s)
        return ans
