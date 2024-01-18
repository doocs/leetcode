class Solution {
public:
    int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if ((B & 1) == 1) {
            return (multiply(A, B >> 1) << 1) + A;
        }
        return multiply(A, B >> 1) << 1;
    }
};