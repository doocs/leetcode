class Trie:
    def __init__(self):
        self.children = {}
        self.is_end = False

    def insert(self, w):
        node = self
        ps = w.split('/')
        for p in ps[1:]:
            if p not in node.children:
                node.children[p] = Trie()
            node = node.children[p]
        node.is_end = True

    def search(self, w):
        node = self
        ps = w.split('/')
        for p in ps[1:]:
            if p not in node.children:
                return False
            node = node.children[p]
            if node.is_end:
                return True
        return False


class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        trie = Trie()
        folder.sort(key=lambda x: len(x.split('/')))
        ans = []
        for v in folder:
            if not trie.search(v):
                trie.insert(v)
                ans.append(v)
        return ans
