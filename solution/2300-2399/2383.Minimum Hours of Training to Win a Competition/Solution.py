class Solution:
    def minNumberOfHours(
        self,
        initialEnergy: int,
        initialExperience: int,
        energy: List[int],
        experience: List[int],
    ) -> int:
        ans = 0
        for a, b in zip(energy, experience):
            if initialEnergy <= a:
                ans += a - initialEnergy + 1
                initialEnergy = a + 1
            if initialExperience <= b:
                ans += b - initialExperience + 1
                initialExperience = b + 1
            initialEnergy -= a
            initialExperience += b
        return ans
