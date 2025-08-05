public class Solution {
    public bool DoesValidArrayExist(int[] derived) {
        return derived.Aggregate(0, (acc, x) => acc ^ x) == 0;
    }
}
