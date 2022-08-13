class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, int> m;
        for (auto& e : reservedSeats) {
            int i = e[0], j = 10 - e[1];
            m[i] |= (1 << j);
        }
        vector<int> masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - m.size()) << 1;
        for (auto& [_, v] : m) {
            for (int& mask : masks) {
                if ((v & mask) == 0) {
                    v |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
};