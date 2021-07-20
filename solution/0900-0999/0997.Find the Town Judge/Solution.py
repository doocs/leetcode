class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        if n == 1 and len(trust) == 0:
            return 1
        dic = {}
        values = set()
        for i in trust:
            values.add(i[0])
            if i[1] in dic:
                dic[i[1]].append(i[0])
            else:
                dic[i[1]] = [i[0]]

        for key, value in dic.items():
            if len(dic[key]) == n-1 and key not in values:
                return k
