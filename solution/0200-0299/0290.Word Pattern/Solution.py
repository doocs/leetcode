class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        ch2str, str2ch = {}, {}
        ss = s.split(' ')
        n = len(pattern)
        if n != len(ss):
            return False
        for i in range(n):
            if ch2str.get(pattern[i]) is not None and ch2str.get(pattern[i]) != ss[i]:
                return False
            if str2ch.get(ss[i]) is not None and str2ch.get(ss[i]) != pattern[i]:
                return False
            ch2str[pattern[i]] = ss[i]
            str2ch[ss[i]] = pattern[i]
        return True
