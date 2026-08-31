class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        cnt = Counter(s)
        n = len(target)
        ans = []
        for c in target:
            if cnt[c] == 0:
                break
            cnt[c] -= 1
            ans.append(c)
        for i in range(len(ans), -1, -1):
            if i < n:
                for c in ascii_lowercase:
                    if c > target[i] and cnt[c] > 0:
                        cnt[c] -= 1
                        rest = ''.join(x * cnt[x] for x in ascii_lowercase)
                        return ''.join(ans[:i]) + c + rest
            if i > 0:
                cnt[ans[i - 1]] += 1
        return ''
