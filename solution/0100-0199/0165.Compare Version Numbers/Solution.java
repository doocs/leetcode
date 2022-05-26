class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version2 == null) return 0;
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        for (int i = 0,j = 0;i < v1.length || j < v2.length;i++,j++){
            int ver1 = 0, ver2 = 0;
            for (;i<v1.length && v1[i]!='.';i++) ver1 = ver1 * 10 + v1[i] - '0';
            for (;j<v2.length && v2[j]!='.';j++) ver2 = ver2 * 10 + v2[j] - '0';
            if(ver1 < ver2) return -1;
            else if(ver1 > ver2) return 1;
        }
        return 0;
    }
}