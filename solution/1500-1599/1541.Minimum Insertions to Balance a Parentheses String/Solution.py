class Solution:
    def minInsertions(self, s: str) -> int:
        ans = x = 0
        i, n = 0, len(s)
        while i < n:
            if s[i] == '(':
                # 待匹配的左括号加 1
                x += 1
            else:
                if i < n - 1 and s[i + 1] == ')':
                    # 有连续两个右括号，i 往后移动
                    i += 1
                else:
                    # 只有一个右括号，插入一个
                    ans += 1
                if x == 0:
                    # 无待匹配的左括号，插入一个
                    ans += 1
                else:
                    # 待匹配的左括号减 1
                    x -= 1
            i += 1
        # 遍历结束，仍有待匹配的左括号，说明右括号不足，插入 x << 1 个
        ans += x << 1
        return ans
