class Solution {
    public boolean isBoomerang(int[][] points) {
        double temp1;
        double temp2;
        double temp3;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });

        if (points[0][0] == points[1][0]) {
            if (points[0][1] == points[1][1])
                return false;
            temp1 = 1;
        } else {
            temp1 = (points[0][1] - points[1][1]) * 1.0 / (points[0][0] - points[1][0]);
        }

        if (points[1][0] == points[2][0]) {
            if (points[1][1] == points[2][1])
                return false;
            temp2 = 1;
        } else {
            temp2 = (points[1][1] - points[2][1]) * 1.0 / (points[1][0] - points[2][0]);
        }

        if (points[0][0] == points[2][0]) {
            if (points[0][1] == points[2][1])
                return false;
            temp3 = 1;
        } else {
            temp3 = (points[0][1] - points[2][1]) * 1.0 / (points[0][0] - points[2][0]);
        }

        if (temp1 == temp2 && temp1 == temp3 && temp2 == temp3) {
            return false;
        } else {
            return true;
        }
    }
}
