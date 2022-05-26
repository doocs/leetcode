class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        def partition(n, cnt):
            res = []
            base, mod = divmod(n, cnt)
            i = j = 0
            while i < cnt:
                t = [' ' * base]
                if j < mod:
                    t.append(' ')
                res.append(''.join(t))
                i, j = i + 1, j + 1
            return res

        ans = []
        i, n = 0, len(words)
        while i < n:
            t = []
            cnt = len(words[i])
            t.append(words[i])
            i += 1
            while i < n and cnt + 1 + len(words[i]) <= maxWidth:
                cnt += 1 + len(words[i])
                t.append(words[i])
                i += 1
            if i == n or len(t) == 1:
                # this is the last line or only one word in a line
                left = ' '.join(t)
                right = ' ' * (maxWidth - len(left))
                ans.append(left + right)
                if i == n:
                    break
                continue
            words_width = cnt - len(t) + 1
            space_width = maxWidth - words_width
            spaces = partition(space_width, len(t) - 1)
            sb = [t[0]]
            for j in range(len(t) - 1):
                sb.append(spaces[j])
                sb.append(t[j + 1])
            ans.append(''.join(sb))
        return ans
