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
            int left = 0, right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[left] != x) {
                System.out.println("-1 -1");
            } else {
                int t = left;
                left = 0;
                right = n - 1;
                while (left < right) {
                    int mid = (left + right + 1) >> 1;
                    if (nums[mid] <= x) {
                        left = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                System.out.printf("%d %d\n", t, left);
            }
        }
    }
}