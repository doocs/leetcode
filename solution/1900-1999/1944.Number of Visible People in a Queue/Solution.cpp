class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> ans(n);
        stack<int> st;

        for(int i = n  - 1; i >= 0; --i) {
            while(!st.empty()) {
                ans[i]++;
                if(heights[i] > st.top()) {
                    st.pop();
                }

                else {
                    break;
                }
            }
            st.push(heights[i]);
        }
        return ans;
    }
};