class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int s = 0;
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            pq.offer(duration);
            s += duration;
            if (s > lastDay) {
                s -= pq.poll();
            }
        }
        return pq.size();
    }
}