class Solution:
    def destCity(self, paths: List[List[str]]) -> str:
        mp = {a: b for a, b in paths}
        a =  paths[0][0]
        while mp.get(a):
            a = mp[a]
        return a
