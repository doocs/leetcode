class Solution:
    def reformatNumber(self, number: str) -> str:
        number = number.replace("-", "").replace(" ", "")
        n = len(number)
        ans = [number[i * 3 : i * 3 + 3] for i in range(n // 3)]
        if n % 3 == 1:
            ans[-1] = ans[-1][:2]
            ans.append(number[-2:])
        elif n % 3 == 2:
            ans.append(number[-2:])
        return "-".join(ans)
