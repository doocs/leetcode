public class Solution {
    public int[] SingleNumbers(int[] nums) {
        int eor = 0;
        foreach(var num in nums) {
            eor ^= num;
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        foreach(var num in nums) {
            if ((num & diff) == 0) {
                a ^= num;
            }
        }
        int b = eor ^ a;
        
        return new int[]{a, b};
    }
}