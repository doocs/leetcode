class Solution:
    def escapeGhosts(self, ghosts, target):
        """
        :type ghosts: List[List[int]]
        :type target: List[int]
        :rtype: bool
        """
        flag = abs(target[0]) + abs(target[1])
        for i in ghosts:
            if abs(i[0] - target[0]) + abs(i[1] - target[1]) <= flag:
                return False
        else:
            return True
