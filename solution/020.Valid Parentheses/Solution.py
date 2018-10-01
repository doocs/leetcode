class Solution:
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        a="()"
        b="[]"
        c="{}"
        f=False
        if s == '':
            return True
        while s != '':
            s=s.replace(a,'')
            s=s.replace(b,'')
            s=s.replace(c,'')
            if s == '':
                f=True
                return f
            if (a in s) or (b in s) or (c in s):
                pass
            else:
                f=False
                return f