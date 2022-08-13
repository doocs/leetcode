class Solution {
public:
    bool checkIfPangram(string sentence) {
        int res = 0;
        for (char c : sentence) {
            res |= (1 << (c - 'a'));
            if (res == 0x3ffffff) return true;
        }
        return false;
    }
};