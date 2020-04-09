class Solution {
    public String multiply(String num1, String num2) {
        char[] chars1 = num1.toCharArray(),chars2 = num2.toCharArray();
        int[] result = new int[chars1.length+chars2.length];
        int pow = result.length-1;
        for (int i = chars1.length - 1; i >= 0; i--) {
            int a = chars1[i] - '0';
            int j = 0,bit = pow;
            for (int i1 = chars2.length - 1; i1 >= 0; i1--) {
                int b = chars2[i1] -'0';
                j = a*b + j + result[bit];
                result[bit--] = j%10;
                j = j/10;
            }
            while (j!=0){
                j += result[bit];
                result[bit--] = j%10;
                j = j/10;
            }
            pow--;
        }
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < result.length; i++) if (result[i] != 0) break;
        for (; i < result.length; i++) builder.append(result[i]);
        return builder.length()==0? "0" : builder.toString();
    }
}