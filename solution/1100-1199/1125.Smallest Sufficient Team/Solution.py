class Solution:
    def smallestSufficientTeam(
        self, req_skills: List[str], people: List[List[str]]
    ) -> List[int]:
        @cache
        def dfs(i, state):
            if i == n:
                return [] if state == (1 << m) - 1 else None
            ans1 = dfs(i + 1, state)
            ans2 = dfs(i + 1, state | ps[i])
            if ans1 is None and ans2 is None:
                return None
            if ans1 is None:
                return [i] + ans2
            if ans2 is None:
                return ans1
            return min(ans1, [i] + ans2, key=len)

        d = {s: i for i, s in enumerate(req_skills)}
        m = len(req_skills)
        n = len(people)
        ps = [0] * n
        for i, skills in enumerate(people):
            for skill in skills:
                ps[i] |= 1 << d[skill]
        return dfs(0, 0)
