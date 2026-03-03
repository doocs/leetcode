class Solution {
    public int uniqueEmailGroups(String[] emails) {
        Set<String> st = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            int plusIndex = local.indexOf('+');
            if (plusIndex != -1) {
                local = local.substring(0, plusIndex);
            }

            local = local.replace(".", "").toLowerCase();
            domain = domain.toLowerCase();

            String normalized = local + domain;
            st.add(normalized);
        }

        return st.size();
    }
}
