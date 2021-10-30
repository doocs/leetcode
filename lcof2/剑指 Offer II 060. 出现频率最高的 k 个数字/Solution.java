class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Queue<Map.Entry<Integer, Long>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Long> entry : frequency.entrySet()) {
            long count = entry.getValue();
            if (queue.size() == k) {
                if (count > queue.peek().getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
            }
        }

        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
