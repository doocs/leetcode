class Solution:
    def sortThreats(self, threats: List[List[int]]) -> List[List[int]]:
        threats.sort(key=lambda x: (-(x[1] * 2 + x[2]), x[0]))
        return threats
