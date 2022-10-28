class Solution {
public:
    int countMatches(vector<vector<string>>& items, string ruleKey, string ruleValue) {
        char x = ruleKey[0];
        int i = x == 't' ? 0 : (x == 'c' ? 1 : 2);
        return count_if(items.begin(), items.end(), [&](auto& v) { return v[i] == ruleValue; });
    }
};