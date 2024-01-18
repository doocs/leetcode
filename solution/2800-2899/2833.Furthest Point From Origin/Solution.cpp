class Solution {
public:
    int furthestDistanceFromOrigin(string moves) {
        auto cnt = [&](char c) {
            return count(moves.begin(), moves.end(), c);
        };
        return abs(cnt('L') - cnt('R')) + cnt('_');
    }
};