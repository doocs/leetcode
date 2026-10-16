class BusBooking {
private:
    int tot;
    int min_tot;
    vector<array<int, 4>> cost_data;

public:
    BusBooking(int n) {
        tot = 0;
        min_tot = 0;
        cost_data = vector<array<int, 4>>(n, {0, 0, 0, 0});
    }

    void toggle(string seat) {
        int i = 0;
        for (int k = 1; k < seat.size(); k++) {
            i = i * 10 + (seat[k] - '0');
        }
        i -= 1;
        int j = seat[0] - 'A';

        if (cost_data[i][j] > 0) {
            min_tot -= 1;
            tot -= cost_data[i][j];
            cost_data[i][j] = 0;
            return;
        }

        int cost = 1;
        if ((j == 0 && cost_data[i][1] > 0) || (j == 3 && cost_data[i][2] > 0)) {
            cost = 3;
        }
        min_tot += 1;
        tot += cost;
        cost_data[i][j] = cost;
    }

    int getTotalTime() {
        return tot;
    }

    int getMinTime() {
        return min_tot;
    }
};

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking* obj = new BusBooking(n);
 * obj->toggle(seat);
 * int param_2 = obj->getTotalTime();
 * int param_3 = obj->getMinTime();
 */