class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        words.sort()
        n = len(words)
        ans = []
        for i in range(n):
            top = words[i]
            for j in range(n):
                if j != i:
                    left = words[j]
                    for k in range(n):
                        if k != j and k != i:
                            right = words[k]
                            for h in range(n):
                                if h != k and h != j and h != i:
                                    bottom = words[h]
                                    if (
                                        top[0] == left[0]
                                        and top[3] == right[0]
                                        and bottom[0] == left[3]
                                        and bottom[3] == right[3]
                                    ):
                                        ans.append([top, left, right, bottom])
        return ans
