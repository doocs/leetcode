class Solution {
public:
    int maxDiff(int num) {
        auto replace = [](string& s, char a, char b) {
            for (auto& c : s) {
                if (c == a) {
                    c = b;
                }
            }
        };
        string a = to_string(num);
        string b = a;
        for (int i = 0; i < a.size(); ++i) {
            if (a[i] != '9') {
                replace(a, a[i], '9');
                break;
            }
        }
        if (b[0] != '1') {
            replace(b, b[0], '1');
        } else {
            for (int i = 1; i < b.size(); ++i) {
                if (b[i] != '0' && b[i] != '1') {
                    replace(b, b[i], '0');
                    break;
                }
            }
        }
        return stoi(a) - stoi(b);
    }
};