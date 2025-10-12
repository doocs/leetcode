class Solution:
    def maxTransactions(self, transactions: List[int]) -> int:
        st = SortedList()
        s = 0
        ans = len(transactions)
        for x in transactions:
            s += x
            st.add(x)
            while s < 0:
                y = st.pop(0)
                s -= y
                ans -= 1
        return ans
