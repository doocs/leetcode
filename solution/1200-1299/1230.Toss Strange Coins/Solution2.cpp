class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        double f[target + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (double p : prob) {
            for (int j = target; j >= 0; --j) {
                f[j] *= (1 - p);
                if (j > 0) {
                    f[j] += p * f[j - 1];
                }
            }
        }
        return f[target];
    }
};