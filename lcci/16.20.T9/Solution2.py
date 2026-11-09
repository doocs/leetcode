class Solution:
    def getValidT9Words(self, num: str, words: list[str]) -> list[str]:
        trans = str.maketrans(ascii_lowercase, "22233344455566677778889999")
        return [w for w in words if w.translate(trans) == num]
