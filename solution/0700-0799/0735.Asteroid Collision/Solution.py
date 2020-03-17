class Solution:
    def asteroidCollision(self, asteroids):
        """
        :type asteroids: List[int]
        :rtype: List[int]
        """
        if not asteroids:
            return []
        ans = []
        for i in asteroids:
            if i > 0:
                ans.append(i)
            if i < 0:
                if not ans or ans[-1] < 0:
                    ans.append(i)
                else:
                    while ans:
                        if ans[-1] < 0:
                            ans.append(i)
                            break
                        elif ans[-1] > abs(i):
                            break
                        elif ans[-1] == abs(i):
                            ans.pop(-1)
                            break
                        else:
                            ans.pop(-1)

                    else:
                        ans.append(i)
        return ans
