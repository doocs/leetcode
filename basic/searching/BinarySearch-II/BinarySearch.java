public class BinarySearch {

    public static int search1(int[] nums, int val) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < val) {
                low = mid + 1;
            } else if (nums[mid] > val) {
                high = mid - 1;
            } else {
                // 如果nums[mid]是第一个元素，或者nums[mid-1]不等于val
                // 说明nums[mid]就是第一个值为给定值的元素
                if (mid == 0 || nums[mid - 1] != val) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int search2(int[] nums, int val) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < val) {
                low = mid + 1;
            } else if (nums[mid] > val) {
                high = mid - 1;
            } else {
                // 如果nums[mid]是最后一个元素，或者nums[mid+1]不等于val
                // 说明nums[mid]就是最后一个值为给定值的元素
                if (mid == n - 1 || nums[mid + 1] != val) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int search3(int[] nums, int val) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < val) {
                low = mid + 1;
            } else {
                // 如果nums[mid]是第一个元素，或者nums[mid-1]小于val
                // 说明nums[mid]就是第一个大于等于给定值的元素
                if (mid == 0 || nums[mid - 1] < val) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int search4(int[] nums, int val) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > val) {
                high = mid - 1;
            } else {
                // 如果nums[mid]是最后一个元素，或者nums[mid+1]大于val
                // 说明nums[mid]就是最后一个小于等于给定值的元素
                if (mid == n - 1 || nums[mid + 1] > val) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2, 8, 9};
        System.out.println(search1(nums, 2)); // 1
        System.out.println(search2(nums, 2)); // 4
        System.out.println(search3(nums, 2)); // 1
        System.out.println(search4(nums, 2)); // 4
    }
}
