class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        vector<int> indices(n);

        iota(indices.begin(), indices.end(), 0);
        stack<int> st;

        auto lambda = [&](int i, int j) { return positions[i] < positions[j]; };

        sort(begin(indices), end(indices), lambda);

        vector<int> result;
        for (int currentIndex : indices) {
            if (directions[currentIndex] == 'R') {
                st.push(currentIndex);
            } else {
                while (!st.empty() && healths[currentIndex] > 0) {
                    int topIndex = st.top();
                    st.pop();

                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        st.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            if (healths[i] > 0) {
                result.push_back(healths[i]);
            }
        }
        return result;
    }
};
