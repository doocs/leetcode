class Solution {
public:
    int minNumberOfHours(int x, int y, vector<int>& energy, vector<int>& experience) {
        int ans = 0;
        for (int i = 0; i < energy.size(); ++i) {
            int dx = energy[i], dy = experience[i];
            if (x <= dx) {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if (y <= dy) {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }
        return ans;
    }
};
