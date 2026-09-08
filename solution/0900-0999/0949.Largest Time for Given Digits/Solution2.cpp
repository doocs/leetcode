class Solution {
public:
    string largestTimeFromDigits(vector<int>& arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        if (ans < 0) return "";
        int h = ans / 60, m = ans % 60;
        return to_string(h / 10) + to_string(h % 10) + ":" + to_string(m / 10) + to_string(m % 10);
    }
};