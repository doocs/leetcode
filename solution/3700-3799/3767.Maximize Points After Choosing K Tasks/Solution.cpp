class Solution {
public:
    long long maxPoints(vector<int>& technique1, vector<int>& technique2, int k) {
        int n = technique1.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);

        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return (technique1[j] - technique2[j]) < (technique1[i] - technique2[i]);
        });

        long long ans = 0;
        for (int x : technique2) {
            ans += x;
        }

        for (int i = 0; i < k; i++) {
            int index = idx[i];
            ans -= technique2[index];
            ans += technique1[index];
        }

        for (int i = k; i < n; i++) {
            int index = idx[i];
            if (technique1[index] >= technique2[index]) {
                ans -= technique2[index];
                ans += technique1[index];
            }
        }

        return ans;
    }
};
