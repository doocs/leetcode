class Solution {
public:
    bool hasMatch(string s, string p) {
        int i = 0;
        int pos = 0;
        int start = 0, end;
        while ((end = p.find("*", start)) != string::npos) {
            string t = p.substr(start, end - start);
            pos = s.find(t, i);
            if (pos == string::npos) {
                return false;
            }
            i = pos + t.length();
            start = end + 1;
        }
        string t = p.substr(start);
        pos = s.find(t, i);
        if (pos == string::npos) {
            return false;
        }
        return true;
    }
};
