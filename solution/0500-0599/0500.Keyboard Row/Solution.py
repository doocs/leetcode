class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        s1 = set('qwertyuiop')
        s2 = set('asdfghjkl')
        s3 = set('zxcvbnm')
        res = []
        for word in words:
            t = set(word.lower())
            if t <= s1 or t <= s2 or t <= s3:
                res.append(word)
        return res
