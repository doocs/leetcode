class Solution {
public:
    int numSpecialEquivGroups(vector<string>& words) {
        unordered_set<string> s;
        for (auto& word : words) {
            string a = "", b = "";
            for (int i = 0; i < word.size(); ++i) {
                if (i & 1)
                    a += word[i];
                else
                    b += word[i];
            }
            sort(a.begin(), a.end());
            sort(b.begin(), b.end());
            s.insert(a + b);
        }
        return s.size();
    }
};