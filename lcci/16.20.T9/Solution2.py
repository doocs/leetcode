class Solution:
    def getValidT9Words(self, num: str, words: List[str]) -> List[str]:
        trans = str.maketrans(ascii_lowercase, "22233344455566677778889999")
        return [w for w in words if w.translate(trans) == num]
