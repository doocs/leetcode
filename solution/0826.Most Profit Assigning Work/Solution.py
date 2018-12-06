class Solution:
    def maxProfitAssignment(self, difficulty, profit, worker):
        """
        :type difficulty: List[int]
        :type profit: List[int]
        :type worker: List[int]
        :rtype: int
        """
        ans = 0
        worker.sort()
        ls = [[difficulty[i], profit[i]] for i in range(len(profit))]
        ls.sort(key=lambda x: x[0])

        loc = 0
        flag = ls[0][1]
        leng = len(ls)
        for i in worker:
            while loc < leng:
                if i < ls[loc][0] and loc == 0:
                    break
                elif i < ls[loc][0]:
                    ans += flag
                    break
                else:
                    if flag < ls[loc][1]:
                        flag = ls[loc][1]
                    loc += 1
            else:
                ans += flag
        return ans
