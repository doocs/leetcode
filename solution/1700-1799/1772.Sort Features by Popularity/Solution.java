class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Set<String> featureSet = new HashSet<>();
        Map<String, Integer> order = new HashMap<>();
        for (int i = 0; i < features.length; ++i) {
            featureSet.add(features[i]);
            order.put(features[i], i);
        }

        Map<String, Integer> counter = new HashMap<>();
        for (String resp : responses) {
            Set<String> s = new HashSet<>();
            String[] words = resp.split(" ");
            for (String word : words) {
                s.add(word);
            }
            for (String word : s) {
                if (featureSet.contains(word)) {
                    counter.put(word, counter.getOrDefault(word, 0) + 1);
                }
            }
        }
        String[] copyFeatures = Arrays.copyOf(features, features.length);
        Arrays.sort(copyFeatures, (a, b) -> {
            int diff = counter.getOrDefault(b, 0) - counter.getOrDefault(a, 0);
            return diff == 0 ? order.get(a) - order.get(b) : diff;
        });
        return copyFeatures;
    }
}