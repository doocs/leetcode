class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        List<int[]> names = new ArrayList<>(); // names.get(k)[i] is equal for equal windows of
                                               // length 2^k starting at i
        names.add(nums);
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            long[] pairs = pairs(names.get(names.size() - 1), half);
            long[] ids = Arrays.stream(pairs)
                             .sorted()
                             .distinct()
                             .toArray(); // a pair's number: its place among the different pairs
            names.add(
                Arrays.stream(pairs).mapToInt(pair -> Arrays.binarySearch(ids, pair)).toArray());
        }

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (found(names, mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    private boolean found(List<int[]> names, int length) { // is some window of this length unique?
        int k = 31
            - Integer.numberOfLeadingZeros(
                length); // its first 2^k elements and its last 2^k cover it
        long[] pairs = pairs(names.get(k), length - (1 << k));
        Arrays.sort(pairs); // equal pairs are neighbors now
        for (int i = 0; i < pairs.length; i++)
            if ((i == 0 || pairs[i - 1] != pairs[i])
                && (i + 1 == pairs.length || pairs[i] != pairs[i + 1]))
                return true;
        return false;
    }

    private long[] pairs(int[] block, int shift) { // every (block[i], block[i + shift]) as one long
        long[] pairs = new long[block.length - shift];
        for (int i = shift; i < block.length; i++)
            pairs[i - shift] = (long) block[i - shift] << 32 | block[i];
        return pairs;
    }
}