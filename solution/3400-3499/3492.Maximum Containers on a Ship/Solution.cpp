class Solution {
public:
    int maxContainers(int n, int w, int maxWeight) {
        return min(n * n * w, maxWeight) / w;
    }
};
