class Solution:
    def splitIntoFibonacci(self, num: str) -> List[int]:
        def dfs(i):
            if i == n:
                return len(ans) > 2
            x = 0
            for j in range(i, n):
                if j > i and num[i] == '0':
                    break
                x = x * 10 + int(num[j])
                if x > 2**31 - 1 or (len(ans) > 2 and x > ans[-2] + ans[-1]):
                    break
                if len(ans) < 2 or ans[-2] + ans[-1] == x:
                    ans.append(x)
                    if dfs(j + 1):
                        return True
                    ans.pop()
            return False

        n = len(num)
        ans = []
        dfs(0)
        return ans
