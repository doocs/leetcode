class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return max(n - i - 1, j);
    }
};
