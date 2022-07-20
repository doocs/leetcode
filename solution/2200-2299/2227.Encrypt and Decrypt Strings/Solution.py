class Encrypter:
    def __init__(self, keys: List[str], values: List[str], dictionary: List[str]):
        self.mp = dict(zip(keys, values))
        self.cnt = Counter(self.encrypt(v) for v in dictionary)

    def encrypt(self, word1: str) -> str:
        res = []
        for c in word1:
            if c not in self.mp:
                return ''
            res.append(self.mp[c])
        return ''.join(res)

    def decrypt(self, word2: str) -> int:
        return self.cnt[word2]


# Your Encrypter object will be instantiated and called as such:
# obj = Encrypter(keys, values, dictionary)
# param_1 = obj.encrypt(word1)
# param_2 = obj.decrypt(word2)
