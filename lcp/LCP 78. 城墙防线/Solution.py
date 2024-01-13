class Solution:
    def rampartDefensiveLine(self, rampart: List[List[int]]) -> int:
        def check(w: int) -> bool:
            last = rampart[0][1]
            for i in range(1, len(rampart) - 1):
                x, y = rampart[i]
                a = x - last
                b = max(w - a, 0)
                if y + b > rampart[i + 1][0]:
                    return False
                last = y + b
            return True

        left, right = 0, rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1]
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
