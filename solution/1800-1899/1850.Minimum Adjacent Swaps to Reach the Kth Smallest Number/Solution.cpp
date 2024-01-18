class Solution {
public:
    int getMinSwaps(string num, int k) {
        string s = num;
        for (int i = 0; i < k; ++i) {
            next_permutation(begin(s), end(num));
        }
        vector<int> d[10];
        int n = num.size();
        for (int i = 0; i < n; ++i) {
            d[num[i] - '0'].push_back(i);
        }
        int idx[10]{};
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = d[s[i] - '0'][idx[s[i] - '0']++];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[i]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};