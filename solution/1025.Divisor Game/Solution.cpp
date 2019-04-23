class Solution {
    unordered_map<int, bool> mem;
public:
    bool divisorGame(int N) { 
        if (N == 1) return false;
        if (mem.count(N)) {
            return mem[N];
        }
        for (int i = 1; i < N; ++i) {
            if (N % i == 0) {
                if (divisorGame(N-i) == false) {
                    mem[N] = true;
                    return true;
                }
            }
        }
        mem[N] = false;
        return false;
    }
};