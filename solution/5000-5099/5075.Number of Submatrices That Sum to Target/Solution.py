'''
from @Arco
就是一层一层的找
'''

from collections import defaultdict


class Solution(object):
    def numSubmatrixSumTarget(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: int
        """
        n, m = len(matrix), len(matrix[0])
        result = 0
        for p in range(n):
            sum_ = [0] * m
            for i in range(p, n):
                mem = defaultdict(int)
                mem[0] = 1
                pre_sum = 0
                for j in range(m):
                    sum_[j] += matrix[i][j]
                    pre_sum += sum_[j]
                    remain = pre_sum - target
                    result += mem[remain]
                    mem[pre_sum] += 1
        return result
