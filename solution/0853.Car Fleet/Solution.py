class Solution:
    def carFleet(self, target, position, speed):
        """
        :type target: int
        :type position: List[int]
        :type speed: List[int]
        :rtype: int
        """
        car = [(pos, spe) for pos, spe in zip(position, speed)]
        car.sort(reverse=True)
        time = [(target - pos) / spe for pos, spe in car]
        ls = []
        for i in time:
            if not ls:
                ls.append(i)
            else:
                if i > ls[-1]:
                    ls.append(i)
        return len(ls)
