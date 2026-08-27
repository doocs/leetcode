class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        cnt = [0] * 26

        for ch in s:
            cnt[ord(ch) - ord('a')] += 1

        ans = []

        for i in range(len(target)):
            x = ord(target[i]) - ord('a')

            # Match target[i] exactly if possible
            if cnt[x] > 0:
                cnt[x] -= 1
                ans.append(target[i])
                continue

            # Can't match target[i].
            # Try the smallest character greater than target[i].
            for c in range(x + 1, 26):
                if cnt[c] > 0:
                    ans.append(chr(c + ord('a')))
                    cnt[c] -= 1

                    # Put remaining characters in sorted order
                    for j in range(26):
                        ans.append(chr(j + ord('a')) * cnt[j])

                    return ''.join(ans)

            # Nothing greater is possible here.
            break

        # Backtrack through the prefix that matched target.
        for i in range(len(ans) - 1, -1, -1):
            x = ord(ans[i]) - ord('a')

            # Return this character to the available pool
            cnt[x] += 1
            ans.pop()

            # Find the smallest character greater than ans[i]
            for c in range(x + 1, 26):
                if cnt[c] > 0:
                    ans.append(chr(c + ord('a')))
                    cnt[c] -= 1

                    # Remaining characters in sorted order
                    for j in range(26):
                        ans.append(chr(j + ord('a')) * cnt[j])

                    return ''.join(ans)

        # No permutation is greater than target
        return ""
