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
            return 0

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
