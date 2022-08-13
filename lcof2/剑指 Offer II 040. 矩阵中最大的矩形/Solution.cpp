class Solution {
public:
    int h[210];
    int l[210], r[210];
    int maximalRectangle(vector<string>& matrix) {
        int n = matrix.size();
        if (n == 0) return 0;
        int m = matrix[0].size();
        int ans = 0;
        stack<int> st;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                h[j] = (matrix[i][j] == '1' ? h[j] + 1 : 0);
                while (st.size() && h[j] <= h[st.top()]) {
                    ans = max(ans, (j - l[st.top()] - 1) * h[st.top()]);
                    st.pop();
                }
                if (st.size())
                    l[j] = st.top();
                else
                    l[j] = -1;
                st.push(j);
            }
            while (st.size()) {
                ans = max(ans, (m - 1 - l[st.top()]) * h[st.top()]);
                st.pop();
            }
        }
        return ans;
    }
};
