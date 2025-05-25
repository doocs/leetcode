
class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
      
      int front=0;
      int back=0;

      int n=distance.size();
      for(int i=start;i!=destination;i=(i+1)%n)
      {
          front+=distance[i];
      }

      for(int i=destination;i!=start;i=(i+1)%n)
      {
        //i=i%n;
        back+=distance[i];
      }


      return min(front,back);
        
    }
};
        
    