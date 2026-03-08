class Solution {
public:
    int minimumIndex(vector<int>& capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.size(); ++i) {
            int x = capacity[i];
            if (x >= itemSize && (ans == -1 || x < capacity[ans])) {
                ans = i;
            }
        }
        return ans;
    }
};
