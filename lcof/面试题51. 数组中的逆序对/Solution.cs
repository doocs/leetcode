public class Solution {
    int[] nums, aux;
    public int ReversePairs(int[] nums) {
        int n = nums.Length;
        if (n == 0) {
            return 0;
        }
        this.nums = nums;
        aux = new int[n];
        return Merge(0, n - 1);
    }

    int Merge(int l, int r)
    {
        if (l == r) {
            return 0;
        }
        var mid = (l + r) >> 1;
        int ans = Merge(l, mid) + Merge(mid + 1, r);
        for (int k = l; k <= r; k++) {
            aux[k] = nums[k];
        }
        for (int i = l, j = mid + 1, k = l; k <= r; k++)
        {
            if (i == mid + 1) {
                nums[k] = aux[j++];
            } else if (j == r + 1) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++];
            } else {
                nums[k] = aux[j++];
                ans += mid + 1 - i;
            }
        }
        return ans;
    }
    
}
