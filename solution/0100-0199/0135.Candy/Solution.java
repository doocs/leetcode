class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        else if (ratings.length == 1) return 1;
        int base = 1 ,cur = base ,sum = cur ,smallNum = 0 ,lastBigCur = cur;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                smallNum = 0;
                cur += base;
                lastBigCur = cur;
                sum += cur;
            } else if (ratings[i - 1] == ratings[i]) {
                smallNum = 0;
                cur = base;
                lastBigCur = cur;
                sum += base;
            } else {
                if (cur == base) {
                    smallNum++;
                    sum = sum + cur + smallNum;
                    if (lastBigCur - 1 == smallNum) {
                        lastBigCur += base;
                        sum += base;
                    }
                } else {
                    cur = base;
                    sum += cur;
                }
            }
        }
        return sum;
    }
}