class Solution {
public:
    long gcd(long x, long y) {
        while (y != 0) {
            if (x < y)
                swap(x, y);
            long tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }

    long lcm(long x, long y) { return x * y / gcd(x, y); }

    long f(int num, int a, int b, int c) {
        long sumabc = long(num / a) + num / b + num / c;
        long intersections = long(num / lcm(a, b)) + num / lcm(a, c) + num / lcm(b, c) - num / lcm(lcm(a, b), c);
        return sumabc - intersections;
    }

    int nthUglyNumber(int n, int a, int b, int c) {
        int left = 1, right = int(2e9);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(mid, a, b, c) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};