class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domains = new HashMap<>();
        for (String domain : cpdomains) {
            String[] t = domain.split(" ");
            int count = Integer.parseInt(t[0]);
            String[] subs = t[1].split("\\.");
            String cur = "";
            for (int i = subs.length - 1; i >= 0; --i) {
                cur = subs[i] + (i == subs.length - 1 ? "" : ".") + cur;
                domains.put(cur, domains.getOrDefault(cur, 0) + count);
            }
        }
        List<String> res = new ArrayList<>();
        domains.forEach((domain, count) -> {
            res.add(count + " " + domain);
        });
        return res;
    }
}