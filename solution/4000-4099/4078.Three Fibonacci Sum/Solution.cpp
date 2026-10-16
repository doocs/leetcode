class Solution {
    static unordered_set<int> f;

public:
    bool threeFibonacciSum(int n) {
        return n % 2 == 0 && f.count(n / 2);
    }
};

unordered_set<int> Solution::f = [] {
    unordered_set<int> f;
    int a = 0, b = 1;
    while (b < 1000000001) {
        int c = a + b;
        a = b;
        b = c;
        f.insert(b);
    }
    return f;
}();
