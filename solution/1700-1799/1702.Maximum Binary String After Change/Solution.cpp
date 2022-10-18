class Solution {
public:
    string maximumBinaryString(string binary) {
        int k = binary.find('0');
        if (k == binary.npos) return binary;
        int n = binary.size();
        for (int i = k + 1; i < n; ++i) {
            if (binary[i] == '0') {
                ++k;
            }
        }
        return string(k, '1') + '0' + string(n - k - 1, '1');
    }
};