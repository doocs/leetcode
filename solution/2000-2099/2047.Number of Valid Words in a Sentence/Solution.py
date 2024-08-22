class Solution:
    def countValidWords(self, sentence: str) -> int:
        def check(s: str) -> bool:
            st = False
            for i, c in enumerate(s):
                if c.isdigit() or (c in "!.," and i < len(s) - 1):
                    return False
                if c == "-":
                    if (
                        st
                        or i in (0, len(s) - 1)
                        or not s[i - 1].isalpha()
                        or not s[i + 1].isalpha()
                    ):
                        return False
                    st = True
            return True

        return sum(check(s) for s in sentence.split())
