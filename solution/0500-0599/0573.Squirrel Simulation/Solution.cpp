class Solution {
public:
    int minDistance(int height, int width, vector<int>& tree, vector<int>& squirrel, vector<vector<int>>& nuts) {
        int ans = INT_MAX;
        int s = 0;
        for (auto& a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (auto& a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = min(ans, s + d - c * 2);
        }
        return ans;
    }

    int f(vector<int>& a, vector<int>& b) {
        return abs(a[0] - b[0]) + abs(a[1] - b[1]);
    }
};