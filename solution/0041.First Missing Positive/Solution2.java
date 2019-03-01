public class Solution2 {

   /**
     * 从misNum=1开始假设不存在，循环一遍数组，如果存在则misNum++即可
     * 
     * @param arrs
     * @return
     */
    private static int findMissFirstNum(int [] arrs){
        int misNum = 1;
        for(int i:arrs){
            if(i==misNum){
                misNum++;
            }
        }
        return misNum;
    }

}
