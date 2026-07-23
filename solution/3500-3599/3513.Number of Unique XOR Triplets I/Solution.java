class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        return n <= 2 ? n : 1 << (32 - Integer.numberOfLeadingZeros(n));
    }
}