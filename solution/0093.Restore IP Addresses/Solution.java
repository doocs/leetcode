class Solution {
    private List<String> result;
    private int length;
    public List<String> restoreIpAddresses(String s) {
        result  = new ArrayList<>();
        length = s.length();
        int[] ip = new int[4];
        restoreIpAddresses(s,0,ip,0);
        return result;
    }
    private void restoreIpAddresses(String s, int si, int[] ip, int pi) {
        int sl = length - si , pl = 3 - pi , i = -1;
        String pfx = null;
        while (si< length){
            int num = s.charAt(si++) - '1' + 1;
            if (i==0) break;
            i = i == -1 ? num : i * 10 + num;
            sl--;
            if (i>255) break;
            if (sl < pl || sl > pl * 3) continue;
            if (pi==3){
                if (pfx==null){
                    StringBuilder pfxBuilder = new StringBuilder();
                    for (int j = 0; j < ip.length-1; j++) pfxBuilder.append(ip[j]).append('.');
                    pfx = pfxBuilder.toString();
                }
                result.add(pfx + i);
            }
            ip[pi] = i;
            restoreIpAddresses(s,si,ip,pi+1);
        }
    }
}