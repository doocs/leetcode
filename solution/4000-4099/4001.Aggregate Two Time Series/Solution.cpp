class Solution {
public:
    vector<vector<int>> aggregateTimeSeries(vector<vector<int>>& series1, vector<vector<int>>& series2) {
        int m = series1.size(), n = series2.size();
        int i = 0, j = 0;
        vector<vector<int>> ans;

        while (i < m && j < n) {
            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 == t2) {
                ans.push_back({t1, v1 + v2});
                i++;
                j++;
            } else if (t1 < t2) {
                ans.push_back({t1, v1 + v2});
                i++;
            } else {
                ans.push_back({t2, v1 + v2});
                j++;
            }
        }

        while (i < m) {
            ans.push_back(series1[i]);
            i++;
        }

        while (j < n) {
            ans.push_back(series2[j]);
            j++;
        }

        return ans;
    }
};