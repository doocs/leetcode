class Solution {
    public long maxNumber(long n) {
        return (1L << (63 - Long.numberOfLeadingZeros(n))) - 1;
    }
}