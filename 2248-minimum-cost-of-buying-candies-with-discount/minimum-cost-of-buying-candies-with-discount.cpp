class Solution {
public:
    int minimumCost(vector<int>& cost) {
        sort(cost.begin(), cost.end());
        int total = 0;
        int take = 0;
        for (int i = cost.size() - 1; i >= 0; i--){
            if (take == 2){
                take = 0;
            } else {
                total += cost[i];
                take += 1;
            }
        }
        return total;
    }
};