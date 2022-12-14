class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        for (int i = 0; i < n; ++i) {
            if (change[num[i] - '0'] > num[i] - '0') {
                for (; i < n && change[num[i] - '0'] >= num[i] - '0'; ++i) {
                    num[i] = change[num[i] - '0'] + '0';
                }
                break;
            }
        }
        return num;
    }
};