'''
https://leetcode.com/problems/replace-the-substring-for-balanced-string/

You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.

A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.

Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.

Return 0 if the string is already balanced.

 

Example 1:

Input: s = "QWER"
Output: 0
Explanation: s is already balanced.
Example 2:

Input: s = "QQWE"
Output: 1
Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
Example 3:

Input: s = "QQQW"
Output: 2
Explanation: We can replace the first "QQ" to "ER". 
Example 4:

Input: s = "QQQQ"
Output: 3
Explanation: We can replace the last 3 'Q' to make s = "QWER".
 

Constraints:

1 <= s.length <= 10^5
s.length is a multiple of 4
s contains only 'Q', 'W', 'E' and 'R'.
'''
'''
Runtime: 172 ms, faster than 92.84% of Python3 online submissions for Replace the Substring for Balanced String.
Memory Usage: 13.4 MB, less than 100.00% of Python3 online submissions for Replace the Substring for Balanced String.

'''


from collections import defaultdict, Counter
class Solution:
    def balancedString(self, s: str) -> int:
        # count the occurence of each char
        count_chars = Counter(s)
        
        required = len(s) // 4
        
        # hold the number of excessive occurences 
        more_chars = defaultdict(int)
        for char, count_char in count_chars.items():
            more_chars[char] = max(0, count_char - required)
        
        min_len = len(s)
        
        # count the number of total replacements
        need_replace = sum(more_chars.values())
        if need_replace == 0:
            return  0
        
        # Sliding windows
        # First, move the second cursors until satisfy the conditions
        # Second, move the first_cursor so that it still satisfy the requirement
        
        first_cursor, second_cursor = 0, 0
        while second_cursor < len(s):
            # Move second_cursor
            if more_chars[s[second_cursor]] > 0:
                need_replace -= 1    
            more_chars[s[second_cursor]] -= 1
            second_cursor += 1
            
            # Move first_cursor
            while first_cursor < second_cursor and need_replace == 0:
                min_len = min(min_len, second_cursor - first_cursor)
                if s[first_cursor] in more_chars:
                    more_chars[s[first_cursor]] += 1
                    if more_chars[s[first_cursor]] > 0:
                        need_replace += 1
                first_cursor += 1
        
        return min_len
    