class Solution:
    def minNumberOfHours(self, initialEnergy: int, initialExperience: int, energy: List[int], experience: List[int]) -> int:
        ans = 0
        for a, b in zip(energy, experience):
            if initialEnergy <= a:
                t = a - initialEnergy + 1
                ans += t
                initialEnergy += t
            if initialExperience <= b:
                t = b - initialExperience + 1
                ans += t
                initialExperience += t
            initialEnergy -= a
            initialExperience += b
        return ans
