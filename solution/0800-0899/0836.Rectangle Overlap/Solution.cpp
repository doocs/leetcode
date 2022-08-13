class Solution {
public:
    bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {
        int l1 = rec1[1], u1 = rec1[0], r1 = rec1[3], d1 = rec1[2];
        int l2 = rec2[1], u2 = rec2[0], r2 = rec2[3], d2 = rec2[2];

        //printf("1: (%d,%d), (%d,%d)\n", l1, u1, r1, d1) ;
        //printf("2: (%d,%d), (%d,%d)\n", l2, u2, r2, d2) ;

        if (l1 < r2 && u1 < d2 && l2 < r1 && u2 < d1)
            return true;
        return false;
    }
};