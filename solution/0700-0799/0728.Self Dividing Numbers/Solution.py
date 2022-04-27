class Solution:
    def selfDividingNumbers(self, left: int, right: int) -> List[int]:
        return [
            num
            for num in range(left, right + 1)
            if all(i != '0' and num % int(i) == 0 for i in str(num))
        ]
