class Solution {
public:
    string toHexspeak(string num) {
        stringstream ss;
        ss << hex << stol(num);
        string t = ss.str();
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] >= '2' && t[i] <= '9') return "ERROR";
            if (t[i] == '0')
                t[i] = 'O';
            else if (t[i] == '1')
                t[i] = 'I';
            else
                t[i] = t[i] - 32;
        }
        return t;
    }
};