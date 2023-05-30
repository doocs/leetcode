class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        sort(weight.begin(), weight.end());
        int s = 0;
        for (int i = 0; i < weight.size(); ++i) {
            s += weight[i];
            if (s > 5000) {
                return i;
            }
        }
        return weight.size();
    }
};