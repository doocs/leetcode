class Solution {
public:
    int numUniqueEmails(vector<string>& emails) {
        unordered_set<string> s;
        for (auto& email : emails) {
            int i = email.find('@');
            string local = email.substr(0, i);
            string domain = email.substr(i + 1);
            i = local.find('+', 0);
            if (~i) local = local.substr(0, i);
            while (~(i = local.find('.', 0)))
                local.erase(local.begin() + i);
            s.insert(local + "@" + domain);
        }
        return s.size();
    }
};