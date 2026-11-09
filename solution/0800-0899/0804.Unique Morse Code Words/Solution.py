class Solution:
    def uniqueMorseRepresentations(self, words: list[str]) -> int:
        codes = [
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--..",
        ]
        s = {''.join([codes[ord(c) - ord('a')] for c in word]) for word in words}
        return len(s)
