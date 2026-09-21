class Solution {
public:
    string countOfAtoms(string formula) {
        unordered_map<string, int> cnt;
        vector<int> stk;
        int multiplier = 1, freq = 0;
        for (int i = formula.size() - 1; i >= 0; --i) {
            if (formula[i] >= 'a' && formula[i] <= 'z') {
                int end = i--;
                while (i >= 0 && formula[i] >= 'a' && formula[i] <= 'z') {
                    --i;
                }
                cnt[formula.substr(i, end - i + 1)] += max(freq, 1) * multiplier;
                freq = 0;
            } else if (formula[i] >= 'A' && formula[i] <= 'Z') {
                cnt[string(1, formula[i])] += max(freq, 1) * multiplier;
                freq = 0;
            } else if (formula[i] >= '0' && formula[i] <= '9') {
                freq = formula[i] - '0';
                int p = 10;
                while (i - 1 >= 0 && formula[i - 1] >= '0' && formula[i - 1] <= '9') {
                    freq += p * (formula[--i] - '0');
                    p *= 10;
                }
            } else if (formula[i] == ')') {
                stk.push_back(multiplier);
                multiplier *= max(freq, 1);
                freq = 0;
            } else {
                multiplier = stk.back();
                stk.pop_back();
            }
        }
        vector<string> keys;
        for (auto& [k, _] : cnt) {
            keys.push_back(k);
        }
        sort(keys.begin(), keys.end());
        string ans;
        for (auto& key : keys) {
            ans += key;
            if (cnt[key] > 1) {
                ans += to_string(cnt[key]);
            }
        }
        return ans;
    }
};
