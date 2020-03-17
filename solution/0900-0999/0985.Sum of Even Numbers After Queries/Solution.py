class Solution:
    def sumEvenAfterQueries(self, A: 'List[int]', queries: 'List[List[int]]') -> 'List[int]':
        base = sum(filter(lambda x: x % 2 == 0, A))
        ans = []
        for val, index in queries:
            if A[index] % 2 == 0:
                base -= A[index]
                A[index] += val
                if A[index] % 2 == 0:
                    base += A[index]
            else:
                A[index] += val
                if A[index] % 2 == 0:
                    base += A[index]
            ans.append(base)
        return ans
