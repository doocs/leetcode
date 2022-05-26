class Solution {
public:
    bool isUnique(string astr) {
        int bitmap = 0;
        for (char c : astr) {
            int pos = c - 'a';
            if ((bitmap & (1 << pos)) != 0) {
                return false;
            }
            bitmap |= (1 << pos);
        }
        return true;
    }
};