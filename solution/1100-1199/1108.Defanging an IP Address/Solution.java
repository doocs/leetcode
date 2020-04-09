class Solution {
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}
