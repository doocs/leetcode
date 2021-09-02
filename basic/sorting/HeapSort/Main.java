import java.util.Scanner;

public class Main {
    private static int[] h = new int[100010];
    private static int size;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i = 1; i <= n; ++i) {
            h[i] = sc.nextInt();
        }
        size = n;
        for (int i = n / 2; i > 0; --i) {
            down(i);
        }
        while (m-- > 0) {
            System.out.print(h[1] + " ");
            h[1] = h[size--];
            down(1);
        }
    }
    
    public static void down(int u) {
        int t = u;
        if (u * 2 <= size && h[u * 2] < h[t]) {
            t = u * 2;
        }
        if (u * 2 + 1 <= size && h[u * 2 + 1] < h[t]) {
            t = u * 2 + 1;
        }
        if (t != u) {
            swap(t, u);
            down(t);
        }
    }
    
    public static void up(int u) {
        while (u / 2 > 0 && h[u / 2] > h[u]) {
            swap(u / 2, u);
            u /= 2;
        }
    }
    
    public static void swap(int i, int j) {
        int t = h[i];
        h[i] = h[j];
        h[j] = t;
    }
}