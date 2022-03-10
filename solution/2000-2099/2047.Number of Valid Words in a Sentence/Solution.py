class Solution:
    def countValidWords(self, sentence: str) -> int:
        def check(token):
            hyphen = False
            for i, c in enumerate(token):
                if c.isdigit() or (c in '!.,' and i < len(token) - 1):
                    return False
                if c == '-':
                    if (
                        hyphen
                        or i == 0
                        or i == len(token) - 1
                        or not token[i - 1].islower()
                        or not token[i + 1].islower()
                    ):
                        return False
                    hyphen = True
            return True

        return sum(check(token) for token in sentence.split())
