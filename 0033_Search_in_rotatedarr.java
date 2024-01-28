class Solution {
    public static int search(int[] ar, int target) {
        int a=BinarySearch(ar,target,0,Pivot(ar));
        int b=BinarySearch(ar,target,Pivot(ar)+1,ar.length-1);
        if(a==-1&&b==-1)
            return -1;
        if(a==-1&&b!=-1)
            return b;
        else
            return a;
    }
    static  int Pivot(int[] ar) {
        int i;
        for (i = 0; i < ar.length - 1; i++) {
            if (ar[i] > ar[i + 1])
                break;
        }
        return i;
    }
    static int BinarySearch(int[] ar,int target,int low,int high)
    {
        if(high>=low) {
            int mid = low + (high - low) / 2;
            if (ar[mid] == target)
                return mid;
            if (ar[mid] > target)
                return BinarySearch(ar, target,low, mid - 1);
            else
                return BinarySearch(ar, target, mid + 1, high);
        }
        return -1;

    }
}
