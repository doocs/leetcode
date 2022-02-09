class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = [0] * 26
        for i, c in enumerate(s):
            last[ord(c) - ord('a')] = i
        ans = []
        left = right = 0
        for i, c in enumerate(s):
            right = max(right, last[ord(c) - ord('a')])
            if i == right:
                ans.append(right - left + 1)
                left = right + 1
        return ans
