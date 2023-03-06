class Solution {
public:
    vector<int> recoverArray(int n, vector<int>& sums) {
        int m = *min_element(sums.begin(), sums.end());
        m = -m;
        multiset<int> st;
        for (int x : sums) {
            st.insert(x + m);
        }
        st.erase(st.begin());
        vector<int> ans;
        ans.push_back(*st.begin());
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < 1 << i; ++j) {
                if (j >> (i - 1) & 1) {
                    int s = 0;
                    for (int k = 0; k < i; ++k) {
                        if (j >> k & 1) {
                            s += ans[k];
                        }
                    }
                    st.erase(st.find(s));
                }
            }
            ans.push_back(*st.begin());
        }
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    s += ans[j];
                }
            }
            if (s == m) {
                for (int j = 0; j < n; ++j) {
                    if (i >> j & 1) {
                        ans[j] = -ans[j];
                    }
                }
                break;
            }
        }
        return ans;
    }
};