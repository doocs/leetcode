class Solution {
public:
    int twoCitySchedCost(vector<vector<int>>& costs) {
        int n = costs.size();
        vector<int> diff;
        int cityA = 0;
        int cityB = 0;
        for(int i=0;i<n;i++){
            cityA += costs[i][0];
            diff.push_back(costs[i][1]-costs[i][0]);
        }

        sort(diff.begin(), diff.end());

        for(int i=0;i<n/2;i++){
            cityB += diff[i];
        }

        return (cityA + cityB);
    }
};
