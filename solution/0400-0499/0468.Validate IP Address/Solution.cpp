class Solution {
public:
    string validIPAddress(string queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }
        if (isIPv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

private:
    bool isIPv4(const string& s) {
        if (s.empty() || s.back() == '.') {
            return false;
        }
        vector<string> ss = split(s, '.');
        if (ss.size() != 4) {
            return false;
        }
        for (const string& t : ss) {
            if (t.empty() || (t.size() > 1 && t[0] == '0')) {
                return false;
            }
            int x = convert(t);
            if (x < 0 || x > 255) {
                return false;
            }
        }
        return true;
    }

    bool isIPv6(const string& s) {
        if (s.empty() || s.back() == ':') {
            return false;
        }
        vector<string> ss = split(s, ':');
        if (ss.size() != 8) {
            return false;
        }
        for (const string& t : ss) {
            if (t.size() < 1 || t.size() > 4) {
                return false;
            }
            for (char c : t) {
                if (!isxdigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    int convert(const string& s) {
        int x = 0;
        for (char c : s) {
            if (!isdigit(c)) {
                return -1;
            }
            x = x * 10 + (c - '0');
            if (x > 255) {
                return x;
            }
        }
        return x;
    }

    vector<string> split(const string& s, char delimiter) {
        vector<string> tokens;
        string token;
        istringstream iss(s);
        while (getline(iss, token, delimiter)) {
            tokens.push_back(token);
        }
        return tokens;
    }
};