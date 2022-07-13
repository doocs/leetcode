class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        ans = []
        for a in asteroids:
            if a > 0:
                ans.append(a)
            else:
                while ans and 0 < ans[-1] < -a:
                    ans.pop()
                if ans and ans[-1] == -a:
                    ans.pop()
                elif not ans or ans[-1] < -a:
                    ans.append(a)
        return ans
