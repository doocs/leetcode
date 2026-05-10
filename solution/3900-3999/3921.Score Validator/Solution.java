class Solution {
    public int[] scoreValidator(String[] events) {
        int score = 0;
        int counter = 0;
        for (String event : events) {
            if (event.matches("\\d+")) {
                score += Integer.parseInt(event);
            } else if (event.equals("W")) {
                if (++counter == 10) {
                    break;
                }
            } else {
                score++;
            }
        }
        return new int[] {score, counter};
    }
}
