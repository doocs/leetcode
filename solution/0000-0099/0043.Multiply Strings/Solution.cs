public class Solution {
    public string Multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }

        int m = num1.Length;
        int n = num2.Length;
        int[] arr = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int a = num1[i] - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = num2[j] - '0';
                arr[i + j + 1] += a * b;
            }
        }

        for (int i = arr.Length - 1; i > 0; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }

        int index = 0;
        while (index < arr.Length && arr[index] == 0) {
            index++;
        }

        StringBuilder ans = new StringBuilder();
        for (; index < arr.Length; index++) {
            ans.Append(arr[index]);
        }

        return ans.ToString();
    }
}