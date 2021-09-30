public class Solution {
    public int MinEatingSpeed(int[] piles, int h) {
        int left = 1, right = piles.Max();
        while (left < right)
        {
            int mid = (left + right) >> 1;
            int s = 0;
            foreach (int pile in piles)
            {
                s += (pile + mid - 1) / mid;
            }
            if (s <= h)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }
}