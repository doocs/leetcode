class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        m, n = len(s), len(goal)
        if m != n:
            return False
        cnt1, cnt2 = Counter(s), Counter(goal)
        if cnt1 != cnt2:
            return False
        diff = sum(s[i] != goal[i] for i in range(n))
        return diff == 2 or (diff == 0 and any(v > 1 for v in cnt1.values()))
