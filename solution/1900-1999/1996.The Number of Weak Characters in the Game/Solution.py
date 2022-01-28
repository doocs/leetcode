class Solution:
    def numberOfWeakCharacters(self, properties: List[List[int]]) -> int:
        properties.sort(key=lambda x: (-x[0], x[1]))
        ans = mx = 0
        for _, d in properties:
            if mx > d:
                ans += 1
            mx = max(mx, d)
        return ans
