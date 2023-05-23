class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, int> d;
        for (auto& e : reservedSeats) {
            int i = e[0], j = e[1];
            d[i] |= 1 << (10 - j);
        }
        int masks[3] = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - d.size()) * 2;
        for (auto& [_, x] : d) {
            for (int& mask : masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
};