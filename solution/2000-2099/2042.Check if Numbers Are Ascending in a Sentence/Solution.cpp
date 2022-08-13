class Solution {
public:
    bool areNumbersAscending(string s) {
        int curr = 0;
        istringstream is(s);
        string t;
        while (is >> t) {
            if (isdigit(t[0])) {
                int x = stoi(t);
                if (curr >= x) return false;
                curr = x;
            }
        }
        return true;
    }
};