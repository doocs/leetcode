class Solution:
    def getValidT9Words(self, num: str, words: list[str]) -> list[str]:
        def check(w: str) -> bool:
            return all(d[c] == num[i] for i, c in enumerate(w))

        d = {c: d for c, d in zip(ascii_lowercase, "22233344455566677778889999")}
        return [w for w in words if check(w)]
