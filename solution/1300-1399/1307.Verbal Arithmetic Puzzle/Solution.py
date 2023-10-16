class Solution:
    def isSolvable(self, words: List[str], result: str) -> bool:
        if max(map(len, words)) > len(
            result
        ):  # If any of the words are bigger than the result, it will be impossible to solve
            return False
        # Add the result to the list, this way we will only subtract values when it comes to the result word.
        # Thus at every index, if the total is zero, then for that letter index the formulat is correct
        words = [word[::-1] for word in words] + [result[::-1]]
        values = {}  # Mapping from letter to values
        nums = [0] * 10

        # i: word index, j: ltr index, total: total current Sum
        def dfs(i, j, total):
            if j == len(
                result
            ):  # Reached end of the indecies for ltrs in all words (END)
                return (
                    total % 10 == 0
                )  # Checking to see if the total for the current character is correct or not
            if i == len(words):  # Checked ltr at index j for all the words
                return total % 10 == 0 and dfs(0, j + 1, total // 10)

            if j >= len(words[i]):
                return dfs(i + 1, j, total)

            if words[i][j] in values:
                if (
                    values[words[i][j]] == 0
                    and j == len(words[i]) - 1
                    and len(words[i]) > 1
                ):
                    return False
                if i == len(words) - 1:
                    return dfs(i + 1, j, total - values[words[i][j]])
                else:
                    return dfs(i + 1, j, total + values[words[i][j]])
            else:
                for val, isUsed in enumerate(nums):
                    if not isUsed and (val != 0 or j == 0 or j < len(words[i]) - 1):
                        values[words[i][j]] = val
                        nums[val] = True

                        if i == len(words) - 1 and dfs(
                            i + 1, j, total - values[words[i][j]]
                        ):
                            return True
                        elif i < len(words) - 1 and dfs(
                            i + 1, j, total + values[words[i][j]]
                        ):
                            return True

                        values.pop(words[i][j])
                        nums[val] = False

        return dfs(0, 0, 0)
