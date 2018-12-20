class Solution:
    def isAlienSorted(self, words, order):
        """
        :type words: List[str]
        :type order: str
        :rtype: bool
        """

        order = {a: d for d, a in enumerate(order)}

        for i in range(1, len(words)):
            j = 0
            while len(words[i - 1]) > j < len(words[i]):

                value = order[words[i - 1][j]] - order[words[i][j]]

                if value < 0:
                    break
                elif value > 0 or (j + 1) == len(words[i]):
                    return False

                j += 1

        return True
