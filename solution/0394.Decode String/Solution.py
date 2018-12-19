class Solution(object):
    def decodeString(self, s):
        """
        :type s: str
        :rtype: str
        """
        def deco(s):
            if '[' not in s and ']' not in s:
                return s
            i = j = 0
            ans = ''
            count = ''
            while i < len(s):
                if s[i].isdigit():
                    count += s[i]
                    i += 1
                elif s[i].isalpha():
                    ans += s[i]
                    i += 1
                elif s[i] == '[':
                    j = i + 1
                    zuo = 0
                    while j < len(s):
                        if s[j] == '[':
                            zuo += 1
                            j += 1
                        elif s[j] == ']':
                            if zuo != 0:
                                zuo -= 1
                                j += 1
                            else:
                                if not count:
                                    ans += deco(s[i + 1:j])
                                else:
                                    ans += int(count) * deco(s[i + 1:j])
                                    count = ''
                                i = j + 1
                                break
                        else:
                            j += 1
            return ans
        return deco(s)
