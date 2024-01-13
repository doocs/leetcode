class Solution:
    def distanceTraveled(self, mainTank: int, additionalTank: int) -> int:
        ans = cur = 0
        while mainTank:
            cur += 1
            ans += 10
            mainTank -= 1
            if cur % 5 == 0 and additionalTank:
                additionalTank -= 1
                mainTank += 1
        return ans
