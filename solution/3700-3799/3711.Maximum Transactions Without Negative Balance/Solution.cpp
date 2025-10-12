class Solution {
public:
    int maxTransactions(vector<int>& transactions) {
        multiset<int> st;
        int ans = transactions.size();
        long long s = 0;
        for (int x : transactions) {
            s += x;
            st.insert(x);
            while (s < 0) {
                auto it = st.begin();
                int y = *it;
                st.erase(it);
                s -= y;
                --ans;
            }
        }
        return ans;
    }
};
