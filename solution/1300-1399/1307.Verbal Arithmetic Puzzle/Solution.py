class Solution:
    def isSolvable(self, words: List[str], result: str) -> bool:
        words.append(result)
        rows = len(words)
        cols = max(map(len, words))
        letterToDigit = {}
        usedDigit = [False] * 10

        def dfs(row: int, col: int, summ: int) -> bool:
            if col == cols:
                return summ == 0
            if row == rows:
                return summ % 10 == 0 and dfs(0, col + 1, summ // 10)

            word = words[row]
            if col >= len(word):
                return dfs(row + 1, col, summ)

            letter = word[~col]
            sign = -1 if row == rows - 1 else 1

            if letter in letterToDigit and (
                letterToDigit[letter] > 0 or col < len(word) - 1
            ):
                return dfs(row + 1, col, summ + sign * letterToDigit[letter])

            for digit, used in enumerate(usedDigit):
                if not used and (digit > 0 or col < len(word) - 1):
                    letterToDigit[letter] = digit
                    usedDigit[digit] = True
                    if dfs(row + 1, col, summ + sign * digit):
                        return True
                    usedDigit[digit] = False
                    if letter in letterToDigit:
                        del letterToDigit[letter]

            return False

        return dfs(0, 0, 0)
