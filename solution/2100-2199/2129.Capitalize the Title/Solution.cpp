class Solution {
public:
    string capitalizeTitle(string title) {
        transform(title.begin(), title.end(), title.begin(), ::tolower);
        istringstream ss(title);
        string ans;
        while (ss >> title) {
            if (title.size() > 2) title[0] = toupper(title[0]);
            ans += title;
            ans += " ";
        }
        ans.pop_back();
        return ans;
    }
};