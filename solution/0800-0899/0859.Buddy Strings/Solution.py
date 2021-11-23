class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        m, n = len(s), len(goal)
        if m != n:
            return False
        diff = sum(1 for i in range(n) if s[i] != goal[i])
        cnt1, cnt2 = Counter(s), Counter(goal)
        if cnt1 != cnt2:
            return False
        return diff == 2 or (diff == 0 and any(e > 1 for e in cnt1.values()))
