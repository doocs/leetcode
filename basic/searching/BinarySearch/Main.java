import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), q = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        while (q-- > 0) {
            int x = sc.nextInt();
            int low = 0, high = n - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (nums[mid] >= x) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            if (nums[low] != x) {
                System.out.println("-1 -1");
            } else {
                int t = low;
                low = 0;
                high = n - 1;
                while (low < high) {
                    int mid = (low + high + 1) >> 1;
                    if (nums[mid] <= x) {
                        low = mid;
                    } else {
                        high = mid - 1;
                    }
                }
                System.out.printf("%d %d\n", t, low);
            }
        }
    }
}