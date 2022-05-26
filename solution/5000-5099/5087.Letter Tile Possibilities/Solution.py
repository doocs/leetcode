'''
搜索
'''
class Solution:

    def numTilePossibilities(self, tiles: str) -> int:
        dic=set()
        visit=set()
        self.dfs(tiles,dic,visit,'')
        return len(dic)

    def dfs(self,tiles,dic,visit,s):
        for i in range(len(tiles)):
            if i not in visit:
                visit.add(i)
                if s+tiles[i] not in dic:
                    dic.add(s+tiles[i])
                self.dfs(tiles,dic,visit,s+tiles[i])
                visit.remove(i)
