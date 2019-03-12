class Solution:
    def leastBricks(self, wall):
        """
        :type wall: List[List[int]]
        :rtype: int
        """
        dic = {}
        count = 0
        for hang in wall:
            count = 0
            for j, ele in enumerate(hang):
                if j == len(hang) - 1:
                    break
                count += ele
                if count not in dic:
                    dic[count] = 1
                else:
                    dic[count] += 1

        if not dic:
            return len(wall)
        return len(wall) - dic[max(dic, key=dic.get)]
