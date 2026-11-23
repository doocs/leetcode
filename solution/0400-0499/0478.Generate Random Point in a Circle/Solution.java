class Solution {
    double r, cx, cy;

    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        cx = x_center;
        cy = y_center;
    }

    public double[] randPoint() {
        double d = r * Math.sqrt(Math.random());
        double a = 2 * Math.PI * Math.random();
        return new double[] {cx + d * Math.cos(a), cy + d * Math.sin(a)};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
