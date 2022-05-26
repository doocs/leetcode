class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        x = y = 0
        cnt1 = [0] * 10
        cnt2 = [0] * 10
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                x += 1
            else:
                cnt1[int(secret[i])] += 1
                cnt2[int(guess[i])] += 1

        for i in range(10):
            y += min(cnt1[i], cnt2[i])
        return f'{x}A{y}B'
