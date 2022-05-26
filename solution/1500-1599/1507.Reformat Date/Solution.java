class Solution {
    public String reformatDate(String date) {
        Map<String, Integer> mapper = new HashMap<>();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; ++i) {
            mapper.put(months[i], i + 1);
        }
        String[] split = date.split(" ");
        int year = Integer.parseInt(split[2]);
        int month = mapper.get(split[1]);
        int day = Integer.parseInt(split[0].substring(0, split[0].length() -2));
        return String.format("%d-%02d-%02d", year, month, day);
    }
}