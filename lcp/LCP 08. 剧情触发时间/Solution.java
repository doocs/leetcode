public int[] getTriggerTime(int[][] inc, int[][] requ) {
        int[] C = new int[inc.length+1];
        int[] R = new int[inc.length+1];
        int[] H = new int[inc.length+1];
        for(int i=0;i<inc.length;i++){
            C[i+1]=C[i]+inc[i][0];
            R[i+1]=R[i]+inc[i][1];
            H[i+1]=H[i]+inc[i][2];
        }
        int[] result = new int[requ.length];
        for(int i=0;i<requ.length;i++){
            result[i]=-1;
            int cIndex = Arrays.binarySearch(C,requ[i][0]);
            if(Math.abs(cIndex)>C.length){
                continue;
            }
            //用于得到值中最小的那个下标
            if(cIndex>0){
                while(cIndex>0&&C[cIndex-1]==C[cIndex]){
                    cIndex--;
                }
            }
            if(cIndex<0){
                cIndex = Math.abs(cIndex+1);
            }
            int rIndex = Arrays.binarySearch(R,cIndex,R.length,requ[i][1]);
            if(Math.abs(rIndex)>R.length){
                continue;
            }
            //得到下标最小，且大于等于cIndex的那个元素的下标
            if(rIndex>0){
                while(rIndex>cIndex&&R[rIndex-1]==R[rIndex]){
                    rIndex--;
                }
            }
            if(rIndex<0){
                rIndex = Math.abs(rIndex+1);
            }
            int hIndex = Arrays.binarySearch(H,rIndex,H.length,requ[i][2]);
            if(Math.abs(hIndex)>H.length){
                continue;
            }
            //得到下标最小，且大于等于rIndex的那个元素的下标
            if(hIndex>0){
                while(hIndex>rIndex&&H[hIndex-1]==H[hIndex]){
                    hIndex--;
                }
            }
            if(hIndex<0){
                hIndex = Math.abs(hIndex+1);
            }
            result[i] = hIndex;
        }
        return result;
    }
