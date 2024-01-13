class Solution:
    def minNumberOfHours(
        self,
        initialEnergy: int,
        initialExperience: int,
        energy: List[int],
        experience: List[int],
    ) -> int:
        ans = max(0, sum(energy) - initialEnergy + 1)
        for x in experience:
            if initialExperience <= x:
                ans += x - initialExperience + 1
                initialExperience = x + 1
            initialExperience += x
        return ans
