class Solution:
    def reverseOnlyLetters(self, S):
        """
        :type S: str
        :rtype: str
        """

        S = list(S)

        start = 0
        end = len(S)-1

        while start < end :

            while start < end and S[start] not in string.ascii_letters :
                start += 1
            while start < end and S[end] not in string.ascii_letters :
                end -= 1

            S[start], S[end] = S[end], S[start]
            start += 1
            end -= 1

        return ''.join(S)
