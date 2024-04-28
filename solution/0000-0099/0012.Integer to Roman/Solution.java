class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "I");
        hm.put(4, "IV");
        hm.put(5, "V");
        hm.put(9, "IX");
        hm.put(10, "X");
        hm.put(40, "XL");
        hm.put(50, "L");
        hm.put(90, "XC");
        hm.put(100, "C");
        hm.put(400, "CD");
        hm.put(500, "D");
        hm.put(900, "CM");
        hm.put(1000, "M");

        ArrayList<Integer> lst = new ArrayList<>();
        for(int key : hm.keySet()) {
            lst.add(key);
        }

        Collections.sort(lst, Collections.reverseOrder());

        for(int n : lst) {
            while(num >= n) {
                sb.append(hm.get(n));
                num -= n;
            }
        }
        
        
        return sb.toString();

        
    }
}


// in the previous code , it's not accepting all the testcases .
// this updated code could help in passing all the testcases .
// main reasons could be declaring the words separately ( by using put fun ) helped compiler to solve in some other testcases .
// my apologies to the Author .
