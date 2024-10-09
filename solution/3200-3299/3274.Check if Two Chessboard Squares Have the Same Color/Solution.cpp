class Solution {
public:
    bool checkTwoChessboards(string coordinate1, string coordinate2) {
        int x = coordinate1[0] - coordinate2[0];
        int y = coordinate1[1] - coordinate2[1];
        return (x + y) % 2 == 0;
    }
};
