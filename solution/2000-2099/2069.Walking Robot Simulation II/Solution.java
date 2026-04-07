class Robot {

    private int mx, my, p, cur;
    private boolean moved;

    public Robot(int width, int height) {
        this.mx = width - 1;
        this.my = height - 1;
        this.p = 2 * this.mx + 2 * this.my;
        this.cur = 0;
        this.moved = false;
    }

    public void step(int num) {
        this.moved = true;
        this.cur = (this.cur + num) % this.p;
    }

    public int[] getPos() {
        int d = this.cur;
        int mx = this.mx, my = this.my;

        if (0 <= d && d <= mx) {
            return new int[] {d, 0};
        }
        if (mx < d && d <= mx + my) {
            return new int[] {mx, d - mx};
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return new int[] {mx - (d - (mx + my)), my};
        }
        return new int[] {0, my - (d - (2 * mx + my))};
    }

    public String getDir() {
        int d = this.cur;
        int mx = this.mx, my = this.my;

        if (!this.moved) {
            return "East";
        }
        if (1 <= d && d <= mx) {
            return "East";
        } else if (mx < d && d <= mx + my) {
            return "North";
        } else if (mx + my < d && d <= 2 * mx + my) {
            return "West";
        }
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
