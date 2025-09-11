public class Solution {
    public int MinChanges(int n, int k) {
        return (n & k) != k ? -1 : BitOperations.PopCount((uint)(n ^ k));
    }
}
