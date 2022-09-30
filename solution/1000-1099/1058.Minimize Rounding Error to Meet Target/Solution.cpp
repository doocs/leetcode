class Solution {
public:
    string minimizeError(vector<string>& prices, int target) {
        int mi = 0;
        vector<double> arr;
        for (auto& p : prices) {
            double price = stod(p);
            mi += (int) price;
            double d = price - (int) price;
            if (d > 0) {
                arr.push_back(d);
            }
        }
        if (target < mi || target > mi + arr.size()) {
            return "-1";
        }
        int d = target - mi;
        sort(arr.rbegin(), arr.rend());
        double ans = d;
        for (int i = 0; i < d; ++i) {
            ans -= arr[i];
        }
        for (int i = d; i < arr.size(); ++i) {
            ans += arr[i];
        }
        string s = to_string(ans);
        return s.substr(0, s.find('.') + 4);
    }
};