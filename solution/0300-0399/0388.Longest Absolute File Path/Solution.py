class Solution:
    def lengthLongestPath(self, input: str) -> int:
        i, n = 0, len(input)
        ans = 0
        stk = []
        while i < n:
            ident = 0
            while input[i] == '\t':
                ident += 1
                i += 1

            cur, isFile = 0, False
            while i < n and input[i] != '\n':
                cur += 1
                if input[i] == '.':
                    isFile = True
                i += 1
            i += 1

            # popd
            while len(stk) > 0 and len(stk) > ident:
                stk.pop()

            if len(stk) > 0:
                cur += stk[-1] + 1

            # pushd
            if not isFile:
                stk.append(cur)
                continue

            ans = max(ans, cur)

        return ans
