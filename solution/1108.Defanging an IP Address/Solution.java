class Solution {
    public String defangIPaddr(String address) {

        String input[] = address.split("\\.");
        String result = "";
        for(int i = 0; i<input.length-1; i++){
            result = result + input[i] + "[.]";
        }
        result = result + input[input.length-1];
        return result;
    }
}
