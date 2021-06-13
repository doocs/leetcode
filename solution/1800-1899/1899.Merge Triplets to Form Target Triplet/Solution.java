class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int maxA = 0, maxB = 0, maxC = 0;
        for (int[] triplet : triplets) {
            int a = triplet[0], b = triplet[1], c = triplet[2];
            if (a <= target[0] && b <= target[1] && c <= target[2]) {
                maxA = Math.max(maxA, a);
                maxB = Math.max(maxB, b);
                maxC = Math.max(maxC, c);
            }
        }
        return maxA == target[0] && maxB == target[1] && maxC == target[2];
    }
}