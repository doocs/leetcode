class Solution:
    def checkInclusion(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """
        if len(s1) > len(s2):
            return False
        flag1 = [0] * 26
        flag2 = [0] * 26
        for i, x in enumerate(s1):
            flag1[ord(x) - ord('a')] += 1

        for i, x in enumerate(s2):
            flag2[ord(x) - ord('a')] += 1
            if i >= len(s1):
                flag2[ord(s2[i - len(s1)]) - ord('a')] -= 1
            if flag1 == flag2:
                return True
        return False
