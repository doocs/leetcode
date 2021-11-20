class Solution:
    def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
        def count(word):
            counter = [0] * 26
            for c in word:
                counter[ord(c) - ord('a')] += 1
            return counter

        def check(counter1, counter2):
            for i in range(26):
                if counter1[i] > counter2[i]:
                    return False
            return True

        counter = count(c.lower() for c in licensePlate if c.isalpha())
        ans, n = None, 16
        for word in words:
            if n <= len(word):
                continue
            t = count(word)
            if check(counter, t):
                n = len(word)
                ans = word
        return ans
