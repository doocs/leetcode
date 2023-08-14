class carState
{
    public int distance;
    public int speed;
    public double time;
    
    public carState(int distance, int speed, double time)
    {
        this.distance=distance;
        this.speed=speed;
        this.time=time;
    }
}

class Solution {
    
    public int carFleet(int target, int[] position, int[] speed) {
        int l=position.length;
        carState cars[]=new carState[l];
        int i;
        for(i=0;i<l;i++)
        {
            cars[i]=new carState(target-position[i],speed[i],((double)(target-position[i]))/(double)(speed[i]));
        }
        Arrays.sort(cars, new Comparator<carState>() {
            public int compare(carState state1, carState state2) {
                return state1.distance-state2.distance;
            }
        });
        
        int ans=0;
        double currTime=0.0;
        for(i=0;i<cars.length;i++)
        {
            if(cars[i].time>currTime)
            {
                ans++;
                currTime=cars[i].time;
            }
        }
        return ans;
    }
}