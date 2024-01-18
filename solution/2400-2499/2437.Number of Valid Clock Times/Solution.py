class Solution:
    def countTime(self, time: str) -> int:
        def check(s: str, t: str) -> bool:
            return all(a == b or b == '?' for a, b in zip(s, t))

        return sum(
            check(f'{h:02d}:{m:02d}', time) for h in range(24) for m in range(60)
        )
