class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        if (colors[0] != colors[n - 1]) return n - 1;
        int i = 0, j = n;
        while (colors[++i] == colors[0])
            ;
        while (colors[--j] == colors[0])
            ;
        return max(n - i - 1, j);
    }
};