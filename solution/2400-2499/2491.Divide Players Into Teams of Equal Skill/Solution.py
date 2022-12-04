class Solution:
    def dividePlayers(self, skill: List[int]) -> int:
        skill.sort()
        t = skill[0] + skill[-1]
        i, j = 0, len(skill) - 1
        ans = 0
        while i < j:
            if skill[i] + skill[j] != t:
                return -1
            ans += skill[i] * skill[j]
            i, j = i + 1, j - 1
        return ans
