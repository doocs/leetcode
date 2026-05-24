class Solution {
    public int passwordStrength(String password) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for(int i=0;i<password.length();i++){
            char c = password.charAt(i);
            if(set.contains(c)){
                continue;
            }
            set.add(c);
            if(c >= 'a' && c <= 'z') count += 1;
            else if(c >= 'A' && c <= 'Z') count += 2;
            else if(c >= '0' && c <= '9') count+=3;
            else if(c == '!' || c == '@' || c == '#' || c == '$') count+=5;
        }
        return count;
    }
}
