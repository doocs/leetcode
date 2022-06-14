class Solution:
    def findDuplicate(self, paths: List[str]) -> List[List[str]]:
        m = defaultdict(list)
        for path in paths:
            a = path.split(" ")
            for i in range(1, len(a)):
                j = a[i].find("(")
                content = a[i][j + 1 : -1]
                name = a[0] + "/" + a[i][:j]
                m[content].append(name)

        ans = []
        for names in m.values():
            if len(names) > 1:
                ans.append(names)
        return ans
