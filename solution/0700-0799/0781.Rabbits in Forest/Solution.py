class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        cnt = Counter(answers)
        ans = 0
        for x, v in cnt.items():
            group = x + 1
            ans += (v + group - 1) // group * group
        return ans
