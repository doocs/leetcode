class Solution:
    
    def beautifulIndices(self, s: str, a: str, b: str, k: int) -> List[int]:
        def build_prefix_function(pattern):
            prefix_function = [0] * len(pattern)
            j = 0
            for i in range(1, len(pattern)):
                while j > 0 and pattern[i] != pattern[j]:
                    j = prefix_function[j - 1]
                if pattern[i] == pattern[j]:
                    j += 1
                prefix_function[i] = j
            return prefix_function

        def kmp_search(pattern, text, prefix_function):
            occurrences = []
            j = 0
            for i in range(len(text)):
                while j > 0 and text[i] != pattern[j]:
                    j = prefix_function[j - 1]
                if text[i] == pattern[j]:
                    j += 1
                if j == len(pattern):
                    occurrences.append(i - j + 1)
                    j = prefix_function[j - 1]
            return occurrences

        prefix_a = build_prefix_function(a)
        prefix_b = build_prefix_function(b)

        resa = kmp_search(a, s, prefix_a)
        resb = kmp_search(b, s, prefix_b)

        res = []
        print(resa, resb)
        i = 0
        j = 0
        while i < len(resa):
            while j < len(resb):
                if abs(resb[j] - resa[i]) <= k:
                    res.append(resa[i])
                    break
                elif j+1 < len(resb) and abs(resb[j+1] - resa[i]) < abs(resb[j] - resa[i]):
                    j+=1
                else:
                    break
            i+=1
        return res
