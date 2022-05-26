class Solution:
    def totalFruit(self, tree: List[int]) -> int:
        counter = Counter()
        i = res = 0
        for j, type in enumerate(tree):
            counter[type] += 1
            while len(counter) > 2:
                counter[tree[i]] -= 1
                if counter[tree[i]] == 0:
                    counter.pop(tree[i])
                i += 1
            res = max(res, j - i + 1)
        return res
