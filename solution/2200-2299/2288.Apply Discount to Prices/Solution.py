class Solution:
    def discountPrices(self, sentence: str, discount: int) -> str:
        ans = []
        for w in sentence.split():
            if w[0] == '$' and w[1:].isdigit():
                t = int(w[1:]) * (1 - discount / 100)
                ans.append('$' + '{:.2f}'.format(t))
            else:
                ans.append(w)
        return ' '.join(ans)
