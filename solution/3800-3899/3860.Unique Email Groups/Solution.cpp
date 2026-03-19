class Solution {
public:
    int uniqueEmailGroups(vector<string>& emails) {
        unordered_set<string> st;

        for (auto& email : emails) {
            int atPos = email.find('@');
            string local = email.substr(0, atPos);
            string domain = email.substr(atPos + 1);

            int plusPos = local.find('+');
            if (plusPos != string::npos) {
                local = local.substr(0, plusPos);
            }

            string cleaned;
            for (char c : local) {
                if (c != '.') {
                    cleaned += tolower(c);
                }
            }

            for (char& c : domain) {
                c = tolower(c);
            }

            st.insert(cleaned + domain);
        }

        return st.size();
    }
};
