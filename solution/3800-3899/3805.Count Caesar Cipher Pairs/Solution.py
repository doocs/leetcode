class Solution:
    def countPairs(self, words: List[str]) -> int:
        cnt = defaultdict(int)
        for s in words:
            t = list(s)
            k = ord("z") - ord(t[0])
            for i in range(1, len(t)):
                t[i] = chr((ord(t[i]) - ord("a") + k) % 26 + ord("a"))
            t[0] = "z"
            cnt["".join(t)] += 1
        return sum(v * (v - 1) // 2 for v in cnt.values())
