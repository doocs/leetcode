class Solution {
public:
    vector<int> recoverOrder(vector<int>& order, vector<int>& friends) {
        int n = order.size();
        vector<int> d(n + 1);
        for (int i = 0; i < n; ++i) {
            d[order[i]] = i;
        }
        sort(friends.begin(), friends.end(), [&](int a, int b) {
            return d[a] < d[b];
        });
        return friends;
    }
};
