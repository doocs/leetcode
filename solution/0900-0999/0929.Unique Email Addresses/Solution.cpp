class Solution {
public:
    int numUniqueEmails(vector<string>& emails) {
        unordered_set<string> s;
        for (const string& email : emails) {
            size_t atPos = email.find('@');
            string local = email.substr(0, atPos);
            string domain = email.substr(atPos + 1);
            string t;
            for (char c : local) {
                if (c == '.') {
                    continue;
                }
                if (c == '+') {
                    break;
                }
                t.push_back(c);
            }
            s.insert(t + "@" + domain);
        }
        return s.size();
    }
};
