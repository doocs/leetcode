public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        if (nums.Length == 0) {
            return new int[]{};
        }
        int[] array = new int[nums.Length - (k - 1)];
        Queue<int> queue = new Queue<int>();
        int index = 0;
        for (int i = 0; i < nums.Length; i++) {
            queue.Enqueue(nums[i]);
            if (queue.Count == k) {
                array[index] = queue.Max();
                queue.Dequeue();
                index++;
            }
        }
        return array;
    }
}