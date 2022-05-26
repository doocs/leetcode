class Solution {
public:
    int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
};