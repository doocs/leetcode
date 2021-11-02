class Trie:
    def __init__(self) -> None:
        self.children = [None] * 26
        self.root = None


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for root in dictionary:
            cur = trie
            for c in root:
                idx = ord(c) - ord('a')
                if cur.children[idx] is None:
                    cur.children[idx] = Trie()
                cur = cur.children[idx]
            cur.root = root

        ans = []
        for word in sentence.split():
            cur = trie
            for c in word:
                idx = ord(c) - ord('a')
                if cur.children[idx] is None or cur.root is not None:
                    break
                cur = cur.children[idx]
            ans.append(word if cur.root is None else cur.root)
        return ' '.join(ans)
