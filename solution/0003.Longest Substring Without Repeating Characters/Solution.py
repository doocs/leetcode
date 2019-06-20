class Solution:
    def lengthOfLongestSubstring(self, s: str):
        max = 0
        length = len(s)
        substr = ""
        for i in range(0, length):
            index = substr.find(s[i])
            if index > -1:
                substr = substr[index + 1:]
            substr += s[i]
            max = (max if (max > len(substr)) else len(substr))
        return max