class Solution:
    def shortestDistanceColor(
        self, colors: List[int], queries: List[List[int]]
    ) -> List[int]:
        color_indexes = defaultdict(list)
        for i, c in enumerate(colors):
            color_indexes[c].append(i)
        res = []
        for i, c in queries:
            if c not in color_indexes:
                res.append(-1)
            else:
                t = color_indexes[c]
                left, right = 0, len(t) - 1
                while left < right:
                    mid = (left + right) >> 1
                    if t[mid] >= i:
                        right = mid
                    else:
                        left = mid + 1
                val = abs(t[left] - i)
                if left > 0:
                    val = min(val, abs(t[left - 1] - i))
                if left < len(t) - 1:
                    val = min(val, abs(t[left + 1] - i))
                res.append(val)
        return res
