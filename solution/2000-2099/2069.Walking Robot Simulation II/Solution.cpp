class Robot {
public:
    int mx, my, p, cur;
    bool moved;

    Robot(int width, int height) {
        mx = width - 1;
        my = height - 1;
        p = 2 * mx + 2 * my;
        cur = 0;
        moved = false;
    }

    void step(int num) {
        moved = true;
        cur = (cur + num) % p;
    }

    vector<int> getPos() {
        int d = cur;
        int mx = this->mx, my = this->my;

        if (0 <= d && d <= mx) {
            return {d, 0};
        }
        if (mx < d && d <= mx + my) {
            return {mx, d - mx};
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return {mx - (d - (mx + my)), my};
        }
        return {0, my - (d - (2 * mx + my))};
    }

    string getDir() {
        int d = cur;
        int mx = this->mx, my = this->my;

        if (!moved) {
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
};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */
