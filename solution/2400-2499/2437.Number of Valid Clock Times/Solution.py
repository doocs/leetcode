class Solution:
    def countTime(self, time: str) -> int:
        def check(s, t):
            for a, b in zip(s, t):
                if a != b and b != '?':
                    return 0
            return 1

        return sum(
            check(f'{h:02d}:{m:02d}', time) for h in range(24) for m in range(60)
        )
