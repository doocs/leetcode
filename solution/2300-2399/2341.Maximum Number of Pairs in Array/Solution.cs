public class Solution {
    public int[] NumberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        foreach(int x in nums) {
            ++cnt[x];
        }
        int s = 0;
        foreach(int v in cnt) {
            s += v / 2;
        }
        return new int[] {s, nums.Length - s * 2};
    }
}