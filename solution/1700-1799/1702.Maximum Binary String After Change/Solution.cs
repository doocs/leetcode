public class Solution {
    public string MaximumBinaryString(string binary) {
        int k = binary.IndexOf('0');
        if (k == -1) {
            return binary;
        }
        k += binary.Substring(k + 1).Count(c => c == '0');
        return new string('1', k) + '0' + new string('1', binary.Length - k - 1);
    }
}