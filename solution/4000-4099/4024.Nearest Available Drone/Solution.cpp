class Solution {
public:
    int nearestDrone(vector<vector<int>>& drones, vector<int>& target) {
        int ans = -1;
        int mn = INT_MAX;
        int tx = target[0], ty = target[1];

        for (int i = 0; i < drones.size(); i++) {
            int x = drones[i][0];
            int y = drones[i][1];
            int r = drones[i][2];

            int d = abs(x - tx) + abs(y - ty);

            if (d <= r && mn > d) {
                ans = i;
                mn = d;
            }
        }

        return ans;
    }
};