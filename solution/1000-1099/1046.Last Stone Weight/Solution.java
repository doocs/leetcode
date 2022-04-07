class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);

        for(int i=0;i<stones.length;i++){
            pq.add(stones[i]);
        }
        
        while (pq.size()>1){
            int x = pq.peek();
            pq.poll();
            int y = pq.peek();
            pq.poll();
            
            if(x != y)
                pq.add(x-y);
        }
        
        return pq.isEmpty()?0:pq.peek();
    }
}
