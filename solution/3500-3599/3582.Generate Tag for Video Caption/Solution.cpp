class Solution {
public:
    string generateTag(string caption) {
        istringstream iss(caption);
        string word;
        ostringstream oss;
        oss << "#";
        bool first = true;
        while (iss >> word) {
            transform(word.begin(), word.end(), word.begin(), ::tolower);
            if (first) {
                oss << word;
                first = false;
            } else {
                word[0] = toupper(word[0]);
                oss << word;
            }
            if (oss.str().length() >= 100) {
                break;
            }
        }

        string ans = oss.str();
        if (ans.length() > 100) {
            ans = ans.substr(0, 100);
        }
        return ans;
    }
};
