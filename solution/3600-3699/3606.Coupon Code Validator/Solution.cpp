class Solution {
public:
    vector<string> validateCoupons(vector<string>& code, vector<string>& businessLine, vector<bool>& isActive) {
        vector<int> idx;
        unordered_set<string> bs = {"electronics", "grocery", "pharmacy", "restaurant"};

        for (int i = 0; i < code.size(); ++i) {
            const string& c = code[i];
            const string& b = businessLine[i];
            bool a = isActive[i];
            if (a && bs.count(b) && check(c)) {
                idx.push_back(i);
            }
        }

        sort(idx.begin(), idx.end(), [&](int i, int j) {
            if (businessLine[i] != businessLine[j]) return businessLine[i] < businessLine[j];
            return code[i] < code[j];
        });

        vector<string> ans;
        for (int i : idx) {
            ans.push_back(code[i]);
        }
        return ans;
    }

private:
    bool check(const string& s) {
        if (s.empty()) return false;
        for (char c : s) {
            if (!isalnum(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
};
