class Solution:
    def decodeMessage(self, key: str, message: str) -> str:
        d = {}
        i = 0
        for c in ''.join(key.split()):
            if c in d:
                continue
            d[c] = ascii_lowercase[i]
            i += 1
        return ''.join([' ' if c == ' ' else d[c] for c in message])
