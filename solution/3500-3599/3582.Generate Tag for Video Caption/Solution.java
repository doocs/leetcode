class Solution {
    public String generateTag(String caption) {
        String[] words = caption.trim().split("\\s+");
        StringBuilder sb = new StringBuilder("#");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }

            word = word.toLowerCase();
            if (i == 0) {
                sb.append(word);
            } else {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
            }

            if (sb.length() >= 100) {
                break;
            }
        }

        return sb.length() > 100 ? sb.substring(0, 100) : sb.toString();
    }
}
