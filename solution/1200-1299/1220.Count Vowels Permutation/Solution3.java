class Solution {
    public int countVowelPermutation(int n) {
        final int mod = 1000000007;
        long countA = 1, countE = 1, countI = 1, countO = 1, countU = 1;
        for (int length = 1; length < n; length++) {
            // Calculate the next counts for each vowel based on the previous counts
            long nextCountA = countE;
            long nextCountE = (countA + countI) % mod;
            long nextCountI = (countA + countE + countO + countU) % mod;
            long nextCountO = (countI + countU) % mod;
            long nextCountU = countA;
            // Update the counts with the newly calculated values for the next length
            countA = nextCountA;
            countE = nextCountE;
            countI = nextCountI;
            countO = nextCountO;
            countU = nextCountU;
        }
        // Calculate the total count of valid strings for length n
        long totalCount = (countA + countE + countI + countO + countU) % mod;
        return (int) totalCount;
    }
}