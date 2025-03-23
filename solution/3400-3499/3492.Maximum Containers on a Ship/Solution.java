class Solution {
    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(n * n * w, maxWeight) / w;
    }
}
