class Solution {
public:
    bool squareIsWhite(string coordinates) {
        int x = coordinates[0] - 'a' + 1;
        int y = coordinates[1] - '0';
        return ((x + y) & 1) == 1;
    }
};