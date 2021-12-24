class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = defaultdict(int)
        n = len(s)
        for i in range(n):
            last[s[i]] = i
        ans = []
        left = right = 0
        for i in range(n):
            right = max(right, last[s[i]])
            if i == right:
                ans.append(right - left + 1)
                left = right + 1
        return ans
