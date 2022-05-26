class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        ans = []
        for a in asteroids:
            if a > 0:
                ans.append(a)
            else:
                while len(ans) > 0 and ans[-1] > 0 and ans[-1] < -a:
                    ans.pop()
                if len(ans) > 0 and ans[-1] == -a:
                    ans.pop()
                elif len(ans) == 0 or ans[-1] < -a:
                    ans.append(a)
        return ans
