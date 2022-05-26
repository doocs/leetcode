public class BinarySearch {
    
    private static int search(int[] nums, int low, int high, int val) {
        while (low <= high) {
            int mid = low + ((high -low) >> 1);
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int searchRecursive(int[] nums, int low, int high, int val) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                return searchRecursive(nums, mid + 1, high, val);
            } else {
                return searchRecursive(nums, low, mid - 1, val);
            }
        }
        return -1;
    }

    /**
     * 二分查找(非递归)
     *
     * @param nums 有序数组
     * @param val 要查找的值
     * @return 要查找的值在数组中的索引位置
     */
    private static int search(int[] nums, int val) {
        return search(nums, 0, nums.length - 1, val);
    }

    /**
     * 二分查找(递归)
     *
     * @param nums 有序数组
     * @param val 要查找的值
     * @return 要查找的值在数组中的索引位置
     */
    private static int searchRecursive(int[] nums, int val) {
        return searchRecursive(nums, 0, nums.length - 1, val);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 7, 8, 9};

        // 非递归查找
        int r1 = search(nums, 7);
        System.out.println(r1);

        // 递归查找
        int r2 = search(nums, 7);
        System.out.println(r2);
    }
}