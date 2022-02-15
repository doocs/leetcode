class Solution:
    def wordCount(self, startWords: List[str], targetWords: List[str]) -> int:
        s = set()
        for word in startWords:
            mask = 0
            for c in word:
                mask |= 1 << (ord(c) - ord('a'))
            s.add(mask)

        ans = 0
        for word in targetWords:
            mask = 0
            for c in word:
                mask |= 1 << (ord(c) - ord('a'))
            for c in word:
                t = mask ^ (1 << (ord(c) - ord('a')))
                if t in s:
                    ans += 1
                    break
        return ans
